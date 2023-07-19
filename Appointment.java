package com.example.assignment;

public class Appointment {

    private int appointmentID;
    private String date;
    private String time;
    private String type;

    public Appointment(int appointmentID, String date, String time, String type) {

        this.appointmentID = appointmentID;
        this.date = date;
        this.time = time;
        this.type = type;

    }

    public int getAppointmentID() {
        return this.appointmentID;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public String getType() {
        return this.type;
    }

}
