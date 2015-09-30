import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BackgroundSize;

import java.awt.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Julianna on 9/25/2015.
 */
public class StoreController implements Initializable, Controller{
    private MuleModel muleModel;
    int food;
    int energy;
    int smithore;
    int crystite;
    int mule;

    private Map<String,Integer> buyItems = new HashMap<>();
    //private String[] buyItems = {"Food", "Energy", "Smithore", "Crystite", "Mule"};
    private ArrayList<Point> sellCoordinates = new ArrayList<>();
    private ArrayList<Point> townCoordinates = new ArrayList<>();

    @FXML
    private Button buyButton = new Button();

    @FXML
    private Button sellButton = new Button();

    @FXML
    private ComboBox buyCombo = new ComboBox();

    @FXML
    private ComboBox sellCombo = new ComboBox();

    @FXML
    @Override  //PRESUMES ALL BUTTONS BE SQUARE! AND SAME SIZE!
    public void initialize(URL url, ResourceBundle rb) {
        BackgroundSize size = new BackgroundSize(150, 150, false, false, false, false);//HARDCODED BUTTON SIZE?
        buyCombo.setValue("Available Store Resources");
        sellCombo.setValue("Player's Available Resources");
        if (muleModel.getLevel() != "Beginner") {
            buyItems.put("Food",food = 8);
            buyItems.put("Energy",energy = 8);
            buyItems.put("Smithore",smithore = 8);
            buyItems.put("Crystite",crystite = 0);
            buyItems.put("Mule",mule = 14);
        } else {
            buyItems.put("Food",food = 16);
            buyItems.put("Energy",energy = 16);
            buyItems.put("Smithore",smithore = 0);
            buyItems.put("Crystite",crystite = 0);
            buyItems.put("Mule",mule = 25);
        }
        //buyCombo.setItems(FXCollections.observableMap(buyItems));
    }

    @FXML
    private void buy(ActionEvent event) {
        muleModel.buy();
    }

    @FXML
    private void returnToTown(ActionEvent event) {
        muleModel.enterTown();
    }

    public void loadModel(MuleModel model) {
        muleModel = model;
    }


}

