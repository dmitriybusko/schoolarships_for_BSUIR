package schoolarship.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import schoolarship.MainApp;
import schoolarship.model.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AuthorizationController {
	@FXML
	private TextField loginField;
	@FXML
	private PasswordField passField;
	Database db = new Database();
	private MainApp mainapp = new MainApp();
	private Stage dialogStage;
	@FXML
	private void initialize() {
		loginField.setText("admin");
		passField.setText("admin");
	}
	@FXML
	private void handleEnter() {
		String k = db.QuerySelect(loginField.getText());
		if(k.equals(passField.getText())){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("”спешно!");
			alert.setHeaderText("¬ход успешно выполнен!");
			alert.showAndWait();
			mainapp.startmain();
			dialogStage.close();
		}else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("¬нимание!");
			alert.setHeaderText("¬веден неправильный логин или пароль!");
			alert.showAndWait();
		}
	}
	@FXML
	private void handleSignIn() {
		mainapp.showRegistration();
	}
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}