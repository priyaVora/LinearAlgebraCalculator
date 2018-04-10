package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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
	private Button dimension1;
	@FXML
	private Button dimension2;
	@FXML
	private Button setButton;
	@FXML
	private Pane firstMatrix;
	@FXML
	private Pane secondMatrix;

	@FXML
	private TextField first00;
	@FXML
	private TextField first01;
	@FXML
	private TextField first02;
	@FXML
	private TextField first03;

	@FXML
	private TextField first10;
	@FXML
	private TextField first11;
	@FXML
	private TextField first12;
	@FXML
	private TextField first13;

	@FXML
	private TextField first20;
	@FXML
	private TextField first21;
	@FXML
	private TextField first22;
	@FXML
	private TextField first23;

	@FXML
	private TextField first30;
	@FXML
	private TextField first31;
	@FXML
	private TextField first32;
	@FXML
	private TextField first33;

	@FXML
	private TextField second00;
	@FXML
	private TextField second01;
	@FXML
	private TextField second02;
	@FXML
	private TextField second03;

	@FXML
	private TextField second10;
	@FXML
	private TextField second11;
	@FXML
	private TextField second12;
	@FXML
	private TextField second13;

	@FXML
	private TextField second20;
	@FXML
	private TextField second21;
	@FXML
	private TextField second22;
	@FXML
	private TextField second23;

	@FXML
	private TextField second30;
	@FXML
	private TextField second31;
	@FXML
	private TextField second32;
	@FXML
	private TextField second33;

	public CalculatorScreen() {
		resetGridTextFields();
	}

	public void resetGridTextFields() {
		mapTextField = new HashMap<>();
		mapTextField2 = new HashMap<>();
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

		first00 = new TextField();
		first01 = new TextField();
		first02 = new TextField();
		first03 = new TextField();

		first10 = new TextField();
		first11 = new TextField();
		first12 = new TextField();
		first13 = new TextField();

		first20 = new TextField();
		first21 = new TextField();
		first22 = new TextField();
		first23 = new TextField();

		first30 = new TextField();
		first31 = new TextField();
		first32 = new TextField();
		first33 = new TextField();

		second00 = new TextField();
		second01 = new TextField();
		second02 = new TextField();
		second03 = new TextField();

		second10 = new TextField();
		second11 = new TextField();
		second12 = new TextField();
		second13 = new TextField();

		second20 = new TextField();
		second21 = new TextField();
		second22 = new TextField();
		second23 = new TextField();

		second30 = new TextField();
		second31 = new TextField();
		second32 = new TextField();
		second33 = new TextField();
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
			ableGridOne();
			ableGridTwo();

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

			List<String> validPositionList = new ArrayList<String>();
			List<String> validPositionListTwo = new ArrayList<String>();

			validPositionList = getValidPositions(Integer.parseInt(firstRowSize), Integer.parseInt(firstColSize));
			setValidPositionsOnGrid(validPositionList, 0);
			System.out.println(" ");
			validPositionListTwo = getValidPositions(Integer.parseInt(secondRowSize), Integer.parseInt(secondColSize));
			setValidPositionsOnGrid(validPositionListTwo, 1);

		}
	}

	public void setValidPositionsOnGrid(List<String> validPositionList, int number) {

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
			ableGridOne();
			ableGridTwo();

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

		disableGridOne();
		disableGridTwo();

	}

	public void ableGridOne() {
		first00.setDisable(false);
		first01.setDisable(false);
		first02.setDisable(false);
		first03.setDisable(false);

		first10.setDisable(false);
		first11.setDisable(false);
		first12.setDisable(false);
		first13.setDisable(false);

		first20.setDisable(false);
		first21.setDisable(false);
		first22.setDisable(false);
		first23.setDisable(false);

		first30.setDisable(false);
		first31.setDisable(false);
		first32.setDisable(false);
		first33.setDisable(false);
	}

	public void ableGridTwo() {
		second00.setDisable(false);
		second01.setDisable(false);
		second02.setDisable(false);
		second03.setDisable(false);

		second10.setDisable(false);
		second11.setDisable(false);
		second12.setDisable(false);
		second13.setDisable(false);

		second20.setDisable(false);
		second21.setDisable(false);
		second22.setDisable(false);
		second23.setDisable(false);

		second30.setDisable(false);
		second31.setDisable(false);
		second32.setDisable(false);
		second33.setDisable(false);
	}

	public void disableGridOne() {
		first00.setDisable(true);
		first01.setDisable(true);
		first02.setDisable(true);
		first03.setDisable(true);

		first10.setDisable(true);
		first11.setDisable(true);
		first12.setDisable(true);
		first13.setDisable(true);

		first20.setDisable(true);
		first21.setDisable(true);
		first22.setDisable(true);
		first23.setDisable(true);

		first30.setDisable(true);
		first31.setDisable(true);
		first32.setDisable(true);
		first33.setDisable(true);
	}

	public void disableGridTwo() {
		second00.setDisable(true);
		second01.setDisable(true);
		second02.setDisable(true);
		second03.setDisable(true);

		second10.setDisable(true);
		second11.setDisable(true);
		second12.setDisable(true);
		second13.setDisable(true);

		second20.setDisable(true);
		second21.setDisable(true);
		second22.setDisable(true);
		second23.setDisable(true);

		second30.setDisable(true);
		second31.setDisable(true);
		second32.setDisable(true);
		second33.setDisable(true);
	}
}
