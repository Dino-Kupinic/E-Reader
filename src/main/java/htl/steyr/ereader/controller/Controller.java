package htl.steyr.ereader.controller;

import htl.steyr.ereader.JavaFxApplication;
import htl.steyr.ereader.model.*;
import htl.steyr.ereader.repository.BorrowRepository;
import htl.steyr.ereader.repository.CustomerRepository;
import htl.steyr.ereader.repository.ResourceRepository;
import htl.steyr.ereader.util.FxUtilities;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Component
public class Controller implements Initializable {
  public ComboBox<Customer> detailsCustomer;
  public ComboBox<Resource> detailsResource;
  public DatePicker detailsStart;
  public DatePicker detailsEnd;
  public TextField detailsResourceName;
  public TextField detailsResourceCategory;
  public TextField detailsResourceType;
  public DatePicker borrowCreateStart;
  public DatePicker borrowCreateEnd;
  public TableView<Borrow> borrowTable;
  public TableColumn<Borrow, Long> borrowTableId;
  public TableColumn<Borrow, String> borrowTableResource;
  public TableColumn<Borrow, Date> borrowTableStart;
  public TableColumn<Borrow, Date> borrowTableEnd;
  public TableColumn<Borrow, Boolean> borrowTableIsReturned;
  public TableView<Resource> resourceTable;
  public TableColumn<Resource, String> resourceTableResource;
  public TableColumn<Resource, Category> resourceTableCategory;
  public TableColumn<Resource, Type> resourceTableType;

  private final BorrowRepository borrowRepository;
  private final CustomerRepository customerRepository;
  private final ResourceRepository resourceRepository;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    borrowTableId.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));

    borrowTableResource.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getResource().getName()));
    borrowTableStart.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStartDate()));
    borrowTableEnd.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getEndDate()));
    borrowTableIsReturned.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getIsReturned()));


    borrowTable.getItems().addAll(borrowRepository.findAll());
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
    String menuItemText = FxUtilities.getMenuItemText(actionEvent, true);
    SubscriberInterface sub = () -> {
      borrowTable.getItems().clear();
      borrowTable.getItems().addAll(borrowRepository.findAll());
      // TODO: clear details view
    };
    switch (menuItemText) {
      case "CREATE":
        createOperationWindow(Operation.CREATE_CUSTOMER, sub);
        break;
      case "EDIT":
        createOperationWindow(Operation.EDIT_CUSTOMER, sub);
        break;
      case "REMOVE":
        createOperationWindow(Operation.REMOVE_CUSTOMER, sub);
        break;
    }
  }

  public void manageResources(ActionEvent actionEvent) throws IOException {
    String menuItemText = FxUtilities.getMenuItemText(actionEvent, true);
    SubscriberInterface sub = () -> {
      borrowTable.getItems().clear();
      borrowTable.getItems().addAll(borrowRepository.findAll());
      // TODO: clear details view
    };
    switch (menuItemText) {
      case "CREATE":
        createOperationWindow(Operation.CREATE_RESOURCE, sub);
        break;
      case "EDIT":
        createOperationWindow(Operation.EDIT_RESOURCE, sub);
        break;
      case "REMOVE":
        createOperationWindow(Operation.REMOVE_RESOURCE, sub);
        break;
    }
  }

  public void manageCategories(ActionEvent actionEvent) throws IOException {
    String menuItemText = FxUtilities.getMenuItemText(actionEvent, true);
    SubscriberInterface sub = () -> {
      borrowTable.getItems().clear();
      borrowTable.getItems().addAll(borrowRepository.findAll());
      // TODO: clear details view
    };
    switch (menuItemText) {
      case "CREATE":
        createOperationWindow(Operation.CREATE_CATEGORY, sub);
        break;
      case "EDIT":
        createOperationWindow(Operation.EDIT_CATEGORY, sub);
        break;
      case "REMOVE":
        createOperationWindow(Operation.REMOVE_CATEGORY, sub);
        break;
    }
  }

  public void manageTypes(ActionEvent actionEvent) throws IOException {
    String menuItemText = FxUtilities.getMenuItemText(actionEvent, true);
    SubscriberInterface sub = () -> {
      borrowTable.getItems().clear();
      borrowTable.getItems().addAll(borrowRepository.findAll());
      // TODO: clear details view
    };
    switch (menuItemText) {
      case "CREATE":
        createOperationWindow(Operation.CREATE_TYPE, sub);
        break;
      case "EDIT":
        createOperationWindow(Operation.EDIT_TYPE, sub);
        break;
      case "REMOVE":
        createOperationWindow(Operation.REMOVE_TYPE, sub);
        break;
    }
  }

  public void borrowEditConfirmClicked(ActionEvent actionEvent) {}

  public void borrowEditCancelClicked(ActionEvent actionEvent) {}

  public void borrowReturnClicked(ActionEvent actionEvent) {}
}
