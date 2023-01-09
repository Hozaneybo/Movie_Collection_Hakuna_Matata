package gui.controller;

import gui.model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;


public class AddCategoryController extends ControllerManager {

    @FXML
    private TextField nameField;

    private CategoryModel categoryModel;

    public AddCategoryController(){
        try {

            categoryModel = new CategoryModel();
        }catch (Exception e){

            e.printStackTrace();
        }

    }
    public void saveCategory(ActionEvent event) {

        //Instantiate variables
        String name = nameField.getText();

        try {
            //Calls a method from the SongModel
            categoryModel.createCategory(name);

        } catch (Exception e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        //Closes the window
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();

    }

    public void cancelSaving(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void setup() {
        try {
            categoryModel = new CategoryModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
