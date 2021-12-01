package com.adithya.moviedatabase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("C:/Users/Adithya/IdeaProjects/MovieDatabase/src/main/resources/com/adithya/moviedatabase/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 650);
        stage.setTitle("Movie Details");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("MySQL Connected!");

        launch();
    }
}