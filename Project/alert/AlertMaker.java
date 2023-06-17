package alert;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AlertMaker {
    
    public static void showItemAddedAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Image image = new Image("/images/Alert/Success.png");
        ImageView imageView = new ImageView(image);
        alert.setGraphic(imageView);

        alert.getDialogPane().getStylesheets().add("/css/Alert.css");

        alert.showAndWait();
    }

    public static void showItemOutofStockAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Image image = new Image("/images/Alert/Error.png");
        ImageView imageView = new ImageView(image);
        alert.setGraphic(imageView);
        
        alert.getDialogPane().getStylesheets().add("/css/Alert.css");
        
        alert.showAndWait();        
    }

    public static void showErrorAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Image image = new Image("/images/Alert/Error.png");
        ImageView imageView = new ImageView(image);
        alert.setGraphic(imageView);
        
        alert.getDialogPane().getStylesheets().add("/css/Alert.css");
        
        alert.showAndWait();        
    }
}