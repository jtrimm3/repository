import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Peter on 9/17/2015.
 */
public class PlacementController implements Initializable, Controller {
    private MuleModel muleModel;
    private String type;
    private List<Point> riverCoordinates = new ArrayList<>();
    private List<Point> townCoordinates = new ArrayList<>();
    private List<Point> plainCoordinates = new ArrayList<>();
    private List<Point> mountainONECoordinates = new ArrayList<>();
    private List<Point> mountainTWOCoordinates = new ArrayList<>();
    private List<Point> mountainTHREECoordinates = new ArrayList<>();
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;

    @FXML
    private Label titleText = new Label();

    @FXML
    private GridPane mapGridPane = new GridPane();

    @FXML
    private Text playerInfoText = new Text();

    @FXML
    private Text roundText = new Text();

    @FXML
    private Text errorText = new Text();

    @FXML
    private Text timerText = new Text();



    @Override
    public final void initialize(URL url, ResourceBundle rb) {
        errorText.setFill(Color.RED);
        roundText.setFill(Color.DARKOLIVEGREEN);
        titleText.setText("Place Your " + type + " M.U.L.E!");
        playerInfoText.setFill(muleModel.getTurningPlayer().getColor());




        riverCoordinates.add(new Point(FOUR,ZERO));
        riverCoordinates.add(new Point(FOUR,ONE));
        riverCoordinates.add(new Point(FOUR,THREE));
        riverCoordinates.add(new Point(FOUR,FOUR));

        townCoordinates.add(new Point(FOUR,TWO));

        plainCoordinates.add(new Point(ZERO,ZERO));
        plainCoordinates.add(new Point(ONE,ZERO));
        plainCoordinates.add(new Point(THREE,ZERO));
        plainCoordinates.add(new Point(FIVE,ZERO));
        plainCoordinates.add(new Point(SEVEN,ZERO));
        plainCoordinates.add(new Point(EIGHT,ZERO));
        plainCoordinates.add(new Point(ZERO,ONE));
        plainCoordinates.add(new Point(TWO,ONE));
        plainCoordinates.add(new Point(THREE,ONE));
        plainCoordinates.add(new Point(FIVE,ONE));
        plainCoordinates.add(new Point(SIX,ONE));
        plainCoordinates.add(new Point(SEVEN,ONE));
        plainCoordinates.add(new Point(ONE,TWO));
        plainCoordinates.add(new Point(TWO,TWO));
        plainCoordinates.add(new Point(THREE,TWO));
        plainCoordinates.add(new Point(FIVE,TWO));
        plainCoordinates.add(new Point(SIX,TWO));
        plainCoordinates.add(new Point(SEVEN,TWO));
        plainCoordinates.add(new Point(ZERO,THREE));
        plainCoordinates.add(new Point(TWO,THREE));
        plainCoordinates.add(new Point(THREE,THREE));
        plainCoordinates.add(new Point(FIVE,THREE));
        plainCoordinates.add(new Point(SEVEN,THREE));
        plainCoordinates.add(new Point(EIGHT,THREE));
        plainCoordinates.add(new Point(ZERO,FOUR));
        plainCoordinates.add(new Point(ONE,FOUR));
        plainCoordinates.add(new Point(THREE,FOUR));
        plainCoordinates.add(new Point(FIVE,FOUR));
        plainCoordinates.add(new Point(SIX,FOUR));
        plainCoordinates.add(new Point(SEVEN,FOUR));

        mountainONECoordinates.add(new Point(TWO,ZERO));
        mountainONECoordinates.add(new Point(ONE,ONE));
        mountainONECoordinates.add(new Point(EIGHT,TWO));

        mountainTWOCoordinates.add(new Point(ONE,THREE));
        mountainTWOCoordinates.add(new Point(SIX,THREE));
        mountainTWOCoordinates.add(new Point(TWO,FOUR));
        mountainTWOCoordinates.add(new Point(EIGHT,FOUR));

        mountainTHREECoordinates.add(new Point(SIX,ZERO));
        mountainTHREECoordinates.add(new Point(EIGHT,ONE));
        mountainTHREECoordinates.add(new Point(ZERO, TWO));

        ObservableList<Node> paneChildren = mapGridPane.getChildren();
        muleModel.updatePropertyImages(mapGridPane);
        muleModel.updatePlayerInfoText(playerInfoText);
        muleModel.setTimerTextReference(timerText);
        for (Node node : paneChildren) {
            Integer xInd = GridPane.getColumnIndex(node);
            Integer yInd = GridPane.getRowIndex(node);
            if (townCoordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        muleModel.enterTown();
                    }
                });
            } else if (mountainONECoordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        String placementMessage = muleModel.placeMule(type, new Property(new Point(xInd,yInd)));
                        errorText.setText(placementMessage);
                        muleModel.updatePlayerInfoText(playerInfoText);
                        muleModel.updateSinglePropertyImage(button);
                        muleModel.squarifyNode(button);
                        if (placementMessage == null) {
                        System.out.println("gotomap");
                        muleModel.enterMap();

                        }
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
            } else if (mountainTWOCoordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        String placementMessage = muleModel.placeMule(type, new Property(new Point(xInd, yInd)));
                        errorText.setText(placementMessage);
                        muleModel.updatePlayerInfoText(playerInfoText);
                        muleModel.updateSinglePropertyImage(button);
                        muleModel.squarifyNode(button);
                        if (placementMessage == null) {
                            muleModel.enterMap();
                        }
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
            } else if (mountainTHREECoordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        String placementMessage = muleModel.placeMule(type, new Property(new Point(xInd, yInd)));
                        errorText.setText(placementMessage);
                        muleModel.updatePlayerInfoText(playerInfoText);
                        muleModel.updateSinglePropertyImage(button);
                        muleModel.squarifyNode(button);
                        if (placementMessage == null) {
                            muleModel.enterMap();
                        }
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
                        String placementMessage = muleModel.placeMule(type, new Property(new Point(xInd, yInd)));
                        errorText.setText(placementMessage);
                        muleModel.updatePlayerInfoText(playerInfoText);
                        muleModel.updateSinglePropertyImage(button);
                        muleModel.squarifyNode(button);
                        if (placementMessage == null) {
                            muleModel.enterMap();
                        }
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
                        String placementMessage = muleModel.placeMule(type, new Property(new Point(xInd, yInd)));
                        errorText.setText(placementMessage);
                        muleModel.updatePlayerInfoText(playerInfoText);
                        muleModel.updateSinglePropertyImage(button);
                        muleModel.squarifyNode(button);
                        if (placementMessage == null) {
                            muleModel.enterMap();
                        }
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


    public final void setMuleType(String t) {
        this.type = t;
    }
    public final void loadModel(MuleModel model) {
        muleModel = model;
    }
}
