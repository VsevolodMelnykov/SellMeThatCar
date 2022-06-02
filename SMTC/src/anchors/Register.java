package anchors;

import java.io.File;
import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Register extends Main{

    @FXML
    private TextField lt;

    @FXML
    private CheckBox pc;

    @FXML
    private PasswordField pt;

    @FXML
    private Button rb;

    /**
     * register button
     * @param event
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    void rb(ActionEvent event) throws IOException, ClassNotFoundException {
    	register(lt.getText(), pt.getText(), pc.isSelected());
    	Stage stage = (Stage) rb.getScene().getWindow();
    	stage.close();
    	java.net.URL url = new File("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\anchors\\login.fxml").toURI().toURL();
    	FXMLLoader fxmlLoader = new FXMLLoader(url);
    	Parent root1 = (Parent) fxmlLoader.load();
    	stage = new Stage();
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setScene(new Scene(root1));
    	stage.show();
    }
}
