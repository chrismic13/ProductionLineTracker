package christian;

/*@author Christian McCann
11/9/2019
This interface is used for making sure that the classes that use it all have the same elements*/
public interface ScreenSpec {

  String getResolution();

  int getRefreshRate();

  int getResponseTime();
}
