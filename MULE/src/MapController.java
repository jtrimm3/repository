import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;

import javax.print.DocFlavor;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Peter on 9/17/2015.
 */
public class MapController implements Initializable {
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

    @FXML
    private AnchorPane anchor = new AnchorPane();

    @FXML
    private void highlight() {

    }

    @FXML
    private void unhighlight() {

    }


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
        ImageView townImageView = new ImageView(TOWN_IMAGE);
        ImageView m1ImageView = new ImageView(M1_IMAGE);
        ImageView m2ImageView = new ImageView(M2_IMAGE);
        ImageView m3ImageView = new ImageView(M3_IMAGE);
        ImageView riverImageView = new ImageView(RIVER_IMAGE);
        ImageView plainImageView = new ImageView(PLAIN_IMAGE);
        for (Node node : paneChildren) {
            Integer xInd = mapGridPane.getColumnIndex(node);
            Integer yInd = mapGridPane.getRowIndex(node);
            if (townCoordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                townImageView = new ImageView(TOWN_IMAGE);
                townImageView.setFitHeight(button.getMinHeight());
                townImageView.setFitWidth(button.getMinWidth());
                button.setGraphic(townImageView);
            } else if (mountain1Coordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                m1ImageView = new ImageView(M1_IMAGE);
                m1ImageView.setFitHeight(button.getMinHeight());
                m1ImageView.setFitWidth(button.getMinWidth());
                button.setGraphic(m1ImageView);
            } else if (mountain2Coordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                m2ImageView = new ImageView(M2_IMAGE);
                m2ImageView.setFitHeight(button.getMinHeight());
                m2ImageView.setFitWidth(button.getMinWidth());
                button.setGraphic(m2ImageView);
            } else if (mountain3Coordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                m3ImageView = new ImageView(M3_IMAGE);
                m3ImageView.setFitHeight(button.getMinHeight());
                m3ImageView.setFitWidth(button.getMinWidth());
                button.setGraphic(m3ImageView);
            } else if (riverCoordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                riverImageView = new ImageView(RIVER_IMAGE);
                riverImageView.setFitHeight(button.getMinHeight());
                riverImageView.setFitWidth(button.getMinWidth());
                button.setGraphic(riverImageView);
            } else if (plainCoordinates.contains(new Point(xInd, yInd))) {
                Button button = (Button) node;
                plainImageView = new ImageView(PLAIN_IMAGE);
                plainImageView.setFitHeight(button.getMinHeight());
                plainImageView.setFitWidth(button.getMinWidth());
                button.setGraphic(plainImageView);
            }


        }
        


    }
}
