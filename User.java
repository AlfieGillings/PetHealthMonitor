package com.example.assignment;

import java.util.ArrayList;

public class User {

    private int userID;
    private String username;
    private String password;

    private ArrayList<Pet> pets = new ArrayList<>();

    public User() {

        this.userID = 0;
        this.username = "null";
        this.password = "null";

    }

    public void clearPets() {

        this.pets = new ArrayList<>();

    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

}
