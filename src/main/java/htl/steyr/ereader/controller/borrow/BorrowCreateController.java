package htl.steyr.ereader.controller.borrow;

import htl.steyr.ereader.model.Customer;
import htl.steyr.ereader.model.PublisherInterface;
import htl.steyr.ereader.model.SubscriberInterface;
import htl.steyr.ereader.repository.CustomerRepository;
import htl.steyr.ereader.util.FxUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Component
public class BorrowCreateController implements Initializable, PublisherInterface {
  public ComboBox<Customer> customerCombobox;

  private SubscriberInterface subscriber = null;
  private final CustomerRepository customerRepository;

  @Override
  public void addSubscriber(SubscriberInterface sub) {
    this.subscriber = sub;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    customerCombobox.getItems().addAll(customerRepository.findAll());
  }

  public void handleCreateClicked(ActionEvent actionEvent) {
    if (customerCombobox.getValue() == null) {
      FxUtilities.createErrorWindow("Please select a customer");
      return;
    }
    this.subscriber.triggerAction();
    FxUtilities.closeWindow(actionEvent);
  }

  public Customer getData() {
    return customerCombobox.getValue();
  }

  public void handleCancelClicked(ActionEvent actionEvent) {
    FxUtilities.closeWindow(actionEvent);
  }
}
