package com.example.knowledge_basejavafx;

import com.example.knowledge_basejavafx.Connection.DBConnection;
import com.example.knowledge_basejavafx.Model.RuleModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Rule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Scene2Controller implements Initializable {
    @FXML
    Label nameLabel;

    private int id = 0;
    @FXML
    private Button createBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private TextField factTxt;
    @FXML
    private TextField resultTxt;
    @FXML
    private TableView<RuleModel> ruleTableView;

    @FXML
    private TableColumn<RuleModel, Integer> colId;
    @FXML
    private TableColumn<RuleModel, String > colFact;

    @FXML
    private TableColumn<RuleModel, String> colResult;


    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;


    //Function show all Allrules that store in "rules" database
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showAllRules();
    }

    //get All Rules from database(Mysql)
    public ObservableList<RuleModel> getAllRules(){
        ObservableList<RuleModel> rules = FXCollections.observableArrayList();

        String query = "select* from rules";
        connection = DBConnection.getDBCon();
        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                //Set each value in RuleModel from value in database
                RuleModel ruleModel = new RuleModel();
                ruleModel.setId(resultSet.getInt("id"));
                ruleModel.setFact(resultSet.getString("Fact"));
                ruleModel.setResult(resultSet.getString("Result"));

                rules.add(ruleModel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rules;
    }


    //display your name on Hello, Label
    public void displayName(String username){
        nameLabel.setText("Hello," + username);
    }

    //set each columns in TableView to show rules from database
    public void showAllRules(){
        ObservableList<RuleModel> list = getAllRules();
        ruleTableView.setItems(list);
        colId.setCellValueFactory(new PropertyValueFactory<RuleModel, Integer>("id"));
        colFact.setCellValueFactory(new PropertyValueFactory<RuleModel, String>("fact"));
        colResult.setCellValueFactory(new PropertyValueFactory<RuleModel, String>("result"));

    }
    //function CreateRule to insert each parameter and store in database
    @FXML
    public void createRule(ActionEvent event) throws SQLException {
        String insert = "insert into rules(fact,result) values(?,?)" ;
        connection = DBConnection.getDBCon();
        statement = connection.prepareStatement(insert);
        statement.setString(1,factTxt.getText());
        statement.setString(2,resultTxt.getText());
        statement.executeUpdate();
        showAllRules();
        clear();
    }
    //function updateRule By get primary key(id) and update on the TableView
    @FXML
    public void updateRule(ActionEvent event) throws SQLException {
        String update = "update rules set fact = ?, result = ? where id = ?";
        connection = DBConnection.getDBCon();

        statement = connection.prepareStatement(update);
        statement.setString(1,factTxt.getText());
        statement.setString(2,resultTxt.getText());
        statement.setInt(3,id);
        statement.executeUpdate();
        showAllRules();
        clear();
    }

    //function deleteRule By get Id and delete from TableView
    @FXML
    public void deleteRule(ActionEvent event) throws SQLException {
        String delete = "delete from rules where id= ?";

        connection = DBConnection.getDBCon();

        statement = connection.prepareStatement(delete);
        statement.setInt(1,id);
        statement.executeUpdate();

        showAllRules();
        clear();
    }
    //function clearRule to clear all data in TableView and database
    @FXML
    public void clearRule(ActionEvent event) throws SQLException {
        String clear = "delete from rules";
        String alter = "alter table rules auto_increment = 1";
        connection = DBConnection.getDBCon();

        statement = connection.prepareStatement(clear);
        PreparedStatement statement2 = connection.prepareStatement(alter) ;
        statement.executeUpdate();
        statement2.executeUpdate();
        showAllRules();

        clear();
    }
    //to set all value in TableView = null
    public void clear(){
        factTxt.setText(null);
        resultTxt.setText(null);
        createBtn.setDisable(false);//set can click createBtn to create new data
    }

    //Select data from tableView by click each row.
    @FXML
    public void getData(MouseEvent event) {
        RuleModel ruleModel = ruleTableView.getSelectionModel().getSelectedItem();
        id = ruleModel.getId();
        factTxt.setText(ruleModel.getFact());
        resultTxt.setText(ruleModel.getResult());
        createBtn.setDisable(true); //set cant click Create Btn
        showAllRules();
    }



}
