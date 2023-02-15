package application;

import java.time.LocalDateTime;

public class Invoice {
private Product[] product;
private String refNum;
private String subtotal;
private String total;
private String vat;
private LocalDateTime currentDateTime;

public Product[] getProduct() {
	return product;
}
public void setProduct(Product[] product) {
	this.product = product;
}
public String getRefNum() {
	return refNum;
}
public void setRefNum(String refNum) {
	this.refNum = refNum;
}
public String getSubtotal() {
	return subtotal;
}
public void setSubtotal(String subtotal) {
	this.subtotal = subtotal;
}
public String getTotal() {
	return total;
}
public void setTotal(String total) {
	this.total = total;
}
public String getVat() {
	return vat;
}
public void setVat(String vat) {
	this.vat = vat;
}
public LocalDateTime getCurrentDateTime() {
	return currentDateTime;
}
public void setCurrentDateTime(LocalDateTime currentDateTime) {
	this.currentDateTime = currentDateTime;
}
}
