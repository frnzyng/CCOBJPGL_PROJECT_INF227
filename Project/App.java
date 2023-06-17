import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(root);

            // Applies the style sheet to the next or "to-be-loaded" scene/page (A.K.A. the fxml files):
            String css = this.getClass().getResource("/css/Login.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage. setResizable(false);
            stage.setScene(scene);
            stage.show();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}