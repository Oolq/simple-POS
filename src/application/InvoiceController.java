package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InvoiceController implements Initializable{

    @FXML
    private TableColumn<Product, Double> listPrice;

    @FXML
    private TableColumn<Product, String> listProduct;

    @FXML
    private TableColumn<Product, Integer> listQty;

    @FXML
    private TableView<Product> purchaseTable;

    @FXML
    private Label txtCash;

    @FXML
    private Label txtChange;

    @FXML
    private Label txtSubTotal;

    @FXML
    private Label txtTotal;

    @FXML
    private Label txtVat;
    private ObservableList<Product> products = FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		try {
			initialize();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		purchaseTable.setItems(products);
			listProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
	         listQty.setCellValueFactory(new PropertyValueFactory<Product, Integer>("qty"));
	         listPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("productPrice"));
	        
	        

	}
	
	public void initialize() throws NumberFormatException, IOException{
		BufferedReader reader = new BufferedReader(new FileReader("/home/shiba/Documents/JavaProj/simple-POS/Database/Invoice.txt")); 
			String Line;
			 Line = reader.readLine();
			 String[] newLine = Line.split(",");
			 String cash = newLine[3];
			 String change = newLine[4];
			 txtCash.setText(cash);
			 txtChange.setText(change);
		try (BufferedReader readers = new BufferedReader(new FileReader("/home/shiba/Documents/JavaProj/simple-POS/Database/Invoice.txt"))) {
			String line;
 while ((line = readers.readLine()) != null) {
			 String[] nextLine = line.split(",");
			   String Name = nextLine[0];
			   String Price = nextLine[1];
			   String Qty = nextLine[2];
			   System.out.print(Name);
			   
			 
			 products.add(new Product(Integer.parseInt(Qty),
						Name,
						Double.parseDouble(Price)));
 }
		} 
				reader.close();
				Double subtotal = 0.0;
		     	for (int i = 0; i < products.size(); i++) {
		 			    Product row = products.get(i);
		 			    subtotal += row.getProductPrice();
		 			    System.out.print(row.getProductName());
		 			} 
		 			txtSubTotal.setText(subtotal.toString());
		 			Double vat = subtotal * .12;
		 			txtVat.setText(vat.toString());
		 			
		 			Double total = (subtotal + vat);
		 			txtTotal.setText(total.toString());
			 
			 
		}
         
	}



