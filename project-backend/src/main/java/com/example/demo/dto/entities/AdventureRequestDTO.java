package com.example.demo.dto.entities;

public class AdventureRequestDTO {
    private String nameOfAdventure;
    private int maxNumberOfPeople;
    private String instructorBiografy;
    private String promoDescription;
    private String fishingEquipment;
    private String cancelationType;

    public String getNameOfAdventure() {
        return nameOfAdventure;
    }

    public void setNameOfAdventure(String nameOfAdventure) {
        this.nameOfAdventure = nameOfAdventure;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(int maxNumberOfPeople) {
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    public String getInstructorBiografy() {
        return instructorBiografy;
    }

    public void setInstructorBiografy(String instructorBiografy) {
        this.instructorBiografy = instructorBiografy;
    }

    public String getPromoDescription() {
        return promoDescription;
    }

    public void setPromoDescription(String promoDescription) {
        this.promoDescription = promoDescription;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }

    public String getCancelationType() {
        return cancelationType;
    }

    public void setCancelationType(String cancelationType) {
        this.cancelationType = cancelationType;
    }

    public AdventureRequestDTO() {
    }

    public AdventureRequestDTO(String nameOfAdventure, int maxNumberOfPeople, String instructorBiografy, String promoDescription, String fishingEquipment, String cancelationType) {
        this.nameOfAdventure = nameOfAdventure;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.instructorBiografy = instructorBiografy;
        this.promoDescription = promoDescription;
        this.fishingEquipment = fishingEquipment;
        this.cancelationType = cancelationType;
    }
}
