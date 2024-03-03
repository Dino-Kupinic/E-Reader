package htl.steyr.ereader.util;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;

/**
 * Utility class for JavaFX that contains utility methods.
 *
 * @author Dino Kupinic
 */
public class FxUtilities {
  /**
   * Get the MenuItem text in UpperCase. (default)
   *
   * @param actionEvent ActionEvent
   * @see #getMenuItemText(ActionEvent, boolean) specifying the case
   * @return menuItemText in UpperCase
   */
  public static String getMenuItemText(ActionEvent actionEvent) {
    return getMenuItemText(actionEvent, true);
  }

  /**
   * Get the MenuItem text.
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
}
