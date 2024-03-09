package htl.steyr.ereader.model;

/**
 * <h4>Publisher Interface</h4>
 * <br>
 * Publisher/Subscriber Pattern for Controllers
 *
 * @author Dino Kupinic
 */
public interface PublisherInterface {
  /**
   * Add Subscriber to Publisher List of Subscribers
   *
   * @param sub Subscriber
   */
  void addSubscriber(SubscriberInterface sub);

  Object getData();
}
