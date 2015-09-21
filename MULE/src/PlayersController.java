/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Julianna
 */
public class PlayersController implements Initializable, Controller {
    private MuleModel muleModel;
    private List<String> races;
    private String selectedRace;
    private Color selectedColor;
    
    @FXML
    private TextField name = new TextField();

    @FXML
    private Text errorTextArea = new Text();
    
    @FXML
    private ComboBox raceBox = new ComboBox();
    
    @FXML
    private ComboBox<String> colorBox = new ComboBox<>();
    
    @FXML
    private Button done = new Button();
    
    @FXML
    private Label playerNum = new Label();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorTextArea.setFill(Color.RED);
        colorBox.setItems(FXCollections.observableArrayList(muleModel.getAvailableColors()));
        colorBox.setValue(muleModel.getAvailableColors().get(0));
        validateName();
        races = new ArrayList<>();
        races.add("Human");
        races.add("Flapper");
        races.add("Bonzoid");
        races.add("Ugaite");
        races.add("Buzzite");
        selectedRace = null;
        selectedColor = null;
        raceBox.setValue(races.get(0));
        raceBox.setItems(FXCollections.observableArrayList(races));
        playerNum.setText("Create Player " + (muleModel.getPlayerHashMap().size() + 1));
        name.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validateName();
            }
        });
    } 

    
    @FXML
    private void complete(ActionEvent event) throws IOException {
        String playerName = (String) name.getCharacters().toString();
        String playerRace = (String) raceBox.getValue();
        String playerColorString = (String) colorBox.getValue();
        Color playerColor = muleModel.removeColor(playerColorString);
        Integer playerCount = muleModel.getPlayerHashMap().size();
        muleModel.addPlayer(new Player(playerName, playerCount + 1, playerRace, playerColor));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        muleModel.continuePlayerConfig();
        //Testing
        for (Integer key : muleModel.getPlayerHashMap().keySet()) {
            System.out.println(muleModel.getPlayerHashMap().get(key));
        }
    }

    private void validateName() {
        String currentEntry = name.getCharacters().toString();
        String validationMessage = muleModel.validateName(currentEntry);
        if (validationMessage.isEmpty()) {
            done.setDisable(false);
        } else {
            done.setDisable(true);
        }
        errorTextArea.setText(validationMessage);
    }

    public void loadModel(MuleModel model) {
        muleModel = model;
    }
}
