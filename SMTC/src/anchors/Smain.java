package anchors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import Auction.Accepted;
import Auction.Auction;
import Auction.Confirmed;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Smain extends Main{

	Auction a;
	Accepted aa;
	Confirmed aaa;
	int bi;
	
	@FXML
	private Button ab;
	
    @FXML
    private Button acc;

    @FXML
    private Button act;

    @FXML
    private Text addr;
    
    @FXML
    private Button bid;

    @FXML
    private Text bidd;

    @FXML
    private Text biddd;

    @FXML
    private Button ca;
    
    @FXML
    private Button confirm;

    @FXML
    private Text id;

    @FXML
    private Button na;

    @FXML
    private Text name;

    @FXML
    private Button nb;

    @FXML
    private Text owner;

    @FXML
    private Button pa;

    @FXML
    private Button pb;

    @FXML
    private Button shi;
    
    @FXML
    private Text shipped;

    @FXML
    private Text st;

    /**
     * akceptovanie ceny
     * @param event
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @FXML
    void ab(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
    	if (act.isDisabled()) {
    		acceptbid(biddd.getText(), bidd.getText(), a);
    		a = getactive();
    		if (a != null) {
        		name.setText(a.getName());
        		owner.setText(a.getOwner().getId());
        		addr.setText(a.getAddr());
        		id.setText(a.getId());
            	if (a.bid.size() != 0) {
            		bidd.setText(Integer.toString(a.bid.get(bi)));
            		biddd.setText(a.bidder.get(bi).getId());
            	}
            	else {
            		bidd.setText("Bid");
            		biddd.setText("Bidder");
            	}
    		}
    	}
    	if (acc.isDisabled()) {
    		cancelacc(aa);
    		aa = getaccep();
        	if (aa != null) {
        		name.setText(aa.getName());
        		owner.setText(aa.getOwner().getId());
        		addr.setText(aa.getAddr());
        		id.setText(aa.getId());
        		if(aa.getabidder() != null){
            		bidd.setText(Integer.toString(aa.getabid()));
            		biddd.setText(aa.getabidder().getId());
            	}
            	else {
            		bidd.setText("Bid");
            		biddd.setText("Bidder");
            	}
        	}
    	}
    	if (shi.isDisabled()) {
    		cancelship(aaa);
    		aaa = getship();
    		if (aaa != null) {
        		name.setText(aaa.getName());
        		owner.setText(aaa.getOwner().getId());
        		addr.setText(aaa.getAddr());
        		id.setText(aaa.getId());
        		if(aaa.getabidder() != null){
        			bidd.setText(Integer.toString(aaa.getabid()));
            		biddd.setText(aaa.getabidder().getId());
            	}
        		else {
            		bidd.setText("Bid");
            		biddd.setText("Bidder");
            	}
            	st.setText(Float.toString(aaa.getTime()));
            	shipped.setText(String.valueOf(aaa.isShipped()));
    		}
    	}
    }
    
    /**
     * preklikavanie na svoje akceptovane aukcie
     * @param event
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @FXML
    void acc(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
    	act.setDisable(false);
    	acc.setDisable(true);
    	shi.setDisable(false);
    	nb.setDisable(true);
    	nb.setVisible(false);
    	pb.setDisable(true);
    	pb.setVisible(false);
    	ab.setText("Cancel");
    	st.setVisible(false);
    	confirm.setVisible(true);
    	confirm.setDisable(false);
    	aa = nextaccep(aa);
    	shipped.setVisible(false);
    	if (aa != null) {
    		name.setText(aa.getName());
    		owner.setText(aa.getOwner().getId());
    		addr.setText(aa.getAddr());
    		id.setText(aa.getId());
    		if(aa.getabidder() != null){
        		bidd.setText(Integer.toString(aa.getabid()));
        		biddd.setText(aa.getabidder().getId());
        	}
        	else {
        		bidd.setText("Bid");
        		biddd.setText("Bidder");
        	}
    	}
    }

    /**
     * preklikavanie na svoje aktivne aukcie
     * @param event
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @FXML
    void act(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
    	act.setDisable(true);
    	acc.setDisable(false);
    	shi.setDisable(false);
    	nb.setDisable(false);
    	nb.setVisible(true);
    	pb.setDisable(false);
    	pb.setVisible(true);
    	ab.setText("Accept");
    	st.setVisible(false);
    	a = nextactive(a);
    	confirm.setVisible(false);
    	confirm.setDisable(true);
    	shipped.setVisible(false);
    	if (a != null) {
    		name.setText(a.getName());
    		owner.setText(a.getOwner().getId());
    		addr.setText(a.getAddr());
    		id.setText(a.getId());
        	if (a.bid.size() != 0) {
        		bidd.setText(Integer.toString(a.bid.get(bi)));
        		biddd.setText(a.bidder.get(bi).getId());
        	}
        	else {
        		bidd.setText("Bid");
        		biddd.setText("Bidder");
        	}
    	}
    }
    
    /**
     * Preklikavanie na Shop.fxml (navrhovanie cen)
     * @param event
     * @throws IOException
     */
    @FXML
    void bid(ActionEvent event) throws IOException {
    	Stage stage = (Stage) bid.getScene().getWindow();
    	stage.close();
    	java.net.URL url = new File("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\anchors\\shop.fxml").toURI().toURL();
    	FXMLLoader fxmlLoader = new FXMLLoader(url);
    	Parent root1 = (Parent) fxmlLoader.load();
    	stage = new Stage();
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setScene(new Scene(root1));
    	stage.show();
    }

    /**
     * vytvaranie aukcii
     * @param event
     * @throws IOException
     */
    @FXML
    void ca(ActionEvent event) throws IOException {
    	Stage stage = (Stage) ca.getScene().getWindow();
    	stage.close();
    	java.net.URL url = new File("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\anchors\\screateauction.fxml").toURI().toURL();
    	FXMLLoader fxmlLoader = new FXMLLoader(url);
    	Parent root1 = (Parent) fxmlLoader.load();
    	stage = new Stage();
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setScene(new Scene(root1));
    	stage.show();
    }
    
    /**
     * podtvrdenie ceny pre shipping
     * @param event
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @FXML
    void confirm(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
    	aa = getaccep();
    	System.out.print(aa.getId());
    	confirmship(aa);
    }

    /**
     * nasledujuca aukcia
     * @param event
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @FXML
    void na(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
    	if (act.isDisabled()) {
    		a = nextactive(a);
        	if (a != null) {
        		name.setText(a.getName());
        		owner.setText(a.getOwner().getId());
        		addr.setText(a.getAddr());
        		id.setText(a.getId());
        		bi = 0;
            	if (a.bid.size() != 0) {
            		bidd.setText(Integer.toString(a.bid.get(bi)));
            		biddd.setText(a.bidder.get(bi).getId());
            	}
            	else {
            		bidd.setText("Bid");
            		biddd.setText("Bidder");
            	}
        	}
    	}
    	if (acc.isDisabled()) {
    		aa = nextaccep(aa);
        	if (aa != null) {
        		name.setText(aa.getName());
        		owner.setText(aa.getOwner().getId());
        		addr.setText(aa.getAddr());
        		id.setText(aa.getId());
        		if(aa.getabidder() != null){
            		bidd.setText(Integer.toString(aa.getabid()));
            		biddd.setText(aa.getabidder().getId());
            	}
            	else {
            		bidd.setText("Bid");
            		biddd.setText("Bidder");
            	}
        	}
    	}
    	if (shi.isDisabled()) {
    		aaa = nextship(aaa);
        	if (aaa != null) {
        		name.setText(aaa.getName());
        		owner.setText(aaa.getOwner().getId());
        		addr.setText(aaa.getAddr());
        		id.setText(aaa.getId());
        		if(aaa.getabidder() != null){
        			bidd.setText(Integer.toString(aaa.getabid()));
            		biddd.setText(aaa.getabidder().getId());
            	}
        		else {
            		bidd.setText("Bid");
            		biddd.setText("Bidder");
            	}
            	st.setText(Float.toString(aaa.getTime()));
            	shipped.setText(String.valueOf(aaa.isShipped()));
        	}
    	}
    }

    /**
     * nasledujuca cena
     * @param event
     */
    @FXML
    void nb(ActionEvent event) {
    	if (a.bid.size() != 0) {
    		if (bi < a.bid.size() - 1) {
    			bi += 1;
        	}
    		bidd.setText(Integer.toString(a.bid.get(bi)));
    		biddd.setText(a.bidder.get(bi).getId());
    	}
    }
    
    /**
     * predchadzajuca aukcia
     * @param event
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @FXML
    void pa(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
    	if (act.isDisabled()) {
    		a = prevactive(a);
        	if (a != null) {
        		name.setText(a.getName());
        		owner.setText(a.getOwner().getId());
        		addr.setText(a.getAddr());
        		id.setText(a.getId());
        		bi = 0;
            	if (a.bid.size() != 0) {
            		bidd.setText(Integer.toString(a.bid.get(bi)));
            		biddd.setText(a.bidder.get(bi).getId());
            	}
            	else {
            		bidd.setText("Bid");
            		biddd.setText("Bidder");
            	}
        	}
    	}
    	if (acc.isDisabled()) {
    		aa = prevaccep(aa);
        	if (aa != null) {
        		name.setText(aa.getName());
        		owner.setText(aa.getOwner().getId());
        		addr.setText(aa.getAddr());
        		id.setText(aa.getId());
            	if(aa.getabidder() != null){
            		bidd.setText(Integer.toString(aa.getabid()));
            		biddd.setText(aa.getabidder().getId());
            	}
            	else {
            		bidd.setText("Bid");
            		biddd.setText("Bidder");
            	}
        	}
    	}
    	if (shi.isDisabled()) {
    		aaa = prevship(aaa);
        	if (aaa != null) {
        		name.setText(aaa.getName());
        		owner.setText(aaa.getOwner().getId());
        		addr.setText(aaa.getAddr());
        		id.setText(aaa.getId());
        		if(aaa.getabidder() != null){
        			bidd.setText(Integer.toString(aaa.getabid()));
            		biddd.setText(aaa.getabidder().getId());
            	}
        		else {
            		bidd.setText("Bid");
            		biddd.setText("Bidder");
            	}
            	st.setText(Float.toString(aaa.getTime()));
            	shipped.setText(String.valueOf(aaa.isShipped()));
        	}
    	}
    }

    /**
     * predchadzajuca cena
     * @param event
     */
    @FXML
    void pb(ActionEvent event) {
    	if (a.bid.size() != 0) {
    		if (bi > 0) {
    			bi -= 1;
        	}
    		bidd.setText(Integer.toString(a.bid.get(bi)));
    		biddd.setText(a.bidder.get(bi).getId());
    	}
    }
    
    /**
     * preklikavanie na svoje shipping aukcie
     * @param event
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @FXML
    void shi(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
    	act.setDisable(false);
    	acc.setDisable(false);
    	shi.setDisable(true);
    	nb.setDisable(true);
    	nb.setVisible(false);
    	pb.setDisable(true);
    	pb.setVisible(false);
    	ab.setText("Cancel");
    	st.setVisible(true);
    	aaa = getship();
    	confirm.setVisible(false);
    	confirm.setDisable(true);
    	shipped.setVisible(true);
    	if (aaa != null) {
    		name.setText(aaa.getName());
    		owner.setText(aaa.getOwner().getId());
    		addr.setText(aaa.getAddr());
    		id.setText(aaa.getId());
    		if(aaa.getabidder() != null){
    			bidd.setText(Integer.toString(aaa.getabid()));
        		biddd.setText(aaa.getabidder().getId());
        	}
    		else {
        		bidd.setText("Bid");
        		biddd.setText("Bidder");
        	}
        	st.setText(Float.toString(aaa.getTime()));
        	shipped.setText(String.valueOf(aaa.isShipped()));
        }
    }

    /**
     * inicializacia dat hned po otvoreni
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @FXML
    void initialize() throws FileNotFoundException, ClassNotFoundException, IOException {
    	act.setDisable(true);
    	a = nextactive(a);
    	st.setVisible(false);
    	shipped.setVisible(false);
    	confirm.setVisible(false);
    	confirm.setDisable(true);
    	if (a != null) {
    		name.setText(a.getName());
    		owner.setText(a.getOwner().getId());
    		addr.setText(a.getAddr());
    		id.setText(a.getId());
        	if (a.bid.size() != 0) {
        		bidd.setText(Integer.toString(a.bid.get(bi)));
        		biddd.setText(a.bidder.get(bi).getId());
        	}
    	}
    }

}
