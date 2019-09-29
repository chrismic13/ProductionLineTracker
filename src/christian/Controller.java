package christian;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import javax.annotation.Resources;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {

  static final String JDBC_DRIVER = "org.h2.Driver";
  static final String DB_URL = "jdbc:h2:./res/ProductDB";

  //  Database credentials
  static final String USER = "";
  static final String PASS = "";

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ObservableList<String> options =
        FXCollections.observableArrayList(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        );
    comboBoxChooseQuantity.setItems(options);
    comboBoxChooseQuantity.setEditable(true);
    comboBoxChooseQuantity.getSelectionModel().selectFirst();
  }

  @FXML
  private TextField textFieldProductName;

  @FXML
  private Label labelProductName;

  @FXML
  private Label labelManufacturer;

  @FXML
  private TextField textFieldManufacturer;

  @FXML
  private Label labelItemType;

  @FXML
  private ChoiceBox<?> choiceBoxItemType;

  @FXML
  private TableView<?> tableViewExistingProducts;

  @FXML
  private Button buttonAddProduct;

  @FXML
  private Label labelExistingProducts;

  @FXML
  private Label labelChooseProduct;

  @FXML
  private ListView<?> listViewChooseProduct;

  @FXML
  private Label labelChooseQuantity;

  @FXML
  private ComboBox comboBoxChooseQuantity;


  @FXML
  private Button buttonRecordProduction;

  @FXML
  void addProduct(MouseEvent event) {
    String pName = textFieldProductName.getText();
    String manufacturer = textFieldManufacturer.getText();
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL);
      //STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql =
          "INSERT INTO Product(type, manufacturer, name) VALUES ( 'AUDIO', '" + manufacturer
              + "', '" + pName + "' );";
      stmt.executeUpdate(sql);
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void recordProduction(MouseEvent event) {
    System.out.println("recordProduction");
  }

}
