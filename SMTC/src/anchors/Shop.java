package anchors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import Auction.Auction;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Shop extends Main {

	Auction a = null;
	
    @FXML
    private Text addr;

    @FXML
    private Button bidb;

    @FXML
    private TextField bidf;

    @FXML
    private Text id;

    @FXML
    private Button ma;

    @FXML
    private Button na;

    @FXML
    private Text name;

    @FXML
    private Text owner;

    @FXML
    private Button pa;

    /**
     * tlacidlo pre navrh aukcie
     * @param event
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @FXML
    void bidb(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
    	 bid(bidf.getText(), a);
    	 bidf.setText("");
    }

    /**
     * My auctions button
     * @param event
     * @throws IOException
     */
    @FXML
    void ma(ActionEvent event) throws IOException {
    	Stage stage = (Stage) ma.getScene().getWindow();
    	stage.close();
    	java.net.URL url = new File("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\anchors\\smain.fxml").toURI().toURL();
    	FXMLLoader fxmlLoader = new FXMLLoader(url);
    	Parent root1 = (Parent) fxmlLoader.load();
    	stage = new Stage();
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setScene(new Scene(root1));
    	stage.show();
    }

    /**
     * next auction
     * @param event
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @FXML
    void na(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
    	a = nextactive2(a);
    	if (a != null) {
    		name.setText(a.getName());
    		owner.setText(a.getOwner().getId());
    		addr.setText(a.getAddr());
    		id.setText(a.getId());
    	}
    }

    /**
     * previous auction
     * @param event
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @FXML
    void pa(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
    	a = prevactive2(a);
    	if (a != null) {
    		name.setText(a.getName());
    		owner.setText(a.getOwner().getId());
    		addr.setText(a.getAddr());
    		id.setText(a.getId());
    	}
    }
    
    /**
     * inicializacia hned okna po otvarani
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @FXML
    void initialize() throws FileNotFoundException, ClassNotFoundException, IOException {
    	a = nextactive2(a);
    	if (a != null) {
    		name.setText(a.getName());
    		owner.setText(a.getOwner().getId());
    		addr.setText(a.getAddr());
    		id.setText(a.getId());
    	}
    }

}
