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
public class TypeRemoveController implements Initializable, PublisherInterface {
  @FXML
  private TextField selectedText;
  @FXML
  private ListView<Type> deleteTypeList;

  private SubscriberInterface subscriber = null;
  private final TypeRepository typeRepository;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    deleteTypeList.getItems().addAll(typeRepository.findAll());
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
    Type c = deleteTypeList.getSelectionModel().getSelectedItem();
    if (c != null) {
      typeRepository.delete(c);
      subscriber.triggerAction();
      FxUtilities.closeWindow(actionEvent);
    }
  }

  public void onCancelClicked(ActionEvent actionEvent) {
    FxUtilities.closeWindow(actionEvent);
  }

  public void typeListViewClicked(MouseEvent event) {
    Type c = deleteTypeList.getSelectionModel().getSelectedItem();
    if (c != null) {
      selectedText.setText(c.getName());
    }
  }
}
