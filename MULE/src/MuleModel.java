import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Peter on 9/19/2015.
 */
public class MuleModel {
    private int round;
    private boolean hasBegun = false;
    private ArrayList<String> availableColors;
    private int lastRoundNum;
    private int passedThisRoundCount;
    private int turnCount;
    private Player turningPlayer;
    private int numberOfPlayers;
    private int boughtOnThisTurnCount;
    private String level;
    private String map;
    private Map<String, Integer> itemsForSaleBeginner;
    private Map<String, Integer> itemsForSaleOther;
    private static int foodBeg = 16;
    private static int foodOther = 8;
    private static int energyBeg = 16;
    private static int energyOther = 8;
    private static int smithoreBeg =0;
    private static int smithoreOther = 8;
    private static int crystiteBeg = 0;
    private static int crystiteOther = 0;
    private static int muleBeg = 25;
    private static int muleOther = 14;
    private static double foodPrice = 30;
    private static double energyPrice = 25;
    private static double smithorePrice = 50;
    private static double crystitePrice = 100;
    private static double mulePrice = 100;
    private double foodSellPrice = 15;
    private double energySellPrice = 18;
    private double smithoreSellPrice = 25;
    private double crystiteSellPrice = 50;
    private double muleSellPrice = 50;
    private Map<String, Double> resourceSellPrices = new HashMap<>();
    private Map<String, Double> resourceBuyPrices = new HashMap<>();
    private Map<String, Integer> resources = new HashMap<>();

    private ArrayList<Player> playerList;

    private ArrayList<CoolMule> redMules = new ArrayList<>();
    private ArrayList<CoolMule> greenMules = new ArrayList<>();
    private ArrayList<CoolMule> blueMules= new ArrayList<>();
    private ArrayList<CoolMule> yellowMules = new ArrayList<>();


    private Stage stage;
    private Timer timer;
    private int secondsLeft;
    private Text timerText;

    private ArrayList<Point> riverCoordinates = new ArrayList<>();
    private ArrayList<Point> townCoordinates = new ArrayList<>();
    private ArrayList<Point> plainCoordinates = new ArrayList<>();
    private ArrayList<Point> mountain1Coordinates = new ArrayList<>();
    private ArrayList<Point> mountain2Coordinates = new ArrayList<>();
    private ArrayList<Point> mountain3Coordinates = new ArrayList<>();

    private final String TOWN_IMAGE = "town.png";
    private final String RIVER_IMAGE = "river.png";
    private final String PLAIN_IMAGE = "plain.png";
    private final String M1_IMAGE = "mountain1.png";
    private final String M2_IMAGE = "mountain2.png";
    private final String M3_IMAGE = "mountain3.png";
    private final String SQUARE_IMAGE = "TransparentSquare.png";

    private int[] roundBonus;
    private Player lastPlayer;

