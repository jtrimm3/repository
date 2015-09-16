import java.awt.*;
import javafx.scene.paint.Color;

/**
 * Created by Peter on 9/15/2015.
 */
public class Player {
    private double money;
    private Color color;
    private String name;
    private String race;
    private int playerNumber;

    //ENUM FOR RACES AND THEIR INFO



    public Player(String name, int playerNumber, String race, Color color) {
        this.name = name;
        this.playerNumber = playerNumber;
        this.race = race;
        this.color = color;
        switch (race) {
            case("Human"):
                money = 600;
                break;
            case("Flapper"):
                money = 1600;
                break;
            default:
                money = 1000;
                break;
        }
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String toString() {
        return name + playerNumber + race + money + color;
    }
}
