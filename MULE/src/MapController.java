import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Peter on 9/17/2015.
 */
public class MapController implements Initializable, Controller {
    private MuleModel muleModel;
    private ArrayList<Point> riverCoordinates = new ArrayList<>();
    private ArrayList<Point> townCoordinates = new ArrayList<>();
    private ArrayList<Point> plainCoordinates = new ArrayList<>();
    private ArrayList<Point> mountain1Coordinates = new ArrayList<>();
    private ArrayList<Point> mountain2Coordinates = new ArrayList<>();
    private ArrayList<Point> mountain3Coordinates = new ArrayList<>();
    private long time = 50;


    @FXML
    private GridPane mapGridPane = new GridPane();

    @FXML
    private Text playerInfoText = new Text();

    @FXML
    private Text roundText = new Text();

    @FXML
    private Text errorText = new Text();



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorText.setFill(Color.RED);
        roundText.setFill(Color.DARKOLIVEGREEN);
        playerInfoText.setFill(muleModel.getTurningPlayer().getColor());




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

        ObservableList<Node> paneChildren = mapGridPane.getChildren();
        muleModel.updatePropertyImages(mapGridPane);
        muleModel.updatePlayerInfoText(playerInfoText);
        for (Node node : paneChildren) {
            muleModel.initializeCounter();
            muleModel.startTimer();
            Integer xInd = mapGridPane.getColumnIndex(node);
            Integer yInd = mapGridPane.getRowIndex(node);
            if (townCoordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        muleModel.enterTown();
                    }
                });
            } else if (mountain1Coordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        String claimMessage = muleModel.claimProperty(new Property(new Point(xInd,yInd)));
                        errorText.setText(claimMessage);
                        muleModel.updatePlayerInfoText(playerInfoText);
                        muleModel.updateSinglePropertyImage(button);
                        muleModel.squarifyNode(button);
                    }
                });
                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, new EventHandler<Event>() {
                    @Override
                    public void handle(Event event) {
                        muleModel.squarifyNode(button);
                    }
                });

                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        muleModel.updateSinglePropertyImage(button);
                    }
                });
            } else if (mountain2Coordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        String claimMessage = muleModel.claimProperty(new Property(new Point(xInd, yInd)));
                        errorText.setText(claimMessage);
                        muleModel.updatePlayerInfoText(playerInfoText);
                        muleModel.updateSinglePropertyImage(button);
                        muleModel.squarifyNode(button);
                    }
                });
                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, new EventHandler<Event>() {
                    @Override
                    public void handle(Event event) {
                        muleModel.squarifyNode(button);
                    }
                });

                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        muleModel.updateSinglePropertyImage(button);
                    }
                });
            } else if (mountain3Coordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        String claimMessage = muleModel.claimProperty(new Property(new Point(xInd, yInd)));
                        errorText.setText(claimMessage);
                        muleModel.updatePlayerInfoText(playerInfoText);
                        muleModel.updateSinglePropertyImage(button);
                        muleModel.squarifyNode(button);
                    }
                });
                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, new EventHandler<Event>() {
                    @Override
                    public void handle(Event event) {
                        muleModel.squarifyNode(button);
                    }
                });

                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        muleModel.updateSinglePropertyImage(button);
                    }
                });
            } else if (riverCoordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        String claimMessage = muleModel.claimProperty(new Property(new Point(xInd, yInd)));
                        errorText.setText(claimMessage);
                        muleModel.updatePlayerInfoText(playerInfoText);
                        muleModel.updateSinglePropertyImage(button);
                        muleModel.squarifyNode(button);
                    }
                });
                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, new EventHandler<Event>() {
                    @Override
                    public void handle(Event event) {
                        muleModel.squarifyNode(button);
                    }
                });

                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        muleModel.updateSinglePropertyImage(button);
                    }
                });
            } else if (plainCoordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        String claimMessage = muleModel.claimProperty(new Property(new Point(xInd, yInd)));
                        errorText.setText(claimMessage);
                        muleModel.updatePlayerInfoText(playerInfoText);
                        muleModel.updateSinglePropertyImage(button);
                        muleModel.squarifyNode(button);
                    }
                });
                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, new EventHandler<Event>() {
                    @Override
                    public void handle(Event event) {
                        muleModel.squarifyNode(button);
                    }
                });

                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        muleModel.updateSinglePropertyImage(button);
                    }
                });
            }
        }
        roundText.setText("Round " + muleModel.getRound());
    }




    public void loadModel(MuleModel model) {
        muleModel = model;
    }
}
