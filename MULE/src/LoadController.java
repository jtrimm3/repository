import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Peter on 11/4/2015.
 */
public class LoadController implements Controller, Initializable {
    private MuleModel muleModel;
    private List<File> fileList;

    //@FXML
    //private Button load = new Button();

    @FXML
    private ComboBox<File> box = new ComboBox<>();

    @Override
    public final void initialize(URL url, ResourceBundle rb) {
        box.setItems(FXCollections.observableArrayList(fileList));
    }

    @FXML
    public final void load() {
        File file = box.getValue();
        Stage stage = muleModel.getStage();
        MuleModel newModel = muleModel.loadGame(file.getName());
        loadModel(newModel);
        muleModel.setStage(stage);
        muleModel.backFromSaveScreen(null, null, null, null, null);
    }


    public final void setFileList(List<File> list) {
        fileList = list;
    }

    public final void loadModel(MuleModel model) {
        this.muleModel = model;
    }


}
