package com.example.demo.dto.users;

import com.example.demo.model.users.DeleteUserRequest;
import com.example.demo.model.users.User;


public class DeleteUserRequestDTO {
    private int id;
    private String explanation;
    private Boolean rejected;
    private Boolean accepted;
    private User user;

    public DeleteUserRequestDTO(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getExplanation() {
        return explanation;
    }
    public Boolean getRejected() {
        return rejected;
    }

    public void setRejected(Boolean rejected) {
        this.rejected = rejected;
    }
    public Boolean getAccepted() {
        return accepted;
    }
    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
    public DeleteUserRequestDTO(String explanation, User user) {
        this.explanation = explanation;
        this.user = user;
    }
    public DeleteUserRequestDTO(String explanation, User user, int id) {
        this.explanation = explanation;
        this.user= user;
        this.id = id;
    }
    public DeleteUserRequestDTO(String explanation, int id) {
        this.explanation = explanation;
        this.id=id;
        //this.user = user;
        //this.reviewedRequest = reviewedRequest;
    }
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public DeleteUserRequestDTO(DeleteUserRequest request){
        this.id = request.getId();
        this.explanation = request.getExplanation();
        this.user= request.getUser();
        this.accepted = request.getAccepted();
        this.rejected = request.getRejected();
    }
}
