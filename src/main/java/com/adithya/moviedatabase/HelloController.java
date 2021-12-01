package com.adithya.moviedatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;


public class HelloController {

    String url = "jdbc:mysql://localhost:3306/Movies";
    String username = "root";
    String password = "password";

    @FXML
    private TextField inputFeild;

    @FXML
    private Label welcomeText;

    @FXML
    private TextArea consoleObj;


    @FXML
    void onDeleteBtnClick(ActionEvent event) {
        System.out.println("DeleteBtnPressed");
        welcomeText.setText("Deleted as your input");
        String inputQuery = inputFeild.getText();
        System.out.println("textField : " + inputQuery);

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();
            int result = statement.executeUpdate("delete from InterestedMovies");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onInsertBtnClick(ActionEvent event) {
        System.out.println("InsertBtnPressed");
        welcomeText.setText("1, 83, Ranveer Singh, Deepika Padukone, 2021");
        String inputQuery = inputFeild.getText();
        System.out.println("textField : " + inputQuery);

        String[] inputValues = inputQuery.split(", ");
        System.out.println(Arrays.toString(inputValues));

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();

            StringBuilder query = new StringBuilder();

            for (int i = 0; i <= 4; i++) {
                if (i == 0 || i == 4) {
                    query.append(String.format("%s, ", inputValues[i]));
                } else {
                    query.append(String.format("'%s', ", inputValues[i]));
                }
            }

            query.setCharAt(query.length()-2, ' ');

            System.out.printf("insert into InterestedMovies values(%s)%n", query);
            int result = statement.executeUpdate(String.format("insert into InterestedMovies values(%s)", query));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onSelectBtnClick(ActionEvent event) throws SQLException {
        System.out.println("SelectBtnPressed");
        welcomeText.setText("Look at Bottom For Results");
        String inputQuery = inputFeild.getText();
        System.out.println("textField : " + inputQuery);
        consoleObj.setText(" ");

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("select * from InterestedMovies");

            while (result.next()) {
                StringBuilder data = new StringBuilder();
                for (int i = 1; i <= 5; i++) {
                    data.append(result.getString(i)).append(" ⁝ ");
                }
                System.out.println(data);
                consoleObj.setText(consoleObj.getText() + "\n" + data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onUpdateBtnClick(ActionEvent event) {
        System.out.println("UpdateBtnPressed");
        welcomeText.setText("LeadActorMale, LeadActorFemale, YearOfRelease, 83");
        String inputQuery = inputFeild.getText();
        System.out.println("textField : " + inputQuery);

        String[] inputValues = inputQuery.split(", ");
        System.out.println(Arrays.toString(inputValues));

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();

            StringBuilder query = new StringBuilder();
            query.append("update InterestedMovies set ");

            String[] columnNames = new String[]{"Lead_Actor_Male", "Lead_Actor_Female", "Year_Of_Release"};

            for (int i = 0; i < 3; i++) {
                String column = columnNames[i];
                query.append(column).append("=").append(String.format("'%s', ", inputValues[i]));
            }

            query.setCharAt(query.length()-2, ' ') ;
            query.append(String.format("where Movie_Name = '%s'", inputValues[inputValues.length-1]));

            System.out.println(query);

            int result = statement.executeUpdate(String.valueOf(query));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }






}