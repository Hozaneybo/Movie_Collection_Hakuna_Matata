package gui.controller;

import gui.model.CategoryModel;
import gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;


public class AddCategoryController {

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

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
}
