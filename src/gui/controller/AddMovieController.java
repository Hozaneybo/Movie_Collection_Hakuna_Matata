package gui.controller;

import be.Category;
import be.Movie;
import gui.model.CategoryModel;
import gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddMovieController implements Initializable {
    @FXML
    private TableView<Category> listOfCategory;

    @FXML
    private TableColumn<Category, String> cCategories;


    @FXML
    private TextField txtName, txtFilelink, txtRating;

    private File file;
    private MovieModel movieModel;
    private CategoryModel categoryModel;

    public AddMovieController() {
        try {
            movieModel = new MovieModel();
            categoryModel = new CategoryModel();
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listOfCategory.getItems().addAll(categoryModel.getObservableAllCategories());
        cCategories.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        listOfCategory.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    public void save(ActionEvent event) {

        //Instantiate variables
        String name = txtName.getText();
        String fileLink = txtFilelink.getText();
        double personalRating = Double.parseDouble(txtRating.getText());
        double IMDBRating = Double.parseDouble(txtRating.getText());
        Date lastView = new Date(1234567890123L);

        try {
            //Calls a method from the SongModel
            movieModel.createMovie(name, fileLink, personalRating, IMDBRating, lastView);

        } catch (Exception e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        //Closes the window
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }


    public void cancel(ActionEvent event) {

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void choose(ActionEvent event) {
        //Instantiate variables
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        //Opens a new window with file explorer.
        file = fc.showOpenDialog(stage);
        if(file != null) {
            //Gets the path of the file and copies it to a text field.
            txtFilelink.setText(file.toURI().toString());
        }
    }

    public void addCategory(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/AddCategory.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Hahaha");
        stage.show();
    }

    public void removeCategory(ActionEvent event) throws Exception {

        Category selectedCategory = listOfCategory.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Are you sure you want to delete: " + selectedCategory.getName().concat( " ?"));
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get() == ButtonType.OK)
        {
            categoryModel.deleteCategory(selectedCategory);
        }

    }
}