    public MuleModel(Stage stage) {
        this.stage = stage;
        timer = new java.util.Timer();
        roundBonus = new int[] {0, 50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};
        resourceBuyPrices.put("Food", foodPrice);
        resourceBuyPrices.put("Energy", energyPrice);
        resourceBuyPrices.put("Smithore", smithorePrice);
        resourceBuyPrices.put("Crystite", crystitePrice);
        resourceBuyPrices.put("Mule", mulePrice);
        resourceSellPrices.put("Food", foodSellPrice);
        resourceSellPrices.put("Energy", energySellPrice);
        resourceSellPrices.put("Smithore", smithoreSellPrice);
        resourceSellPrices.put("Crystite", crystiteSellPrice);
        resourceSellPrices.put("Mule", muleSellPrice);
        availableColors = new ArrayList<String>(Arrays.asList("Red","Green","Blue","Yellow"));
        riverCoordinates.add(new Point(4,0));
        riverCoordinates.add(new Point(4,1));
        riverCoordinates.add(new Point(4,3));
        riverCoordinates.add(new Point(4,4));

        townCoordinates.add(new Point(4,2));

        plainCoordinates.add(new Point(0,0));
        plainCoordinates.add(new Point(1,0));
        plainCoordinates.add(new Point(3,0));
        plainCoordinates.add(new Point(5,0));
        plainCoordinates.add(new Point(7,0));
        plainCoordinates.add(new Point(8,0));
        plainCoordinates.add(new Point(0,1));
        plainCoordinates.add(new Point(2,1));
        plainCoordinates.add(new Point(3,1));
        plainCoordinates.add(new Point(5,1));
        plainCoordinates.add(new Point(6,1));
        plainCoordinates.add(new Point(7,1));
        plainCoordinates.add(new Point(1,2));
        plainCoordinates.add(new Point(2,2));
        plainCoordinates.add(new Point(3,2));
        plainCoordinates.add(new Point(5,2));
        plainCoordinates.add(new Point(6,2));
        plainCoordinates.add(new Point(7,2));
        plainCoordinates.add(new Point(0,3));
        plainCoordinates.add(new Point(2,3));
        plainCoordinates.add(new Point(3,3));
        plainCoordinates.add(new Point(5,3));
        plainCoordinates.add(new Point(7,3));
        plainCoordinates.add(new Point(8,3));
        plainCoordinates.add(new Point(0,4));
        plainCoordinates.add(new Point(1,4));
        plainCoordinates.add(new Point(3,4));
        plainCoordinates.add(new Point(5,4));
        plainCoordinates.add(new Point(6,4));
        plainCoordinates.add(new Point(7,4));

        mountain1Coordinates.add(new Point(2,0));
        mountain1Coordinates.add(new Point(1,1));
        mountain1Coordinates.add(new Point(8,2));

        mountain2Coordinates.add(new Point(1,3));
        mountain2Coordinates.add(new Point(6,3));
        mountain2Coordinates.add(new Point(2,4));
        mountain2Coordinates.add(new Point(8,4));

        mountain3Coordinates.add(new Point(6,0));
        mountain3Coordinates.add(new Point(8,1));
        mountain3Coordinates.add(new Point(0, 2));
    }

    public void initializeTimer() {
        secondsLeft = calculateTimeForTurn(getTurningPlayer());
        timer.cancel();
        timer.purge();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if(secondsLeft < 1) {
                        enterEndTurnScreen("Out of Time!");
                    } else {
                        secondsLeft--;
                        timerText.setText("" + secondsLeft);
                    }
                });
            }
        },0,1000);//delay,period (millis)

    }

    public void initializeBuyData(HashMap<String, Integer> buyItems) {
        if (!getLevel().equals("Beginner")) {
            initializeBuyDataOther(buyItems);
        } else {
            initializeBuyDataBeginner(buyItems);
        }
    }



    public int calculateTimeForTurn(Player p) {
        return 500;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }

