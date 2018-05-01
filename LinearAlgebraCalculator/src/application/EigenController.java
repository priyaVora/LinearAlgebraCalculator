package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import control.MatrixCalculator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Matrix;

public class EigenController implements Initializable {
	@FXML
	private Button Back;

	@FXML
	private Button calculateButton;
	@FXML
	private Button showWorkButton;
	@FXML
	private GridPane gridFirstMatrix;
	@FXML
	private GridPane gridSecondMatrix;

	@FXML
	private TextField lambdaField;
	@FXML
	private Label lambdaLabel;

	@FXML
	private GridPane resultGrid;
	@FXML
	private TextField firstRowSizeField;
	@FXML
	private TextField secondRowSizeField;
	@FXML
	private TextField firstColSizeField;
	@FXML
	private TextField secondColSizeField;
	@FXML
	private Label operationTypeLabel;
	@FXML
	private ChoiceBox<String> operationChoiceBox;
	@FXML
	private Button dimension1;
	@FXML
	private Button dimension2;
	@FXML
	private Button setButton;
	@FXML
	private Pane firstMatrix;
	@FXML
	private Pane secondMatrix;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		operationChoiceBox.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {
				showWorkButton.setVisible(true);
				Integer indexSelected = operationChoiceBox.getSelectionModel().getSelectedIndex();
				System.out.println(operationChoiceBox.getItems().get(indexSelected));
				operationTypeLabel.setText(operationChoiceBox.getItems().get(indexSelected));

				setButton.setDisable(false);
				calculateButton.setDisable(false);
			}
		});
	}

	@FXML
	private void calculateButtonAction(ActionEvent event) throws NumberFormatException, IOException {
		System.out.println("Calculated!");
	}

	@FXML
	private void setAction() {
		System.out.println("Set Action!");
	}

	@FXML
	private void backAction(ActionEvent e) {
		System.out.println("Back!");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CalculatorScreen.fxml"));
		Parent root;
		try {
			root = loader.load();
			CalculatorScreen controller = loader.getController();

			// call method from controller to set Data
			Stage stage = new Stage();
			stage.setTitle("Linear Algebra Calculator");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			// Hide this current window (if this is what you want)
			((Node) (e.getSource())).getScene().getWindow().hide();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
