package christian;

/*@author Christian McCann
11/9/2019
This class is a big part of the program and is integral to it working.
This class implements the item interface.
This class is also an abstract class because there are different types of products and a
product itself cannot exist.*/
public abstract class Product implements Item {

  private int Id;
  /*These next three fields are vital to making this program run. the type field is created
  using the ItemType enum that was created for this program.*/
  private ItemType type;
  private String manufacturer;
  private String name;

  /*This is a simple constructor that sets all of the fields.*/
  Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /*This toString method overrides the super to string method to display its fields in a
  neat and clean way.*/
  @Override
  public String toString() {
    return "Name: " + name + "\n" + "Manufacturer: " + manufacturer + "\n" + "Type: "
        + type;
  }

  /*The rest of this class contains basic getter and setters for the fields in the class*/
  public int getId() {
    return Id;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setType(ItemType type) {
    this.type = type;
  }

  public ItemType getType() {
    return type;
  }
}

/*The widget class is a temporary class that extends product and is used to create a basic product
because we cannot create a product as it is an abstract class.*/
class Widget extends Product {

  /*The constructor for this class just calls the constructor for its super class product.*/
  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }
}