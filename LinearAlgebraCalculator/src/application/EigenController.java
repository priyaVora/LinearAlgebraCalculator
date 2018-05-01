package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EigenController implements Initializable {
	@FXML
	private Button Back;
	@FXML
	private ScrollPane scrollMatrixTwo;

	@FXML
	AnchorPane secondMatrixAnchor;
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
		scrollMatrixTwo = new ScrollPane();
		secondMatrixAnchor = new AnchorPane();
		secondMatrix = new Pane();
		setButton.setDisable(true);
		calculateButton.setDisable(true);
		operationChoiceBox.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				showWorkButton.setVisible(true);
				Integer indexSelected = operationChoiceBox.getSelectionModel().getSelectedIndex();
				System.out.println(operationChoiceBox.getItems().get(indexSelected));
				operationTypeLabel.setText(operationChoiceBox.getItems().get(indexSelected));

				if (operationChoiceBox.getItems().get(operationChoiceBox.getSelectionModel().getSelectedIndex())
						.equals("Is EigenValue?")) {
					System.out.println("Print!");

					dimension2.setDisable(true);

					secondRowSizeField.setText("0");
					secondColSizeField.setText("0");

					secondRowSizeField.setDisable(true);
					secondColSizeField.setDisable(true);

					setButton.setDisable(false);
					calculateButton.setDisable(false);
					lambdaField.setPromptText("Enter λ Value Here");

				}

				setButton.setDisable(false);
				calculateButton.setDisable(false);
			}
		});
	}

	@FXML
	private void calculateButtonAction(ActionEvent event) throws NumberFormatException, IOException {
		System.out.println("Calculated!");
		String lambdaValue = lambdaField.getText().trim();
		System.out.println("Lambda Value: " + lambdaValue);
	}

	@FXML
	private void setAction() {

		gridFirstMatrix.getChildren().clear();
		gridSecondMatrix.getChildren().clear();
		int operationIndexSelected = operationChoiceBox.getSelectionModel().getSelectedIndex();
		String operationSelected = operationChoiceBox.getItems().get(operationIndexSelected);
		String firstRowSize = firstRowSizeField.getText().trim();
		String firstColSize = firstColSizeField.getText().trim();
		String secondRowSize = secondRowSizeField.getText().trim();
		String secondColSize = secondColSizeField.getText().trim();

		System.out.println(firstRowSize);
		System.out.println(firstColSize);
		System.out.println(secondRowSize);
		System.out.println(secondColSize);

		operationHelper(Integer.parseInt(firstRowSize), Integer.parseInt(firstColSize), Integer.parseInt(secondRowSize),
				Integer.parseInt(secondColSize), firstRowSize, firstColSize, secondRowSize, secondColSize);
		//operationHelper(2, 2, 0, 0, firstRowSize, firstColSize, secondRowSize, secondColSize);
		
		if (operationChoiceBox.getItems().get(operationChoiceBox.getSelectionModel().getSelectedIndex())
				.equals("Is EigenValue?")) {
			System.out.println("Print!");

			dimension2.setDisable(true);

			secondRowSizeField.setText("0");
			secondColSizeField.setText("0");

			secondRowSizeField.setDisable(true);
			secondColSizeField.setDisable(true);

			setButton.setDisable(false);
			calculateButton.setDisable(false);
			lambdaField.setPromptText("Enter λ Value Here");

		}

	}

	private String generateMapCellName(int row, int col) {
		return "Cell_" + row + "_" + col;
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
				// mapTextField.put(name, field);
			}
		}

		for (int i = 0; i < row2Size; i++) {
			for (int j = 0; j < col2Size; j++) {
				String name = generateMapCellName(i, j);
				TextField field = new TextField();
				field.getStyleClass().add("gridTextField2");
				gridSecondMatrix.add(field, j, i);
				// mapTextField2.put(name, field);
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

			if (Integer.parseInt(firstRowSize) > 6 || Integer.parseInt(firstColSize) > 6
					|| Integer.parseInt(secondColSize) > 6 || Integer.parseInt(secondRowSize) > 6) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Dimensions are larger than 6X6");
				alert.setContentText("Click on Dimension button to calculate!");

				alert.showAndWait();

			} else {
				dimension1.setText(dimensionLabelOne);
				dimension2.setText(dimensionLabelTwo);
			}

		}
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
