package htl.steyr.ereader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFxApplication extends Application {
  private static final String APPLICATION_TITLE = "E-Reader";

  private static ConfigurableApplicationContext springContext;
  private Parent root;

  @Override
  public void init() throws Exception {
    String[] args = new String[0];

    springContext =
      new SpringApplicationBuilder().
        sources(EReaderApplication.class).
        run(args);

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene.fxml"));

    fxmlLoader.setControllerFactory(springContext::getBean);
    root = fxmlLoader.load();
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle(APPLICATION_TITLE);
    Scene scene = new Scene(root, 1280, 720);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static ConfigurableApplicationContext getSpringContext() {
    return springContext;
  }

  @Override
  public void stop() {
    springContext.stop();
  }
}