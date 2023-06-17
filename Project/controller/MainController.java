package controller;

import javafx.event.ActionEvent;
import java.io.IOException;

// Super class of all the controller classes
// Naka-interface para sa navigateToPage method parameter which is yung "MainController controller" 
// Para mas maikli yung code hehe
public interface MainController {
    public void navigationBar(ActionEvent event) throws IOException;
    public void navigateToPage(ActionEvent event, MainController controller, String url, String css) throws IOException;
}
