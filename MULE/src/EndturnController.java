import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Peter on 9/28/2015.
 */
public class EndturnController implements Controller, Initializable {
    private MuleModel muleModel;
    private String message;

    @FXML
    private Text text = new Text();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        text.setText(message);
    }

    public void setMessageText(String str) {
        message = str;
    }

    public void setPlayerList(ArrayList<Player> list) {

    }


    public void loadModel(MuleModel model) {
        muleModel = model;
    }


}
