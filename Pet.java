package com.example.assignment;

import java.util.ArrayList;

public class Pet {

    private int petID;
    private String name;
    private String species;
    private String coatColour;
    private String dateOfBirth;
    private String notes;

    private ArrayList<PetLog> petInfo = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public Pet(int petID, String name, String species, String coatColour, String dateOfBirth, String notes) {

        this.petID = petID;
        this.name = name;
        this.species = species;
        this.coatColour = coatColour;
        this.dateOfBirth = dateOfBirth;
        this.notes = notes;

    }

    public void clearLogs() {

        this.petInfo = new ArrayList<>();

    }

    public void clearAppointments() {

        this.appointments = new ArrayList<>();

    }

    public void addPetLog(PetLog petLog) {

        petInfo.add(petLog);

    }

    public ArrayList<PetLog> getLogs() {

        return this.petInfo;

    }

    public void addAppointment(Appointment appointment) {

        appointments.add(appointment);

    }

    public ArrayList<Appointment> getAppointments() {

        return this.appointments;

    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public int getPetID() {
        return this.petID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSpecies() {
        return this.species;
    }

    public void setCoatColour(String coatColour) {
        this.coatColour = coatColour;
    }

    public String getCoatColour() {
        return this.coatColour;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return this.notes;
    }

}
