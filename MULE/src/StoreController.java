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
import java.util.ResourceBundle;

/**
 * Created by Julianna on 9/25/2015.
 */
public class StoreController implements Initializable, Controller{
    private MuleModel muleModel;
    private ArrayList<Point> buyCoordinates = new ArrayList<>();
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

    @Override  //PRESUMES ALL BUTTONS BE SQUARE! AND SAME SIZE!
    public void initialize(URL url, ResourceBundle rb) {
        BackgroundSize size = new BackgroundSize(150, 150, false, false, false, false);//HARDCODED BUTTON SIZE?

    }

    @FXML
    private void returnToTown(ActionEvent event){
        muleModel.enterTown();
    }

    public void loadModel(MuleModel model) {
        muleModel = model;
    }


}

