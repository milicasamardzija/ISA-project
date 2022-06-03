package com.example.demo.dto.entities;

import com.example.demo.dto.enums.CancelationType;
import com.example.demo.model.entities.Address;
import com.example.demo.model.entities.Adventure;
import com.example.demo.model.entities.Image;

import java.util.Set;

public class AdventureHelpDTO {
    private String realName;
    private String name;
    private Integer id;

    private String nameOfAdventure;

    private Address address;

    private String promoDescription;

    private Set<Image> images;

    private String rules;

    private int price;

    private double grade;

    private String instructorBiografy;

    private int maxNumberOfPeople;

    private String fishingEquipment;

    private CancelationType cancelationType;

    public AdventureHelpDTO(){

    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public AdventureHelpDTO(Adventure adventure){
        this.id = adventure.getId();
        this.name = adventure.getName();
        this.address = adventure.getAddress();
        this.promoDescription = adventure.getPromoDescription();
        this.images = adventure.getImage();
        this.rules = adventure.getRules();
        this.price = adventure.getPrice();
        this.grade = adventure.getGrade();
        this.instructorBiografy = adventure.getInstructorBiografy();
        this.maxNumberOfPeople = adventure.getMaxNumberOfPeople();
        this.fishingEquipment = adventure.getFishingEquipment();
        this.cancelationType = adventure.getCancelationType();
        this.nameOfAdventure =adventure.getNameOfAdventure();
    }

    public Integer getId() {
        return id;
    }

    public String getNameOfAdventure() {
        return nameOfAdventure;
    }

    public void setNameOfAdventure(String nameOfAdventure) {
        this.nameOfAdventure = nameOfAdventure;
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

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getInstructorBiografy() {
        return instructorBiografy;
    }

    public void setInstructorBiografy(String instructorBiografy) {
        this.instructorBiografy = instructorBiografy;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(int maxNumberOfPeople) {
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }

    public CancelationType getCancelationType() {
        return cancelationType;
    }

    public void setCancelationType(CancelationType cancelationType) {
        this.cancelationType = cancelationType;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> image) {
        this.images = image;
    }
}
