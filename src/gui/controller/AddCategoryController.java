package gui.controller;

import gui.model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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

        if (!nameField.getText().isEmpty()) {
            try {

            //Calls a method from the SongModel
            categoryModel.createCategory(name);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/gui/view/AddMovie.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            AddMovieController addMovieController = loader.getController();

            addMovieController.setModel(super.getModel());
            addMovieController.showAllMoviesInTable();
            addMovieController.setup();
            setup();


            //Closes the window
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.close();
        } catch (Exception e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please add a category ");
            // Get the dialog pane of the alert
            DialogPane dialogPane = alert.getDialogPane();
            // Add the CSS file to the dialog pane
            dialogPane.getStylesheets().add("CSS/scratch.css");
            alert.showAndWait();
        }



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
