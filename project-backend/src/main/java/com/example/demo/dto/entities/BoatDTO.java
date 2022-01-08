package com.example.demo.dto.entities;

import com.example.demo.enums.BoatType;
import com.example.demo.enums.CancelationType;
import com.example.demo.model.entities.Boat;
import com.example.demo.model.entities.Image;
import com.example.demo.model.entities.Address;

import java.util.Set;

public class BoatDTO {

    private Integer id;

    private String name;

    private Address address;

    private String promoDescription;

    private Set<Image> image;

    private String rules;

    private int price;

    private double grade;

    private BoatType boatType;

    private int lenght;

    private int motorNumber;

    private int power;

    private int maxSpeed;

    private String navigationEquipment;

    private String fishingEquipment;

    private int quantity;

    private CancelationType cancelationType;

    public BoatDTO(Boat boat){
        this.id = boat.getId();
        this.name = boat.getName();
        this.address = boat.getAddress();
        this.promoDescription = boat.getPromoDescription();
        this.image = boat.getImage();
        this.rules = boat.getRules();
        this.price = boat.getPrice();
        this.grade = boat.getGrade();
        this.boatType =  boat.getBoatType();
        this.lenght =  boat.getLenght();
        this.motorNumber =  boat.getMotorNumber();
        this.power =  boat.getPower();
        this.maxSpeed =  boat.getMaxSpeed();
        this.navigationEquipment =  boat.getNavigationEquipment();
        this.fishingEquipment =  boat.getFishingEquipment();
        this.quantity =  boat.getQuantity();
        this.cancelationType =  boat.getCancelationType();
        this.image = boat.getImage();
    }

    public Integer getId() {
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

    public Set<Image> getImage() {
        return image;
    }

    public void setImage(Set<Image> image) {
        this.image = image;
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

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public BoatType getBoatType() {
        return boatType;
    }

    public void setBoatType(BoatType boatType) {
        this.boatType = boatType;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int getMotorNumber() {
        return motorNumber;
    }

    public void setMotorNumber(int motorNumber) {
        this.motorNumber = motorNumber;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getNavigationEquipment() {
        return navigationEquipment;
    }

    public void setNavigationEquipment(String navigationEquipment) {
        this.navigationEquipment = navigationEquipment;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CancelationType getCancelationType() {
        return cancelationType;
    }

    public void setCancelationType(CancelationType cancelationType) {
        this.cancelationType = cancelationType;
    }
}
