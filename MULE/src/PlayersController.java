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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Julianna
 */
public class PlayersController implements Initializable {
    private List<String> races;
    private String selectedRace;
    private Color selectedColor;
    private int players, pageCount;
    private HashMap<Integer, Player> playerHashMap;
    
    @FXML
    private TextField name = new TextField();
    
    @FXML
    private ComboBox raceBox = new ComboBox();
    
    @FXML
    private ColorPicker colorBox = new ColorPicker();
    
    @FXML
    private Button done = new Button();
    
    @FXML
    private Label playerNum = new Label();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        races = new ArrayList<>();
        races.add("Human");
        races.add("Flapper");
        races.add("Bonzoid");
        races.add("Ugaite");
        races.add("Buzzite");
        selectedRace = null;
        selectedColor = null;
        raceBox.setValue("Choose a race!");
        raceBox.setItems(FXCollections.observableArrayList(races));
    } 
    
    public void initData(int noOfPlayers, int count, HashMap<Integer, Player> map) {
        players = noOfPlayers;
        pageCount = count;
        playerHashMap = map;
        playerNum.setText("Create Player " + pageCount);
    }
    
    @FXML
    private void namePlayer(ActionEvent event) {
        String nameIn = name.getText();
    }
    
    @FXML
    private void selectRace(){
        raceBox.getSelectionModel().selectedIndexProperty().addListener(
            new ChangeListener<Number>() {
                public void changed(ObservableValue v, Number val, Number newVal) {
                    String sel = races.get(newVal.intValue());
                    selectedRace = sel;
                }
            });
    }
    
    
    @FXML
    private void selectColor() {
        colorBox.setOnAction(new EventHandler() {
            public void handle(Event t) {
                selectedColor = colorBox.getValue();               
            }
        });        
    }
    
    @FXML
    private void complete(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (pageCount < players) {
            Node node = (Node) event.getSource();
            stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Players.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);

           //STATIC  Parent root = FXMLLoader.load(getClass().getResource("Players.fxml"));
            String playerName = (String) name.getCharacters().toString();
            String playerRace = (String) raceBox.getValue();
            Color playerColor = (Color) colorBox.getValue();
            Player player = new Player(playerName, pageCount, playerRace, playerColor);
            playerHashMap.put(player.getPlayerNumber(), player);
            System.out.println(players);
            System.out.println(pageCount);
            PlayersController controller = loader.<PlayersController>getController();
            controller.initData(players, ++pageCount, playerHashMap);


            stage.show();
        } else {
            Node node = (Node) event.getSource();
            stage = (Stage) node.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Map.fxml"));
            //Println test for players

            Scene scene = new Scene(root);
            stage.setScene(scene);
            String playerName = (String) name.getCharacters().toString();
            String playerRace = (String) raceBox.getValue();
            Color playerColor = (Color) colorBox.getValue();
            Player player = new Player(playerName, pageCount, playerRace, playerColor);
            playerHashMap.put(player.getPlayerNumber(), player);

            stage.show();
            for (Integer key : playerHashMap.keySet()) {
                System.out.println(playerHashMap.get(key));
            }
        }
        
    }
    
}
