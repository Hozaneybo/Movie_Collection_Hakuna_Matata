package gui.controller;

import be.Movie;
import gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MovieViewController implements Initializable {
    // public TableColumn cIMDBRate;
    public TableColumn cLastview;

    @FXML
    private TableColumn<Movie, Integer> cNr;

    @FXML
    private TableColumn<Movie, Double> cRate, cIMDBRate;

    @FXML
    private TableColumn<Movie, String> cTitle;

    @FXML
    private Label category;

    @FXML
    private TextField serch;

    @FXML
    private TableView<Movie> tableview;

    private MovieModel movieModel;

    public MovieViewController() {
        try {
            movieModel = new MovieModel();
            // songsInPlaylistModel = new SongsInPlaylistModel();
            //allPlaylistsModel = new AllPlaylistsModel();
        }catch (Exception e){

            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableview.getItems().addAll(movieModel.getObservableAllMovies());
        cTitle.setCellValueFactory(new PropertyValueFactory<Movie, String>("name"));
        cIMDBRate.setCellValueFactory(new PropertyValueFactory<Movie, Double>("IMDBRating"));

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
        serch.textProperty().addListener((observableValue, oldValue, newValue) ->
        {
            try {
                movieModel.searchMovie(newValue);
                refreshMovieTable();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


    public void addMovie(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/AddMovie.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Hahaha");
        stage.show();
    }


    public void removeMovie(ActionEvent event) throws Exception {

        Movie selectedMovie = tableview.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Are you sure you want to delete: " + selectedMovie.getName().concat( " ?"));
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get() == ButtonType.OK)
        {
            movieModel.deleteMovie(selectedMovie);
            refreshMovieTable();
        }
    }
    public void refreshMovieTable()
    {
        tableview.setItems(movieModel.getObservableAllMovies());
    }
}

