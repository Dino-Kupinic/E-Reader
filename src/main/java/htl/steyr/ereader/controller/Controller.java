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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
  public TableColumn<Borrow, String> borrowTableCustomer;
  public TableColumn<Borrow, Date> borrowTableStart;
  public TableColumn<Borrow, Date> borrowTableEnd;
  public TableColumn<Borrow, Boolean> borrowTableIsReturned;
  public TableView<Resource> resourceTable;
  public TableColumn<Resource, String> resourceTableResource;
  public TableColumn<Resource, Category> resourceTableCategory;
  public TableColumn<Resource, Type> resourceTableType;
  public ListView<Customer> returnBorrowList;

  private final BorrowRepository borrowRepository;
  private final CustomerRepository customerRepository;
  private final ResourceRepository resourceRepository;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    borrowTableId.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));
    borrowTableResource.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getResource().getName()));
    borrowTableCustomer.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomer().toString()));
    borrowTableStart.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStartDate()));
    borrowTableEnd.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getEndDate()));
    borrowTableIsReturned.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getIsReturned()));

    resourceTableResource.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
    resourceTableCategory.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCategory()));
    resourceTableType.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getType()));

    borrowTable.getItems().addAll(borrowRepository.findAll());
    returnBorrowList.getItems().addAll(customerRepository.findAll());
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
      clearDetails();
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
      clearDetails();
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
      clearDetails();
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
      clearDetails();
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

  public void borrowEditConfirmClicked(ActionEvent actionEvent) {
    Borrow b = borrowTable.getSelectionModel().getSelectedItem();
    if (b != null) {
      b.setCustomer(detailsCustomer.getValue());
      b.setResource(detailsResource.getValue());
      b.setStartDate(Date.from(detailsStart.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
      b.setEndDate(Date.from(detailsEnd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
      borrowRepository.save(b);
      borrowTable.getItems().clear();
      borrowTable.getItems().addAll(borrowRepository.findAll());
      clearDetails();
    }
  }

  public void borrowEditCancelClicked(ActionEvent actionEvent) {
    clearDetails();
  }

  private void clearDetails() {
    borrowTable.getSelectionModel().clearSelection();
    detailsCustomer.valueProperty().setValue(null);
    detailsResource.valueProperty().setValue(null);
    detailsCustomer.getItems().clear();
    detailsResource.getItems().clear();
    detailsStart.setValue(null);
    detailsEnd.setValue(null);
    detailsResourceName.clear();
    detailsResourceCategory.clear();
    detailsResourceType.clear();

    detailsCustomer.setDisable(true);
    detailsResource.setDisable(true);
    detailsStart.setDisable(true);
    detailsEnd.setDisable(true);
    detailsResourceName.setDisable(true);
    detailsResourceCategory.setDisable(true);
    detailsResourceType.setDisable(true);
  }

  public void borrowReturnClicked(ActionEvent actionEvent) {}

  public void calculateAvailableDates(ActionEvent actionEvent) {
    LocalDate startDate = borrowCreateStart.getValue();
    LocalDate endDate = borrowCreateEnd.getValue();

    if (startDate == null || endDate == null) {
      return;
    }

    List<Resource> availableResources = new ArrayList<>();
    List<Resource> allResources = resourceRepository.findAll();

    for (Resource resource : allResources) {
      boolean isAvailable = true;
      List<Borrow> borrows = borrowRepository.findByResource(resource);

      for (Borrow borrow : borrows) {
        LocalDate borrowStartDate = borrow.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate borrowEndDate = borrow.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if ((startDate.isAfter(borrowStartDate) && startDate.isBefore(borrowEndDate)) ||
          (endDate.isAfter(borrowStartDate) && endDate.isBefore(borrowEndDate)) ||
          (startDate.isBefore(borrowStartDate) && endDate.isAfter(borrowEndDate))) {
          isAvailable = false;
          break;
        }
      }

      if (isAvailable) {
        availableResources.add(resource);
      }
    }
    resourceTable.getItems().clear();
    resourceTable.getItems().addAll(availableResources);
  }

  public void handleBorrowRowSelected(MouseEvent mouseEvent) {
    Borrow b = borrowTable.getSelectionModel().getSelectedItem();
    if (b != null) {
      detailsCustomer.getItems().addAll(customerRepository.findAll());
      detailsResource.getItems().addAll(resourceRepository.findAll());
      detailsCustomer.getSelectionModel().select(b.getCustomer());
      detailsResource.getSelectionModel().select(b.getResource());

      LocalDate startDate = b.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      LocalDate endDate = b.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

      detailsStart.setValue(startDate);
      detailsEnd.setValue(endDate);

      detailsResourceName.setText(b.getResource().getName());
      detailsResourceCategory.setText(b.getResource().getCategory().getName());
      detailsResourceType.setText(b.getResource().getType().getName());

      detailsCustomer.setDisable(false);
      detailsResource.setDisable(false);
      detailsStart.setDisable(false);
      detailsEnd.setDisable(false);
      detailsResourceName.setDisable(false);
      detailsResourceCategory.setDisable(false);
      detailsResourceType.setDisable(false);
    }
  }

  public void handleCreateResourceClicked(MouseEvent mouseEvent) {
    if (mouseEvent.getClickCount() == 2) {
      Resource r = resourceTable.getSelectionModel().getSelectedItem();
      if (r != null) {
        detailsResource.setValue(r);
      }
    }
  }
}
