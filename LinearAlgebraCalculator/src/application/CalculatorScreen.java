package application;

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
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;

import model.Matrix;

public class CalculatorScreen implements Initializable {
	MatrixCalculator cal = new MatrixCalculator();
	Map<String, TextField> mapTextField;
	Map<String, TextField> mapTextField2;
	Map<String, TextField> resultMap;

	int resultRow = 0;
	int resultCol = 0;

	@FXML
	private Button calculateButton;
	@FXML
	private Button showWorkButton;
	@FXML
	private GridPane gridFirstMatrix;
	@FXML
	private GridPane gridSecondMatrix;
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

	public CalculatorScreen() {
		resetGridTextFields();
	}

	public void resetGridTextFields() {
		mapTextField = new HashMap<>();
		mapTextField2 = new HashMap<>();
		resultMap = new HashMap<>();
		showWorkButton = new Button();

		operationChoiceBox = new ChoiceBox<String>();
		operationTypeLabel = new Label();
		calculateButton = new Button();
		setButton = new Button();
		gridFirstMatrix = new GridPane();
		gridSecondMatrix = new GridPane();
		resultGrid = new GridPane();
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
			gridFirstMatrix.getChildren().clear();
			gridSecondMatrix.getChildren().clear();
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

		if (col1Size == row2Size) {
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
		mapTextField = new HashMap<>();
		mapTextField2 = new HashMap<>();
		resultMap = new HashMap<>();
		
		
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

	public int[][] printHashMap(Map<String, TextField> map, int row, int col) {
		int[][] dataArray = new int[row][col];
		System.out.println("Prints Hashmap: ");
		for (int currentRow = 0; currentRow < row; currentRow++) {
			for (int currentCol = 0; currentCol < col; currentCol++) {
				System.out.print(currentRow);
				System.out.print(",");
				System.out.print(currentCol);
				System.out.print(" ");

				String getValueFrom = "Cell_" + currentRow + "_" + currentCol;
				System.out.println("VALUE: " + map.get(getValueFrom).getText());
				dataArray[currentRow][currentCol] = Integer.parseInt(map.get(getValueFrom).getText().trim());
			}
			System.out.println(" ");
		}
		return dataArray;
	}

	public void matrixOperation(int row1, int col1, int row2, int col2) {
		// I need to get the data from hashmap
		int[][] dataOne = printHashMap(mapTextField, row1, col1);
		int[][] dataTwo = printHashMap(mapTextField2, row2, col2);

		Matrix resultMatrix = new Matrix();

		Matrix a = new Matrix("FirstMatrix", row1, col1);
		a.setCurrentMatrix(dataOne);
		Matrix b = new Matrix("SecondMatrix", row2, col2);
		b.setCurrentMatrix(dataTwo);

		if (operationTypeLabel.getText().equals("Add Matrices")) {
			resultMatrix = cal.addMatrices(a, b);
		} else if (operationTypeLabel.getText().equals("Subtract Matrices")) {
			resultMatrix = cal.subtractMatrices(a, b);
		} else if (operationTypeLabel.getText().equals("Multiply Matrices")) {
			resultMatrix = cal.multipyMatrices(a, b);
			System.out.println("Row: " + resultMatrix.getRow());
			System.out.println("Col: " + resultMatrix.getColumn());
		}
		resultMatrix.printMatrix();

		for (int i = 0; i < resultMatrix.getRow(); i++) {
			for (int j = 0; j < resultMatrix.getColumn(); j++) {
				String name = generateMapCellName(i, j);
				TextField field = new TextField();
				field.setText("" + resultMatrix.getCurrentMatrix()[i][j]);
				field.getStyleClass().add("gridResultTextField");
				resultGrid.add(field, j, i);
				resultMap.put(name, field);
				System.out.println(resultMap.isEmpty());
			}
		}
	}

	@FXML
	private void calculateButtonAction(ActionEvent event) {
		System.out.println("Calculating...");
		calculateButton.setDisable(false);
		String firstRowSize = firstRowSizeField.getText().trim();
		String firstColSize = firstColSizeField.getText().trim();
		String secondRowSize = secondRowSizeField.getText().trim();
		String secondColSize = secondColSizeField.getText().trim();

		activelySetDimensions(firstRowSize, firstColSize, secondRowSize, secondColSize);

		if (operationChoiceBox.getItems().get(operationChoiceBox.getSelectionModel().getSelectedIndex())
				.equals("Add Matrices")) {
			matrixOperation(Integer.parseInt(firstRowSize), Integer.parseInt(firstColSize),
					Integer.parseInt(secondRowSize), Integer.parseInt(secondColSize));

		} else if (operationChoiceBox.getItems().get(operationChoiceBox.getSelectionModel().getSelectedIndex())
				.equals("Subtract Matrices")) {
			matrixOperation(Integer.parseInt(firstRowSize), Integer.parseInt(firstColSize),
					Integer.parseInt(secondRowSize), Integer.parseInt(secondColSize));

		} else if (operationChoiceBox.getItems().get(operationChoiceBox.getSelectionModel().getSelectedIndex())
				.equals("Multiply Matrices")) {
			matrixOperation(Integer.parseInt(firstRowSize), Integer.parseInt(firstColSize),
					Integer.parseInt(secondRowSize), Integer.parseInt(secondColSize));
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
		setButton.setDisable(true);
		calculateButton.setDisable(true);

		operationChoiceBox.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Integer indexSelected = operationChoiceBox.getSelectionModel().getSelectedIndex();
				System.out.println(operationChoiceBox.getItems().get(indexSelected));
				operationTypeLabel.setText(operationChoiceBox.getItems().get(indexSelected));
				setButton.setDisable(false);
				calculateButton.setDisable(false);
				mapTextField = new HashMap<>();
				mapTextField2 = new HashMap<>();
				resultMap = new HashMap<>();

			}
		});
	}

}
