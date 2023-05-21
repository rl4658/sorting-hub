package se2203.assignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SortingHubApplication extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        //gets the file from fxml
        FXMLLoader fxmlLoader = new FXMLLoader(SortingHubApplication.class.getResource("SortingHub-view.fxml"));
        //loads the window screen
        Scene scene = new Scene(fxmlLoader.load());
        //sets the window title to the string below
        stage.setTitle("Sorting Hub");
        //sets the icon of window to western logo
        stage.getIcons().add(new Image("file:src/main/resources/se2203/assignment1/WesternLogo.png"));
        //sets the scene of the stage
        stage.setScene(scene);
        //shows the stage when ran
        stage.show();
    }

    public static void main(String[] args) {
        //runs the program
        launch();
    }
}