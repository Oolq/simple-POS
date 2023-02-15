package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class PosController implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;
    @FXML
    private Button NewProduct;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnPurchase;

    @FXML
    private Button btnRemove;
    
    

  
    @FXML
    private TableColumn<Product, String> code;

    @FXML
    private TableColumn<Product, ImageView> image;

    @FXML
    private TableColumn<Product, Double> price;

    @FXML
    private TableColumn<Product, String> product;

    @FXML
    private TableView<Product> productTable;
    
    @FXML
    private TableColumn<Product, Double> listPrice;

    @FXML
    private TableColumn<Product, String> listProduct;

    @FXML
    private TableColumn<Product , Integer> listQty;


    @FXML
    private TableView<Product> purchaseTable;

    @FXML
    private TextField searchBar;

    @FXML
    private TextField txtCash;

    @FXML
    private Label txtChange;


    @FXML
    private Label txtPrice;

    @FXML
    private Label txtProduct;

    @FXML
    private TextField txtQty;

    @FXML
    private Label txtSubTotal;

    @FXML
    private Label txtTotal;

    @FXML
    private Label txtVat;
    
    private ObservableList<Product> data = FXCollections.observableArrayList();
    private ObservableList<Product> products = FXCollections.observableArrayList();
   
    Integer index;
    @FXML
    void add(ActionEvent event) throws IOException {
    	
    	Double fprice = (Integer.parseInt(txtQty.getText())*(Double.parseDouble(txtPrice.getText())));
    	Product product = new Product(Integer.parseInt(txtQty.getText()),
    									txtProduct.getText(),
    									fprice);
        products.add(product);
        purchaseTable.setItems(products);
    	Double subtotal = 0.0;
    	for (int i = 0; i < products.size(); i++) {
			    Product row = products.get(i);
			    subtotal += row.getProductPrice();
			    }
    		txtSubTotal.setText(subtotal.toString());
			Double vat = subtotal * .12;
			txtVat.setText(vat.toString());
			
			Double total = (subtotal + vat);
			txtTotal.setText(total.toString());
	    }
    			
    
    @FXML
   void clear(ActionEvent event) {
	   products.clear();
	   purchaseTable.setItems(products);
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

    @FXML
    void purchase(ActionEvent event) throws IOException {
    
	    
	    Double total = Double.valueOf(txtTotal.getText());
    	Double cash = Double.valueOf(txtCash.getText());
    	if(cash > total) {
    	Double change = cash - total;
    	
    	File file = new File("/home/shiba/Documents/JavaProj/simple-POS/Database/Invoice.txt");
	    try (FileWriter wroter = new FileWriter(file)) {
	    	purchaseTable.setItems(products);
	     	for (int i = 0; i < products.size(); i++) {
	 			    Product row = products.get(i);
	 			    String productname = row.getProductName();
	 			    String productprice = String.valueOf(row.getProductPrice());
	 			    String qty = String.valueOf(row.getQty());
	 			    wroter.write( productname+","+ productprice+"," + qty + "," + cash.toString() + "," + change.toString()+ "\n");
	     			}
	 	    			wroter.close();
	    }
	    
    	txtChange.setText(change.toString());
    	products.clear();
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
	  		alert.setTitle("NOT ENOUGH WAWART");
	  		alert.setHeaderText("kulang kag kwarta");
	  		alert.setContentText("pangayu sa mama");
	  		alert.showAndWait();
    	}
    	  stage = new Stage();
    	    root = FXMLLoader.load(getClass().getResource("Invoice.fxml"));
    	    stage.initModality(Modality.APPLICATION_MODAL);
    	    stage.setScene(new Scene(root));
    	    stage.showAndWait();
    }

    @FXML
    void remove(ActionEvent event) {
    
    	int selectedID = purchaseTable.getSelectionModel().getSelectedIndex();
        purchaseTable.getItems().remove(selectedID);
          
          purchaseTable.setItems(products);
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
	@FXML
    void getItem(MouseEvent event) {
    	index = productTable.getSelectionModel().getSelectedIndex();
    	
    	if(index <= -1) {
    		return;
    	}
    	txtProduct.setText(product.getCellData(index).toString());
    	txtPrice.setText(price.getCellData(index).toString());
    }
    @FXML
    void SwitchToNewProduct(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			initialize();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 productTable.setItems(data);
          image.setCellValueFactory(new PropertyValueFactory<Product, ImageView>("productImage"));
          code.setCellValueFactory(new PropertyValueFactory<Product, String>("productCode"));
          price.setCellValueFactory(new PropertyValueFactory<Product, Double>("productPrice"));
         product.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
         
         listProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
         listQty.setCellValueFactory(new PropertyValueFactory<Product, Integer>("qty"));
         listPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("productPrice"));
         
        
         FilteredList<Product> filteredData = new FilteredList<>(data, b -> true);
 		
 	
         searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
 			filteredData.setPredicate(product -> {
 				
 								if (newValue == null || newValue.isEmpty()) {
 					return true;
 				}
 				String lowerCaseFilter = newValue.toLowerCase();
 				if (product.getProductName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
 					return true; 
 				} else if (product.getProductCode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
 					return true; 
 				} else 
 					return false; 
 				
 				
 			});
 		});
 		
 		
 		SortedList<Product> sortedData = new SortedList<>(filteredData);
 		sortedData.comparatorProperty().bind(productTable.comparatorProperty());
 		productTable.setItems(sortedData);
 		purchaseTable.setItems(products);
 	
	}
	public void initialize() throws NumberFormatException, IOException {
	  	BufferedReader reader = new BufferedReader(new FileReader("/home/shiba/Documents/JavaProj/simple-POS/Database/Product.txt"));
	    String Line;
	  
	  data.clear();
	  while ((Line = reader.readLine()) != null) {
	   	 String[] nextLine = Line.split(",");
	        String product = nextLine[0];
	        String code = nextLine[1];
	        String price = nextLine[2];
	        String image = nextLine[3];
	        
	        System.out.print(image);
	        
	        
	        ImageView imageView = new ImageView(new Image(new FileInputStream("/home/shiba/Documents/JavaProj/simple-POS/Product/"+ image)));
	        imageView.setFitHeight(140);
	        imageView.setFitWidth(140);
	        data.add(new Product(product, code, Double.parseDouble(price), imageView ));
	    
	    }
	    System.out.println(data);
	    
	    reader.close();
	}
	

}