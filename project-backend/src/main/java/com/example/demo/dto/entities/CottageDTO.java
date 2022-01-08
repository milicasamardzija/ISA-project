package com.example.demo.dto.entities;

import com.example.demo.model.entities.Cottage;
import com.example.demo.model.entities.Image;
import com.example.demo.model.entities.Address;

import java.util.Set;

public class CottageDTO {

    private int roomsNumber;
    private int bedsByRoom;
    private int id;
    private String name;
    private Address address;
    private String promoDescription; //promotivni opis
    private String rules;
    private double grade;
    private Set<Image> images;

 private int price; //je l ovo dovoljno za cenovnik
    //slobodni termini sa akcijama za rezervaciju OVO NEMAMO
    

    public CottageDTO(Cottage cottage){
        this.roomsNumber = cottage.getRoomsNumber();
        this.bedsByRoom = cottage.getBedsByRoom();
        this.id = cottage.getId();
        this.name = cottage.getName();
        this.address = cottage.getAddress();
        this.promoDescription = cottage.getPromoDescription();
        this.rules = cottage.getRules();
        this.price = cottage.getPrice();
        this.grade = cottage.getGrade();
        this.images = cottage.getImage();
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPromoDescription() {
        return promoDescription;
    }

    public void setPromoDescription(String promoDescription) {
        this.promoDescription = promoDescription;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

   

    public double getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getRoomsNumber() {
            return roomsNumber;
        }

    public void setRoomsNumber(int roomsNumber) {
            this.roomsNumber = roomsNumber;
        }

    public int getBedsByRoom() {
            return bedsByRoom;
        }

    public void setBedsByRoom(int bedsByRoom) { this.bedsByRoom = bedsByRoom; }

    public CottageDTO() {

    }



}
