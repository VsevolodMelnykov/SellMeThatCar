package anchors;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import Exceptions.IncorrectUserException;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Login extends Main{

    @FXML
    private AnchorPane ap;

    @FXML
    private Button lb;

    @FXML
    private TextField lt;

    @FXML
    private PasswordField pt;

    @FXML
    private Button rb;

    /**
     * login button
     * @param event
     * @throws MalformedURLException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IncorrectUserException vlastna vynimka
     */
    @FXML
    void lb(ActionEvent event) throws MalformedURLException, IOException, ClassNotFoundException, IncorrectUserException{
    	if (login(lt.getText(), pt.getText())) {
    		if (premium(lt.getText(), pt.getText())) {
    			Stage stage = (Stage) rb.getScene().getWindow();
        		stage.close();
        		java.net.URL url = new File("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\anchors\\shop.fxml").toURI().toURL();
        		FXMLLoader fxmlLoader = new FXMLLoader(url);
        		Parent root1 = (Parent) fxmlLoader.load();
        		stage = new Stage();
        		stage.initModality(Modality.APPLICATION_MODAL);
        		stage.setScene(new Scene(root1));
        		stage.show();
    		}
    		else {
    			Stage stage = (Stage) rb.getScene().getWindow();
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
    	else {
    		throw new IncorrectUserException(lt.getText() + ": login or password is incorrect");
    	}
    }

    /**
     * register button
     * @param event
     * @throws IOException
     */
    @FXML
    void rb(ActionEvent event) throws IOException {
    	Stage stage = (Stage) rb.getScene().getWindow();
    	stage.close();
    	java.net.URL url = new File("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\anchors\\register.fxml").toURI().toURL();
    	FXMLLoader fxmlLoader = new FXMLLoader(url);
    	Parent root1 = (Parent) fxmlLoader.load();
    	stage = new Stage();
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setScene(new Scene(root1));
    	stage.show();

    }

}
