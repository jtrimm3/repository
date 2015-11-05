import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

/**
 * Created by Peter on 9/15/2015.
 */
public class Player implements Comparable<Player>, Serializable {
    private MuleModel muleModel;
    private double money;
    private transient Color color;
    private int food, energy, smithore;
    private String name;
    private String race;
    private ArrayList<Property> properties;
    private int playerNumber;
    private Map<String, Integer> resources = new HashMap<>();
    private ArrayList<Property> foodMules;
    private ArrayList<Property> energyMules;
    private ArrayList<Property> oreMules;
    private int foodDelta, energyDelta, oreDelta, muleDelta;
    private double moneyDelta;
    private double lastScore;
    private String redString = Color.RED.toString();
    private String greenString = Color.GREEN.toString();
    private String blueString = Color.BLUE.toString();
    private String yellowString = Color.YELLOW.toString();

    //ENUM FOR RACES AND THEIR INFO



    public Player(String name, int playerNumber, String race, Color color, Map<String, Integer> resources) {
        foodDelta =0; energyDelta = 0; oreDelta = 0; muleDelta = 0;
        moneyDelta = 0;
        resources.put("Smithore", 0);
        foodMules = new ArrayList<>();
        energyMules = new ArrayList<>();
        oreMules = new ArrayList<>();
        this.properties = new ArrayList<Property>();
        this.name = name;
        this.playerNumber = playerNumber;
        this.race = race;
        this.color = color;
        this.resources = resources;
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

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.defaultWriteObject();
        if (color.equals(Color.RED)) {
            stream.writeObject(redString);
        } else if (color.equals(Color.GREEN)) {
            stream.writeObject(greenString);
        } else if (color.equals(Color.BLUE)) {
            stream.writeObject(blueString);
        } else if (color.equals(Color.YELLOW)) {
            stream.writeObject(yellowString);
        }
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        String str = (String) stream.readObject();
        if (str.equals(redString)) {
            this.color = Color.RED;
        } else if (str.equals(blueString)) {
            this.color = Color.BLUE;
        } else if (str.equals(greenString)) {
            this.color = Color.GREEN;
        } else if (str.equals(yellowString)) {
            this.color = Color.YELLOW;
        }
    }

    public void resetDeltas() {
        setFoodDelta(0);
        setEnergyDelta(0);
        setOreDelta(0);
        setMuleDelta(0);
        setMoneyDelta(0);
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

    public Map getResources() { return resources; }

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

    public void buyResource(String resource, int amount) {
        if (resources.get(resource) != null) {
            int prevAmount = resources.get(resource);
            resources.put(resource, prevAmount + amount);
            System.out.println(resources);
        } else {
            resources.put(resource, amount);
        }
    }


    public void sellResource(String resource, int amount) {

        int prevAmount = resources.get(resource);
        if (prevAmount - amount >= 0) {
            resources.put(resource, prevAmount - amount);
            System.out.println(resources);
            System.out.println("selling");
        } else {
            System.out.println("Not enough resources");
        }
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

    public int getSmithore() {
        return resources.get("Smithore");
    }

    public void setSmithore(int smithore) {resources.put("Smithore", smithore);
    }

    public int getEnergy() {
        return resources.get("Energy");
    }

    public void setEnergy(int energy) {
        resources.put("Energy", energy);
    }

    public int getFood() {
        return resources.get("Food");
    }

    public void setFood(int food) {
        resources.put("Food", food);
    }

    public void removeMule(){
        resources.put("Mule",0);
    }

    public int getMuleDelta() {
        return muleDelta;
    }

    public void setMuleDelta(int muleDelta) {
        this.muleDelta = muleDelta;
    }

    public int getOreDelta() {
        return oreDelta;
    }

    public void setOreDelta(int oreDelta) {
        this.oreDelta = oreDelta;
    }

    public int getEnergyDelta() {
        return energyDelta;
    }

    public void setEnergyDelta(int energyDelta) {
        this.energyDelta = energyDelta;
    }

    public int getFoodDelta() {
        return foodDelta;
    }

    public void setFoodDelta(int foodDelta) {
        this.foodDelta = foodDelta;
    }

    public double getMoneyDelta() {
        return moneyDelta;
    }

    public void setMoneyDelta(double moneyDelta) {
        this.moneyDelta = moneyDelta;
    }

    public double getLastScore() {
        return lastScore;
    }

    public void setLastScore(double lastScore) {
        this.lastScore = lastScore;
    }

    public double getScoreDelta() {
        return getScore() - lastScore;
    }
}
