package htl.steyr.ereader.util;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Utility class for JavaFX that contains utility methods.
 *
 * @author Dino Kupinic
 */
public class FxUtilities {
  /**
   * Get the MenuItem text.
   *
   * @param actionEvent ActionEvent
   * @return menuItemText in UpperCase
   * @see #getMenuItemText(ActionEvent, boolean) specifying the case
   */
  public static String getMenuItemText(ActionEvent actionEvent) {
    return getMenuItemText(actionEvent, false);
  }

  /**
   * Get the MenuItem text while providing if it should be returned UpperCase or not.
   *
   * @param actionEvent ActionEvent
   * @param inUpperCase boolean
   * @return menuItemText
   */
  public static String getMenuItemText(ActionEvent actionEvent, boolean inUpperCase) {
    MenuItem menuItem = (MenuItem) actionEvent.getSource();
    if (inUpperCase) {
      return menuItem.getText().toUpperCase();
    }
    return menuItem.getText();
  }

  /**
   * Close the window associated with the event.
   *
   * @param e ActionEvent
   */
  public static void closeWindow(ActionEvent e) {
    Button button = (Button) (e.getSource());
    Stage stage = (Stage) button.getScene().getWindow();
    stage.close();
  }
}
