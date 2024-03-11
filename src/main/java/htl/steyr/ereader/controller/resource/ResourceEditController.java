package htl.steyr.ereader.controller.resource;

import htl.steyr.ereader.model.*;
import htl.steyr.ereader.repository.CategoryRepository;
import htl.steyr.ereader.repository.ResourceRepository;
import htl.steyr.ereader.repository.TypeRepository;
import htl.steyr.ereader.util.FxUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Component
public class ResourceEditController implements Initializable, PublisherInterface {
  @FXML
  private ListView<Resource> editResourceList;
  @FXML
  private ComboBox<Category> categoryComboBox;
  @FXML
  private ComboBox<Type> typeComboBox;
  @FXML
  private TextField nameInput;
  @FXML
  private TextField dailyRateInput;

  private SubscriberInterface subscriber = null;
  private final ResourceRepository resourceRepository;
  private final CategoryRepository categoryRepository;
  private final TypeRepository typeRepository;

  public void saveClicked(ActionEvent actionEvent) {
    Resource r = editResourceList.getSelectionModel().getSelectedItem();
    if (r == null) {
      return;
    }

    if (
      nameInput.getText().trim().isEmpty() ||
        dailyRateInput.getText().trim().isEmpty() ||
        categoryComboBox.getSelectionModel().isEmpty() ||
        typeComboBox.getSelectionModel().isEmpty()
    ) {
      FxUtilities.createErrorWindow("All fields must be filled");
      return;
    }

    if (!dailyRateInput.getText().trim().matches("\\d+(\\.\\d+)?")) {
      FxUtilities.createErrorWindow("Daily rate must be a number");
      return;
    }

    if (resourceRepository.findByNameAndCategoryAndTypeAndDailyRate(
      nameInput.getText().trim(),
      categoryComboBox.getSelectionModel().getSelectedItem(),
      typeComboBox.getSelectionModel().getSelectedItem(),
      Double.parseDouble(dailyRateInput.getText().trim())
    ) != null) {
      FxUtilities.createErrorWindow("Resource with this name, category, type and daily rate already exists");
      return;
    }

    r.setName(nameInput.getText());
    r.setDailyRate(Double.parseDouble(dailyRateInput.getText()));
    r.setCategory(categoryComboBox.getSelectionModel().getSelectedItem());
    r.setType(typeComboBox.getSelectionModel().getSelectedItem());

    resourceRepository.save(r);
    subscriber.triggerAction();
    FxUtilities.closeWindow(actionEvent);
  }

  public void cancelClicked(ActionEvent actionEvent) {
    FxUtilities.closeWindow(actionEvent);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    editResourceList.getItems().addAll(resourceRepository.findAll());
  }

  @Override
  public void addSubscriber(SubscriberInterface sub) {
    this.subscriber = sub;
  }

  @Override
  public Object getData() {
    return null;
  }

  public void editResourceListClicked(MouseEvent mouseEvent) {
    Resource r = editResourceList.getSelectionModel().getSelectedItem();

    if (r != null) {
      nameInput.setText(r.getName());
      dailyRateInput.setText(String.valueOf(r.getDailyRate()));
      categoryComboBox.getItems().addAll(categoryRepository.findAll());
      categoryComboBox.getSelectionModel().select(r.getCategory());
      typeComboBox.getItems().addAll(typeRepository.findAll());
      typeComboBox.getSelectionModel().select(r.getType());
    }
  }
}
