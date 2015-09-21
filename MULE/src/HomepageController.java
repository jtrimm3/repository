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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Julianna
 */
public class HomepageController implements Initializable, Controller {
    private MuleModel muleModel;
    @FXML
    final private ToggleGroup group = new ToggleGroup();
    final private ToggleGroup group2 = new ToggleGroup();
    
    public int players;
    public int playerPageCount;
    
    public HomepageController() {
        this.players = players;
    }
    
    public int getPlayers() {
        return players;
    }

    private String selectedMap;
    private List<String> mapChoices;
    
    @FXML
    private RadioButton beginner = new RadioButton();
    
    @FXML
    private RadioButton standard = new RadioButton();
    
    @FXML
    private RadioButton tournament = new RadioButton();
    
    @FXML
    private RadioButton numPlay2 = new RadioButton();
    
    @FXML
    private RadioButton numPlay3 = new RadioButton();
    
    @FXML
    private RadioButton numPlay4 = new RadioButton();
   
    @FXML
    private ComboBox  map = new ComboBox();
    
    @FXML
    private Button contin = new Button();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        beginner.setToggleGroup(group);
        standard.setToggleGroup(group);
        tournament.setToggleGroup(group);
        numPlay2.setToggleGroup(group2);
        numPlay3.setToggleGroup(group2);
        numPlay4.setToggleGroup(group2);
        numPlay2.fire();
        players = 2;
        mapChoices = new ArrayList<>();
        mapChoices.add("Forrest");
        mapChoices.add("Space");
        mapChoices.add("Desert");
        mapChoices.add("Seaside");
        mapChoices.add("Farm");
        map.setValue("Choose a map!");
        map.setItems(FXCollections.observableArrayList(mapChoices));
    }    
    
    @FXML
    private void selectMap() {
        map.getSelectionModel().selectedIndexProperty().addListener(
            new ChangeListener<Number>() {
                public void changed(ObservableValue v, Number val, Number newVal) {
                    String sel = mapChoices.get(newVal.intValue());
                    selectedMap = sel;
                }
    });
    
    }
    
    @FXML
    private void selectPlayerNum()  {
        group2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                RadioButton check = (RadioButton)t1.getToggleGroup().getSelectedToggle();
                players = Integer.parseInt(check.getText());
            }
        });

    }
    
    @FXML
    private void next(ActionEvent event) throws IOException {
        muleModel.initializeConfigData(players);
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        muleModel.continuePlayerConfig();
    }

    public void loadModel(MuleModel model) {
        muleModel = model;
    }
}