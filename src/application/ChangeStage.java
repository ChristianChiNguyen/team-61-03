package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Represent a class to change from this view to another view */
public class ChangeStage {
	
	/** Function to close current view and open new view 
	 * @Param viewDirectory directory of the .fxml file of the new view 
	 * @param event event when clicking a button */
	public void show(String viewDirectory, ActionEvent event) throws Exception{
		//Close current stage/view
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        
        //Show new stage/view
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(viewDirectory));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        newStage.setScene(scene);
        newStage.show();
	}
}
