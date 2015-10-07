import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
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
    private String soldItem;
    private Map<String, Integer> buyItems = new HashMap<>();
    private ArrayList<Point> sellCoordinates = new ArrayList<>();
    private ArrayList<Point> townCoordinates = new ArrayList<>();


    @FXML
    private Button buyButton = new Button();

    @FXML
    private TextField buyAmount = new TextField();

    @FXML
    private TextField soldAmount = new TextField();

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
        ObservableList<Map.Entry<String, Integer>> itemsToSell = FXCollections.observableArrayList(muleModel.getTurningPlayer().getResources().entrySet());
        buyCombo.setItems(items);
        sellCombo.setItems(itemsToSell);
        validateBuy();
        validateSell();
        buyAmount.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validateBuy();
            }
        });
        soldAmount.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validateSell();
            }
        });
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
    private void selectSellItem() {
        sellCombo.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue v, Number val, Number newVal) {
                        ObservableList<Map.Entry<String, Integer>> items = FXCollections.observableArrayList(muleModel.getTurningPlayer().getResources().entrySet());
                        System.out.println(items);
                        soldItem = items.get(newVal.intValue()).getKey();
                        System.out.println(soldItem);
                    }
                });

    }


    @FXML
    private void confirmBuy(ActionEvent event) throws IOException {
        int amountBought = Integer.valueOf(buyAmount.getText());
        muleModel.buyResource(boughtItem, amountBought);
    }

    @FXML
    private void confirmSell(ActionEvent event) throws IOException {
        int amountSold = Integer.valueOf(soldAmount.getText());
        System.out.println("Sold Item: " + soldItem);
        muleModel.sellResource(soldItem, amountSold);
    }

    @FXML
    private void returnToTown(ActionEvent event) {
        muleModel.enterTown();
    }



    private void validateBuy() {
        String currentEntry = "";
        currentEntry = buyAmount.getCharacters().toString();
        int num = muleModel.toInteger(currentEntry);
        if (muleModel.validateBuySellName(currentEntry).isEmpty() && muleModel.enoughMoney(boughtItem, num) && boughtItem != null) {
            System.out.println("Selected: " + boughtItem);
            buyButton.setDisable(false);
        } else {
            buyButton.setDisable(true);
        }
    }



    private void validateSell() {
        String currentEntry = "";
        currentEntry = soldAmount.getCharacters().toString();
        int num = muleModel.toInteger(currentEntry);
        if (muleModel.validateBuySellName(currentEntry).isEmpty() && muleModel.enoughResources(soldItem, num) && soldItem != null) {
            sellButton.setDisable(false);
        } else {
            sellButton.setDisable(true);
        }
    }




    public void loadModel(MuleModel model) {
        muleModel = model;
    }


    }

