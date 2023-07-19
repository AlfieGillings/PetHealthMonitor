package com.example.assignment;

public class PetLog {

    private int petLogID;
    private String date;
    private String weight;
    private String medication;

    public PetLog(int petLogID, String date, String weight, String medication) {

        this.petLogID = petLogID;
        this.date = date;
        this.weight = weight;
        this.medication = medication;

    }

    public int getPetLogID() {
        return this.petLogID;
    }

    public String getDate() {
        return this.date;
    }

    public String getWeight() {
        return this.weight;
    }

    public String getMedication() {
        return this.medication;
    }

}
