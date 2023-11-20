package com.example.knowledge_basejavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.security.PublicKey;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage)  {

        try{
            //set Main load Scene1 when Run
            Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
            primaryStage.setTitle("Knowledge Base Editor");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }


}