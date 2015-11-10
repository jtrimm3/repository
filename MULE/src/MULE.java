/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Julianna
 */
public class MULE extends Application {
    
        @Override
        public void start(Stage stage) throws IOException {
            MuleModel muleModel = new MuleModel(stage);
            muleModel.begin();
        }
        
        public static void main(String[] args) {
            System.out.println("[BEGIN main()]");

            System.out.println("+--------------------------------------+");
            System.out.println("|    MULE Portal    |");
            System.out.println("+--------------------------------------+");
            launch(args);
        }
    
}
