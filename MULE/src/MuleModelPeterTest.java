import static org.junit.Assert.*;

import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * Created by Peter on 11/2/2015.
 */
public class MuleModelPeterTest extends TestCase {
    MuleModel m;

    @Before
    public void setup() {
        m = new MuleModel(new Stage());
    }



    @Test
    public void testBuy() {
        m = new MuleModel(null);
        m = new MuleModel(null);
        HashMap<String, Integer> resources = new HashMap<String, Integer>();
        Player p1 = new Player("Michael", 1, "Human", Color.RED, resources);
        Player p2 = new Player("Peter", 2, "Human", Color.BLUE, resources);
        ArrayList<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(p1);
        m.playerList = listOfPlayers;
        new JFXPanel();
        Button aButton = new Button();
        Text errText = new Text();
        m.setTurningPlayer(p1);
        m.validateBuy(aButton, "2","Mule", errText);
        assertEquals("Can't buy more than one mule", errText.getText(),"Can't buy more than one mule at a time");
        m.validateBuy(aButton, "1","Mule", errText);
        assertEquals("Buying one mule", errText.getText(), "");
        m.validateBuy(aButton, "100","Energy", errText);
        assertEquals("Not enough money: ", errText.getText(), "Not enough money!");
        m.validateBuy(aButton, "a","Energy", errText);
        assertEquals("Need a valid Quantity: ", errText.getText(), "Enter a valid quantity");
    }
}