package gui.controller;

import be.Category;
import gui.model.CatMovieModel;
import gui.model.CategoryModel;
import gui.model.MovieModel;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddMovieController extends ControllerManager implements Initializable {
    @FXML
    private TableView<Category> listOfCategory;
    @FXML
    private TableColumn<Category, String> cCategories;
    @FXML
    private TextField txtName, txtFilelink, txtRating;

    private MovieModel movieModel;
    private CategoryModel categoryModel;
    private CatMovieModel catMovieModel;

    public String targetString = "movies";
    private Path target = Paths.get(targetString);
    private File file;

    public AddMovieController() {
        try {
            movieModel = new MovieModel();
            categoryModel = new CategoryModel();
            catMovieModel = new CatMovieModel();
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // A method to save the movie after filling the information in textFields
    public void save(ActionEvent event) throws Exception {
        if ((!txtName.getText().isEmpty()) && (!txtFilelink.getText().isEmpty()) && (!txtRating.getText().isEmpty())) {
        //Instantiate variables
        String name = txtName.getText();
            String fileLink = targetString + "/" + file.getName().replaceAll(" ", "_");
            double personalRating = 0;
        double IMDBRating = Double.parseDouble(txtRating.getText());
        Date lastView = new Date(System.currentTimeMillis());

            String fileName = file.getName().replaceAll(" ", "_");


            if (fileName.endsWith(".mp4") || fileName.endsWith(".mpeg4")) {
                Files.copy(file.toPath(), target.resolve(fileName));
                fileLink = targetString + "/" + fileName;
            movieModel.createMovie(name, fileLink, personalRating, IMDBRating, lastView);
            ObservableList<Category> selectedItems = listOfCategory.getSelectionModel().getSelectedItems();
            List<Integer> ids = new ArrayList<>();
            for (Category c : selectedItems) {
                ids.add(c.getId());
            }
            catMovieModel.setCategories(movieModel.getMovieIdByName(name), ids);
            cancel(event);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Only files ending with .mp4 or .mpeg4 can be added.");
            // Get the dialog pane of the alert
            DialogPane dialogPane = alert.getDialogPane();
            // Add the CSS file to the dialog pane
            dialogPane.getStylesheets().add("CSS/scratch.css");
            alert.showAndWait();
        }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill all information");
            // Get the dialog pane of the alert
            DialogPane dialogPane = alert.getDialogPane();
            // Add the CSS file to the dialog pane
            dialogPane.getStylesheets().add("CSS/scratch.css");
            alert.showAndWait();
        }
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/view/AddCategory.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        AddCategoryController addCategoryController = loader.getController();
        addCategoryController.setModel(super.getModel());
        //showAllMoviesInTable();
        addCategoryController.setup();

        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("Add Category");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node)event.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);

        dialogWindow.showAndWait();
    }

    public void removeCategory(ActionEvent event) throws Exception {

        if(listOfCategory.getSelectionModel().getSelectedItem() != null){
        Category selectedCategory = listOfCategory.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Are you sure you want to delete: " + selectedCategory.getName().concat( " ?"));
        DialogPane dialogPane = alert.getDialogPane();
        // Add the CSS file to the dialog pane
        dialogPane.getStylesheets().add("CSS/scratch.css");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get() == ButtonType.OK)
        {
            categoryModel.deleteCategory(selectedCategory);
        }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a category to remove it ");
            // Get the dialog pane of the alert
            DialogPane dialogPane = alert.getDialogPane();
            // Add the CSS file to the dialog pane
            dialogPane.getStylesheets().add("CSS/scratch.css");
            alert.showAndWait();
        }
    }

    @Override
    public void setup() {
        movieModel = getModel().getMovieModel();
        categoryModel = getModel().getCategoryModel();
        showAllCategoriesInTable();

    }
    public void showAllCategoriesInTable()
    {
        listOfCategory.setItems(categoryModel.getObservableAllCategories());
        cCategories.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        listOfCategory.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
