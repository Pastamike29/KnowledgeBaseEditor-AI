package com.example.knowledge_basejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene1Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String username;
    @FXML
    TextField nameTextfield;

    Scene2Controller scene2Controller;


    //function switch to Scene2 that use in START buttons
    public void switchToScene2(ActionEvent event) throws IOException {
        //get Username from Textfield
        username = nameTextfield.getText();

        //get path Scene2.fxml
        FXMLLoader loaderScene2 = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        root = loaderScene2.load();

        //use  function displayname in Scene2Controller
        scene2Controller = loaderScene2.getController();
        scene2Controller.displayName(username);

        //set stage properties
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setX(100);
        stage.setY(50);
        stage.setResizable(false); //Cannot Resize Window
        stage.setScene(scene);
        stage.show();
    }
}
