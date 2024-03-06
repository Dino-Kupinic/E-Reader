package htl.steyr.ereader.controller.type;

import htl.steyr.ereader.model.PublisherInterface;
import htl.steyr.ereader.model.SubscriberInterface;
import htl.steyr.ereader.model.Type;
import htl.steyr.ereader.repository.TypeRepository;
import htl.steyr.ereader.util.FxUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Component
public class TypeCreateController implements Initializable, PublisherInterface {
  public TextField nameInput;

  private SubscriberInterface subscriber = null;
  private final TypeRepository typeRepository;

  public void saveClicked(ActionEvent actionEvent) {
    Type type = new Type(
      nameInput.getText().trim()
    );
    this.typeRepository.save(type);
    this.subscriber.triggerAction();
    FxUtilities.closeWindow(actionEvent);
  }

  public void cancelClicked(ActionEvent actionEvent) {
    FxUtilities.closeWindow(actionEvent);
  }

  @Override
  public void addSubscriber(SubscriberInterface sub) {
    this.subscriber = sub;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {}
}
