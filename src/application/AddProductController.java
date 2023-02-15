package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.opencsv.CSVWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddProductController implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button btnBack;

    @FXML
    private ImageView image;

    @FXML
    private TextField productCode;

    @FXML
    private TextField productImagePath;

    @FXML
    private TextField productName;

    @FXML
    private TextField productPrice;
    FileChooser fileChooser = new FileChooser();
   
    @Override
   	public void initialize(URL arg0, ResourceBundle arg1) {
       	fileChooser.setInitialDirectory(new File("/home/shiba/Documents/JavaProj/simple-POS/Product"));
       	
       }
    public void initialize() {
		image = new ImageView();
	}
    @FXML
    void addProduct(ActionEvent event) throws IOException {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setHeaderText("INPUT CONFIRMATION");
    	alert.setContentText("Are you ok with this?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		String product = productName.getText().toString();
        	String code = productCode.getText().toString();
        	Double price  = Double.valueOf(productPrice.getText().toString());
        	String imagepath = productImagePath.getText().toString();
        	
        	FileWriter fileWriter = new FileWriter("/home/shiba/Documents/JavaProj/simple-POS/Database/Product.csv", true);
    	    CSVWriter writer = new CSVWriter(fileWriter);
    	    String[] data = {product, code, price.toString(), imagepath};
    	    File file = new File("/home/shiba/Documents/JavaProj/simple-POS/Database/Product.txt");
    	    try (FileWriter wroter = new FileWriter(file, true)) {
    	    	
    			wroter.write( product +","+ code +"," + price.toString()+","+ imagepath+"\n");
    			wroter.close();
    	    }
    	    writer.writeNext(data);
    	    writer.close();
    	} else {
    	    
    	}
    }

    @FXML
    void ChooseFile(ActionEvent event) {
    	  File file =  fileChooser.showOpenDialog(new Stage());
		  if (file != null) {
			    String fileName = file.getName();
			    System.out.print(fileName);
			    image.setImage(new Image(file.toURI().toString()));
			    productImagePath.setText(fileName);
			} else {
				Alert alert = new Alert(AlertType.ERROR);
		  		alert.setTitle("No File Detected");
		  		alert.setHeaderText("No Image Choosen");
		  		alert.setContentText("TRY AGAIN");
		  		alert.showAndWait();
			}
    }

    @FXML
    void ChoosePicture(ActionEvent event) {
    	 try {
		        Runtime.getRuntime().exec("guvcview");
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
    }

    @FXML
    void goBackToDashboard(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("Pos.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    	
    }

}
