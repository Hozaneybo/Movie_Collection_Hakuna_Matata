package gui.controller;

import be.CatMovie;
import be.Category;
import be.Movie;
import gui.model.CategoryModel;
import gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MovieViewController extends ControllerManager implements Initializable {
    @FXML
    private TableColumn <Movie, Date> cLastview;

    @FXML
    private TableColumn<Movie, Integer> cNr;

    @FXML
    private TableColumn<Movie, Double> cRate, cIMDBRate;

    @FXML
    private TableColumn<Movie, String> cTitle;

    @FXML
    private Label lbl1;

    @FXML
    private TextField serch, txtRate;

    @FXML
    private TableView<Movie> tableview;

    private MovieModel movieModel;

    private CategoryModel categoryModel;



    public MovieViewController() {
        try {
            movieModel = new MovieModel();
            categoryModel = new CategoryModel();
        }catch (Exception e){

            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void tblMoviesClicked(javafx.scene.input.MouseEvent mouseEvent) throws SQLException {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            showCategories();
        }
    }



    public void addMovie(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/view/AddMovie.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        AddMovieController addMovieController = loader.getController();
        addMovieController.setModel(super.getModel());
        showAllMoviesInTable();
        addMovieController.setup();


        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("Add Movie");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node)event.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);

        dialogWindow.showAndWait();
    }


    public void removeMovie(ActionEvent event) throws Exception {

        if(tableview.getSelectionModel().getSelectedItem() != null) {
            Movie selectedMovie = tableview.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Are you sure you want to delete: " + selectedMovie.getName().concat(" ?"));
            DialogPane dialogPane = alert.getDialogPane();
            // Add the CSS file to the dialog pane
            dialogPane.getStylesheets().add("CSS/scratch.css");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                movieModel.deleteMovie(selectedMovie);
                getModel().getCatMovieModel().deleteCatMovie(selectedMovie.getId());
                refreshMovieTable();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a movie to remove it ");
            // Get the dialog pane of the alert
            DialogPane dialogPane = alert.getDialogPane();
            // Add the CSS file to the dialog pane
            dialogPane.getStylesheets().add("CSS/scratch.css");
            alert.showAndWait();
        }
    }
    public void refreshMovieTable()
    {
        tableview.setItems(movieModel.getObservableAllMovies());
    }

    @Override
    public void setup() {
        movieModel = getModel().getMovieModel();
        categoryModel = getModel().getCategoryModel();
        showAllMoviesInTable();
        //Search function.
        serch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                movieModel.searchMovie(newValue);
                refreshMovieTable();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        if(movieModel.getObservableAllMovies().size()!= 0){
        // Create an alert with the warning message
        Alert alert = new Alert(Alert.AlertType.WARNING, "Remember to delete movies that have a personal rating under 6 and have not been opened from the application in more than 2 years.");
        // Get the dialog pane of the alert
        DialogPane dialogPane = alert.getDialogPane();
        // Add the CSS file to the dialog pane
        dialogPane.getStylesheets().add("CSS/scratch.css");
        alert.showAndWait();}
    }

    public void showAllMoviesInTable()
    {

        if(movieModel.getObservableAllMovies().size()!=0){
            tableview.setItems(movieModel.getObservableAllMovies());
            cTitle.setCellValueFactory(new PropertyValueFactory<Movie, String>("name"));
            cIMDBRate.setCellValueFactory(new PropertyValueFactory<Movie, Double>("IMDBRating"));
            cLastview.setCellValueFactory(new PropertyValueFactory<Movie, Date>("lastView"));
            cRate.setCellValueFactory(new PropertyValueFactory<Movie, Double>("personalRating"));


        }


        cNr.setCellFactory(column -> {
            return new TableCell<Movie, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        // Set the text to the row number
                        setText(String.valueOf(getIndex() + 1));
                    }
                }
            };
        });
    }

    public void playVideo(ActionEvent actionEvent) throws URISyntaxException, IOException, SQLException {

        if (tableview.getSelectionModel().getSelectedItem() != null) {
            File file = new File(tableview.getSelectionModel().getSelectedItem().getFileLink());
            String absolutePath = file.getAbsolutePath().replaceAll("\\\\", "/");
            Desktop.getDesktop().browse(URI.create(absolutePath));
            Date lastView = new Date(System.currentTimeMillis());
            try {
                movieModel.updateDate(tableview.getSelectionModel().getSelectedItem().getId(), lastView);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a movie to be played");
            // Get the dialog pane of the alert
            DialogPane dialogPane = alert.getDialogPane();
            // Add the CSS file to the dialog pane
            dialogPane.getStylesheets().add("CSS/scratch.css");
            alert.showAndWait();
        }
    }

    public void showCategories() {

        try {
            if (tableview.getSelectionModel().getSelectedItem() != null) {
                int movieId = tableview.getSelectionModel().getSelectedItem().getId();
                java.util.List<Integer> categoryIds = getModel().getCatMovieModel().getCategories(movieId);
                List<String> categoryNames = new ArrayList<>();
                for (int id : categoryIds) {
                    categoryNames.add(getModel().getCategoryModel().getObservableAllCategories().get(id-1).getName());
                }
                lbl1.setText(categoryNames.toString());
            }
        } catch (Exception e) {
            // handle the exception
        }
    }
    public void rate(ActionEvent actionEvent) {
        String rate = txtRate.getText();
        if (!rate.matches("[1-9]|10")) {
            // show an error message or alert to the user
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please give a rate between [1 - 10]");
            // Get the dialog pane of the alert
            DialogPane dialogPane = alert.getDialogPane();
            // Add the CSS file to the dialog pane
            dialogPane.getStylesheets().add("CSS/scratch.css");
            alert.showAndWait();
            return;
        }
        try {
            movieModel.updatePersonalRating(tableview.getSelectionModel().getSelectedItem().getId(), Double.parseDouble(rate));
            txtRate.clear();
            showAllMoviesInTable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}