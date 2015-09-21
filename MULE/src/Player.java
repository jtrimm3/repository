import java.awt.*;
import java.util.ArrayList;

import javafx.scene.paint.Color;

/**
 * Created by Peter on 9/15/2015.
 */
public class Player implements Comparable<Player> {
    private double money;
    private Color color;
    private String name;
    private String race;
    private ArrayList<Property> properties;
    private int playerNumber;

    //ENUM FOR RACES AND THEIR INFO



    public Player(String name, int playerNumber, String race, Color color) {
        this.properties = new ArrayList<Property>();
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

    public void buyProperty(Property property, Integer round) {
        if (round > 2) {
            money = money - 300;
        }
        properties.add(property);
    }

    public ArrayList<Property> getProperties(){
        return properties;
    }

    public double getScore() {
        return getMoney() + 500 * getProperties().size(); // + getNumberOfGoods() implemented later
    }

    public int compareTo(Player otherPlayer) {
        if (this.getScore() > otherPlayer.getScore()) {
            return 1;
        } else if (this.getScore() < otherPlayer.getScore()) {
            return -1;
        } else {
            return 1; //MAINTAINS IN PLACE ORDERING I THINK, SOMEONE CHECK THIS FOR ME...
        }
    }
}
