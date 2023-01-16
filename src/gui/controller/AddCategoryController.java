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

    /**
    * The AddCategoryController class is responsible for handling events and actions
    * related to the creation of a new category.
    */
    public class AddCategoryController extends ControllerManager {

    // TextField for the name of the new category
    @FXML
    private TextField nameField;

    // Field to hold an instance of the CategoryModel class
    private CategoryModel categoryModel;

    /**
     * Constructor that creates a new instance of the CategoryModel class
     */
    public AddCategoryController() {
        try {
            categoryModel = new CategoryModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to handle the save button event and creates a new category with the entered name
     *
     * @param event the event that triggered the method call
     */
    public void saveCategory(ActionEvent event) {

        //Instantiate variables
        String name = nameField.getText();

        if (!nameField.getText().isEmpty()) {
            try {

                //Calls a method from the CategoryModel
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
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Show a warning message if the name field is empty
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please add a category ");
            // Get the dialog pane of the alert
            DialogPane dialogPane = alert.getDialogPane();
            // Add the CSS file to the dialog pane
            dialogPane.getStylesheets().add("CSS/scratch.css");
            alert.showAndWait();
        }
    }

    /**
     * Method to handle the cancel button event and closes the current window
     *
     * @param event the event that triggered the method call
     */
    public void cancelSaving(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Method that creates a new instance of the CategoryModel class
     */
    @Override
    public void setup() {
        try {
            categoryModel = new CategoryModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
