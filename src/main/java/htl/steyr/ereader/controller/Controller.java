package htl.steyr.ereader.controller;

import htl.steyr.ereader.JavaFxApplication;
import htl.steyr.ereader.model.*;
import htl.steyr.ereader.util.FxUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

@Component
public class Controller implements Initializable {
  public ComboBox<Customer> detailsCustomer;
  public ComboBox<Resource> detailsResource;
  public DatePicker detailsStart;
  public DatePicker detailsEnd;
  public TextField detailsResourceName;
  public TextField detailsResourceCategory;
  public TextField detailsResourceType;
  public ComboBox<Customer> borrowCreateCustomer;
  public ComboBox<Resource> borrowCreateResource;
  public DatePicker borrowCreateStart;
  public DatePicker borrowCreateEnd;
  public Button borrowEditConfirm;
  public Button borrowEditCancel;
  public Button borrowCreateButton;
  public Button borrowCreateCancel;
  public Button borrowReturn;
  public TableView<Borrow> borrowTable;
  public TableColumn<Borrow, Long> borrowTableId;
  public TableColumn<Borrow, String> borrowTableResource;
  public TableColumn<Borrow, Date> borrowTableStart;
  public TableColumn<Borrow, Date> borrowTableEnd;
  public TableColumn<Borrow, Boolean> borrowTableIsReturned;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }

  public void createOperationWindow(Operation operation, SubscriberInterface sub) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource(operation.getFileName()));
    loader.setControllerFactory(JavaFxApplication.getSpringContext()::getBean);
    Parent root = loader.load();

    PublisherInterface controller = loader.getController();
    if (sub != null) {
      controller.addSubscriber(sub);
    }

    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
  }

  public void manageCustomers(ActionEvent actionEvent) throws IOException {
    String menuItemText = FxUtilities.getMenuItemText(actionEvent);
    switch (menuItemText) {
      case "CREATE":
        createOperationWindow(Operation.CREATE_CUSTOMER, null);
        break;
      case "EDIT":
        createOperationWindow(Operation.EDIT_CUSTOMER, null);
        break;
      case "REMOVE":
        createOperationWindow(Operation.REMOVE_CUSTOMER, null);
        break;
    }
  }


}
