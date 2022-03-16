package com.example.demo.dto.entities;

import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.entities.Image;
import com.example.demo.model.entities.Address;

import java.util.Set;

public class EntityDTO {

    private Integer id;

    private String name;

    private Address address;

    private String promoDescription;

    private Set<Image> image;

    private String rules;

    private int price;

    private double grade;

    public EntityDTO(EntityClass entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.promoDescription = entity.getPromoDescription();
        this.image = entity.getImage();
        this.rules = entity.getRules();
        this.price = entity.getPrice();
        this.grade = entity.getGrade();
    }

    public EntityDTO() {}

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

}
