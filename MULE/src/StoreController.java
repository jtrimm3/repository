import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BackgroundSize;

import java.awt.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * Created by Julianna on 9/25/2015.
 */
public class StoreController implements Initializable, Controller{
    private MuleModel muleModel;
    private String boughtItem;
    private Map<String, Integer> buyItems = new HashMap<>();
    private ArrayList<Point> sellCoordinates = new ArrayList<>();
    private ArrayList<Point> townCoordinates = new ArrayList<>();


    @FXML
    private Button buyButton = new Button();

    @FXML
    private TextField buyAmount = new TextField();

    @FXML
    private Button sellButton = new Button();

    @FXML
    private ComboBox buyCombo = new ComboBox();

    @FXML
    private ComboBox sellCombo = new ComboBox();

    @FXML
    @Override  //PRESUMES ALL BUTTONS BE SQUARE! AND SAME SIZE!
    public void initialize(URL url, ResourceBundle rb) {
        buyCombo.setValue("Available Store Resources");
        sellCombo.setValue("Player's Available Resources");
        if (!muleModel.getLevel().equals("Beginner")) {
            muleModel.initializeBuyDataOther(buyItems);
        } else {
            muleModel.initializeBuyDataBeginner(buyItems);
        }
        ObservableList<Map.Entry<String, Integer>> items = FXCollections.observableArrayList(buyItems.entrySet());
        buyCombo.setItems(items);
    }

    @FXML
    private void selectBuyItem() {
        buyCombo.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue v, Number val, Number newVal) {
                        ObservableList<Map.Entry<String, Integer>> items = FXCollections.observableArrayList(buyItems.entrySet());
                        boughtItem = items.get(newVal.intValue()).getKey();
                        System.out.println(boughtItem);
                    }
                });

    }

    @FXML
    private void confirmBuy(ActionEvent event) throws IOException {
<<<<<<< HEAD
        amountEntered = Integer.valueOf(buyAmount.getText());
        if (!muleModel.getLevel().equals("Beginner")) {
            muleModel.initializeBuyDataOther(boughtItem, amountEntered, buyItems);
            if (boughtItem.equals("Food")) {
                foodOther = Math.max(foodOther - amountEntered, 0);
            } else if (boughtItem.equals("Energy")) {
                energyOther = Math.max(energyOther - amountEntered, 0);
            } else if (boughtItem.equals("Smithore")) {
                smithoreOther = Math.max(smithoreOther - amountEntered, 0);
            } else if (boughtItem.equals("Crystite")) {
                crystiteOther = Math.max(crystiteOther - amountEntered, 0);
            } else {
                muleOther = Math.max(muleOther - amountEntered, 0);
                muleModel.enterMuleConfig();
            }
        } else {
            muleModel.initializeBuyDataBeginner(boughtItem, amountEntered, buyItems);
            if (boughtItem.equals("Food")) {
                foodBeg = Math.max(foodBeg - amountEntered, 0);
            } else if (boughtItem.equals("Energy")) {
                energyBeg = Math.max(energyBeg - amountEntered, 0);
            } else if (boughtItem.equals("Smithore")) {
                smithoreBeg = Math.max(smithoreBeg - amountEntered, 0);
            } else if (boughtItem.equals("Crystite")) {
                crystiteBeg = Math.max(crystiteBeg - amountEntered, 0);
            } else {
                muleBeg = Math.max(muleBeg - amountEntered, 0);
                muleModel.enterMuleConfig();
            }
        }
        muleModel.buyResource();
=======
        int amountBought = Integer.valueOf(buyAmount.getText());
        muleModel.buyResource(boughtItem,amountBought);
>>>>>>> d5f045014bb1cc38e0c6004bbb5b894586ab4898
    }

    @FXML
    private void returnToTown(ActionEvent event) {
        muleModel.enterTown();
    }

    public void loadModel(MuleModel model) {
        muleModel = model;
    }


    }

