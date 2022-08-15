package JavaGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/* 
    This class is the driver for the program containging the program's main method
    and code for initializing and creating a JavaFX scene and window.
*/

public class WeightTrainingGUI extends Application {

    private FXML_WeightTrainingController controller;
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("FXML_WeightTraining.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.setTitle("Weight Training");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        
        controller.createOnCloseListener(stage);
    }


    public static void main(String[] args) {
        launch(args);
    }

}