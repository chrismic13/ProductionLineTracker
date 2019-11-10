package christian;

/*@author Christian McCann
11/9/2019
This enum is used for all products. each product has a specific type and this enum defines
the select few choices for type.*/
public enum ItemType {
  /*These are the enum options*/
  AUDIO("AU"), VISUAL("VI"), AUDIO_MOBILE("AM"), VISUAL_MOBILE("VM");

  /*The String label is used in various places and is relative to a certain enum option*/
  private String label;

  /*This constructor sets the label type.*/
  ItemType(String typeLabel) {
    label = typeLabel;
  }

  /*This method returns the itemtype label for use in other classes.*/
  public String getLabel() {
    return label;
  }
}