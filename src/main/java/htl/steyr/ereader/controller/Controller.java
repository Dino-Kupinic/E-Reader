package htl.steyr.ereader.controller;

import htl.steyr.ereader.JavaFxApplication;
import htl.steyr.ereader.model.Customer;
import htl.steyr.ereader.model.Operation;
import htl.steyr.ereader.model.PublisherInterface;
import htl.steyr.ereader.model.SubscriberInterface;
import htl.steyr.ereader.util.FxUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class Controller implements Initializable {

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }

  public void createOperationWindow(Operation operation, SubscriberInterface sub) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource(operation.getFileName()));
    loader.setControllerFactory(JavaFxApplication.getSpringContext()::getBean);
    Parent root = loader.load();

    PublisherInterface controller = loader.getController();
    if (sub != null) {
      controller.addSubscriber(sub);
    }

    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
  }

  public void manageCustomers(ActionEvent actionEvent) throws IOException {
    String menuItemText = FxUtilities.getMenuItemText(actionEvent);
    switch (menuItemText) {
      case "CREATE":
        createOperationWindow(Operation.CREATE_CUSTOMER, null);
        break;
      case "EDIT":
        createOperationWindow(Operation.EDIT_CUSTOMER, null);
        break;
      case "REMOVE":
        createOperationWindow(Operation.REMOVE_CUSTOMER, null);
        break;
    }
  }


}
