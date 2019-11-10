package christian;

/*@author Christian McCann
11/9/2019
This class extends the class product and implements the multimedia control interface. */
public class MoviePlayer extends Product implements MultimediaControl {

  private Screen screen;
  private MonitorType monitorType;

  /*This constructor uses the parent class constructor while also setting the new
  variables for the class.*/
  MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  /*The following methods are supposed to simulate the controls on a Movie Player.*/
  public void play() {
    System.out.println("Playing movie");
  }

  public void stop() {
    System.out.println("Stopping movie");
  }

  public void previous() {
    System.out.println("Previous movie");
  }

  public void next() {
    System.out.println("Next movie");
  }

  /*This toString method overrides its parent toString method while also utilizing it to
  decrease on the amount of code needed.*/
  @Override
  public String toString() {
    return super.toString() + "\n" + screen.toString() + "\n" + "Monitor Type: " + monitorType;

  }
}