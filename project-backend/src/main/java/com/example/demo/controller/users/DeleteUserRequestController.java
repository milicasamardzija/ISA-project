package com.example.demo.controller.users;

import com.example.demo.dto.users.DeleteUserRequestDTO;
import com.example.demo.model.users.DeleteUserRequest;
import com.example.demo.model.users.User;
import com.example.demo.service.users.DeleteUserRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/userDeleteReq")
public class DeleteUserRequestController {

    private DeleteUserRequestService deleteUserRequestService;

    public DeleteUserRequestController(DeleteUserRequestService deleteUserRequestService){
        this.deleteUserRequestService = deleteUserRequestService;
    }

    @PostMapping
    public ResponseEntity<DeleteUserRequest> saveRequest(@RequestBody DeleteUserRequestDTO request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<DeleteUserRequest>(this.deleteUserRequestService.saveRequest(new DeleteUserRequest(request.getExplanation(), user)), HttpStatus.OK);
    }
}
