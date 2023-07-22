package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChangeStage {
	public void show(String viewDirectory, ActionEvent event) throws Exception{
		//Close current stage
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        
        //Show new stage
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(viewDirectory));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        newStage.setScene(scene);
        newStage.show();
	}

}
