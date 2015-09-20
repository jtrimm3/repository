import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.Parent;
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

    private final String TOWN_IMAGE = "town.png";
    private final String RIVER_IMAGE = "river.png";
    private final String PLAIN_IMAGE = "plain.png";
    private final String M1_IMAGE = "mountain1.png";
    private final String M2_IMAGE = "mountain2.png";
    private final String M3_IMAGE = "mountain3.png";
    private final String SQUARE_IMAGE = "TransparentSquare.png";

    @FXML
    private GridPane mapGridPane = new GridPane();



    @Override
    public void initialize(URL url, ResourceBundle rb) {


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
        BackgroundImage townImageView, m1ImageView, m2ImageView, m3ImageView,riverImageView,plainImageView;
        for (Node node : paneChildren) {
            Integer xInd = mapGridPane.getColumnIndex(node);
            Integer yInd = mapGridPane.getRowIndex(node);
            if (townCoordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                BackgroundSize size = new BackgroundSize(button.getMinWidth(), button.getMinHeight(),
                        false,false,false,false);
                townImageView = new BackgroundImage(new Image(TOWN_IMAGE),null,null,null,size);
                button.setBackground(new Background(townImageView));
                button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        muleModel.enterTown((Stage) node.getScene().getWindow());
                    }
                });
            } else if (mountain1Coordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                BackgroundSize size = new BackgroundSize(button.getMinWidth(), button.getMinHeight(),
                        false,false,false,false);
                m1ImageView = new BackgroundImage(new Image(M1_IMAGE),null,null,null,size);
                button.setBackground(new Background(m1ImageView));
            } else if (mountain2Coordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                BackgroundSize size = new BackgroundSize(button.getMinWidth(), button.getMinHeight(),
                        false,false,false,false);
                m2ImageView = new BackgroundImage(new Image(M2_IMAGE),null,null,null,size);
                button.setBackground(new Background(m2ImageView));
            } else if (mountain3Coordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                BackgroundSize size = new BackgroundSize(button.getMinWidth(), button.getMinHeight(),
                        false,false,false,false);
                m3ImageView = new BackgroundImage(new Image(M3_IMAGE),null,null,null,size);
                button.setBackground(new Background(m3ImageView));
            } else if (riverCoordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                BackgroundSize size = new BackgroundSize(button.getMinWidth(), button.getMinHeight(),
                        false,false,false,false);
                riverImageView = new BackgroundImage(new Image(RIVER_IMAGE),null,null,null,size);
                button.setBackground(new Background(riverImageView));
            } else if (plainCoordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                BackgroundSize size = new BackgroundSize(button.getMinWidth(), button.getMinHeight(),
                        false,false,false,false);
                plainImageView = new BackgroundImage(new Image(PLAIN_IMAGE),null,null,null,size);
                button.setBackground(new Background(plainImageView));
            }
        }
        for (Node node : paneChildren) {
            Button button = (Button) node;
            button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    ImageView square = new ImageView(SQUARE_IMAGE);
                    square.setFitHeight(button.getMinHeight());
                    square.setFitWidth(button.getMinWidth());
                    button.setGraphic(square);
                }
            });

            button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    button.setGraphic(null);
                }
            });
        }
    }




    public void loadModel(MuleModel model) {
        muleModel = model;
    }
}
