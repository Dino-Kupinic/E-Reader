package htl.steyr.ereader.controller.customer;

import htl.steyr.ereader.model.PublisherInterface;
import htl.steyr.ereader.model.SubscriberInterface;
import htl.steyr.ereader.repository.CustomerRepository;
import javafx.fxml.Initializable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Component
public class CustomerRemoveController implements Initializable, PublisherInterface {
  private SubscriberInterface subscriber = null;
  private final CustomerRepository customerRepository;

  // TODO: implement the rest

  @Override
  public void addSubscriber(SubscriberInterface sub) {

  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }
}
