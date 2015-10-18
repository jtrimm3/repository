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
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

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
    private HashMap<String, Integer> buyItems = new HashMap<>();
    private ArrayList<Point> sellCoordinates = new ArrayList<>();
    private ArrayList<Point> townCoordinates = new ArrayList<>();

    @FXML
    private Text errorBuy = new Text();

    @FXML
    private Text errorSell = new Text();

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
        errorBuy.setFill(Color.RED);
        errorSell.setFill(Color.RED);
        buyCombo.setValue("Available Store Resources");
        sellCombo.setValue("Player's Available Resources");
        muleModel.initializeBuyData(buyItems);

        ObservableList<Map.Entry<String, Integer>> items = FXCollections.observableArrayList(buyItems.entrySet());
        ObservableList<Map.Entry<String, Integer>> itemsToSell = FXCollections.observableArrayList(muleModel.getTurningPlayer().getResources().entrySet());
        buyCombo.setItems(items);
        buyCombo.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue v, Number val, Number newVal) {
                        ObservableList<Map.Entry<String, Integer>> items = FXCollections.observableArrayList(buyItems.entrySet());
                        boughtItem = items.get(newVal.intValue()).getKey();
                        buyCombo.setItems(items);
                        muleModel.validateBuy(buyButton, buyAmount.getCharacters().toString(), boughtItem, errorBuy);
                        System.out.println(boughtItem);
                    }
                });
        sellCombo.setItems(itemsToSell);
        sellCombo.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue v, Number val, Number newVal) {
                        ObservableList<Map.Entry<String, Integer>> items = FXCollections.observableArrayList(muleModel.getTurningPlayer().getResources().entrySet());
                        System.out.println(items);
                        soldItem = items.get(newVal.intValue()).getKey();
                        sellCombo.setItems(items);
                        muleModel.validateSell(sellButton, soldAmount.getCharacters().toString(), soldItem, errorSell);
                        System.out.println(soldItem);
                    }
                });
//        muleModel.validateBuy(buyButton, buyAmount.getCharacters().toString(), boughtItem, errorBuy);
//        muleModel.validateSell(sellButton, soldAmount.getCharacters().toString(), soldItem, errorSell);
        buyAmount.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                muleModel.validateBuy(buyButton, buyAmount.getCharacters().toString(), boughtItem, errorBuy);
            }
        });
        soldAmount.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                muleModel.validateSell(sellButton, soldAmount.getCharacters().toString(), soldItem, errorSell);
            }
        });
    }




    @FXML
    private void confirmBuy(ActionEvent event) throws IOException {
        int amountBought = Integer.valueOf(buyAmount.getText());
        muleModel.buyResource(boughtItem, amountBought);
//        ObservableList<Map.Entry<String, Integer>> items = FXCollections.observableArrayList(buyItems.entrySet());
//        buyCombo.setItems(items);
        if (boughtItem.equals("Mule")) {
            muleModel.enterMuleConfig();
        }

    }

    @FXML
    private void confirmSell(ActionEvent event) throws IOException {
        int amountSold = Integer.valueOf(soldAmount.getText());
        System.out.println("Sold Item: " + soldItem);
        muleModel.sellResource(soldItem, amountSold);
        ObservableList<Map.Entry<String, Integer>> itemsToSell = FXCollections.observableArrayList(muleModel.getTurningPlayer().getResources().entrySet());
        sellCombo.setItems(itemsToSell);
    }

    @FXML
    private void returnToTown(ActionEvent event) {
        muleModel.enterTown();
    }











    public void loadModel(MuleModel model) {
        muleModel = model;
    }


    }

