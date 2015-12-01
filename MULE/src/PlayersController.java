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
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Julianna
 */
public class PlayersController implements Initializable, Controller {
    private MuleModel muleModel;
    private static final int NORMAL_FOOD_STARTING_INV = 4;
    private static final int BEGINNER_FOOD_STARTING_INV = 8;
    private static final int NORMAL_ENERGY_STARTING_INV = 2;
    private static final int BEGINNER_ENERGY_STARTING_INV = 4;
    //private String selectedRace;
    //private Color selectedColor;
    
    @FXML
    private TextField name = new TextField();

    @FXML
    private TextField email = new TextField();

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
    public final void initialize(URL url, ResourceBundle rb) {
        errorTextArea.setFill(Color.RED);
        colorBox.setItems(FXCollections.observableArrayList(muleModel.getAvailableColors()));
        colorBox.setValue(muleModel.getAvailableColors().get(0));
        validateName();
        List<String> races = new ArrayList<>();
        races.add("Human");
        races.add("Flapper");
        races.add("Bonzoid");
        races.add("Ugaite");
        races.add("Buzzite");
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
    public final void complete(ActionEvent event) throws IOException {
        String playerName = name.getCharacters().toString();
        String emailString = email.getCharacters().toString();
        String playerRace = (String) raceBox.getValue();
        String playerColorString = colorBox.getValue();
        Color playerColor = muleModel.removeColor(playerColorString);
        Integer playerCount = muleModel.getPlayerList().size();
        Map<String, Integer> resources = new HashMap<>();
        if (!muleModel.getLevel().equals("Beginner")) {
            resources.put("Food", NORMAL_FOOD_STARTING_INV);
            resources.put("Energy", NORMAL_ENERGY_STARTING_INV);
        } else {
            resources.put("Food", BEGINNER_FOOD_STARTING_INV);
            resources.put("Energy", BEGINNER_ENERGY_STARTING_INV);
        }
        muleModel.addPlayer(new Player(playerName, emailString, playerCount + 1, playerRace, playerColor, resources));
//        Node node = (Node) event.getSource();
//        Stage stage = (Stage) node.getScene().getWindow();
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

    public final void loadModel(MuleModel model) {
        muleModel = model;
    }
}
