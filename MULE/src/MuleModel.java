import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.Timer;
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
    private String map;
    private ArrayList<Player> playerList;
    //private HashMap<Integer, Player> playerHashMap;

    private Stage stage;
    protected Timer timer;
    protected int secondsLeft;

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


    public MuleModel(Stage stage) {
        this.stage = stage;
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

    public void initializeCounter() {
        secondsLeft = getTime();
        timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(secondsLeft < 1) {
                    endTurn();
                } else {
                    System.out.println(secondsLeft);
                    secondsLeft--;
                }
            }
        });


    }

    public int getTime() {
        return 50;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }

    public void startTimer() {
        timer.start();
    }

    public void initializeConfigData(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        this.playerList = new ArrayList<>();
        //this.playerHashMap = new HashMap<Integer,Player>();
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
                            endTurn();
                    }
                }
            });
            stage.setScene(scene);
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

    public void endTurn() {
        System.out.println("ENDED TURN FOR PLAYER" + getTurningPlayer() + " ROUND " + getRound());

        if (turnCount % numberOfPlayers == 0) {
            Collections.sort(playerList);
        }
        if (getRound() - 1 > lastRoundNum) {
            lastRoundNum++;
            passedThisRoundCount = 0;
        }
        if (boughtOnThisTurnCount == 0) {
            passedThisRoundCount++;
        }
        if (passedThisRoundCount == numberOfPlayers) {
            postselectionphase();
        } else {
            startNextTurn();
            if (getRound() > 12) {
                endGame();
            } else {
                enterMap();
            }
        }

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




}
