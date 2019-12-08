package christian;

/**
 * @author Christian McCann 9/28/2019 This class is where the bulk of the code goes that deals with
 * the gui of the program. In this class I connect to a database to send and retrieve information
 * relating to products and a log of production. This class deals with connecting to the Database
 * and also the displaying of information on the GUI.
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * This class implements Initializable to help with the combobox and other initialized sections of
 * the code.
 */
public class Controller implements Initializable {

  /*The next two final Strings are used to connect to the database.*/
  static final String JDBC_DRIVER = "org.h2.Driver";
  static final String DB_URL = "jdbc:h2:./res/ProductDB";

  /*These two final strings are placeholders for later on.*/
  static final String USER = "";
  static final String PASS = "";

  /*These next to lines are here to declare the connection and statement
    that I used for connecting to the database*/
  Connection conn = null;
  Statement stmt = null;

  /**
   * This initialize method is used to run code when the program starts.
   *
   * @param location  Default
   * @param resources Defult
   */
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

    loadProductList();
    loadProductionLog();

  }

  /*The following fields/lists/events are here to help interact with the GUI*/
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
  private TextArea textAreaProductionLog;

  @FXML
  private Button buttonRecordProduction;

  @FXML
  private TextField textFieldFullName;

  @FXML
  private TextField textFieldPassword;

  @FXML
  private Button buttonEmployee;

  @FXML
  private Label labelUserName;

  @FXML
  private Label labelEmail;

  @FXML
  private Label labelPassWord;

  /**
   * The following method is a mouseclick event that triggers the generation of employee
   * information. This information is not stored and is a demostration of capability.
   *
   * @param event This parameter is dealing with the event that is a mouse click
   */
  @FXML
  public void genEmpInfo(MouseEvent event) {
    String name = textFieldFullName.getText();
    String password = textFieldPassword.getText();
    Employee employee = new Employee(name, password);
    String username = employee.getUserName();
    password = employee.getPassword();
    String email = employee.getEmail();
    labelUserName.setText(username);
    labelEmail.setText(email);
    labelPassWord.setText(password);
  }

  /**
   * the following method is used to retrieve information about products from a database, add them
   * to a list, and then display that list to the user.
   */
  public void loadProductList() {
    try {
      productLine.clear();
      Class.forName(JDBC_DRIVER);
      /*This next line establishes a connection*/
      conn = DriverManager.getConnection(DB_URL);
      /*The next section creates a statement and then prepared statement for use
      to insert values into the database*/
      stmt = conn.createStatement();

      String sql = "SELECT * FROM PRODUCT";

      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        // these lines correspond to the database table columns
        String name = rs.getString(2);
        String type = rs.getString(3);
        String manufacturer = rs.getString(4);
        // create object
        Widget productFromDB = new Widget(name, manufacturer, ItemType.valueOf(type));
        // save to observable list
        productLine.add(productFromDB);

      }
      tableViewExistingProducts.setItems(productLine);
      listViewChooseProduct.setItems(productLine);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * This next method is a mouse click event used to add products to the Database and display them
   * for users. The event parameter here is because the mouse click triggers this method.
   *
   * @param event This is the event trigger
   */
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
          "INSERT INTO Product(name, type, MANUFACTURER) VALUES ( ?,?,?);";
      PreparedStatement st = conn.prepareStatement(sql);
      st.setString(1, productName);
      st.setString(2, itemType.toString());
      st.setString(3, manufacturer);
      st.executeUpdate();
      /*The next three lines are used to clean up the code and not have any
      openings into the database.*/
      st.close();
      /*These two catches are used to diagnose any problems or issued with the
       database connection*/
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    loadProductList();
  }


  /**
   * this event method is used to help record the production of praducts. products and quantities
   * are added to a list that is then passed to the addToProductionDB method that adds these
   * production records to the Database.
   *
   * @param event This is the event trigger
   */
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
      ArrayList<ProductionRecord> productionRun = new ArrayList<>();
      for (int i = 0; i < count; i++) {
        ProductionRecord prodRec = new ProductionRecord(product, i, product.getId());
        productionRun.add(prodRec);
      }
      addToProductionDB(productionRun);
      loadProductionLog();
      showProduction();

    }
  }

  /**
   * This method is used to add production logs to the DB. It accomplished this by taking an
   * arraylist of production records and cycles through them adding them to the database.
   *
   * @param productionRun An arraylist used to store records
   */
  public void addToProductionDB(ArrayList<ProductionRecord> productionRun) {
    try {
      for (ProductionRecord prodRec : productionRun) {
        String sql =
            "INSERT INTO PRODUCTIONRECORD(PRODUCTION_NUM, PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED) "
                + "VALUES ( ?,?,?,?);";
        PreparedStatement st = conn.prepareStatement(sql);
        java.sql.Date sqlDate = new java.sql.Date(prodRec.getProdDate().getTime());
        st.setInt(1, prodRec.getProductionNum());
        st.setInt(2, prodRec.getProductID());
        st.setString(3, prodRec.getSerialNum());
        st.setDate(4, sqlDate);
        st.executeUpdate();
        /*The next three lines are used to clean up the code and not have any
        openings into the database.*/
        st.close();
      }

      stmt.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is used to display the production log. It does this by retrieving the production
   * logs from the DB and adds each record to an observable list before displaing the list.
   */
  public void loadProductionLog() {
    try {
      productionRecords.clear();
      String sql = "SELECT * FROM PRODUCTIONRECORD";

      conn = DriverManager.getConnection(DB_URL);
      /*The next section creates a statement and then prepared statement for use
      to insert values into the database*/
      stmt = conn.createStatement();

      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        // these lines correspond to the database table columns
        int prodNum = rs.getInt(1);
        int prodID = rs.getInt(2);
        String serialNum = rs.getString(3);
        Date dateProd = rs.getDate(4);
        // create object
        ProductionRecord prodRecFromDB = new ProductionRecord(prodNum, prodID, serialNum, dateProd);
        // save to observable list
        productionRecords.add(prodRecFromDB);


      }
      showProduction();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is used to display the production records to the database. It does this by sending
   * the production records toStrings and then does some editing to present the information in a
   * neat manner.
   */
  public void showProduction() {
    textAreaProductionLog.appendText(
        productionRecords.toString().substring(1, productionRecords.toString().length() - 1)
            .replace(",", ""));
  }

}
