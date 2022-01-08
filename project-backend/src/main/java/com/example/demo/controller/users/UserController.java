package com.example.demo.controller.users;

import com.example.demo.dto.users.UpdateUserDTO;
import com.example.demo.dto.users.UserDTO;
import com.example.demo.dto.users.UserTokenState;
import com.example.demo.model.users.User;
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

@Controller
@RequestMapping(value = "api/user")
public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private TokenUtils tokenUtils;

    public UserController(UserService userService, AuthenticationManager authenticationManager, TokenUtils tokenUtils){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenUtils = tokenUtils;
    }

    @PreAuthorize("hasAnyRole('CLIENT','COTTAGE_OWNER', 'SHIP_OWNER', 'INSTRUCTOR','ADMIN')")
    @PostMapping(value="/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@RequestBody UpdateUserDTO updatedUser){
        userService.update(updatedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('CLIENT','COTTAGE_OWNER', 'SHIP_OWNER', 'INSTRUCTOR','ADMIN')")
    @GetMapping(value = "/userInfo")
    public  ResponseEntity<UserDTO> fetchProfileInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('CLIENT','COTTAGE_OWNER', 'SHIP_OWNER', 'INSTRUCTOR','ADMIN')")
    @PutMapping(value="/changePassword/{password}")
    public ResponseEntity<UserTokenState> changePassword(@PathVariable String password){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        userService.updatePassword(user, password);

        Authentication authenticationNew = null;
        try {
            authenticationNew = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getEmail(), password));
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        SecurityContextHolder.getContext().setAuthentication(authenticationNew);
        User userAuth = (User) authenticationNew.getPrincipal();
        String jwt = tokenUtils.generateToken(userAuth.getEmail());
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn,userAuth.getRole().getName()));
    }

}
