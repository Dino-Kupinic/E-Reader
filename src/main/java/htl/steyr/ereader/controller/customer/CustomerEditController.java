package htl.steyr.ereader.controller.customer;

import htl.steyr.ereader.model.Customer;
import htl.steyr.ereader.model.PublisherInterface;
import htl.steyr.ereader.model.SubscriberInterface;
import htl.steyr.ereader.repository.CustomerRepository;
import htl.steyr.ereader.util.FxUtilities;
import javafx.event.ActionEvent;
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
public class CustomerEditController implements Initializable, PublisherInterface {
  public TextField firstNameInput;
  public TextField lastNameInput;
  public ListView<Customer> editCustomerList;

  private SubscriberInterface subscriber = null;
  private final CustomerRepository customerRepository;

  public void saveClicked(ActionEvent actionEvent) {
    Customer c = editCustomerList.getSelectionModel().getSelectedItem();
    if (c != null) {
      if (c.getFirstName().equals(firstNameInput.getText().trim()) && c.getLastName().equals(lastNameInput.getText().trim())) {
        customerRepository.save(c);
        subscriber.triggerAction();
        FxUtilities.closeWindow(actionEvent);
        return;
      }

      if (checkNames(firstNameInput, lastNameInput, customerRepository)) return;

      c.setFirstName(firstNameInput.getText());
      c.setLastName(lastNameInput.getText());
      customerRepository.save(c);
      subscriber.triggerAction();
      FxUtilities.closeWindow(actionEvent);
    }
  }

  static boolean checkNames(TextField firstNameInput, TextField lastNameInput, CustomerRepository customerRepository) {
    if (firstNameInput.getText().trim().isEmpty() || lastNameInput.getText().trim().isEmpty()) {
      FxUtilities.createErrorWindow("First and last name cannot be empty");
      return true;
    }

    // name cannot contain characters other than letters
    if (!firstNameInput.getText().trim().matches("[a-zA-Z]+") || !lastNameInput.getText().trim().matches("[a-zA-Z]+")) {
      FxUtilities.createErrorWindow("First and last name can only contain letters");
      return true;
    }

    if (customerRepository.findByFirstNameAndLastName(
      firstNameInput.getText().trim(),
      lastNameInput.getText().trim()
    ) != null) {
      FxUtilities.createErrorWindow("Customer with this name already exists");
      return true;
    }
    return false;
  }

  public void cancelClicked(ActionEvent actionEvent) {
    FxUtilities.closeWindow(actionEvent);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    editCustomerList.getItems().addAll(customerRepository.findAll());
  }

  @Override
  public void addSubscriber(SubscriberInterface sub) {
    this.subscriber = sub;
  }

  @Override
  public Object getData() {
    return null;
  }

  public void customerListViewClicked(MouseEvent mouseEvent) {
    Customer c = editCustomerList.getSelectionModel().getSelectedItem();
    if (c != null) {
      firstNameInput.setText(c.getFirstName());
      lastNameInput.setText(c.getLastName());
    }
  }
}
