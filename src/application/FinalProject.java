package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/// ALL OF THE ACTUAL CODE IS WITHIN MainController.java
// JAVAFX MAKES YOU CREATE A SEPERATE JAVA FILE FOR BUTTON LISTENERS
/*
Connection conn = DriverManager.getConnection(url, user, pass);
Statement stmnt = conn.createStatement();
 */

public class FinalProject extends Application {
	@Override
	public void start(Stage stage) throws Exception{
		Parent parent = FXMLLoader.load(getClass().getResource("ManagementStudentSystem.fxml"));
		Scene scene = new Scene(parent);
		stage.setTitle("Management Student System");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}