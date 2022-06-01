package com.example.demo.controller.users;

import com.example.demo.dto.entities.ChangePasswordDTO;
import com.example.demo.dto.users.UpdateUserDTO;
import com.example.demo.dto.users.UserDTO;
import com.example.demo.dto.users.UserTokenState;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.User;
import com.example.demo.service.email.EmailSenderService;
import com.example.demo.service.email.EmailService;
import com.example.demo.service.users.UserService;
import com.example.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.awt.datatransfer.SystemFlavorMap;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/user")
public class UserController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private TokenUtils tokenUtils;
    private EmailService emailService;
    @Autowired
    private EmailSenderService service;
    public UserController(UserService userService, AuthenticationManager authenticationManager, TokenUtils tokenUtils){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenUtils = tokenUtils;
    }

    @PreAuthorize("hasAnyRole('CLIENT','COTTAGE_OWNER', 'BOAT_OWNER', 'INSTRUCTOR','ADMIN','PREDEF_ADMIN')")
    @PostMapping(value="/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@RequestBody UpdateUserDTO updatedUser){
        userService.update(updatedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('CLIENT','COTTAGE_OWNER', 'BOAT_OWNER', 'INSTRUCTOR','ADMIN','PREDEF_ADMIN')")
    @GetMapping(value = "/userInfo")
    public  ResponseEntity<UserDTO> fetchProfileInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAll(){
        List<User> allUsers = userService.findAll();
        List<UserDTO> users = new ArrayList<>();
        for(User u : allUsers ) {
            UserDTO userDTO = new UserDTO(u);
            System.out.print(userDTO.getRole());
            users.add(userDTO);
        }
        return  new ResponseEntity<>(users, HttpStatus.OK);
    }
    @DeleteMapping(value = "/deleteUser/{deleteId}")
    public ResponseEntity<Void> deleteUser (@PathVariable int deleteId) {
        List<User> users = userService.findAll();
        for(User u : users) {
            if (u.getId()== deleteId) {
                userService.deleteUserById(u.getId());
                return new ResponseEntity<>(HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/getAllRequestUser")
    public ResponseEntity<List<UserDTO>> getAllRequestUser(){
        List<User> allUsers = userService.findAll();
        List<UserDTO> users = new ArrayList<>();
        for(User u : allUsers ) {
            if(u.getEnabled()!= true) {
            users.add(new UserDTO(u));}
            System.out.print(u.getName());
        }
        return  new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('CLIENT','COTTAGE_OWNER', 'BOAT_OWNER', 'INSTRUCTOR','ADMIN','PREDEF_ADMIN')")
   // @PutMapping(value="/changePassword/{password}")
    @PostMapping(value="/changePassword")
    public ResponseEntity<UserTokenState> changePassword(@RequestBody ChangePasswordDTO password){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        userService.updatePassword(user, password.getPassword());
        SecurityContextHolder.getContext().setAuthentication(null);

        Authentication authenticationNew = null;
        try {
            authenticationNew = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getEmail(), password.getPassword()));
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        SecurityContextHolder.getContext().setAuthentication(authenticationNew);
        User userAuth = (User) authenticationNew.getPrincipal();
        String jwt = tokenUtils.generateToken(userAuth.getEmail());
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn,userAuth.getRole().getName(), user.isEnabled(),user.isMust_change_password()));
    }

    @PostMapping(value = "/confirm/{id}")
    public ResponseEntity<Void> updateUser1(@PathVariable int id) throws InterruptedException, MessagingException {

        User u = this.userService.findById(id);
        if (u != null){

            this.userService.updateEnableState(u);
            service.sendEmailWithAttachment(u.getEmail(),
                    "Tvoj zahtev za registraciju je prihvacen ! ",
                    "Accepting registration");
            return new ResponseEntity<>(HttpStatus.OK);}
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(value = "/reject/{deleteID}/{reasonn}/{userEmail}")
    public ResponseEntity<Void> rejectRegistration(@PathVariable int deleteID,@PathVariable String reasonn,@PathVariable String userEmail) throws MessagingException {
        System.out.print(deleteID);
        System.out.print(reasonn);
        System.out.print(userEmail);
        User u = this.userService.findById(deleteID);
            if (u != null){
                userService.deleteUserById(deleteID);
                service.sendEmailWithAttachment(userEmail,
                        "Razlog odbijanja zahteva za registraciju:"+reasonn,
                        "Odbijanje registracije");
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
}
