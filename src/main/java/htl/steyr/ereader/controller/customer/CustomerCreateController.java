package htl.steyr.ereader.controller.customer;

import htl.steyr.ereader.model.PublisherInterface;
import htl.steyr.ereader.model.SubscriberInterface;
import javafx.fxml.Initializable;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CustomerCreateController implements Initializable, PublisherInterface {
  private SubscriberInterface subscriber = null;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    System.out.println("Hello");
  }

  @Override
  public void addSubscriber(SubscriberInterface sub) {
    this.subscriber = sub;
  }
}
