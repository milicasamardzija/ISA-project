package com.example.demo.dto.users;
import com.example.demo.model.entities.Address;

public class UserRequest {

    private int id;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
    private String telephone;
    private String reasonForRegistration;
    private Address address;
    public Address getAddress(){ return address;}


    public void setAddress(Address address) { this.address= address; }


    public String getPassword() {
        return password;
    }

    public void setReasonForRegistration(String reasonForRegistration) {
        this.reasonForRegistration = reasonForRegistration;
    }

    public String getReasonForRegistration() {
        return reasonForRegistration;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserRequest(String name,String surname, String email, String password, Address address, String role, String reason, String telephone){
        this.firstname= name;
        this.lastname=surname;
        this.email=email;
        this.password = password;
        this.address=address;
        this.role=role;
        this.reasonForRegistration = reason;
        this.telephone = telephone;
    }

    public UserRequest() {}

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
