package application;
	
import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.print.DocFlavor.URL;

import Auction.*;
import User.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;

/**
 * @author Ctdf6
 *
 */
public class Main extends Application {
	/**
	 * thread that decreases shipping time of auctions
	 * @author Ctdf6
	 *
	 */
	class Timer extends Thread {
	    public Timer(String crunchifyString) {
	        super(crunchifyString);
	    }
	 
	    public void run() {
	    	while(true) {
	    		try {
					loaddata2();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
	    		for (int y = 0; y < confirmed.size(); y++) {
	    			System.out.print(confirmed.get(y).getTime());
	    			confirmed.get(y).setTime(1);
	    		}
	    		try {
					savedata2();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    		try {
					sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    	}
	    }
	}
	
	/**
	 * zoznam pouzivatelov
	 * 
	 */
	public ArrayList<User> users = new ArrayList<>();
	/**
	 * zoznam nakupcov
	 */
	public ArrayList<Buyer> buyers = new ArrayList<>();
	/**
	 * zoznam predajcov
	 */
	public ArrayList<Seller> sellers = new ArrayList<>();
	/**
	 * zoznam bezacich aukcii
	 */
	public ArrayList<Auction> auctions= new ArrayList<>();
	/**
	 * zoznam prijatych aukcii
	 */
	public ArrayList<Accepted> accepted = new ArrayList<>();
	/**
	 * zoznam shipping aukcii
	 */
	public ArrayList<Confirmed> confirmed = new ArrayList<>();
	public Stage stage = new Stage();
	public Scene scene = null;
	public User current = null;
	
	//active auction indexes for GUI
	public int pos = -1;
	public int posi = 0;
	public int posit = 0;
	public int pos2 = -1;
	
	/**
	 * akceptovanie aukcii na dopravu
	 * @param a
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void confirmship(Accepted a) throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		for (int i = 0; i < accepted.size(); i++) {
			if (accepted.get(i).getId().equals(a.getId())) {
				confirmed.get(i).setorder();
			}
		}
		savedata2();
	}
	
	/**
	 * odmena akceptovanej aukcii
	 * @param auc
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void cancelacc(Accepted auc) throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		for (int i = 0; i < accepted.size(); i++) {
			if(accepted.get(i).getId().equals(auc.getId())) {
				accepted.get(i).cancelorder();
				confirmed.get(i).cancelorder();
			}
		}
		savedata2();
	}
	
	/**
	 * odmena shipping aukcii
	 * @param auc
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void cancelship(Confirmed auc) throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		for (int i = 0; i < confirmed.size(); i++) {
			if(confirmed.get(i).getId().equals(auc.getId())) {
				confirmed.get(i).cancelship();
			}
		}
		savedata2();
	}

	/**
	 * obnovenie informacii o aktualne ukazujucej sa aukcii pre guicko
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Auction getactive() throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		if (auctions.size() != 0) {
			return auctions.get(pos);
		}
		return null;
	}
	/**
	 * obnovenie informacii o aktualne ukazujucej sa aukcii pre guicko
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Accepted getaccep() throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		if (accepted.size() != 0) {
			return accepted.get(pos);
		}
		return null;
	}
	/**
	 * obnovenie informacii o aktualne ukazujucej sa aukcii pre guicko
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Confirmed getship() throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		if (confirmed.size() != 0) {
			return confirmed.get(pos);
		}
		return null;
	}
	
	/**
	 * akceptovanie ceny
	 * @param owner
	 * @param bid
	 * @param auc
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void acceptbid(String owner, String bid, Auction auc) throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		for (int j = 0; j < sellers.size(); j++) {
			if (sellers.get(j).getId().equals(owner)) {
				for (int i = 0; i < accepted.size(); i++) {
					if (accepted.get(i).getId().equals(auc.getId())) {
						accepted.get(i).setorder(Integer.valueOf(bid), sellers.get(j));
						break;
					}
				}
				for (int i = 0; i < confirmed.size(); i++) {
					if (confirmed.get(i).getId().equals(auc.getId())) {
						confirmed.get(i).setorder(Integer.valueOf(bid), sellers.get(j));
						break;
					}
				}
			}
		}
		savedata2();
	}
	
	/**
	 * navrhovanie ceny
	 * @param bid
	 * @param auc
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void bid(String bid, Auction auc) throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		int b = Integer.valueOf(bid);
		for (int i = 0; i < auctions.size(); i++) {
			if (auctions.get(i).getId().equals(auc.getId())) {
				auctions.get(i).addbid(b, users.get(0));
			}
		}
		for (int i = 0; i < sellers.size(); i++) {
			if (sellers.get(i).getId().equals(users.get(0).getId())) {
				sellers.get(i).addauction(b, auc);
			}
		}
		savedata2();
	}
	
	/**
	 * auction changer pre okno navrhovania cen predajca
	 * @param us
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Auction nextactive2(Auction us) throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		for (int i = pos2 + 1 ; i < auctions.size(); i++) {
			Auction a = auctions.get(i);
			if (!a.getOwner().getId().equals(users.get(0).getId())) {
				pos2 = i;
				us = a;
				break;
			}
		}
		return us;
	}
	/**
	 * auction changer pre okno navrhovania cen predajca
	 * @param us
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Auction prevactive2(Auction us) throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		for (int i = pos2 - 1 ; i > -1; i--) {
			Auction a = auctions.get(i);
			if (!a.getOwner().getId().equals(users.get(0).getId())) {
				pos2 = i;
				us = a;
				break;
			}
		}
		return us;
	}	

	/**
	 * auction changer pre svoje aktivne aukcie pouzivatela
	 * @param us
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Auction nextactive(Auction us) throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		for (int i = pos + 1 ; i < auctions.size(); i++) {
			Auction a = auctions.get(i);
			if (a.getOwner().getId().equals(users.get(0).getId())) {
				pos = posi = posit = i;
				us = a;
				break;
			}
		}
		return us;
	}
	/**
	 * auction changer pre svoje aktivne aukcie pouzivatela
	 * @param us
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Auction prevactive(Auction us) throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		for (int i = pos - 1 ; i > -1; i--) {
			Auction a = auctions.get(i);
			if (a.getOwner().getId().equals(users.get(0).getId())) {
				pos = posi = posit = i;
				us = a;
				break;
			}
		}
		return us;
	}
	/**
	 * auction changer pre svoje akceptovane aukcie pouzivatela
	 * @param us
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Accepted nextaccep(Accepted us) throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		for (int i = posi + 1 ; i < accepted.size(); i++) {
			Accepted a = accepted.get(i);
			if (a.getOwner().getId().equals(users.get(0).getId())) {
				pos = posi = posit = i;
				us = a;
				break;
			}
		}
		return us;
	}
	/**
	 * auction changer pre svoje akceptovane aukcie pouzivatela
	 * @param us
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Accepted prevaccep(Accepted us) throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		for (int i = posi - 1 ; i > -1; i--) {
			Accepted a = accepted.get(i);
			if (a.getOwner().getId().equals(users.get(0).getId())) {
				pos = posi = posit = i;
				us = a;
				break;
			}
		}
		return us;
	}
	/**
	 * auction changer pre svoje shipping aukcie pouzivatela
	 * @param us
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Confirmed nextship(Confirmed us) throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		for (int i = posit + 1 ; i < accepted.size(); i++) {
			Confirmed a = confirmed.get(i);
			if (a.getOwner().getId().equals(users.get(0).getId())) {
				pos = posi = posit = i;
				us = a;
				break;
			}
		}
		return us;
	}
	/**
	 * auction changer pre svoje shipping aukcie pouzivatela
	 * @param us
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Confirmed prevship(Confirmed us) throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		for (int i = posit - 1 ; i > -1; i--) {
			Confirmed a = confirmed.get(i);
			if (a.getOwner().getId().equals(users.get(0).getId())) {
				pos = posi = posit = i;
				us = a;
				break;
			}
		}
		return us;
	}
	
	/**
	 * vytvaranie aukcie
	 * @param id
	 * @param name
	 * @param addr
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void createauction(String id, String name, String addr) throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata2();
		auctions.add(new Auction(users.get(0), id, name, addr));
		accepted.add(new Accepted(users.get(0), id, name, addr));
		confirmed.add(new Confirmed(users.get(0), id, name, addr));
		if(users.get(0).getPrem()) {
			for (int i = 0; i < sellers.size(); i++) {
				if (sellers.get(i).getId().equals(users.get(0).getId())) {
					sellers.get(i).addauction(auctions.get(auctions.size()-1));
				}
			}
		}
		else {
			for (int i = 0; i < buyers.size(); i++) {
				if (buyers.get(i).getId().equals(users.get(0).getId())) {
					buyers.get(i).addauction(auctions.get(auctions.size()-1));
				}
			}
		}
		savedata2();
	}

	/**
	 * ispremium check pri logine
	 * @param lt
	 * @param pt
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public boolean premium(String lt, String pt) throws IOException, ClassNotFoundException {
		if (users.get(0).getPrem() == true) {
			return true;
		}
		return false;
	}
	/**
	 * registracia pouzivatela
	 * @param lt
	 * @param pt
	 * @param pre
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void register(String lt, String pt, boolean pre) throws FileNotFoundException, ClassNotFoundException, IOException {
		loaddata();
		users.add(new User(lt, pt, pre));
		if(pre){
			sellers.add(new Seller(lt, pt, pre));
		}
		else {
			buyers.add(new Buyer(lt, pt, pre));
		}
		savedata();
		return;
	}
	/**
	 * login 
	 * @param lt
	 * @param pt
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public boolean login(String lt, String pt) throws IOException, ClassNotFoundException {
		loaddata();
		boolean lo = false;
		for (int i = 0; i < users.size(); i++) {
			User a = users.get(i);
			if (lt.equals(a.getId()) && pt.equals(a.getPw())) {
				current = a;
				users.clear();
				users.add(current);
				savedata2();
				 new Timer("Timer").start();
				lo = true;
			}
		}
		return lo;
	}
	
	/**
	 * serealizacia load
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void loaddata() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream uin=new ObjectInputStream(new FileInputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\users.txt"));        
		Object uobject = uin.readObject();
		users = (ArrayList) uobject;
		uin.close(); 
		
		ObjectInputStream bin=new ObjectInputStream(new FileInputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\buyers.txt"));        
		Object bobject = bin.readObject();
		
		
		buyers = (ArrayList) bobject;
		bin.close(); 
		
		ObjectInputStream sin=new ObjectInputStream(new FileInputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\sellers.txt"));        
		Object sobject = sin.readObject();
		
		
		sellers = (ArrayList) sobject;
		sin.close(); 
		
		ObjectInputStream ain=new ObjectInputStream(new FileInputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\auctions.txt"));        
		Object aobject = ain.readObject();
		
		
		auctions = (ArrayList) aobject;
		ain.close(); 
		
		ObjectInputStream in=new ObjectInputStream(new FileInputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\accepted.txt"));        
		Object object = in.readObject();
		
		
		accepted = (ArrayList) object;
		in.close(); 
		
		ObjectInputStream cin=new ObjectInputStream(new FileInputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\confirmed.txt"));        
		Object cobject = cin.readObject();
		
		
		confirmed = (ArrayList) cobject;
		cin.close(); 
	}
	/**
	 * serealizacia save
	 * @throws IOException
	 */
	public void savedata() throws IOException {
		FileOutputStream uout=new FileOutputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\users.txt");    
        ObjectOutputStream u_out=new ObjectOutputStream(uout);    
        u_out.writeObject(users);
        u_out.flush();
        u_out.close();
		
		FileOutputStream bout=new FileOutputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\buyers.txt");    
        ObjectOutputStream b_out=new ObjectOutputStream(bout);    
        b_out.writeObject(buyers);
        b_out.flush();
        b_out.close();
        
        FileOutputStream sout=new FileOutputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\sellers.txt");    
        ObjectOutputStream s_out=new ObjectOutputStream(sout); 
        s_out.writeObject(sellers);
        s_out.flush();    
        s_out.close(); 		
        
        FileOutputStream aout=new FileOutputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\auctions.txt");    
        ObjectOutputStream a_out=new ObjectOutputStream(aout);    
        a_out.writeObject(auctions);
        a_out.flush();
        a_out.close();
		
        FileOutputStream out=new FileOutputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\accepted.txt");    
        ObjectOutputStream _out=new ObjectOutputStream(out);    
        _out.writeObject(accepted);
        _out.flush();
        _out.close();
        
        FileOutputStream cout=new FileOutputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\confirmed.txt");    
        ObjectOutputStream c_out=new ObjectOutputStream(cout);    
        c_out.writeObject(confirmed);
        c_out.flush();
        c_out.close();
        
	}
	/**
	 * serealizacia load
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void loaddata2() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream uin=new ObjectInputStream(new FileInputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\users2.txt"));        
		Object uobject = uin.readObject();
		users = (ArrayList) uobject;
		uin.close();
		
		ObjectInputStream bin=new ObjectInputStream(new FileInputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\buyers.txt"));        
		Object bobject = bin.readObject();
		
		
		buyers = (ArrayList) bobject;
		bin.close(); 
		
		ObjectInputStream sin=new ObjectInputStream(new FileInputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\sellers.txt"));        
		Object sobject = sin.readObject();
		
		
		sellers = (ArrayList) sobject;
		sin.close(); 
		
		ObjectInputStream ain=new ObjectInputStream(new FileInputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\auctions.txt"));        
		Object aobject = ain.readObject();
		
		
		auctions = (ArrayList) aobject;
		ain.close(); 
		
		ObjectInputStream in=new ObjectInputStream(new FileInputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\accepted.txt"));        
		Object object = in.readObject();
		
		
		accepted = (ArrayList) object;
		in.close(); 
		
		ObjectInputStream cin=new ObjectInputStream(new FileInputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\confirmed.txt"));        
		Object cobject = cin.readObject();
		
		
		confirmed = (ArrayList) cobject;
		cin.close(); 
	}
	/**
	 * serealizacia save
	 * @throws IOException
	 */
	public void savedata2() throws IOException {
		
		FileOutputStream uout=new FileOutputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\users2.txt");    
        ObjectOutputStream u_out=new ObjectOutputStream(uout);    
        u_out.writeObject(users);
        u_out.flush();
        u_out.close();
		
		FileOutputStream bout=new FileOutputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\buyers.txt");    
        ObjectOutputStream b_out=new ObjectOutputStream(bout);    
        b_out.writeObject(buyers);
        b_out.flush();
        b_out.close();
        
        FileOutputStream sout=new FileOutputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\sellers.txt");    
        ObjectOutputStream s_out=new ObjectOutputStream(sout); 
        s_out.writeObject(sellers);
        s_out.flush();    
        s_out.close(); 		
        
        FileOutputStream aout=new FileOutputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\auctions.txt");    
        ObjectOutputStream a_out=new ObjectOutputStream(aout);    
        a_out.writeObject(auctions);
        a_out.flush();
        a_out.close();
		
        FileOutputStream out=new FileOutputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\accepted.txt");    
        ObjectOutputStream _out=new ObjectOutputStream(out);    
        _out.writeObject(accepted);
        _out.flush();
        _out.close();
        
        FileOutputStream cout=new FileOutputStream("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\confirmed.txt");    
        ObjectOutputStream c_out=new ObjectOutputStream(cout);    
        c_out.writeObject(confirmed);
        c_out.flush();
        c_out.close();
        
	}
	
	/**
	 * inicializacia
	 */
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException, IOException, ClassNotFoundException {		

		loaddata();
		System.out.print("Users\n");
		for (int i = 0; i < users.size(); i++) {
			User a = users.get(i);
			System.out.print(a.getId() + " " + a.getPw() + " " + a.getPrem() + "\n");
		}
		System.out.print("Buyers\n");
		for (int i = 0; i < buyers.size(); i++) {
			Buyer a = buyers.get(i);
			System.out.print(a.getId() + " " + a.getPw() + " " + a.getPrem() + "\n");
			for (int j = 0; j < a.getauctions().size(); j++) {
				Auction b = a.getauctions().get(j);
				System.out.print(b.getOwner() + " " + b.getId() + " " + b.getName() + " " + b.getAddr() + "\n");
				for (int k = 0; k < b.getBid().size(); k++) {
					System.out.print(b.getBid().get(j) + " " + b.getBidder().get(j) + "\n");
				}
			}
		}
		System.out.print("Sellers\n");
		for (int i = 0; i < sellers.size(); i++) {
			Seller a = sellers.get(i);
			System.out.print(a.getId() + " " + a.getPw() + " " + a.getPrem() + "\n");
			for (int j = 0; j < a.getauctions().size(); j++) {
				Auction b = a.getauctions().get(j);
				System.out.print(b.getOwner() + " " + b.getId() + " " + b.getName() + " " + b.getAddr() + "\n");
				for (int k = 0; k < b.getBid().size(); k++) {
					System.out.print(b.getBid().get(j) + " " + b.getBidder().get(j) + "\n");
				}
			}
		}
		
		System.out.print("Auctions\n");
		for (int i = 0; i < auctions.size(); i++) {
			Auction a = auctions.get(i);
			System.out.print(a.getOwner() + " " + a.getId() + " " + a.getName() + " " + a.getAddr() + "\n");
			for (int j = 0; j < a.getBid().size(); j++) {
				System.out.print(a.getBid().get(j) + " " + a.getBidder().get(j) + "\n");
			}
		}
		System.out.print("Accepted\n");
		for (int i = 0; i < accepted.size(); i++) {
			Accepted a = accepted.get(i);
			System.out.print(a.getOwner() + " " + a.getId() + " " + a.getName() + " " + a.getAddr() + " " + a.getabid() + " " + a.getabidder() + "\n");
		}
		System.out.print("Confirmed\n");
		for (int i = 0; i < confirmed.size(); i++) {
			Confirmed a = confirmed.get(i);
			System.out.print(a.getOwner() + " " + a.getId() + " " + a.getName() + " " + a.getAddr() + "\n");
		}
		
		savedata();
		java.net.URL url = new File("C:\\Users\\Ctdf6\\eclipse-workspace\\SMTC\\src\\anchors\\login.fxml").toURI().toURL();
		Parent root = FXMLLoader.load(url);
		scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}