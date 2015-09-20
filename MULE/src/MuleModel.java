import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Peter on 9/19/2015.
 */
public class MuleModel {
    private int numberOfPlayers;
    private int playerPageCount;
    private String map;
    private HashMap<Integer, Player> playerHashMap;

    public void initializeConfigData(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        this.playerHashMap = new HashMap<Integer,Player>();
    }

    public String validateName(String name) {
        boolean nameTaken = false;
        boolean nameEmpty = false;
        if (name.isEmpty()) {
            nameEmpty = true;
        }
        for (Integer key : playerHashMap.keySet()) {
            Player playerChecking = playerHashMap.get(key);
            if (name.equalsIgnoreCase(playerChecking.getName())) {
                nameTaken = true;
                break;
            }
        }
        if (nameTaken) {
            return name + " is taken already";
        } else if (nameEmpty){
            return "Please choose a name";
        } else {
            return "";
        }
    }

    public void continuePlayerConfig(Stage stage) {
        if (playerHashMap.size() < numberOfPlayers) { //Return to playerconfig
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Players.fxml"));
                Controller controller = new PlayersController();
                controller.loadModel(this);
                loader.setController(controller);
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                System.out.println(numberOfPlayers);
                System.out.println(playerHashMap.size());
                //stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {//go to map
            enterMap(stage);
        }

    }

    public int getPlayerPageCount() {
        return playerPageCount;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void addPlayer(Player player) {
        playerHashMap.put(player.getPlayerNumber(), player);
    }

    public HashMap<Integer,Player> getPlayerHashMap() {
        return playerHashMap;
    }

    public Player getPlayerByPlayerNumber(int num) {
        return playerHashMap.get(num);
    }

    public void setMap(String map) {
        this.map = map;
    }



    public void begin() {
        Stage stage = new Stage(); //Stage first created!
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

    public void enterMap(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Map.fxml"));
            Controller controller = new MapController();
            controller.loadModel(this);
            loader.setController(controller);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enterTown(Stage stage) {
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


}
