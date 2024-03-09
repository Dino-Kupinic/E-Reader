package htl.steyr.ereader.controller.resource;

import htl.steyr.ereader.model.*;
import htl.steyr.ereader.repository.CategoryRepository;
import htl.steyr.ereader.repository.CustomerRepository;
import htl.steyr.ereader.repository.ResourceRepository;
import htl.steyr.ereader.repository.TypeRepository;
import htl.steyr.ereader.util.FxUtilities;
import javafx.event.ActionEvent;
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
  public ListView<Resource> editResourceList;
  public ComboBox<Category> categoryComboBox;
  public ComboBox<Type> typeComboBox;
  public TextField nameInput;
  public TextField dailyRateInput;

  private SubscriberInterface subscriber = null;
  private final ResourceRepository resourceRepository;
  private final CategoryRepository categoryRepository;
  private final TypeRepository typeRepository;

  public void saveClicked(ActionEvent actionEvent) {
    Resource r = editResourceList.getSelectionModel().getSelectedItem();

    if (r != null) {
      r.setName(nameInput.getText());
      r.setDailyRate(Double.parseDouble(dailyRateInput.getText()));
      r.setCategory(categoryComboBox.getSelectionModel().getSelectedItem());
      r.setType(typeComboBox.getSelectionModel().getSelectedItem());

      resourceRepository.save(r);
      subscriber.triggerAction();
    }
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
