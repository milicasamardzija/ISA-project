package com.example.demo.controller.business;

import com.example.demo.dto.business.ComplaintClientDTO;
import com.example.demo.model.business.Complaint;
import com.example.demo.model.users.User;
import com.example.demo.service.business.ComplaintService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/complaint")
public class ComplaintController {

    private ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService){
        this.complaintService = complaintService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComplaintClientDTO> saveComplaint(@RequestBody ComplaintClientDTO complaint){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        complaintService.saveComplaint(complaint,user);

        return new ResponseEntity<ComplaintClientDTO>(new ComplaintClientDTO(), HttpStatus.OK);
    }

}
