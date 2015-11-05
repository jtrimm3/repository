import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Peter on 11/4/2015.
 */
public class SaveController implements Controller, Initializable {
    private MuleModel muleModel;
    private int timesPressed;
    private String lastNameUsed;
    private String top, oldm, newm;
    private Player oldp, newp;

    @FXML
    private TextField name = new TextField();

    @FXML
    private Text messageText = new Text();



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        messageText.setFill(Color.RED);
        messageText.setText(null);
        timesPressed = 0;
    }

    @FXML
    private void save(ActionEvent event) {
        timesPressed++;
        String fileName = name.getText();
        messageText.setText(null);
        if (lastNameUsed != null && !fileName.equals(lastNameUsed)) {
            timesPressed = 1;
            lastNameUsed = fileName;
        } else if (lastNameUsed == null) {
            lastNameUsed = fileName;
        }
        fileName = "m" + fileName + ".mul";
        List<File> existingFiles = muleModel.getSaveGames();
        ArrayList<String> existingFileNames = new ArrayList<>();
        for (File file : existingFiles) {
            existingFileNames.add(file.getName());
        }
        if (existingFileNames.contains(fileName)) {
            if (timesPressed == 2) {
                muleModel.saveGame(fileName);
            } else {
                messageText.setText("Game " + name.getText() +" already exists. Press again to overwrite.");
            }
        } else {
            if (timesPressed == 2) {
                muleModel.saveGame(fileName);
            } else {
                messageText.setText("Press again to confirm save of " + name.getText());
            }
        }
    }

    @FXML
    private void back(ActionEvent event) {
        muleModel.backFromSaveScreen(top, oldp, newp, oldm, newm);
    }

    @Override
    public void loadModel(MuleModel model) {
        muleModel = model;
    }

    public void loadEndTurnData(String top, Player oldp, Player newp, String oldm, String newm) {
        this.top = top;
        this.oldp = oldp;
        this.newp = newp;
        this.oldm = oldm;
        this.newm = newm;
    }
}
