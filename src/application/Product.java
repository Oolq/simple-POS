package application;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

public class Product {
	private SimpleIntegerProperty qty;
	private SimpleStringProperty productName;
	private SimpleStringProperty productCode;
	private SimpleDoubleProperty	productPrice;
	private ImageView productImage;
	public Product(String productname, String productcode, double productprice, ImageView productImage) {
		super();
		this.productName = new SimpleStringProperty(productname);
		this.productCode = new SimpleStringProperty(productcode);
		this.productPrice = new SimpleDoubleProperty(productprice);
		this.productImage = productImage;
	}
	
	public Product(int Qty, String productname, double productprice) {
		super();
		this.qty = new SimpleIntegerProperty(Qty);
		this.productName = new SimpleStringProperty(productname);
		this.productPrice = new SimpleDoubleProperty(productprice);
	}
	
	public String getProductName() {
		return productName.get();
	}
	public void setProductName(String productname) {
		this.productName.set(productname);
	}
	public String getProductCode() {
		
				return productCode.get();
	}
	public void setProductCode(String productcode) {
		this.productCode.set(productcode);;
	}
	public double getProductPrice() {
		return productPrice.get();
	}
	public void setProductPrice(double productprice) {
		this.productPrice.set(productprice);;
	}
	public ImageView getProductImage() {
		return productImage;
	}
	public void setProductImage(ImageView productImage) {
		this.productImage = productImage;
	}
	public int getQty() {
		return qty.get();
	}
	public void setQty(int Qty) {
		this.qty.set(Qty);
	}
	

}
