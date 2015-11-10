//import static org.junit.Assert.*;
//
//import javafx.stage.Stage;
//import junit.framework.TestCase;
//import org.junit.Before;
//import org.junit.Test;
//import javafx.scene.paint.Color;
//
//import java.util.HashMap;
//
///**
// * Created by Julianna on 11/2/2015.
// */
//public class MuleModelTest extends TestCase {
//    MuleModel m;
//
//    @Before
//    public void setup() {
//        m = new MuleModel(new Stage());
//    }
//
//    public Player makePlayerNoFood() {
//        HashMap<String, Integer> resources = new HashMap<String, Integer>();
//        resources.put("Food", 0);
//        Player p = new Player("Jim", 1, "Human", Color.RED, resources);
//        System.out.println(p.getFood());
//        return p;
//    }
//
//    public Player makePlayerFoodRound4() {
//        HashMap<String, Integer> resources = new HashMap<String, Integer>();
//        resources.put("Food", 2);
//        Player p = new Player("Jim", 1, "Human", Color.RED, resources);
//        return p;
//    }
//
//    public Player makePlayerFoodRound5() {
//        HashMap<String, Integer> resources = new HashMap<String, Integer>();
//        resources.put("Food", 3);
//        Player p = new Player("Jim", 1, "Human", Color.RED, resources);
//        return p;
//    }
//
//    public Player makePlayerFoodRound10() {
//        HashMap<String, Integer> resources = new HashMap<String, Integer>();
//        resources.put("Food", 4);
//        Player p = new Player("Jim", 1, "Human", Color.RED, resources);
//        return p;
//    }
//
//    public Player makePlayerMoreFoodRound4() {
//        HashMap<String, Integer> resources = new HashMap<String, Integer>();
//        resources.put("Food", 3);
//        Player p = new Player("Jim", 1, "Human", Color.RED, resources);
//        return p;
//    }
//
//    public Player makePlayerMoreFoodRound5() {
//        HashMap<String, Integer> resources = new HashMap<String, Integer>();
//        resources.put("Food", 4);
//        Player p = new Player("Jim", 1, "Human", Color.RED, resources);
//        return p;
//    }
//
//    public Player makePlayerMoreFoodRound10() {
//        HashMap<String, Integer> resources = new HashMap<String, Integer>();
//        resources.put("Food", 5);
//        Player p = new Player("Jim", 1, "Human", Color.RED, resources);
//        int round = 10;
//        return p;
//    }
//
//    //make different methods for different players
//
//    @Test
//    public void testCalculateTimeForTurn() {
//        m = new MuleModel(null);
//        assertEquals("Test with no food round 4", 5, m.calculateTimeForTurn(makePlayerNoFood()), 4);
//        assertEquals("Test with no food round 5", 5, m.calculateTimeForTurn(makePlayerNoFood()), 5);
//        assertEquals("Test with no food round 10", 5, m.calculateTimeForTurn(makePlayerNoFood()), 10);
//        assertEquals("Test with null player", 50, m.calculateTimeForTurn(null), 4);
//        assertEquals("Test low round insufficient food", 30, m.calculateTimeForTurn(makePlayerFoodRound4()), 4);
//        assertEquals("Test mid round insufficient food", 30, m.calculateTimeForTurn(makePlayerFoodRound5()), 5);
//        assertEquals("Test high round insufficient food", 30, m.calculateTimeForTurn(makePlayerFoodRound10()), 10);
//        assertEquals("Test low round sufficient food", 50, m.calculateTimeForTurn(makePlayerMoreFoodRound4()), 4);
//        assertEquals("Test mid round sufficient food", 50, m.calculateTimeForTurn(makePlayerMoreFoodRound5()), 5);
//        assertEquals("Test for high round with sufficient food", 50,  m.calculateTimeForTurn(makePlayerMoreFoodRound10()), 10);
//
//        //get player with food
//        //get player no food
//        //check player above and below and at round 5 and 9
//        //
//    }
//}