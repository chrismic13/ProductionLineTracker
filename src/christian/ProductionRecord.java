package christian;

/**
 * @author Christian McCann 11/9/2019 This class is used to create a record of production which is
 * used to create a production log.
 */

import java.util.Date;

public class ProductionRecord {

  private int productionNumber;
  private int productID;
  private String serialNumber;
  private Date dateProduced;

  /**
   * This constructor is used to create a serial number for production and give a date.
   *
   * @param product   product object
   * @param count     the number of items being produced
   * @param productID The product ID passed into production record
   */
  public ProductionRecord(Product product, int count, int productID) {
    this.serialNumber =
        product.getManufacturer().substring(0, 3).toUpperCase() + product.getType().getLabel()
            + String.format("%05d", count);
    dateProduced = new Date();
    this.productID = productID;
  }

  /**
   * This constructor takes in a product id and then assigns default values to the other variables.
   *
   * @param productID created a record with just the id
   */
  public ProductionRecord(int productID) {
    this.productID = productID;
    productionNumber = 0;
    serialNumber = "0";
    dateProduced = new Date();
  }

  /**
   * This basic constructor takes all of the fields as inputs and assigns them to the class fields.
   *
   * @param productID        ID number for the product
   * @param productionNumber The production number of the project
   * @param serialNumber     Serial number of the product
   * @param dateProduced     The date the product was created
   */
  public ProductionRecord(int productID, int productionNumber, String serialNumber,
      Date dateProduced) {
    this.productID = productID;
    this.productionNumber = productionNumber;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

  /**
   * This toString method overrides its super to create a nicely formatted string of this class'
   * variables.
   *
   * @return
   */
  @Override
  public String toString() {
    return "\nProd. Num: " + productionNumber + " Product ID: " + productID + " Serial Num: "
        + serialNumber + " Date: " + dateProduced;
  }

  /*The rest of this class consists of basic getter and setter methods.*/
  public int getProductionNum() {
    return productionNumber;
  }

  public int getProductID() {
    return productID;
  }

  public String getSerialNum() {
    return serialNumber;
  }

  public Date getProdDate() {
    return dateProduced;
  }

  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public void setSerialNum(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public void setProdDate(Date dateProduced) {
    this.dateProduced = dateProduced;
  }
}