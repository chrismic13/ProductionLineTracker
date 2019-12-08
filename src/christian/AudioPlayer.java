package christian;

/**
 * @author Christian McCann 11/9/2019 This class is class for creating AudioPlayer objects.
 * AudioPlayer is a subclass of Product, and it implements the multimediaControl Interface
 */
public class AudioPlayer extends Product implements MultimediaControl {

  private String supportedAudioFormats;
  private String supportedPlaylistFormats;

  /**
   * This constructor uses the parent class constructor while also setting the new variables for the
   * class.
   *
   * @param name                     name of the product
   * @param manufacturer             manufacturer of the product
   * @param supportedAudioFormats    different formats of the product
   * @param supportedPlaylistFormats different formats of the product
   */
  AudioPlayer(String name, String manufacturer, String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(name, manufacturer, ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  /*The following methods are supposed to simulate the controls on an Audio Player.*/
  public void play() {
    System.out.println("Playing");
  }

  public void stop() {
    System.out.println("Stopping");
  }

  public void previous() {
    System.out.println("Previous");
  }

  public void next() {
    System.out.println("Next");
  }

  /**
   * This toString method overrides its parent toString method while also utilizing it to decrease
   * on the amount of code needed.
   *
   * @return
   */
  @Override
  public String toString() {
    return super.toString() + "\nsupported Audio Formats: " + supportedAudioFormats
        + "\nSupported Playlist Formats: " + supportedPlaylistFormats;
  }

}