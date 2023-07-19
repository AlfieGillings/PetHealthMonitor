package com.example.assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class MainMenuController {

    @FXML
    private TableView<Pet> mainTableView;
    @FXML
    private TableColumn<Pet, Integer> petIDColumn;
    @FXML
    private TableColumn<Pet, String> nameColumn;
    @FXML
    private TableColumn<Pet, String> speciesColumn;
    @FXML
    private TableColumn<Pet, String> coatColourColumn;
    @FXML
    private TableColumn<Pet, String> dateOfBirthColumn;
    @FXML
    private TableColumn<Pet, String> notesColumn;

    @FXML
    private TableView<PetLog> logsTableView;
    @FXML
    private TableColumn<PetLog, String> logDateColumn;
    @FXML
    private TableColumn<PetLog, String> weightColumn;
    @FXML
    private TableColumn<PetLog, String> medicationColumn;

    @FXML
    private TableView<Appointment> appointmentsTableView;
    @FXML
    private TableColumn<Appointment, String> appointmentDateColumn;
    @FXML
    private TableColumn<Appointment, String> timeColumn;
    @FXML
    private TableColumn<Appointment, String> typeColumn;

    @FXML
    private Button editPetButton;
    @FXML
    private Button logsButton;
    @FXML
    private Button appointmentsButton;
    @FXML
    private Button deletePetButton;
    @FXML
    private Button deleteLogButton;
    @FXML
    private Button deleteAppointmentButton;

    @FXML
    private Label loggedInAsLabel;
    @FXML
    private Button logOutButton;

    private static Pet selectedPet;
    private static PetLog selectedLog;
    private static Appointment selectedAppointment;

    @FXML
    void initialize() {

        selectedPet = null;
        loggedInAsLabel.setText("Logged in as: " + MainController.getUser().getUsername());

        loadPetsTable();

    }

    @FXML
    void addPet() throws IOException {

        MainController.openWindow("AddPetPopup.fxml", "Add Pet", 350, 450);
        MainController.closeWindow(logOutButton);

    }

    @FXML
    void editPet() throws IOException {

        MainController.openWindow("EditPetPopup.fxml", "Edit Pet", 350, 450);
        MainController.closeWindow(logOutButton);

    }

    @FXML
    void logs() throws IOException {

        MainController.openWindow("LogPopup.fxml", "Add Log", 350, 250);
        MainController.closeWindow(logOutButton);

    }

    @FXML
    void appointments() throws IOException {

        MainController.openWindow("AppointmentPopup.fxml", "Add Appointment", 350, 250);
        MainController.closeWindow(logOutButton);

    }

    @FXML
    void deletePet() {

        MainController.deletePet(selectedPet);
        loadPetsTable();
        loadLogsTable();
        editPetButton.setDisable(true);
        logsButton.setDisable(true);
        appointmentsButton.setDisable(true);
        deletePetButton.setDisable(true);

    }

    @FXML
    void deleteLog() {

        MainController.deleteLog(selectedLog);
        loadLogsTable();
        deleteLogButton.setDisable(true);

    }

    @FXML
    void deleteAppointment() {

        MainController.deleteAppointment(selectedAppointment);
        loadAppointmentsTable();
        deleteAppointmentButton.setDisable(true);

    }

    @FXML
    void selectPet() {

        selectedPet = mainTableView.getSelectionModel().getSelectedItem();
        if (selectedPet != null) {
            editPetButton.setDisable(false);
            logsButton.setDisable(false);
            appointmentsButton.setDisable(false);
            deletePetButton.setDisable(false);
            deleteLogButton.setDisable(true);
            deleteAppointmentButton.setDisable(true);

            loadLogsTable();
            loadAppointmentsTable();
        }

    }

    @FXML
    void selectLog() {

        selectedLog = logsTableView.getSelectionModel().getSelectedItem();
        if (selectedLog != null) {
            deleteLogButton.setDisable(false);
            deleteAppointmentButton.setDisable(true);
        }

    }

    @FXML
    void selectAppointment() {

        selectedAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            deleteAppointmentButton.setDisable(false);
            deleteLogButton.setDisable(true);
        }

    }

    @FXML
    void logOut() throws IOException {

        MainController.logOut();
        MainController.openWindow("LoginMenu.fxml", "Pet Health Monitor", 400, 250);
        MainController.closeWindow(logOutButton);

    }

    private void loadPetsTable() {

        ArrayList<Pet> pets = MainController.getPets();
        ObservableList<Pet> petsObservableList = FXCollections.observableArrayList();

        petsObservableList.addAll(pets);

        petIDColumn.setCellValueFactory(new PropertyValueFactory<>("petID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        speciesColumn.setCellValueFactory(new PropertyValueFactory<>("species"));
        coatColourColumn.setCellValueFactory(new PropertyValueFactory<>("coatColour"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        mainTableView.setItems(petsObservableList);

    }

    private void loadLogsTable() {

        ArrayList<PetLog> logs = MainController.getLogs(selectedPet);
        ObservableList<PetLog> logsObservableList = FXCollections.observableArrayList();

        logsObservableList.addAll(logs);

        logDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        medicationColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
        logsTableView.setItems(logsObservableList);

    }

    private void loadAppointmentsTable() {

        ArrayList<Appointment> appointments = MainController.getAppointments(selectedPet);
        ObservableList<Appointment> appointmentsObservableList = FXCollections.observableArrayList();

        appointmentsObservableList.addAll(appointments);

        appointmentDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentsTableView.setItems(appointmentsObservableList);

    }

    public static Pet getSelectedPet() {
        return selectedPet;
    }

}
