package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorScreen {
	@FXML
	private Button calculateButton = new Button();
	@FXML
	private TextField firstRowSizeField = new TextField();
	@FXML
	private TextField secondRowSizeField = new TextField();
	@FXML
	private TextField firstColSizeField = new TextField();
	@FXML
	private TextField secondColSizeField = new TextField();

	@FXML
	private void calculateButtonAction(ActionEvent event) {
		String firstRowSize = firstRowSizeField.getText().trim();
		String firstColSize = firstColSizeField.getText().trim();
		String secondRowSize = secondRowSizeField.getText().trim();
		String secondColSize = secondColSizeField.getText().trim();
		
	}
}
