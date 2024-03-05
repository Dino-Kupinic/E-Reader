package htl.steyr.ereader.controller.customer;

import htl.steyr.ereader.model.Customer;
import htl.steyr.ereader.model.PublisherInterface;
import htl.steyr.ereader.model.SubscriberInterface;
import htl.steyr.ereader.repository.CustomerRepository;
import htl.steyr.ereader.util.FxUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Component
public class CustomerCreateController implements Initializable, PublisherInterface {
  public TextField firstNameInput;
  public TextField lastNameInput;

  private SubscriberInterface subscriber = null;
  private final CustomerRepository customerRepository;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {}

  @Override
  public void addSubscriber(SubscriberInterface sub) {
    this.subscriber = sub;
  }

  public void saveClicked(ActionEvent actionEvent) {
    Customer customer = new Customer(
        firstNameInput.getText().trim(),
        lastNameInput.getText().trim()
    );
    this.customerRepository.save(customer);
    this.subscriber.triggerAction();
    FxUtilities.closeWindow(actionEvent);
  }

  public void cancelClicked(ActionEvent actionEvent) {
    FxUtilities.closeWindow(actionEvent);
  }
}
