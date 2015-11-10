
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Peter on 9/18/2015.
 */
public class TownController implements Initializable, Controller {
    private MuleModel muleModel;
//    private ArrayList<Point> pubCoordinates = new ArrayList<>();
//    private ArrayList<Point> storeCoordinates = new ArrayList<>();
//    private ArrayList<Point> assayCoordinates = new ArrayList<>();
//    private ArrayList<Point> landCoordinates = new ArrayList<>();

    private static final String PUB_IMAGE = "pub.png";
    private static final String STORE_IMAGE = "store.png";
    private static final String ASSAY_IMAGE = "assay.png";
    private static final String LAND_IMAGE = "land.png";

//    @FXML
//    private GridPane townGridPane = new GridPane();

    @FXML
    private Button pubButton = new Button();

    @FXML
    private Button storeButton = new Button();

    @FXML
    private Button assayButton = new Button();

    @FXML
    private Button landButton = new Button();

    @Override  //PRESUMES ALL BUTTONS BE SQUARE! AND SAME SIZE!
    public void initialize(URL url, ResourceBundle rb) {
        BackgroundSize size = new BackgroundSize(150, 150, false, false, false, false);//HARDCODED BUTTON SIZE?
        BackgroundImage pubImage = new BackgroundImage(new Image(PUB_IMAGE),null,null,null,size);
        pubButton.setBackground(new Background(pubImage));
        BackgroundImage storeImage = new BackgroundImage(new Image(STORE_IMAGE),null,null,null,size);
        storeButton.setBackground(new Background(storeImage));
        BackgroundImage assayImage = new BackgroundImage(new Image(ASSAY_IMAGE),null,null,null,size);
        assayButton.setBackground(new Background(assayImage));
        BackgroundImage landImage = new BackgroundImage(new Image(LAND_IMAGE),null,null,null,size);
        landButton.setBackground(new Background(landImage));
    }

    @FXML
    public void returnToMap() {
        muleModel.enterMap();

    }

    @FXML
    public void enterPub(){
        muleModel.enterPub();
    }

    @FXML
    public void enterStore() {
        muleModel.enterStore();
    }

    @FXML
    public void enterLandOffice() {
        muleModel.enterLandOffice();
    }

    public void loadModel(MuleModel model) {
        muleModel = model;
    }
}
