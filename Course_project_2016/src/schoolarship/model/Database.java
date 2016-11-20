package schoolarship.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Database {
	private Connection con;

	public Database() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/mydbperson", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String QuerySelect(String field1) {
		String k = "";
		try {
			Statement st = con.createStatement();
			String zapros = "select password from user where login='" + field1 + "'";
			ResultSet r = st.executeQuery(zapros);
			while (r.next()) {
				k = r.getString("password");
			}
			st.close();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Неверные поля");
			alert.setHeaderText("Ошибка в БД");
			alert.setContentText("Error: " + e);
			alert.showAndWait();
		}
		return k;
	}

	public boolean Query(String field1, String field2) {
		try {
			PreparedStatement stmt = con.prepareStatement
					("INSERT INTO user " + "(iduser, login, password) VALUES (?, ?, ?)");
			stmt.setString(1, null);
			stmt.setString(2, field1);
			stmt.setString(3, field2);
			stmt.execute();
			stmt.close();
			return true;
		} catch (MySQLIntegrityConstraintViolationException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Неверные поля");
			alert.setHeaderText("Такой логин уже существует");
			alert.showAndWait();
			return false;
		} catch(SQLException e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Ошибка в БД");
			alert.setContentText("Error: " + e);
			alert.showAndWait();
			return false;
		}
	}
}
