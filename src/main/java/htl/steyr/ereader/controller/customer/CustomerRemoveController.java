package htl.steyr.ereader.controller.customer;

import htl.steyr.ereader.model.Customer;
import htl.steyr.ereader.model.PublisherInterface;
import htl.steyr.ereader.model.SubscriberInterface;
import htl.steyr.ereader.repository.CustomerRepository;
import htl.steyr.ereader.util.FxUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Component
public class CustomerRemoveController implements Initializable, PublisherInterface {
  @FXML
  private ListView<Customer> deleteCustomerList;
  @FXML
  private TextField selectedText;
  private SubscriberInterface subscriber = null;
  private final CustomerRepository customerRepository;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    deleteCustomerList.getItems().addAll(customerRepository.findAll());
  }

  @Override
  public void addSubscriber(SubscriberInterface sub) {
    this.subscriber = sub;
  }

  @Override
  public Object getData() {
    return null;
  }

  public void onDeleteClicked(ActionEvent actionEvent) {
    Customer c = deleteCustomerList.getSelectionModel().getSelectedItem();
    if (c != null) {
      customerRepository.delete(c);
      subscriber.triggerAction();
      FxUtilities.closeWindow(actionEvent);
    }
  }

  public void onCancelClicked(ActionEvent actionEvent) {
    FxUtilities.closeWindow(actionEvent);
  }

  public void customerListViewClicked(MouseEvent event) {
    Customer c = deleteCustomerList.getSelectionModel().getSelectedItem();
    if (c != null) {
      selectedText.setText(c.toString());
    }
  }
}
