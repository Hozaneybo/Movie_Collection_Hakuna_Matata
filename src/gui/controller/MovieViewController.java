package gui.controller;

import be.Movie;
import gui.model.CategoryModel;
import gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class MovieViewController extends ControllerManager implements Initializable {
    // public TableColumn cIMDBRate;
    @FXML
    private TableColumn <Movie, Date> cLastview;

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

    private CategoryModel categoryModel;

    private MediaPlayer mediaPlayer;
    private ArrayList<File> movies;
    private File directory;
    private int songNumber;

    private Media media;
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
        movies = new ArrayList<>();
        directory = new File("movies");



    }

    public void tblMoviesClicked(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {

            }
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

        Movie selectedMovie = tableview.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Are you sure you want to delete: " + selectedMovie.getName().concat( " ?"));
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get() == ButtonType.OK)
        {
            movieModel.deleteMovie(selectedMovie);
            getModel().getCatMovieModel().deleteCatMovie(selectedMovie.getId());
            refreshMovieTable();
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

    }

    public void showAllMoviesInTable()
    {

        if(movieModel.getObservableAllMovies().size()!=0){
            tableview.setItems(movieModel.getObservableAllMovies());
            cTitle.setCellValueFactory(new PropertyValueFactory<Movie, String>("name"));
            cIMDBRate.setCellValueFactory(new PropertyValueFactory<Movie, Double>("IMDBRating"));
            cLastview.setCellValueFactory(new PropertyValueFactory<Movie, Date>("lastView"));
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
}
