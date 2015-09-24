import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import java.util.ResourceBundle;


/**
 * Created by benjaminsmith on 9/23/15.
 */
public class PubController implements Initializable, Controller{
    private MuleModel muleModel;
    private ArrayList<Point> gambleCoordinates = new ArrayList<>();
    private ArrayList<Point> townCoordinates = new ArrayList<>();
    private ArrayList<Point> mapCoordinates = new ArrayList<>();

    @FXML
    private Button gambleButton = new Button();

    @Override  //PRESUMES ALL BUTTONS BE SQUARE! AND SAME SIZE!
    public void initialize(URL url, ResourceBundle rb) {
        BackgroundSize size = new BackgroundSize(150, 150, false, false, false, false);//HARDCODED BUTTON SIZE?

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
