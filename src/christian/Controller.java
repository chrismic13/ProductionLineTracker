package christian;
/*@author Christian McCann
9/28/2019
This class is where the bulk of the code goes that deals with the gui of the program.
In this class I establish a connection to a Database and insert date into it.*/

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    /*These next three lines set the combobox to the options list from about, allow the
    users to edit it, and selects the first option.*/
    comboBoxChooseQuantity.setItems(options);
    comboBoxChooseQuantity.setEditable(true);
    comboBoxChooseQuantity.getSelectionModel().selectFirst();
    /*This next line is adding the item type values to the choicebox so that users can select it.*/
    choiceBoxItemType.getItems().setAll(ItemType.values());
    /*These next two lines are creating observable lists to store products in the product line tab,
    and production records for the production records tab.*/
    productLine = FXCollections.observableArrayList();
    productionRecords = FXCollections.observableArrayList();

    /*These next three lines are setting each column in the tableview to the variable that it
    goes with.*/
    colProductName.setCellValueFactory(new PropertyValueFactory("name"));
    colManufacturer.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    colItemType.setCellValueFactory(new PropertyValueFactory("type"));

    /*These next three lines set the observable lists that will be populating the tableview of
    existing products, the listview for choosing items to produce, and the listview being used for
    the production log*/
    tableViewExistingProducts.setItems(productLine);
    listViewChooseProduct.setItems(productLine);
    listViewProductionLog.setItems(productionRecords);
  }


  /*The following fields are here because everything in the fxml has an fxid
  so that these elements can be used in the code.*/
  private ObservableList<Product> productLine;

  private ObservableList<ProductionRecord> productionRecords;

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
  private ChoiceBox<ItemType> choiceBoxItemType;

  @FXML
  private TableView<Product> tableViewExistingProducts;

  @FXML
  private Button buttonAddProduct;

  @FXML
  private Label labelExistingProducts;

  @FXML
  private Label labelChooseProduct;

  @FXML
  private ListView<Product> listViewChooseProduct;

  @FXML
  private Label labelChooseQuantity;

  @FXML
  private ComboBox comboBoxChooseQuantity;

  @FXML
  private TableColumn<?, ?> colProductName;

  @FXML
  private TableColumn<?, ?> colManufacturer;

  @FXML
  private TableColumn<?, ?> colItemType;

  @FXML
  private ListView listViewProductionLog;

  @FXML
  private Button buttonRecordProduction;

  /*This next event method is used to establish the connection to the
  database and insert values into the database.

  It is also used to add products to an observable list of products that is being used to test
  this program before full db integration*/
  @FXML
  void addProduct(MouseEvent event) {
    /*These next two lines are taking the two fields from the gui and turning
    them into strings to be used in the code*/
    String productName = textFieldProductName.getText();
    String manufacturer = textFieldManufacturer.getText();
    ItemType itemType = choiceBoxItemType.getValue();

    /*This next line created a new product that is added to the product observable list.*/
    productLine.add(new Widget(productName, manufacturer, itemType));
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


  /*This next event method is here to create production records and display them to the user*/
  @FXML
  void recordProduction(MouseEvent event) {
    /*This if statement is here to make sure that the program does not run into any errors if you
    product is selected in the choose product list view.*/
    if (!listViewChooseProduct.getSelectionModel().isEmpty()) {
      /*These next lines grab the product and count numbers from the Produce tab so that they can
      be used to create production records.*/
      Product product = listViewChooseProduct.getSelectionModel().getSelectedItem();
      int count = comboBoxChooseQuantity.getSelectionModel().getSelectedIndex() + 1;
      /*This next line is adding a production record object to an observable list that is
      used later.*/
      productionRecords.add(new ProductionRecord(product, count));
    } else {

    }


  }

}
