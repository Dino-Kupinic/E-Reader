package htl.steyr.ereader.controller.category;

import htl.steyr.ereader.model.Category;
import htl.steyr.ereader.model.PublisherInterface;
import htl.steyr.ereader.model.SubscriberInterface;
import htl.steyr.ereader.repository.CategoryRepository;
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
public class CategoryEditController implements Initializable, PublisherInterface {
  public ListView<Category> editCategoryList;
  public TextField nameInput;

  private SubscriberInterface subscriber = null;
  private final CategoryRepository categoryRepository;

  public void saveClicked(ActionEvent actionEvent) {
    Category c = editCategoryList.getSelectionModel().getSelectedItem();
    if (c != null) {
      if (c.getName().equals(nameInput.getText().trim())) {
        categoryRepository.save(c);
        subscriber.triggerAction();
        FxUtilities.closeWindow(actionEvent);
        return;
      }

      c.setName(nameInput.getText().trim());
      if (c.getName().isEmpty()) {
        FxUtilities.createErrorWindow("Category name cannot be empty");
        return;
      }


      if (categoryRepository.findByName(c.getName()) != null) {
        FxUtilities.createErrorWindow("Category with this name already exists");
        return;
      }

      categoryRepository.save(c);
      subscriber.triggerAction();
      FxUtilities.closeWindow(actionEvent);
    }
  }

  public void cancelClicked(ActionEvent actionEvent) {
    FxUtilities.closeWindow(actionEvent);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    editCategoryList.getItems().addAll(categoryRepository.findAll());
  }

  @Override
  public void addSubscriber(SubscriberInterface sub) {
    this.subscriber = sub;
  }

  @Override
  public Object getData() {
    return null;
  }

  public void categoryListViewClicked(MouseEvent mouseEvent) {
    Category c = editCategoryList.getSelectionModel().getSelectedItem();
    if (c != null) {
      nameInput.setText(c.getName());
    }
  }
}
