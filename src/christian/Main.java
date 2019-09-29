package christian;

/*@author Christian McCann
 * 9/28/2019
 * Main Class of Production Line Tracker Program.*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*This is the main class of my Production line tracker program.*/

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
    primaryStage.setTitle("Production Line Tracker");
    primaryStage.setScene(new Scene(root, 550, 400));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
