import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;

/**
 * Created by Peter on 9/15/2015.
 */
public class Player implements Comparable<Player>, Serializable {
    static final long serialVersionUID = 12312211L;
    private double money;
    private transient Color color;
    private String name;
    private String email;
    private String race;
    private List<Property> properties;
    private int playerNumber;
    private Map<String, Integer> resources = new HashMap<>();
    private int foodDelta, energyDelta, oreDelta, muleDelta;
    private double moneyDelta;
    private double lastScore;
    private String redString = Color.RED.toString();
    private String greenString = Color.GREEN.toString();
    private String blueString = Color.BLUE.toString();
    private String yellowString = Color.YELLOW.toString();
    private static final int HUMAN_STARTING_MONEY = 600;
    private static final int FLAPPER_STARTING_MONEY = 1600;
    private static final int DEFAULT_STARTING_MONEY = 1000;
    private static final int PROPERTY_COST = 300;
    private static final int PROPERTY_SCORE_MULTIPLIER = 500;

    //ENUM FOR RACES AND THEIR INFO



    public Player(String n, String mail, int num, String r, Color col, Map<String, Integer> res) {
        foodDelta = 0; energyDelta = 0; oreDelta = 0; muleDelta = 0;
        moneyDelta = 0;
        res.put("Smithore", 0);
        this.properties = new ArrayList<Property>();
        this.name = n;
        this.email = mail;
        this.playerNumber = num;
        this.race = r;
        this.color = col;
        this.resources = res;
        switch (race) {
            case("Human"):
                money = HUMAN_STARTING_MONEY;
                break;
            case("Flapper"):
                money = FLAPPER_STARTING_MONEY;
                break;
            default:
                money = DEFAULT_STARTING_MONEY;
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

    public final void resetDeltas() {
        setFoodDelta(0);
        setEnergyDelta(0);
        setOreDelta(0);
        setMuleDelta(0);
        setMoneyDelta(0);
    }

    public final String getRace() {
        return race;
    }

    public final void setRace(String r) {
        this.race = r;
    }

    public final double getMoney() {
        return money;
    }

    public final void setMoney(double m) {
        this.money = m;
    }

    public final Map getResources() { return resources; }

    public final String getName() {
        return name;
    }

    public final void setName(String n) {
        this.name = n;
    }

    public final Color getColor() {
        return color;
    }

    public final void setColor(Color col) {
        this.color = col;
    }

    public final int getPlayerNumber() {
        return playerNumber;
    }

    public final void setPlayerNumber(int num) {
        this.playerNumber = num;
    }

    public final String toString() {
        return name + playerNumber + race + money + color;
    }

    public final void buyProperty(Property property, Integer round) {
        if (round > 2) {
            money = money - PROPERTY_COST;
        }
        properties.add(property);
    }

    public final void buyResource(String resource, int amount) {
        if (resources.get(resource) != null) {
            int prevAmount = resources.get(resource);
            resources.put(resource, prevAmount + amount);
            System.out.println(resources);
        } else {
            resources.put(resource, amount);
        }
    }


    public final void sellResource(String resource, int amount) {

        int prevAmount = resources.get(resource);
        if (prevAmount - amount >= 0) {
            resources.put(resource, prevAmount - amount);
            System.out.println(resources);
            System.out.println("selling");
        } else {
            System.out.println("Not enough resources");
        }
    }

    public final List<Property> getProperties(){
        return properties;
    }

    public final double getScore() {
        return getMoney() + PROPERTY_SCORE_MULTIPLIER * getProperties().size(); // + getNumberOfGoods() implemented later
    }

    public final int compareTo(Player otherPlayer) {
        if (this.getScore() > otherPlayer.getScore()) {
            return 1;
        } else if (this.getScore() < otherPlayer.getScore()) {
            return -1;
        } else {
            return 1; //MAINTAINS IN PLACE ORDERING I THINK, SOMEONE CHECK THIS FOR ME...
        }
    }

    public final boolean equals(Object other) {
        if (!(other instanceof Player)) {
            return false;
        }
        return this.name.equals(((Player) other).getName())
                && this.getScore() == ((Player) other).getScore();
    }

    public final int hashCode() {
        return this.getName().hashCode() + (int) this.getScore();
    }

    public final int getSmithore() {
        return resources.get("Smithore");
    }

    public final void setSmithore(int smithore) {resources.put("Smithore", smithore);
    }

    public final int getEnergy() {
        return resources.get("Energy");
    }

    public final void setEnergy(int energy) {
        resources.put("Energy", energy);
    }

    public final int getFood() {
        return resources.get("Food");
    }

    public final void setFood(int food) {
        resources.put("Food", food);
    }

    public final void removeMule(){
        resources.put("Mule",0);
    }

    public final int getMuleDelta() {
        return muleDelta;
    }

    public final void setMuleDelta(int d) {
        this.muleDelta = d;
    }

    public final int getOreDelta() {
        return oreDelta;
    }

    public final void setOreDelta(int d) {
        this.oreDelta = d;
    }

    public final int getEnergyDelta() {
        return energyDelta;
    }

    public final void setEnergyDelta(int d) {
        this.energyDelta = d;
    }

    public final int getFoodDelta() {
        return foodDelta;
    }

    public final void setFoodDelta(int d) {
        this.foodDelta = d;
    }

    public final double getMoneyDelta() {
        return moneyDelta;
    }

    public final void setMoneyDelta(double d) {
        this.moneyDelta = d;
    }

    public final double getLastScore() {
        return lastScore;
    }

    public final void setLastScore(double score) {
        this.lastScore = score;
    }

    public final double getScoreDelta() {
        return getScore() - lastScore;
    }

    public String getEmail() {
        return email;
    }
}
