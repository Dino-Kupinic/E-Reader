package htl.steyr.ereader.controller.type;

import htl.steyr.ereader.model.PublisherInterface;
import htl.steyr.ereader.model.SubscriberInterface;
import htl.steyr.ereader.model.Type;
import htl.steyr.ereader.repository.TypeRepository;
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
public class TypeEditController implements Initializable, PublisherInterface {
  @FXML
  private ListView<Type> editTypeList;
  @FXML
  private TextField nameInput;

  private SubscriberInterface subscriber = null;
  private final TypeRepository typeRepository;

  public void saveClicked(ActionEvent actionEvent) {
    Type c = editTypeList.getSelectionModel().getSelectedItem();
    if (c == null) {
      return;
    }

    if (c.getName().equals(nameInput.getText().trim())) {
      typeRepository.save(c);
      subscriber.triggerAction();
      FxUtilities.closeWindow(actionEvent);
      return;
    }

    c.setName(nameInput.getText().trim());
    if (c.getName().isEmpty()) {
      FxUtilities.createErrorWindow("Type name cannot be empty");
      return;
    }

    if (typeRepository.findByName(c.getName()) != null) {
      FxUtilities.createErrorWindow("Type with this name already exists");
      return;
    }
    c.setName(nameInput.getText());
    typeRepository.save(c);
    subscriber.triggerAction();
    FxUtilities.closeWindow(actionEvent);
  }

  public void cancelClicked(ActionEvent actionEvent) {
    FxUtilities.closeWindow(actionEvent);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    editTypeList.getItems().addAll(typeRepository.findAll());
  }

  @Override
  public void addSubscriber(SubscriberInterface sub) {
    this.subscriber = sub;
  }

  @Override
  public Object getData() {
    return null;
  }

  public void typeListViewClicked(MouseEvent mouseEvent) {
    Type c = editTypeList.getSelectionModel().getSelectedItem();
    if (c != null) {
      nameInput.setText(c.getName());
    }
  }
}
