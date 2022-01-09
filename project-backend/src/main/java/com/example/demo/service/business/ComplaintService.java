package com.example.demo.service.business;

import com.example.demo.dto.business.ComplaintClientDTO;
import com.example.demo.model.business.Complaint;
import com.example.demo.model.users.User;
import com.example.demo.repository.business.ComplaintRepository;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

    private ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository){
        this.complaintRepository = complaintRepository;
    }

    public Complaint saveComplaint(ComplaintClientDTO complaint, User userWhoSendsComplaint) {
        Complaint newComplaint = new Complaint();
        newComplaint.setUser(complaint.getUser());
        newComplaint.setEntity(complaint.getEntity());
        newComplaint.setUserWhoSendsComplaint(userWhoSendsComplaint);
        newComplaint.setContentUser(complaint.getContentUser());
        newComplaint.setContentEntity(complaint.getContentEntity());
        return this.complaintRepository.save(newComplaint);
    }
}
