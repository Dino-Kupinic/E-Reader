package htl.steyr.ereader.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum for the CRUD operations.
 *
 * @author Dino Kupinic
 */
@Getter
@RequiredArgsConstructor
public enum Operation {
  // Customer
  CREATE_CUSTOMER("customer/create.fxml"),
  EDIT_CUSTOMER("customer/edit.fxml"),
  REMOVE_CUSTOMER("customer/remove.fxml"),

  // Resource
  CREATE_RESOURCE("resource/create.fxml"),
  EDIT_RESOURCE("resource/edit.fxml"),
  REMOVE_RESOURCE("resource/remove.fxml"),

  // Category
  CREATE_CATEGORY("category/create.fxml"),
  EDIT_CATEGORY("category/edit.fxml"),
  REMOVE_CATEGORY("category/remove.fxml"),

  // Type
  CREATE_TYPE("type/create.fxml"),
  EDIT_TYPE("type/edit.fxml"),
  REMOVE_TYPE("type/remove.fxml"),

  // Borrow
  CREATE_BORROW("borrow/create.fxml"),
  RETURN_BORROW("borrow/return.fxml");

  /**
   * Filename of the FXML file.
   */
  private final String fileName;
}
