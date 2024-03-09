package htl.steyr.ereader.controller.category;

import htl.steyr.ereader.model.Category;
import htl.steyr.ereader.model.Customer;
import htl.steyr.ereader.model.PublisherInterface;
import htl.steyr.ereader.model.SubscriberInterface;
import htl.steyr.ereader.repository.CategoryRepository;
import htl.steyr.ereader.repository.CustomerRepository;
import htl.steyr.ereader.util.FxUtilities;
import javafx.event.ActionEvent;
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
public class CategoryRemoveController implements Initializable, PublisherInterface {
  public TextField selectedText;
  public ListView<Category> deleteCategoryList;

  private SubscriberInterface subscriber = null;
  private final CategoryRepository categoryRepository;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    deleteCategoryList.getItems().addAll(categoryRepository.findAll());
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
    Category c = deleteCategoryList.getSelectionModel().getSelectedItem();
    if (c != null) {
      categoryRepository.delete(c);
      subscriber.triggerAction();
      FxUtilities.closeWindow(actionEvent);
    }
  }

  public void onCancelClicked(ActionEvent actionEvent) {
    FxUtilities.closeWindow(actionEvent);
  }

  public void customerListViewClicked(MouseEvent event) {
    Category c = deleteCategoryList.getSelectionModel().getSelectedItem();
    if (c != null) {
      selectedText.setText(c.getName());
    }
  }
}
