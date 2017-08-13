package quickquizquestions;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMain.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.getIcons().add(new Image("file:icon.png"));

        root.requestFocus(); //stop random button being focused
        stage.sizeToScene(); //get correct stage size
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}