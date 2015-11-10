
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by benjaminsmith on 9/23/15.
 */
public class PubController implements Initializable, Controller{
    private MuleModel muleModel;
//    private ArrayList<Point> gambleCoordinates = new ArrayList<>();
//    private ArrayList<Point> townCoordinates = new ArrayList<>();
//    private ArrayList<Point> mapCoordinates = new ArrayList<>();


//    @FXML
//    private Button gambleButton = new Button();

    //@FXML
    //private Label status = new Label();

    @Override  //PRESUMES ALL BUTTONS BE SQUARE! AND SAME SIZE!
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void gamble() {
        muleModel.gamble();
    }

    @FXML
    public void returnToMap() {
        muleModel.enterMap();
    }

    @FXML
    public void returnToTown(){
        muleModel.enterTown();
    }



    public void loadModel(MuleModel model) {
        muleModel = model;
    }


}
