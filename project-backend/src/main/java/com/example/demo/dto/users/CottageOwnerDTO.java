package com.example.demo.dto.users;

import com.example.demo.model.entities.Cottage;
import com.example.demo.model.entities.Address;
import com.example.demo.model.users.CottageOwner;

import java.util.List;

public class CottageOwnerDTO {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String telephone;
    private Address address;
    private List<Cottage> cottageList;
    private double grade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Cottage> getCottageList() {
        return cottageList;
    }

    public void setCottageList(List<Cottage> cottageList) {
        this.cottageList = cottageList;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public  CottageOwnerDTO(){}

    public CottageOwnerDTO(CottageOwner owner) {
        this.id = owner.getId();
        this.name = owner.getName();
        this.surname = owner.getSurname();
        this.email = owner.getEmail();
        this.password = owner.getPassword();
        this.telephone =  owner.getTelephone();
        this.address =  owner.getAddress();
       //this.cottageList = owner.getCottageList();    //ZAKOMENTARISANO ZBOG 500 ER I LAZY LOADINGA
    }

    public CottageOwnerDTO(int id, String name, String surname, String email, String password, String telephone, Address address, List<Cottage> cottageList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.address = address;
        this.cottageList = cottageList;
    }
}
