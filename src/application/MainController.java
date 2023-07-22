package application;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;

public class MainController implements Initializable{
	
	ChangeStage changeStage = new ChangeStage();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	 	@FXML
	    public void Logout(ActionEvent event) throws Exception {
	 		String viewDirectory = "/application/Login.fxml";
	 		changeStage.show(viewDirectory, event);
	    }
	}


