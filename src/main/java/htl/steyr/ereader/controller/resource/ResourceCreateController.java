package htl.steyr.ereader.controller.resource;

import htl.steyr.ereader.model.*;
import htl.steyr.ereader.repository.CategoryRepository;
import htl.steyr.ereader.repository.ResourceRepository;
import htl.steyr.ereader.repository.TypeRepository;
import htl.steyr.ereader.util.FxUtilities;
import javafx.event.ActionEvent;
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
  public TextField nameInput;
  public TextField dailyRate;
  public ComboBox<Category> categoryComboBox;
  public ComboBox<Type> typeCombobox;

  private SubscriberInterface subscriber = null;
  private final ResourceRepository resourceRepository;
  private final CategoryRepository categoryRepository;
  private final TypeRepository typeRepository;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    categoryComboBox.getItems().addAll(categoryRepository.findAll());
    typeCombobox.getItems().addAll(typeRepository.findAll());
  }

  @Override
  public void addSubscriber(SubscriberInterface sub) {
    this.subscriber = sub;
  }

  public void saveClicked(ActionEvent actionEvent) {
    Resource resource = new Resource(
      nameInput.getText().trim(),
      Double.parseDouble(dailyRate.getText().trim()),
      categoryComboBox.getValue(),
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
