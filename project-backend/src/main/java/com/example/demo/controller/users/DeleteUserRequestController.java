package com.example.demo.controller.users;

import com.example.demo.dto.users.DeleteUserRequestDTO;
import com.example.demo.model.users.DeleteUserRequest;
import com.example.demo.model.users.User;
import com.example.demo.service.email.EmailSenderService;
import com.example.demo.service.users.UserService;
import com.example.demo.service.users.DeleteUserRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/userDeleteReq")
public class DeleteUserRequestController {

    private DeleteUserRequestService deleteUserRequestService;
    private  UserService userService;
    private EmailSenderService service;

    public DeleteUserRequestController(DeleteUserRequestService deleteUserRequestService,UserService userService){
        this.deleteUserRequestService = deleteUserRequestService;
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteUserRequest> saveRequest(@RequestBody DeleteUserRequestDTO request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<DeleteUserRequest>(this.deleteUserRequestService.saveRequest(new DeleteUserRequest(request.getExplanation(), user)), HttpStatus.OK);
    }

    @GetMapping(value = "/getRequests")
    public ResponseEntity<List<DeleteUserRequestDTO>> getRequests(){
        List<DeleteUserRequest> allRequests = deleteUserRequestService.findAll();
        List<DeleteUserRequestDTO> requests = new ArrayList<>();
        for(DeleteUserRequest u : allRequests ) {
            int id = u.getUser().getId();
            User user = this.userService.findById(id);
            DeleteUserRequestDTO du = new DeleteUserRequestDTO(u);
            du.setUser(user);
            if (du.getAccepted()==null && du.getRejected()==null) {
                requests.add(du);
            }
        }
        return  new ResponseEntity<>(requests, HttpStatus.OK);
    }
    @PostMapping(value = "/confirm/{selectID}/{reason}")
    public ResponseEntity<Void> acceptRequest(@PathVariable int selectID,@PathVariable String reason) throws MessagingException {
        System.out.print("USLA !!! ");
        DeleteUserRequest du = this.deleteUserRequestService.findById(selectID);
        System.out.print("LORD SHOW ME WAY" + du.getId());
        if (du != null){
            this.deleteUserRequestService.acceptRequest(du);
            System.out.print("Because of you");
            System.out.print(du.getAccepted());
            System.out.print(du.getRejected());
            System.out.print(du.getUser().getEmail());
            String userEmail = du.getUser().getEmail();
            service.sendEmailWithAttachment(userEmail,
                    reason,
                    "Accepting request");
            return new ResponseEntity<>(HttpStatus.OK);}

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/reject/{id}/{reason}")
    public ResponseEntity<Void> rejectRegistration(@PathVariable int id, @PathVariable String reason) throws MessagingException {
        DeleteUserRequest du = this.deleteUserRequestService.findById(id);
        if (du != null){
            this.deleteUserRequestService.rejectRequest(du);
            String userEmail = du.getUser().getEmail();
            service.sendEmailWithAttachment(userEmail,
                    reason,
                    "Accepting request");
            return new ResponseEntity<>(HttpStatus.OK);}
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
