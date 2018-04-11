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

	int resultRow = 0;
	int resultCol = 0;

	@FXML
	private Button calculateButton;
	@FXML
	private GridPane gridFirstMatrix;
	@FXML
	private GridPane gridSecondMatrix;
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
		operationTypeLabel = new Label();
		calculateButton = new Button();
		setButton = new Button();
		gridFirstMatrix = new GridPane();
		gridSecondMatrix = new GridPane();
		firstMatrix = new Pane();
		secondMatrix = new Pane();
		firstRowSizeField = new TextField();
		firstColSizeField = new TextField();
		secondRowSizeField = new TextField();
		secondColSizeField = new TextField();
		dimension1 = new Button();
		dimension2 = new Button();

	}

	private String generateMapCellName(int row, int col) {
		return "Cell_" + row + "_" + col;
	}

	public void addOperation() {
		String firstRowSize = firstRowSizeField.getText().trim();
		String firstColSize = firstColSizeField.getText().trim();
		int row1Size = Integer.parseInt(firstRowSize);
		int col1Size = Integer.parseInt(firstColSize);

		String secondRowSize = secondRowSizeField.getText().trim();
		String secondColSize = secondColSizeField.getText().trim();

		int row2Size = Integer.parseInt(secondRowSize);
		int col2Size = Integer.parseInt(secondColSize);

		if (row1Size == row2Size && col1Size == col2Size) {
			operationHelper(row1Size, col1Size, row2Size, col2Size, firstRowSize, firstColSize, secondRowSize,
					secondColSize);
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Dimensions must be the same for both matrices!");
			alert.setContentText("Please Reset!");

			alert.showAndWait();

		}
	}

	public void operationHelper(int row1Size, int col1Size, int row2Size, int col2Size, String firstRowSize,
			String firstColSize, String secondRowSize, String secondColSize) {
		for (int i = 0; i < row1Size; i++) {
			for (int j = 0; j < col1Size; j++) {
				String name = generateMapCellName(i, j);
				TextField field = new TextField();
				field.getStyleClass().add("gridTextField");
				gridFirstMatrix.add(field, j, i);
				mapTextField.put(name, field);
			}
		}

		for (int i = 0; i < row2Size; i++) {
			for (int j = 0; j < col2Size; j++) {
				String name = generateMapCellName(i, j);
				TextField field = new TextField();
				field.getStyleClass().add("gridTextField2");
				gridSecondMatrix.add(field, j, i);
				mapTextField2.put(name, field);
			}
		}

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

			if (Integer.parseInt(firstRowSize) > 10 || Integer.parseInt(firstColSize) > 10
					|| Integer.parseInt(secondColSize) > 10 || Integer.parseInt(secondRowSize) > 10) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Dimensions are larger than 4X4");
				alert.setContentText("Click on Dimension button to calculate!");

				alert.showAndWait();

			} else {
				dimension1.setText(dimensionLabelOne);
				dimension2.setText(dimensionLabelTwo);
			}

			List<String> validPositionList = new ArrayList<String>();
			List<String> validPositionListTwo = new ArrayList<String>();

			validPositionList = getValidPositions(Integer.parseInt(firstRowSize), Integer.parseInt(firstColSize));
			System.out.println(" ");
			validPositionListTwo = getValidPositions(Integer.parseInt(secondRowSize), Integer.parseInt(secondColSize));
		}
	}

	public void multiplicationOperation() {
		String firstRowSize = firstRowSizeField.getText().trim();
		String firstColSize = firstColSizeField.getText().trim();
		int row1Size = Integer.parseInt(firstRowSize);
		int col1Size = Integer.parseInt(firstColSize);

		String secondRowSize = secondRowSizeField.getText().trim();
		String secondColSize = secondColSizeField.getText().trim();

		int row2Size = Integer.parseInt(secondRowSize);
		int col2Size = Integer.parseInt(secondColSize);

		if (row1Size == row2Size) {
			operationHelper(row1Size, col1Size, row2Size, col2Size, firstRowSize, firstColSize, secondRowSize,
					secondColSize);
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("The column of first matrix must match the row of second matrix!");
			alert.setContentText("Please Reset!");

			alert.showAndWait();

		}
	}

	@FXML
	private void setAction() {
		int operationIndexSelected = operationChoiceBox.getSelectionModel().getSelectedIndex();
		String operationSelected = operationChoiceBox.getItems().get(operationIndexSelected);
		if (operationSelected.equals("Add Matrices") || operationSelected.equals("Subtract Matrices")) {

			addOperation();

		} else if (operationSelected.equals("Multiply Matrices")) {
			multiplicationOperation();
		} else {
			System.out.println("User has not selected any operation...");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Must select an operation type to continue!");
			alert.setContentText("Select operation type in the dropdown.");

			alert.showAndWait();
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

	private void activelySetDimensions(String firstRowSize, String firstColSize, String secondRowSize,
			String secondColSize) {
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

			if (Integer.parseInt(firstRowSize) > 10 || Integer.parseInt(firstColSize) > 10
					|| Integer.parseInt(secondColSize) > 10 || Integer.parseInt(secondRowSize) > 10) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Dimensions are larger than 10X10");
				alert.setContentText("Click on Dimension button to calculate!");

				alert.showAndWait();
				dimension1.setText(dimensionLabelOne);
				dimension2.setText(dimensionLabelTwo);
			}
		}
	}

	@FXML
	private void calculateButtonAction(ActionEvent event) {
		calculateButton.setDisable(false);
		String firstRowSize = firstRowSizeField.getText().trim();
		String firstColSize = firstColSizeField.getText().trim();
		String secondRowSize = secondRowSizeField.getText().trim();
		String secondColSize = secondColSizeField.getText().trim();

		activelySetDimensions(firstRowSize, firstColSize, secondRowSize, secondColSize);

		// Stage stageTheLabelBelongs = new Stage();
		// stageTheLabelBelongs.setScene(new Scene(alert, 450, 450));
		// stageTheLabelBelongs.show();
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
		setButton.setDisable(true);
		calculateButton.setDisable(true);

		operationChoiceBox.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Integer indexSelected = operationChoiceBox.getSelectionModel().getSelectedIndex();
				System.out.println(operationChoiceBox.getItems().get(indexSelected));
				operationTypeLabel.setText(operationChoiceBox.getItems().get(indexSelected));
				setButton.setDisable(false);
				calculateButton.setDisable(false);

			}
		});
	}

}
