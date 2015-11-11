
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.text.Text;

import java.net.URL;

import java.util.ResourceBundle;

/**
 * Created by Peter on 9/28/2015.
 */
public class EndturnController implements Controller, Initializable {
    private MuleModel muleModel;
    private String topMessage, oldMessage, newMessgae;
    private Player oldPlayer,newPlayer;

    @FXML
    private Text text = new Text();

    @FXML
    private Text pOverText = new Text();

    @FXML
    private Text pNextText = new Text();

    @FXML
    private Text overNameText = new Text();

    @FXML
    private Text nextNameText = new Text();

    @FXML
    private Text overMoneyText = new Text();

    @FXML
    private Text nextMoneyText = new Text();

    @FXML
    private Text overMessageText = new Text();

    @FXML
    private Text nextMessageText = new Text();

    //@FXML
    //private Button saveButton = new Button();



    @Override
    public final void initialize(URL url, ResourceBundle rb) {
        text.setText(topMessage);
        pOverText.setText("Last Player");
        pNextText.setText("Next Player");
        overNameText.setText(oldPlayer.getName());
        nextNameText.setText(newPlayer.getName());
        overMoneyText.setText("$" + oldPlayer.getMoney());
        nextMoneyText.setText("$" + newPlayer.getMoney());
        overMessageText.setText(oldMessage);
        nextMessageText.setText(newMessgae);
    }

    @FXML
    public final void saveButtonPress() {
        muleModel.enterSaveScreen(topMessage,oldPlayer,newPlayer,oldMessage,newMessgae);

    }

    public final void setTopMessageText(String str) {
        topMessage = str;
    }

    public final void setNewPlayer(Player turningPlayer, String message) {
        newMessgae = message;
        newPlayer = turningPlayer;
    }

    public final void setOldPlayer(Player old, String message) {
        oldMessage = message;
        this.oldPlayer = old;
    }


    public final void loadModel(MuleModel model) {
        muleModel = model;
    }



}
