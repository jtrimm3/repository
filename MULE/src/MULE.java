/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;

import com.guigarage.flatterfx.FlatterFX;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.AudioClip;
import java.io.File;
/**
 *
 * @author Julianna
 */
public class MULE extends Application {
    
        @Override
        public final void start(Stage stage) throws IOException {
            MuleModel muleModel = new MuleModel(stage);
            setUserAgentStylesheet(STYLESHEET_CASPIAN);
            String path = "./src/music.wav";
            Media media = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.onRepeatProperty();
            mediaPlayer.setCycleCount(AudioClip.INDEFINITE);
            MediaView mediaView = new MediaView(mediaPlayer);
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
