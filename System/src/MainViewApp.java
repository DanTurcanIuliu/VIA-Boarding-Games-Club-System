
 import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

  public class MainViewApp extends Application
  {

    @Override public void start(Stage primaryStage) throws Exception
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("MyFXML.fxml"));
      // Parent root = loader.load();
      Scene scene = new Scene(loader.load());
      primaryStage.setScene(scene);
      primaryStage.show();
    }
  }


