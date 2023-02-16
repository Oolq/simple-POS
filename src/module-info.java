module pos {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.opencsv;
	requires javafx.graphics;
	
	
	opens application to javafx.graphics, javafx.fxml,javafx.base;
}
