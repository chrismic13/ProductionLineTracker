package christian;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
    primaryStage.setTitle("Production Line Tracker");
    Scene scene = new Scene(root, 550, 400);
    primaryStage.setScene(scene);
    primaryStage.show();

  }


  public static void main(String[] args) {
    launch(args);
  }
}