//    public void startTimer() {
//        timer.start();
//    }

    public void initializeConfigData(int numberOfPlayers, String level) {
        this.numberOfPlayers = numberOfPlayers;
        this.playerList = new ArrayList<>();
        this.level = level;
        //this.playerHashMap = new HashMap<Integer,Player>();
    }

    public void initializeBuyDataBeginner(Map itemsForSaleBeginner) {
        this.itemsForSaleBeginner = itemsForSaleBeginner;
        itemsForSaleBeginner.put("Food",foodBeg);
        itemsForSaleBeginner.put("Energy",energyBeg);
        itemsForSaleBeginner.put("Smithore",smithoreBeg);
        itemsForSaleBeginner.put("Crystite",crystiteBeg);
        itemsForSaleBeginner.put("Mule", muleBeg);
    }

    public Map<String, Integer> getItemsForSaleBeginner() {
        return itemsForSaleBeginner;
    }

    public void initializeBuyDataOther(Map itemsForSaleOther) {
        this.itemsForSaleOther = itemsForSaleOther;
        itemsForSaleOther.put("Food", foodOther);
        itemsForSaleOther.put("Energy",energyOther);
        itemsForSaleOther.put("Smithore",smithoreOther);
        itemsForSaleOther.put("Crystite",crystiteOther);
        itemsForSaleOther.put("Mule",muleOther);
    }

    public Map<String, Integer> getItemsForSaleOther() {
        return itemsForSaleOther;
    }

    public void initializeSellData(Map resources) {
        this.resources = resources;
    }

    public String validateName(String name) {
        boolean allSpaces = false;
        boolean nameTaken = false;
        boolean nameEmpty = false;
        if (name.isEmpty()) {
            nameEmpty = true;
        }
        if (name.trim().length() == 0) {
            allSpaces = true;
        }
        for (Player playerChecking : playerList) {
            if (name.equalsIgnoreCase(playerChecking.getName())) {
                nameTaken = true;
                break;
            }
        }
        if (nameTaken) {
            return name + " is taken already";
        } else if (nameEmpty) {
            return "Please choose a name";
        } else if (allSpaces) {
            return "Name can't be all spaces!";
        } else {
            return "";
        }
    }

    public String validateBuySellName(String quantity){

        if(!isNumeric(quantity)) {
            return "Not a valid number";
        } else if (quantity.isEmpty()){
            return "Please select a value!";
        } else {
            return "";
        }
    }
    public boolean enoughMoney(String boughtResource, int boughtAmount) {
        double price = resourceBuyPrices.get(boughtResource);
        double totalPrice = price * boughtAmount;
        double playerMoney = getTurningPlayer().getMoney();
        return playerMoney >= totalPrice;
    }

    public boolean enoughResources(String soldResource, int soldAmount) {
        if( (int) getTurningPlayer().getResources().get(soldResource) >= soldAmount) {
            return true;
        }
        return false;
    }
    private int toInteger(String str) {
        if(isNumeric(str)) {
            return Integer.parseInt(str);
        }
        return 0;
    }

    public static boolean isNumeric(String str) {
        try {
            int d = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void continuePlayerConfig() {
        if (playerList.size() < numberOfPlayers) { //Return to playerconfig
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Players.fxml"));
                Controller controller = new PlayersController();
                controller.loadModel(this);
                loader.setController(controller);
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                System.out.println(numberOfPlayers);
                System.out.println(playerList.size());
                //stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {//go to map
            enterMap();
        }

    }


    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public String getLevel() {
        return level;
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

//    public HashMap<Integer,Player> getPlayerHashMap() {
//        return playerHashMap;
//    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

//    public Player getPlayerByPlayerNumber(int num) {
//        return playerHashMap.get(num);
//    }

    public void setMap(String map) {
        this.map = map;
    }

    public Stage getStage() {
        return stage;
    }





    public void begin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homepage.fxml"));
            HomepageController controller = new HomepageController();
            controller.loadModel(this);
            loader.setController(controller);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enterMap() {
        if (!hasBegun) {
            initializeTimer();
            startNextTurn();
            hasBegun = true;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Map.fxml"));
            Controller controller = new MapController();
            controller.loadModel(this);
            loader.setController(controller);
            Scene scene = new Scene(loader.load());
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {  //ENTER ENDS TURN
                @Override
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case ENTER:
                            enterEndTurnScreen("Turn ended without gambling!");
                    }
                }
            });
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enterEndTurnScreen(String oldPlayerMessage) {
        timer.cancel();
        timer.purge();
        try {
            Player oldPlayer = getTurningPlayer();
            String roundMessage = endTurn();
            startNextTurn();
            if (passedThisRoundCount == numberOfPlayers) {
                postselectionphase();
            } else if (getRound() > 12) {
                    endGame();
            } else {
                String newPlayerMessage = null;
                String topMessageToDisplay = "Press enter to start next turn! " + roundMessage;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("endturn.fxml"));
                EndturnController controller = new EndturnController();
                controller.setTopMessageText(topMessageToDisplay);
                controller.setOldPlayer(oldPlayer, oldPlayerMessage);
                controller.setNewPlayer(getTurningPlayer(), newPlayerMessage);


                loader.setController(controller);
                Scene scene = new Scene(loader.load());
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {  //ENTER ENDS TURN
                    @Override
                    public void handle(KeyEvent event) {
                        switch (event.getCode()) {
                            case ENTER:
                                enterMap();
                                initializeTimer();
                        }
                    }
                });
                stage.setScene(scene);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enterTown() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Town.fxml"));
            Controller controller = new TownController();
            controller.loadModel(this);
            loader.setController(controller);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void enterMuleConfig() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MuleConfig.fxml"));
            Controller controller = new MuleConfigController();
            controller.loadModel(this);
            loader.setController(controller);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void validateBuy(Button button, String currentEntry, String boughtItem, Text error) {
        int num = toInteger(currentEntry);
        if (boughtItem != null && boughtItem.equals("Mule") && num > 1) {
            button.setDisable(true);
            error.setText("Can't buy more than one mule at a time");
        } else if (boughtItem != null && validateBuySellName(currentEntry).isEmpty() && enoughMoney(boughtItem, num)) {
            System.out.println("Selected: " + boughtItem);
            button.setDisable(false);
            error.setText(null);
        } else if (boughtItem != null && !enoughMoney(boughtItem, num)) {
            error.setText("Not enough money!");
        }  else {
            button.setDisable(true);
            error.setText("Enter a valid quantity");
        }
    }

    public void validateSell(Button button, String currentEntry, String soldItem, Text error) {
        int num = toInteger(currentEntry);
        if (validateBuySellName(currentEntry).isEmpty() && enoughResources(soldItem, num) && soldItem != null) {
            button.setDisable(false);
            error.setText(null);
        } else {
            button.setDisable(true);
            error.setText("Enter a valid quantity");
        }
    }



    public void enterPub() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Pub.fxml"));
            Controller controller = new PubController();
            controller.loadModel(this);
            loader.setController(controller);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void enterStore() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Store.fxml"));
            Controller controller = new StoreController();
            controller.loadModel(this);
            loader.setController(controller);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void enterLandOffice() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Land Office.fxml"));
            Controller controller = new LandOfficeController();
            controller.loadModel(this);
            loader.setController(controller);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void endGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("endgame.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startNextTurn() {
        boughtOnThisTurnCount = 0;
        round = (turnCount / numberOfPlayers) + 1;
        turningPlayer = playerList.get(turnCount % numberOfPlayers);
        turnCount++;
    }

    public String endTurn() {
        String roundMessage = "";
        String turnOrderMessage = "";
        System.out.println("ENDED TURN FOR PLAYER" + getTurningPlayer() + " ROUND " + getRound());

        if (turnCount % numberOfPlayers == 0) {
            Collections.sort(playerList);

        }
        if (getRound() - 1 > lastRoundNum) {
            roundMessage = "Start of round " + getRound() +"!";
            lastRoundNum++;
            passedThisRoundCount = 0;
        }
        if (boughtOnThisTurnCount == 0) {
            passedThisRoundCount++;
        }
        return roundMessage;
    }

    public void postselectionphase() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("postselectionphase.fxml"));
//            Controller controller = new MapController();
//            controller.loadModel(this);
//            loader.setController(controller);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Player getTurningPlayer() {
        return turningPlayer;
    }

    public int getRound() {
        return round;
    }

    public Color getPropertyColor(Point point) {
        for (Player p : playerList) {
            for (Property property : p.getProperties()) {
                Point location = property.getPoint();
                if (point.equals(location)) {
                    return Color.hsb(p.getColor().getHue(),p.getColor().getSaturation(),
                            p.getColor().getBrightness(),0.2);
                }
            }
        }
        return Color.hsb(0,0,0,0);
    }


    public void updatePropertyImages(GridPane mapGridPane) {
        for (Node node : mapGridPane.getChildren()) {
            updateSinglePropertyImage(node);
        }
    }

    public void updateSinglePropertyImage(Node node) {
        Integer xInd = GridPane.getColumnIndex(node);
        Integer yInd = GridPane.getRowIndex(node);
        Button button = (Button) node;
        Point location = new Point(xInd,yInd);
        Color buttonColor = getPropertyColor(location);
        if (townCoordinates.contains(location)) {
            BackgroundImage townImageView;
            BackgroundSize size = new BackgroundSize(button.getMinWidth(), button.getMinHeight(),
                    false,false,false,false);
            townImageView = new BackgroundImage(new javafx.scene.image.Image(TOWN_IMAGE),null,null,null,size);
            button.setBackground(new Background(townImageView));
        } else if (mountain1Coordinates.contains(location)) {
            ColorInput colorEffect = new ColorInput(0,0,64,64,buttonColor);
            Image image = new Image(M1_IMAGE,button.getMinWidth(),button.getMinHeight(),true,true);
            Blend blended = new Blend(BlendMode.SRC_OVER, new ImageInput(image), colorEffect);
            button.setEffect(blended);
        } else if (mountain2Coordinates.contains(location)) {
            ColorInput colorEffect = new ColorInput(0,0,64,64,buttonColor);
            Image image = new Image(M2_IMAGE,button.getMinWidth(),button.getMinHeight(),true,true);
            Blend blended = new Blend(BlendMode.SRC_OVER, new ImageInput(image), colorEffect);
            button.setEffect(blended);
        } else if (mountain3Coordinates.contains(location)) {
            ColorInput colorEffect = new ColorInput(0,0,64,64,buttonColor);
            Image image = new Image(M3_IMAGE,button.getMinWidth(),button.getMinHeight(),true,true);
            Blend blended = new Blend(BlendMode.SRC_OVER, new ImageInput(image), colorEffect);
            button.setEffect(blended);
        } else if (riverCoordinates.contains(location)) {
            ColorInput colorEffect = new ColorInput(0,0,64,64,buttonColor);
            Image image = new Image(RIVER_IMAGE,button.getMinWidth(),button.getMinHeight(),true,true);
            Blend blended = new Blend(BlendMode.SRC_OVER, new ImageInput(image), colorEffect);
            button.setEffect(blended);
        } else if (plainCoordinates.contains(location)) {
            ColorInput colorEffect = new ColorInput(0,0,64,64,buttonColor);
            Image image = new Image(PLAIN_IMAGE,button.getMinWidth(),button.getMinHeight(),true,true);
            Blend blended = new Blend(BlendMode.SRC_OVER, new ImageInput(image), colorEffect);
            button.setEffect(blended);
        }
        ArrayList<CoolMule> allMules = new ArrayList<>();
        allMules.addAll(redMules); allMules.addAll(greenMules); allMules.addAll(blueMules); allMules.addAll(yellowMules);
        for (CoolMule mule : allMules) {
            if (mule.equals(new CoolMule("RandomAssType", new Property(new Point(xInd,yInd))))) {
                if (redMules.contains(mule)) {
                    button.setEffect(null);
                    button.setGraphic(new ImageView(new Image("RedMule.PNG")));
                } else if (greenMules.contains(mule)) {
                    button.setEffect(null);
                    button.setGraphic(new ImageView(new Image("GreenMule.PNG")));
                } else if (blueMules.contains(mule)) {
                    button.setEffect(null);
                    button.setGraphic(new ImageView(new Image("BlueMule.png")));
                } else if (yellowMules.contains(mule)) {
                    button.setEffect(null);
                    button.setGraphic(new ImageView(new Image("YellowMule.PNG")));
                }
            }
        }

    }

    public String claimProperty(Property property) {
        if (getRound() <= 2 && boughtOnThisTurnCount > 0) {
            return "YOU ALREADY GOT FREE PROPERTY! END TURN WITH ENTER";
        }
        if (isOwned(property)) {
            return "CANT BUY, THIS PROPERTY IS OWNED";
        }
        getTurningPlayer().buyProperty(property, getRound());
        boughtOnThisTurnCount++;
        return null;
    }

    public boolean isOwned(Property property) {
        for (Player p : playerList) {
            for (Property ownedProperty : p.getProperties()) {
                if (ownedProperty.equals(property)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void squarifyNode(Node node) {
        Button button = (Button) node;
        ImageInput image = new ImageInput(new Image(SQUARE_IMAGE,
                button.getMinWidth(),button.getMinHeight(),true,true));
        Blend blend = new Blend(BlendMode.SRC_OVER, button.getEffect(), image);
        button.setEffect(blend);
    }

    public void updatePlayerInfoText(Text textZone) {
        Player turningPlayer = getTurningPlayer();
        textZone.setText(turningPlayer.getName() + "'s turn!   $" +
                turningPlayer.getMoney() + "\n    Score:     " + turningPlayer.getScore());
    }

    public ArrayList<String> getAvailableColors() {
        return availableColors;
    }

    public Color removeColor(String color) {
        availableColors.remove(color);
        switch (color) {
            case "Red":
                return Color.RED;
            case "Blue":
                return Color.BLUE;
            case "Green":
                return Color.GREEN;
            case "Yellow":
                return Color.YELLOW;
        }
        return null;
    }


    public void setTimerTextReference(Text timerTextReference) {
        timerText = timerTextReference;
    }

    public void gamble() {
        int time = secondsLeft;
        Random num = new Random();
        int moneyBonus = 0;
        if (time >= 37) {
            moneyBonus = roundBonus[getRound()] + num.nextInt(201);
        } else if (time >= 25 && time <= 36) {
            moneyBonus = roundBonus[getRound()] + num.nextInt(151);
        } else if (time >= 12 && time <= 24) {
            moneyBonus = roundBonus[getRound()] + num.nextInt(101);
        } else {
            moneyBonus = roundBonus[getRound()] + num.nextInt(51);
        }
        int moneyGained = Math.min(moneyBonus, 250);
        Player turningPlayer = getTurningPlayer();
        turningPlayer.setMoney(turningPlayer.getMoney() + moneyGained);
        enterEndTurnScreen("Congratulations! You just earned " + moneyGained + " dollars!");
    }

    public void buyResource(String boughtResource, int boughtAmount) {
        int prevAmount;
        int newAmount;
        double paymentPrice = resourceBuyPrices.get(boughtResource) * boughtAmount;
        if (!level.equals("Beginner")) {
            prevAmount = itemsForSaleOther.get(boughtResource);
            System.out.println(prevAmount);
            if (getTurningPlayer().getMoney() >= paymentPrice) {
                newAmount = Math.max(prevAmount - boughtAmount, 0);
                itemsForSaleOther.put(boughtResource, newAmount);
                System.out.println(itemsForSaleOther);
                if (prevAmount - boughtAmount >= 0) {
                    getTurningPlayer().buyResource(boughtResource, boughtAmount);
                    getTurningPlayer().setMoney(getTurningPlayer().getMoney() - paymentPrice);
                    if (boughtResource.equals("Food")) {
                        foodOther = newAmount;
                    } else if (boughtResource.equals("Energy")) {
                        energyOther = newAmount;
                    } else if (boughtResource.equals("Smithore")) {
                        smithoreOther = newAmount;
                    } else if (boughtResource.equals("Crystite")) {
                        crystiteOther = newAmount;
                    } else {
                        muleOther = newAmount;
                    }
                } else {
                    System.out.println("Not enough goods");
                    return;
                }
            } else {
                System.out.println("Not enough money");
            }
        } else {
            prevAmount = itemsForSaleBeginner.get(boughtResource);
            System.out.println(prevAmount);
            if (getTurningPlayer().getMoney() >= paymentPrice) {
                newAmount = prevAmount - boughtAmount;
                itemsForSaleBeginner.put(boughtResource, Math.max(0, newAmount));
                System.out.println(itemsForSaleBeginner);
                if (prevAmount - boughtAmount >= 0) {
                    getTurningPlayer().buyResource(boughtResource, boughtAmount);
                    getTurningPlayer().setMoney(getTurningPlayer().getMoney() - paymentPrice);
                    if (boughtResource.equals("Food")) {
                        foodBeg = newAmount;
                    } else if (boughtResource.equals("Energy")) {
                        energyBeg = newAmount;
                    } else if (boughtResource.equals("Smithore")) {
                        smithoreBeg = newAmount;
                    } else if (boughtResource.equals("Crystite")) {
                        crystiteBeg = newAmount;
                    } else {
                        muleBeg = newAmount;
                    }
                } else {
                    System.out.println("Not enough goods");
                }
            } else {
                System.out.println("Not enough money!");
            }
        }
    }

    public void sellResource(String soldResource, int soldAmount) {
        if ((Integer) getTurningPlayer().getResources().get(soldResource) >= soldAmount) {
            getTurningPlayer().sellResource(soldResource, soldAmount);
            getTurningPlayer().setMoney(getTurningPlayer().getMoney() + resources.get(soldResource) * soldAmount);
        } else {
            System.out.println("You don't have enough to sell");
        }
    }

    public void enterMulePlacement(String type) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Map.fxml"));
            PlacementController controller = new PlacementController();
            controller.loadModel(this);
            controller.setMuleType(type);
            loader.setController(controller);
            Scene scene = new Scene(loader.load());
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {  //ENTER ENDS TURN
                @Override
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case ENTER:
                            enterEndTurnScreen("Turn ended without gambling!");
                    }
                }
            });
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public String placeMule(String type, Property location) {
        if(type.equals("Food")){
            getTurningPlayer().setMoney(getTurningPlayer().getMoney() - 25);
            if(getTurningPlayer().getMoney() < 0) getTurningPlayer().setMoney(0);

        }
        if(type.equals("Energy")){
            getTurningPlayer().setMoney(getTurningPlayer().getMoney() - 50);
            if(getTurningPlayer().getMoney() < 0) getTurningPlayer().setMoney(0);
        }
        if(type.equals("Ore")){
            getTurningPlayer().setMoney(getTurningPlayer().getMoney() - 75);
            if(getTurningPlayer().getMoney() < 0) getTurningPlayer().setMoney(0);
        }
        System.out.println(getTurningPlayer().getResources());
        getTurningPlayer().removeMule();
        System.out.println(getTurningPlayer().getResources());



        String message = "You don't own that property!";
        if (getTurningPlayer().getProperties().contains(location)) {
            if (getTurningPlayer().getColor().equals(Color.RED)) {
                CoolMule mule = new CoolMule(type, location);
                if (redMules.contains(mule)) {
                    return "Already a mule here!";
                }
                redMules.add(new CoolMule(type, location));
            } else if (getTurningPlayer().getColor().equals(Color.GREEN)) {
                CoolMule mule = new CoolMule(type, location);
                if (greenMules.contains(mule)) {
                    return "Already a mule here!";
                }
                greenMules.add(new CoolMule(type, location));
            } else if (getTurningPlayer().getColor().equals(Color.BLUE)) {
                CoolMule mule = new CoolMule(type, location);
                if (blueMules.contains(mule)) {
                    return "Already a mule here!";
                }
                blueMules.add(new CoolMule(type, location));
            } else if (getTurningPlayer().getColor().equals(Color.YELLOW)) {
                CoolMule mule = new CoolMule(type, location);
                if (yellowMules.contains(mule)) {
                    return "Already a mule here!";
                }
                yellowMules.add(new CoolMule(type, location));
            }
            message = null;
        }
        return message;
    }
}
