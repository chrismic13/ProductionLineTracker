package christian;
/*@author Christian McCann
9/28/2019
This class is the main driver of the program. In this class I establish a
connection to a Database and insert date into it.*/

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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

/*This class implements Initializable to help with the combobox later on.*/
public class Controller implements Initializable {

  /*The next two final Strings are used to connect to the database.*/
  static final String JDBC_DRIVER = "org.h2.Driver";
  static final String DB_URL = "jdbc:h2:./res/ProductDB";

  /*These two final strings are placeholders for later on.*/
  static final String USER = "";
  static final String PASS = "";

  /*This next initialize method is used to give the options 1-10 to the combobox.*/
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    /*This Observable list is what is being sent to the combobox.*/
    ObservableList<String> options =
        FXCollections.observableArrayList(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        );
    /*These next three lines set the combobox to the iotions list from about, allow the
    users to edit it, and selects the first option.*/
    comboBoxChooseQuantity.setItems(options);
    comboBoxChooseQuantity.setEditable(true);
    comboBoxChooseQuantity.getSelectionModel().selectFirst();
  }

  /*The following fields are here because everything in the fxml has an fxid
  so that these elements can be used later.*/
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

  /*This next event method is used to establish the connection to the
  database and insert values into the database.*/
  @FXML
  void addProduct(MouseEvent event) {
    /*These next two lines are taking the two fields from the gui and turning
    them into strings to be inserted into the database*/
    String productName = textFieldProductName.getText();
    String manufacturer = textFieldManufacturer.getText();
    /*This next line is a placeholder for the type value in the database
     table Product.*/
    String type = "";
    /*These next to lines are here to declare the connection and statement
    that I used for connecting to the database*/
    Connection conn = null;
    Statement stmt = null;
    /*The following try and catch are used for the connection to the database to
    help diagnose any problems and where they occur*/
    try {
      /*This next line registers the JDBC driver*/
      Class.forName(JDBC_DRIVER);
      /*This next line establishes a connection*/
      conn = DriverManager.getConnection(DB_URL);
      /*The next section creates a statement and then prepared statement for use
      to insert values into the database*/
      stmt = conn.createStatement();
      String sql =
          "INSERT INTO Product(type, manufacturer, name) VALUES ( ?,?,?);";
      PreparedStatement st = conn.prepareStatement(sql);
      st.setString(1, type);
      st.setString(2, manufacturer);
      st.setString(3, productName);
      st.executeUpdate();
      /*The next three lines are used to clean up the code and not have any
      openings into the database.*/
      st.close();
      stmt.close();
      conn.close();
      /*These two catches are used to diagnose any problems or issued with the
       database connection*/
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /*This next event method is here for future development of the Record Production button*/
  @FXML
  void recordProduction(MouseEvent event) {
    System.out.println("recordProduction");
  }

}
