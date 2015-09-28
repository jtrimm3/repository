import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;


/**
 * Created by benjaminsmith on 9/23/15.
 */
public class PubController implements Initializable, Controller{
    private MuleModel muleModel;
    private ArrayList<Point> gambleCoordinates = new ArrayList<>();
    private ArrayList<Point> townCoordinates = new ArrayList<>();
    private ArrayList<Point> mapCoordinates = new ArrayList<>();
    private int[] roundBonus = {50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};

    @FXML
    private Button gambleButton = new Button();

    @FXML
    private Label status = new Label();

    @Override  //PRESUMES ALL BUTTONS BE SQUARE! AND SAME SIZE!
    public void initialize(URL url, ResourceBundle rb) {
        BackgroundSize size = new BackgroundSize(150, 150, false, false, false, false);//HARDCODED BUTTON SIZE?

    }

    @FXML
    private void gamble(ActionEvent event) {
        int time = muleModel.secondsLeft;
        Random num = new Random();
        int moneyBonus = 0;
        if (time >= 37) {
            moneyBonus = roundBonus[muleModel.getRound() + 1] + num.nextInt(201);
        } else if (time >= 25 && time <= 36) {
            moneyBonus = roundBonus[muleModel.getRound() + 1] + num.nextInt(151);
        } else if (time >= 12 && time <= 24) {
            moneyBonus = roundBonus[muleModel.getRound() + 1] + num.nextInt(101);
        } else {
            moneyBonus = roundBonus[muleModel.getRound() + 1] + num.nextInt(51);
        }
        Player turningPlayer = muleModel.getTurningPlayer();
        turningPlayer.setMoney(turningPlayer.getMoney() + Math.min(moneyBonus, 250));
        //status.setText("Congratulations! You just earned " + Math.min(moneyBonus, 250) + " dollars!");
        muleModel.endTurn();
    }

    @FXML
    private void returnToMap(ActionEvent event) {
        muleModel.enterMap();
    }

    @FXML
    private void returnToTown(ActionEvent event){
        muleModel.enterTown();
    }



    public void loadModel(MuleModel model) {
        muleModel = model;
    }


}
