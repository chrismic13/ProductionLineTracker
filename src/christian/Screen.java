package christian;

/**
 * @author Christian McCann 11/9/2019 This class is used to create a screen. It implements the
 * ScreenSpec interface.
 */
public class Screen implements ScreenSpec {

  private String resolution;
  private int refreshRate;
  private int responseTime;

  /**
   * This is a basic constructor that sets all of the fields in the class.
   *
   * @param resolution   resolution of screen
   * @param refreshRate  refresh rate of screen
   * @param responseTime responsetime of screen
   */
  Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  /*These next three methods are basic getter methods.*/
  public String getResolution() {
    return resolution;
  }

  public int getRefreshRate() {
    return refreshRate;
  }

  public int getResponseTime() {
    return responseTime;
  }

  /**
   * This toString method overrides its super method to give a clean way to present the class
   * fields.
   *
   * @return
   */
  @Override
  public String toString() {
    return "Screen:" + "\n" + "Resolution: " + resolution + "\n" + "Refresh rate: " + refreshRate
        + "\n" + "Response time: "
        + responseTime;
  }


}
