package htl.steyr.ereader.controller.category;

import htl.steyr.ereader.model.Category;
import htl.steyr.ereader.model.PublisherInterface;
import htl.steyr.ereader.model.SubscriberInterface;
import htl.steyr.ereader.repository.CategoryRepository;
import htl.steyr.ereader.util.FxUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Component
public class CategoryCreateController implements Initializable, PublisherInterface {
  @FXML
  private TextField nameInput;

  private SubscriberInterface subscriber = null;
  private final CategoryRepository categoryRepository;

  public void saveClicked(ActionEvent actionEvent) {
    if (categoryRepository.findByName(nameInput.getText().trim()) != null) {
      FxUtilities.createErrorWindow("Category with this name already exists");
      return;
    }
    Category category = new Category(
      nameInput.getText().trim()
    );
    this.categoryRepository.save(category);
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
  public Object getData() {
    return null;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {}
}
