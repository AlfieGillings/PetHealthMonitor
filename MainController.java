package com.example.assignment;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MainController {

    private static User user = new User();

    public static Connect database = new Connect("pethealthmonitordb","postgres", "admin");

    public static void connectDatabase() {

        if (!database.getConnected()) {
            System.out.println("[DEBUG] Attempting to create database...");
            database.createDatabase("pethealthmonitordb");
            database.getConnected();
        }

        database.sqlUpdate("CREATE TABLE IF NOT EXISTS users (user_id serial PRIMARY KEY, username VARCHAR(50), password VARCHAR(50));");
        database.sqlUpdate("CREATE TABLE IF NOT EXISTS pet (pet_id serial PRIMARY KEY, user_id int, name VARCHAR(50), species VARCHAR(50), coatcolour VARCHAR(50), dateofbirth VARCHAR(50), notes VARCHAR (200), FOREIGN KEY (user_id) REFERENCES users (user_id));");
        database.sqlUpdate("CREATE TABLE IF NOT EXISTS appointment (appointment_id serial PRIMARY KEY, pet_id int, type VARCHAR(50), date VARCHAR(50), time VARCHAR(50), FOREIGN KEY (pet_id) REFERENCES pet (pet_id));");
        database.sqlUpdate("CREATE TABLE IF NOT EXISTS petlog (petlog_id serial PRIMARY KEY, pet_id int, weight VARCHAR(50), medication VARCHAR(50), date VARCHAR(50), FOREIGN KEY (pet_id) REFERENCES pet (pet_id));");

    }

    public static void disconnectDatabase() {

        database.disconnect();

    }

    public static void openWindow(String fileName, String title, int width, int height) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fileName));
        Parent loginMenu = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(loginMenu, width, height));
        stage.show();

    }

    public static void closeWindow(Button closeButton) {

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }

    public static boolean signUp(String username, String password) {

        boolean matchingUsernames = true;
        ResultSet usernames = database.sqlExecute("SELECT username FROM users WHERE username = '" + username + "';");

        try {
            matchingUsernames = usernames.next();
        } catch (Exception e) {
            System.out.println("[ERROR] Unable to read matching usernames result set.");
        }

        if (!matchingUsernames) {
            database.sqlUpdate("INSERT INTO users(username, password) VALUES ('" + username + "', '" + password + "');");
            return true;
        }
        else {
            return false;
        }

    }

    public static boolean logIn(String username, String password) {

        boolean matchingAccounts = false;
        ResultSet accounts = database.sqlExecute("SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "';");

        try {
            matchingAccounts = accounts.next();
        } catch (Exception e) {
            System.out.println("[ERROR] Unable to read matching accounts result set.");
        }

        if (matchingAccounts) {
            try {
                user.setUserID(accounts.getInt("user_id"));
                user.setUsername(accounts.getString("username"));
                user.setPassword(accounts.getString("password"));
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to read account result set.");
            }
            loadPets();
            loadLogs();
            loadAppointments();
            return true;
        }
        else {
            return false;
        }

    }

    private static void loadPets() {

        user.clearPets();
        ResultSet pets = database.sqlExecute("SELECT * FROM pet WHERE user_id = " + user.getUserID() + ";");

        try {
            while (pets.next()) {
                user.addPet(new Pet(pets.getInt("pet_id"), pets.getString("name"), pets.getString("species"), pets.getString("coatcolour"), pets.getString("dateofbirth"), pets.getString("notes")));
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Unable to read pets result set.");
        }

    }

    public static void addPet(Pet pet) {

        database.sqlUpdate("INSERT INTO pet(user_id, name, species, coatcolour, dateofbirth, notes) VALUES ('" + user.getUserID() + "', '" + pet.getName() + "', '" + pet.getSpecies() + "', '" + pet.getCoatColour() + "', '" + pet.getDateOfBirth() + "', '" + pet.getNotes() + "');");
        loadPets();

    }

    public static void editPet(Pet pet) {

        database.sqlUpdate("UPDATE pet SET name = '" + pet.getName() + "', species = '" + pet.getSpecies() + "', coatcolour = '" + pet.getCoatColour() + "', dateofbirth = '" + pet.getDateOfBirth() + "', notes = '" + pet.getNotes() + "' WHERE pet_id = " + pet.getPetID() + ";");
        loadPets();

    }

    public static ArrayList<Pet> getPets() {

        return user.getPets();

    }

    public static void deletePet(Pet pet) {

        database.sqlUpdate("DELETE FROM petlog where pet_id = " + pet.getPetID() + ";");
        database.sqlUpdate("DELETE FROM pet WHERE pet_id = " + pet.getPetID() + ";");
        loadPets();

    }

    private static void loadLogs() {

        ArrayList<Pet> pets = user.getPets();

        for (int i = 0; i < pets.size(); i++) {

            Pet pet = pets.get(i);
            pet.clearLogs();
            ResultSet logs = database.sqlExecute("SELECT * FROM petlog WHERE pet_id = " + pet.getPetID() + ";");

            try {
                while (logs.next()) {
                    pet.addPetLog(new PetLog(logs.getInt("petlog_id"), logs.getString("date"), logs.getString("weight"), logs.getString("medication")));
                }
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to read logs result set.");
            }

        }

    }

    public static void addPetLog(Pet pet, PetLog petLog) {

        database.sqlUpdate("INSERT INTO petlog(pet_id, date, weight, medication) VALUES ('" + pet.getPetID() + "', '" + petLog.getDate() + "', '" + petLog.getWeight() + "', '" + petLog.getMedication() + "');");
        loadLogs();

    }

    public static ArrayList<PetLog> getLogs(Pet pet) {

        return pet.getLogs();

    }

    public static void deleteLog(PetLog log) {

        database.sqlUpdate("DELETE FROM petlog WHERE petlog_id = " + log.getPetLogID() + ";");
        loadLogs();

    }

    private static void loadAppointments() {

        ArrayList<Pet> pets = user.getPets();

        for (int i = 0; i < pets.size(); i++) {

            Pet pet = pets.get(i);
            pet.clearAppointments();
            ResultSet appointments = database.sqlExecute("SELECT * FROM appointment WHERE pet_id = " + pet.getPetID() + ";");

            try {
                while (appointments.next()) {
                    pet.addAppointment(new Appointment(appointments.getInt("appointment_id"), appointments.getString("date"), appointments.getString("time"), appointments.getString("type")));
                }
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to read appointments result set.");
            }

        }

    }

    public static void addAppointment(Pet pet, Appointment appointment) {

        database.sqlUpdate("INSERT INTO appointment(pet_id, date, time, type) VALUES ('" + pet.getPetID() + "', '" + appointment.getDate() + "', '" + appointment.getTime() + "', '" + appointment.getType() + "');");
        loadAppointments();

    }

    public static ArrayList<Appointment> getAppointments(Pet pet) {

        return pet.getAppointments();

    }

    public static void deleteAppointment(Appointment appointment) {

        database.sqlUpdate("DELETE FROM appointment WHERE appointment_id = " + appointment.getAppointmentID() + ";");
        loadAppointments();

    }

    public static void logOut() {

        user = new User();

    }

    public static User getUser() {

        return user;

    }

}
