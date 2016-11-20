package schoolarship.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import schoolarship.model.*;
import javafx.scene.control.Alert.AlertType;

public class RegistrationController {
	@FXML
	private TextField loginField;
	@FXML
	private PasswordField passField1;
	@FXML
	private PasswordField passField2;
	Database db = new Database();
	private Stage dialogStage;

	@FXML
	private void handleSignIn() {
		if (passField1.getText().equals(passField2.getText())) {
			if (db.Query(loginField.getText(), passField1.getText())) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Регистрация завершена");
				alert.setHeaderText("Для авторизации в программе используйте свой логин и пароль");
				alert.showAndWait();
				dialogStage.close();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Неверные поля");
			alert.setHeaderText("Пароли должны совпадать");
			alert.showAndWait();
		}
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void handleBack() {
		dialogStage.close();
	}
}
