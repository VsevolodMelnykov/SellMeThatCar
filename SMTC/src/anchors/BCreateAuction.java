package anchors;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BCreateAuction extends Main{

    @FXML
    private TextField br;

    @FXML
    private Button cancel;

    @FXML
    private TextField ci;

    @FXML
    private TextField co;

    @FXML
    private Button confirm;

    @FXML
    private TextField id;

    @FXML
    private TextField mo;

    @FXML
    private TextField sh;

    /**
     * odmena vytvarania aukcii
     * @param event
     * @throws IOException
     */
    @FXML
    void cancel(ActionEvent event) throws IOException {
    	Stage stage = (Stage) cancel.getScene().getWindow();
    	stage.close();
    	java.net.URL url = new File("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\anchors\\bmain.fxml").toURI().toURL();
    	FXMLLoader fxmlLoader = new FXMLLoader(url);
    	Parent root1 = (Parent) fxmlLoader.load();
    	stage = new Stage();
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setScene(new Scene(root1));
    	stage.show();
    }

    
    /**
     * podtvrdenie vytvarania aukcii
     * @param event
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    void confirm(ActionEvent event) throws IOException, ClassNotFoundException {
    	createauction(id.getText(), br.getText() + " " + mo.getText(), co.getText() + ", " + ci.getText() + ", " + sh.getText());
    	Stage stage = (Stage) cancel.getScene().getWindow();
    	stage.close();
    	java.net.URL url = new File("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\anchors\\bmain.fxml").toURI().toURL();
    	FXMLLoader fxmlLoader = new FXMLLoader(url);
    	Parent root1 = (Parent) fxmlLoader.load();
    	stage = new Stage();
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setScene(new Scene(root1));
    	stage.show();
    }

}
