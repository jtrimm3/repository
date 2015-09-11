/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg2340;

import java.io.IOException;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Julianna
 */
public class MULE extends Application {
    
        @Override
        public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("../resources/homepage.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
        
        public static void main(String[] args) {
            System.out.println("[BEGIN main()]");

            System.out.println("+--------------------------------------+");
            System.out.println("|    MULE Portal    |");
            System.out.println("+--------------------------------------+");
            launch(args);
        }
    
}
