/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
        playerNum.setText("Create Player " + (muleModel.getPlayerList().size() + 1));
        name.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validateName();
            }
        });
    } 

    
    @FXML
    public void complete(ActionEvent event) throws IOException {
        String playerName = name.getCharacters().toString();
        String playerRace = (String) raceBox.getValue();
        String playerColorString = colorBox.getValue();
        Color playerColor = muleModel.removeColor(playerColorString);
        Integer playerCount = muleModel.getPlayerList().size();
        Map<String, Integer> resources = new HashMap<>();
        if (!muleModel.getLevel().equals("Beginner")) {
            resources.put("Food", 4);
            resources.put("Energy", 2);
        } else {
            resources.put("Food", 8);
            resources.put("Energy", 4);
        }
        muleModel.addPlayer(new Player(playerName, playerCount + 1, playerRace, playerColor, resources));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        muleModel.continuePlayerConfig();
        //Testing
        for (Player p: muleModel.getPlayerList()) {
            System.out.println(p);
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
