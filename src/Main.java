import gui.controller.MovieViewController;
import gui.model.MCModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/MovieView.fxml"));
        Parent root = loader.load();
        MovieViewController controller = loader.getController();
        controller.setModel(new MCModel());
        controller.setup();

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Movie Collection");
        primaryStage.show();
        primaryStage.setResizable(false);

    }
}