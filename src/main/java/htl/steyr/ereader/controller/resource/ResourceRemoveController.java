package htl.steyr.ereader.controller.resource;

import htl.steyr.ereader.model.PublisherInterface;
import htl.steyr.ereader.model.Resource;
import htl.steyr.ereader.model.SubscriberInterface;
import htl.steyr.ereader.repository.ResourceRepository;
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
public class ResourceRemoveController implements Initializable, PublisherInterface {
  @FXML
  private TextField selectedText;
  @FXML
  private ListView<Resource> deleteResourceList;
  @FXML
  private TextField selectedCategory;
  @FXML
  private TextField selectedType;

  private SubscriberInterface subscriber = null;
  private final ResourceRepository resourceRepository;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    deleteResourceList.getItems().addAll(resourceRepository.findAllByIsDeletedFalse());
  }

  @Override
  public void addSubscriber(SubscriberInterface sub) {
    this.subscriber = sub;
  }

  @Override
  public Object getData() {
    return null;
  }

  public void onDeleteClicked(ActionEvent actionEvent) {
    Resource r = deleteResourceList.getSelectionModel().getSelectedItem();
    if (r != null) {
      r.setIsDeleted(true);
      resourceRepository.save(r);
      subscriber.triggerAction();
    }
    FxUtilities.closeWindow(actionEvent);
  }

  public void onCancelClicked(ActionEvent actionEvent) {
    FxUtilities.closeWindow(actionEvent);
  }

  public void customerListViewClicked(MouseEvent event) {
    Resource r = deleteResourceList.getSelectionModel().getSelectedItem();
    if (r != null) {
      selectedText.setText(r.getName());
      selectedCategory.setText(r.getCategory().getName());
      selectedType.setText(r.getType().getName());
    }
  }
}
