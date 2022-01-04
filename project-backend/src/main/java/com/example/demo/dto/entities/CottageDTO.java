package com.example.demo.dto.entities;

import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.Address;
import com.example.demo.model.users.CottageOwner;

import java.util.List;

public class CottageDTO {

        private int roomsNumber;
        private int bedsByRoom;
        private int id;
        private String name;
        private Address address;
        private String promoDescription; //promotivni opis
        private String rules;
        private double grade;

    public CottageDTO() {

    }

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

    private int price; //je l ovo dovoljno za cenovnik
    //slobodni termini sa akcijama za rezervaciju OVO NEMAMO

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

    public static class CottageOwnerDTO {

        private int id;
        private String name;
        private String surname;
        private String email;
        private String password;
        private String telephone;
        private Address address;
        private List<Cottage> cottageList;

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

        public  CottageOwnerDTO(){}

        public CottageOwnerDTO(CottageOwner owner) {
            this.id = owner.getId();
            this.name = owner.getName();
            this.surname = owner.getSurname();
            this.email = owner.getEmail();
            this.password = owner.getPassword();
            this.telephone =  owner.getTelephone();
            this.address =  owner.getAddress();
            this.cottageList = owner.getCottageList();
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
}
