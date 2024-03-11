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
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Component
public class ResourceCreateController implements Initializable, PublisherInterface {
  @FXML
  private TextField nameInput;
  @FXML
  private TextField dailyRate;
  @FXML
  private ComboBox<Type> typeCombobox;
  @FXML
  private ComboBox<Category> categoryCombobox;

  private SubscriberInterface subscriber = null;
  private final ResourceRepository resourceRepository;
  private final CategoryRepository categoryRepository;
  private final TypeRepository typeRepository;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    categoryCombobox.getItems().addAll(categoryRepository.findAll());
    typeCombobox.getItems().addAll(typeRepository.findAll());
  }

  @Override
  public void addSubscriber(SubscriberInterface sub) {
    this.subscriber = sub;
  }

  @Override
  public Object getData() {
    return null;
  }

  public void saveClicked(ActionEvent actionEvent) {
    String dailyRateText = dailyRate.getText().trim();
    if (!dailyRateText.matches("\\d+(\\.\\d+)?")) {
      FxUtilities.createErrorWindow("Daily rate must be a number");
      return;
    }

    if (
      nameInput.getText().trim().isEmpty() ||
      dailyRateText.isEmpty() ||
      categoryCombobox.getSelectionModel().isEmpty() ||
      typeCombobox.getSelectionModel().isEmpty()
    ) {
      FxUtilities.createErrorWindow("All fields must be filled");
      return;
    }

    Resource resource = new Resource(
      nameInput.getText().trim(),
      Double.parseDouble(dailyRateText),
      false,
      categoryCombobox.getValue(),
      typeCombobox.getValue()
    );
    this.resourceRepository.save(resource);
    this.subscriber.triggerAction();
    FxUtilities.closeWindow(actionEvent);
  }

  public void cancelClicked(ActionEvent actionEvent) {
    FxUtilities.closeWindow(actionEvent);
  }
}
