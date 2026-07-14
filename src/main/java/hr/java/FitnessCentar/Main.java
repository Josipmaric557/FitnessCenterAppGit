package hr.java.FitnessCentar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
    public static void main(String[] args) throws Exception {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/hr/java/FitnessCenter/View/FitnessLoginForm.fxml")
        );

        Scene scene = new Scene(loader.load());


        stage.setTitle("Fitness Centar");
        stage.setScene(scene);
        stage.show();
    }


}
