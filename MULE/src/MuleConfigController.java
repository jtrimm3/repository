import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by benjaminsmith on 9/23/15.
 */
public class MuleConfigController implements Initializable, Controller{
    private MuleModel muleModel;
    private String type;

    @FXML
    private Text errorText = new Text();

    @FXML
    private final ToggleGroup group = new ToggleGroup();

    @FXML
    private Button ok = new Button();

    @FXML
    private RadioButton food = new RadioButton();

    @FXML
    private RadioButton energy = new RadioButton();

    @FXML
    private RadioButton ore = new RadioButton();




    @Override  //PRESUMES ALL BUTTONS BE SQUARE! AND SAME SIZE!
    public void initialize(URL url, ResourceBundle rb) {
        errorText.setFill(Color.RED);
        food.setToggleGroup(group);
        energy.setToggleGroup(group);
        ore.setToggleGroup(group);
        energy.fire();
        food.fire();
        RadioButton check = (RadioButton) group.getSelectedToggle();
        type = check.getText();
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                RadioButton check = (RadioButton) t1.getToggleGroup().getSelectedToggle();
                type = check.getText();
                System.out.println(type);
                String message = muleModel.muleConfigEnoughMoney(type, ok);
                errorText.setText(message);
            }
        });


    }

    @FXML
    public void toPlaceMule() {
        muleModel.enterMulePlacement(type);
    }







    public void loadModel(MuleModel model) {
        muleModel = model;
    }


}
