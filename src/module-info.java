module DataStructsFinal {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires mysql.connector.java;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
}
