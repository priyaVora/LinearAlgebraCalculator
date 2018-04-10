package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorScreen implements Initializable {
	Map<String, TextField> mapTextField;
	Map<String, TextField> mapTextField2;

	@FXML
	private Button calculateButton;
	@FXML
	private GridPane gridFirstMatrix;
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

	public CalculatorScreen() {
		resetGridTextFields();
	}

	public void resetGridTextFields() {
		mapTextField = new HashMap<>();
		mapTextField2 = new HashMap<>();
		operationChoiceBox = new ChoiceBox<String>();
		calculateButton = new Button();
		setButton = new Button();
		gridFirstMatrix = new GridPane();
		firstMatrix = new Pane();
		secondMatrix = new Pane();
		firstRowSizeField = new TextField();
		firstColSizeField = new TextField();
		secondRowSizeField = new TextField();
		secondColSizeField = new TextField();
		dimension1 = new Button();
		dimension2 = new Button();

	}

	@FXML
	private void setAction() {
		String firstRowSize = firstRowSizeField.getText().trim();
		String firstColSize = firstColSizeField.getText().trim();
		String secondRowSize = secondRowSizeField.getText().trim();
		String secondColSize = secondColSizeField.getText().trim();

		if (firstRowSize.isEmpty() && firstColSize.isEmpty() && secondRowSize.isEmpty() && secondColSize.isEmpty()) {
			dimension1.setDisable(true);
			dimension2.setDisable(true);
		} else if (firstRowSize.isEmpty() || firstColSize.isEmpty() || secondRowSize.isEmpty()
				|| secondColSize.isEmpty()) {
			dimension1.setDisable(true);
			dimension2.setDisable(true);
		} else {
			dimension1.setDisable(false);
			dimension2.setDisable(false);

			String dimensionLabelOne = firstRowSize + "X" + firstColSize;
			String dimensionLabelTwo = secondRowSize + "X" + secondColSize;

			System.out.println("First Matrix Dimension: " + dimensionLabelOne);
			System.out.println("Second Matrix Dimension: " + dimensionLabelTwo);

			if (Integer.parseInt(firstRowSize) > 4 || Integer.parseInt(firstColSize) > 4
					|| Integer.parseInt(secondColSize) > 4 || Integer.parseInt(secondRowSize) > 4) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Dimensions are larger than 4X4");
				alert.setContentText("Click on Dimension button to calculate!");

				alert.showAndWait();

			} else {
				dimension1.setText(dimensionLabelOne);
				dimension2.setText(dimensionLabelTwo);
			}

		}
	}

	public List<String> getValidPositions(int rowSize, int colSize) {
		List<String> validPositionList = new ArrayList<String>();
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				String validPosition = row + "," + col + "";
				validPositionList.add(validPosition.trim());
				System.out.print(row);
				System.out.print(",");
				System.out.print(col);
				System.out.print(" ");
			}
			System.out.println(" ");
		}
		return validPositionList;
	}

	@FXML
	private void calculateButtonAction(ActionEvent event) {
		String firstRowSize = firstRowSizeField.getText().trim();
		String firstColSize = firstColSizeField.getText().trim();
		String secondRowSize = secondRowSizeField.getText().trim();
		String secondColSize = secondColSizeField.getText().trim();

		if (firstRowSize.isEmpty() && firstColSize.isEmpty() && secondRowSize.isEmpty() && secondColSize.isEmpty()) {
			dimension1.setDisable(true);
			dimension2.setDisable(true);
		} else if (firstRowSize.isEmpty() || firstColSize.isEmpty() || secondRowSize.isEmpty()
				|| secondColSize.isEmpty()) {
			dimension1.setDisable(true);
			dimension2.setDisable(true);
		} else {
			dimension1.setDisable(false);
			dimension2.setDisable(false);

			String dimensionLabelOne = firstRowSize + "X" + firstColSize;
			String dimensionLabelTwo = secondRowSize + "X" + secondColSize;

			System.out.println("First Matrix Dimension: " + dimensionLabelOne);
			System.out.println("Second Matrix Dimension: " + dimensionLabelTwo);

			if (Integer.parseInt(firstRowSize) > 4 || Integer.parseInt(firstColSize) > 4
					|| Integer.parseInt(secondColSize) > 4 || Integer.parseInt(secondRowSize) > 4) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Dimensions are larger than 4X4");
				alert.setContentText("Click on Dimension button to calculate!");

				alert.showAndWait();
				dimension1.setText(dimensionLabelOne);
				dimension2.setText(dimensionLabelTwo);
			}

			// Stage stageTheLabelBelongs = new Stage();
			// stageTheLabelBelongs.setScene(new Scene(alert, 450, 450));
			// stageTheLabelBelongs.show();
		}
	}

	@FXML
	private void dimension1Action(ActionEvent event) {
		String firstRowSize = firstRowSizeField.getText().trim();
		String firstColSize = firstColSizeField.getText().trim();
		String secondRowSize = secondRowSizeField.getText().trim();
		String secondColSize = secondColSizeField.getText().trim();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dimension1.setDisable(true);
		dimension2.setDisable(true);
		operationChoiceBox.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Priya Vora: " + operationChoiceBox.getItems());
				Integer indexSelected = operationChoiceBox.getSelectionModel().getSelectedIndex();
				System.out.println(operationChoiceBox.getItems().get(indexSelected));
				
			}
		});
	}

}
