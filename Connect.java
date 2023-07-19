package com.example.assignment;

import java.sql.*;
import java.util.ArrayList;

public class Connect {

    private String url;
    private String name;
    private String username;
    private String password;
    private Connection db;

    public Connect(String dbName, String dbUsername, String dbPassword) {

        this.url = "jdbc:postgresql://localhost:5432/";
        this.name = dbName;
        this.username = dbUsername;
        this.password = dbPassword;

    }

    public boolean getConnected() {

        try {
            this.db = DriverManager.getConnection(this.url + this.name, this.username, this.password);
            System.out.println("[DEBUG] Successfully connected to the database. [" + this.name + "]");
            return true;
        } catch (Exception e) {
            System.out.println("[DEBUG] Unable to connect to the database. [" + this.name + "]");
            return false;
        }

    }

    public void disconnect() {

        try {
            this.db.close();
            System.out.println("[DEBUG] Successfully disconnected from the database. [" + this.name + "]");
        } catch (Exception e) {
            System.out.println("[DEBUG] Unable to disconnect from the database. [" + this.name + "]");
        }

    }

    public void createDatabase(String database) {

        try {
            Connection tempConnection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement sqlStatement = tempConnection.createStatement();
            sqlStatement.executeUpdate("CREATE DATABASE " + database + ";");
            tempConnection.close();
            System.out.println("[DEBUG] Successfully created database. [" + database + "]");
        } catch(Exception e) {
            System.out.println("[DEBUG] Unable to create database. [" + database + "]");
        }

    }

    public void sqlUpdate(String statement) {

        try {
            Statement sqlStatement = this.db.createStatement();
            sqlStatement.executeUpdate(statement);
            System.out.println("[DEBUG] Successfully updated SQL statement. [" + statement + "]");
        } catch (Exception e) {
            System.out.println("[DEBUG] Unable to update SQL statement. [" + statement + "]");
        }

    }

    public ResultSet sqlExecute(String statement) {

        try {
            Statement sqlStatement = this.db.createStatement();
            ResultSet results = sqlStatement.executeQuery(statement);
            System.out.println("[DEBUG] Successfully executed SQL statement. [" + statement + "]");
            return results;
        } catch (Exception e) {
            System.out.println("[DEBUG] Unable to execute SQL statement. [" + statement + "]");
            return null;
        }

    }

    public static ArrayList<String[]> getResults(ResultSet results) {

        ArrayList<String[]> resultsArrayList = new ArrayList<String[]>();

        try {
            while (results.next()) {
                String[] resultsArray = new String[results.getMetaData().getColumnCount()];
                for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
                    resultsArray[i - 1] = results.getString(i);
                }
                resultsArrayList.add(resultsArray);
            }
            return resultsArrayList;
        } catch (Exception e) {
            System.out.println("[DEBUG] Unable to display results.");
            return null;
        }

    }

}
