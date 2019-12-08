package christian;

/**
 * @author Christian McCann 9/28/2019 Main Class of Production Line Tracker Program. This program is
 * used to keep track of products that can be produced as well as generating logs of that
 * production. This program is also used to generate employee credentials
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class extends application as this program uses a GUI.
 */

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
