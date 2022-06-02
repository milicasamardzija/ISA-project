package com.example.demo.dto.business;

public class ActionRequestDTO {
    private String dateStart;
    private String dateEnd;
    private int price;
    private String name;
    private String validFrom;
    private String validTo;

    public ActionRequestDTO() {
    }

    public ActionRequestDTO(String dateStart, String dateEnd, int price, String name, String validFrom, String validTo) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.price = price;
        this.name = name;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }
}
