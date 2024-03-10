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
import javafx.collections.ObservableList;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Consumer;

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
  public ListView<Borrow> returnBorrowOpenBorrows;
  public Label priceLabel;
  public Label discountLabel;

  private final BorrowRepository borrowRepository;
  private final CustomerRepository customerRepository;
  private final ResourceRepository resourceRepository;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    returnBorrowOpenBorrows.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
    stage.setResizable(false);
    stage.show();
  }

  public void createOperationWindow(Operation operation, SubscriberInterface sub, Consumer<Object> callback) throws IOException {
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
    stage.setOnHidden(e -> {
      Object data = controller.getData();
      callback.accept(data);
    });
    stage.setResizable(false);
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

  public void handleCreateBorrowClicked(MouseEvent mouseEvent) throws IOException {
    final int DOUBLE_CLICK = 2;
    if (mouseEvent.getClickCount() != DOUBLE_CLICK) {
      return;
    }

    Resource resource = resourceTable.getSelectionModel().getSelectedItem();
    if (resource == null)
      return;

    SubscriberInterface sub = () -> {
      borrowTable.getItems().clear();
      borrowTable.getItems().addAll(borrowRepository.findAll());
      clearDetails();
    };

    createOperationWindow(Operation.CREATE_BORROW, sub, data -> {
      if (data != null) {
        LocalDate newBorrowStart = borrowCreateStart.getValue();
        LocalDate newBorrowEnd = borrowCreateEnd.getValue();

        List<Borrow> borrows = borrowRepository.findByResource(resource);
        for (Borrow borrow : borrows) {
          LocalDate borrowStartDate = borrow.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
          LocalDate borrowEndDate = borrow.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
          if ((newBorrowStart.isAfter(borrowStartDate) && newBorrowStart.isBefore(borrowEndDate)) ||
            (newBorrowEnd.isAfter(borrowStartDate) && newBorrowEnd.isBefore(borrowEndDate)) ||
            (newBorrowStart.isBefore(borrowStartDate) && newBorrowEnd.isAfter(borrowEndDate))) {
            FxUtilities.createErrorWindow("Resource is already borrowed in the selected period");
            return;
          }
        }

        Borrow b = new Borrow(
          Date.from(newBorrowStart.atStartOfDay(ZoneId.systemDefault()).toInstant()),
          Date.from(newBorrowEnd.atStartOfDay(ZoneId.systemDefault()).toInstant()),
          false,
          (Customer) data,
          resource
        );
        borrowRepository.save(b);
        borrowTable.getItems().clear();
        borrowTable.getItems().addAll(borrowRepository.findAll());

        resourceTable.getItems().clear();
        borrowCreateStart.setValue(null);
        borrowCreateEnd.setValue(null);
        returnBorrowList.getItems().clear();
        returnBorrowList.getItems().addAll(customerRepository.findAll());
      }
    });
  }

  public void borrowEditConfirmClicked(ActionEvent actionEvent) {
    Borrow b = borrowTable.getSelectionModel().getSelectedItem();
    if (b != null) {
      b.setCustomer(detailsCustomer.getValue());
      b.setResource(detailsResource.getValue());

      if (detailsEnd.getValue().isBefore(detailsStart.getValue())) {
        FxUtilities.createErrorWindow("End date must be after start date");
        return;
      }

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

  public void borrowReturnClicked(ActionEvent actionEvent) {
    ObservableList<Borrow> selectedItems = returnBorrowOpenBorrows.getSelectionModel().getSelectedItems();
    for (Borrow b : selectedItems) {
      b.setIsReturned(true);
      borrowRepository.save(b);
    }
    returnBorrowOpenBorrows.getItems().clear();
    borrowTable.getItems().clear();
    borrowTable.getItems().addAll(borrowRepository.findAll());
  }

  public void calculateAvailableDates(ActionEvent actionEvent) {
    LocalDate startDate = borrowCreateStart.getValue();
    LocalDate endDate = borrowCreateEnd.getValue();

    if (startDate == null || endDate == null) {
      return;
    }

    if (endDate.isBefore(startDate)) {
      FxUtilities.createErrorWindow("End date must be after start date");
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

  public void handleResourceChange(ActionEvent actionEvent) {
    Resource r = detailsResource.getValue();
    if (r != null) {
      detailsResourceName.setText(r.getName());
      detailsResourceCategory.setText(r.getCategory().getName());
      detailsResourceType.setText(r.getType().getName());
    }
  }

  public void handleOpenBorrowsClicked(MouseEvent mouseEvent) {
    ObservableList<Borrow> selectedItems = returnBorrowOpenBorrows.getSelectionModel().getSelectedItems();

    if (selectedItems.isEmpty()) {
      return;
    }

    double discountedRate = 0;
    if (selectedItems.size() >= 5) {
      Set<LocalDate> uniqueDates = new HashSet<>();

      for (Borrow b : selectedItems) {
        LocalDate startDate = b.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = b.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        while (!startDate.isAfter(endDate)) {
          uniqueDates.add(startDate);
          startDate = startDate.plusDays(1);
        }
      }

      double totalDailyRate = 0;
      for (LocalDate date : uniqueDates) {
        for (Borrow b : selectedItems) {
          LocalDate borrowStartDate = b.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
          LocalDate borrowEndDate = b.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

          if (!date.isBefore(borrowStartDate) && !date.isAfter(borrowEndDate)) {
            totalDailyRate += b.getResource().getDailyRate();
          }
        }
      }

      discountedRate = 0.9 * totalDailyRate;
    }

    double sum = 0;
    for (Borrow b : selectedItems) {
      LocalDateTime startDateTime = LocalDateTime.ofInstant(b.getStartDate().toInstant(), ZoneId.systemDefault());
      if (startDateTime.isAfter(LocalDateTime.now())) {
        continue;
      }
      long daysBetween = ChronoUnit.DAYS.between(startDateTime, LocalDateTime.now());
      if (daysBetween == 0) {
        sum += b.getResource().getDailyRate();
        continue;
      }
      sum += daysBetween * b.getResource().getDailyRate();
      if (discountedRate != 0) {
        sum -= discountedRate;
        discountLabel.setVisible(true);
      }
    }
    priceLabel.setText(Math.abs(sum) + "€");
  }

  public void handleReturnBorrowListClicked(MouseEvent mouseEvent) {
    Customer c = returnBorrowList.getSelectionModel().getSelectedItem();
    if (c != null) {
      returnBorrowOpenBorrows.getItems().clear();
      returnBorrowOpenBorrows.getItems().addAll(borrowRepository.findByCustomerAndIsReturned(c, false));
    }
    priceLabel.setText("0.00€");
    discountLabel.setVisible(false);
  }
}
