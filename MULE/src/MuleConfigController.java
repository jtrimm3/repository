import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
public class MuleConfigController implements Initializable, Controller{
    private MuleModel muleModel;
    public String type;


    @FXML
    final private ToggleGroup group = new ToggleGroup();



    @FXML
    private Button muleConfig = new Button();

    @FXML
    private RadioButton food = new RadioButton();

    @FXML
    private RadioButton energy = new RadioButton();

    @FXML
    private RadioButton ore = new RadioButton();




    @Override  //PRESUMES ALL BUTTONS BE SQUARE! AND SAME SIZE!
    public void initialize(URL url, ResourceBundle rb) {
        food.setToggleGroup(group);
        energy.setToggleGroup(group);
        ore.setToggleGroup(group);
        food.fire();

    }

    @FXML
    private void toPlaceMule(ActionEvent event) {
        muleModel.enterMap();
    }

    @FXML
    private void selectType() {
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                RadioButton check = (RadioButton)t1.getToggleGroup().getSelectedToggle();
                type = check.getText();
                System.out.println(type);
            }
        });
    }





    public void loadModel(MuleModel model) {
        muleModel = model;
    }


}
