package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {

	static BorderPane root;

	@Override

	public void start(Stage primaryStage) { // method that starts the application

		try {

			root = new BorderPane();

			Scene scene = new Scene(root, 400, 400);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Set background color for the root container

			root.setStyle("-fx-background-color: lightblue;"); // Light blue background color

			// Set up the scene

			primaryStage.setScene(scene);

			primaryStage.setTitle("Colorful Menu Example");

			primaryStage.show();

			load();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public static void main(String[] args) {

		launch(args); // launches the javafx application

	}

	public void load() { // creates the necessary components

		Label title = new Label("RouteGenie");

		Label welcome = new Label("Welcome to RouteGenie!");

		VBox topContent = new VBox(10);

		topContent.getChildren().addAll(title, welcome);

		Label info = new Label("Please enter your first and last name);");

		Label firstName = new Label("First Name"); // textfields for the user to fill with their name and submit with a
													// buttton

		Label lastName = new Label("Last Name");

		TextField tf1 = new TextField();

		TextField tf2 = new TextField();

		Button btn = new Button("Submit");

		btn.setStyle("-fx-background-color: blue; -fx-text-fill: white;");

		btn.setOnAction(e -> { // Assigns an action to the button when clicked

			System.out.println("First Name:" + tf1.getText() + " Last Name:" + tf2.getText()); // Prints the input from
																								// the text fields to
																								// the console

			tf1.setText(""); // clears the textfields after submission

			tf2.setText("");

		});

		VBox nameFields = new VBox(10);

		nameFields.getChildren().addAll(info, firstName, tf1, lastName, tf2, btn);

		CheckBox c1 = new CheckBox("1. Athens"); // creation of 14 checkboxes with the app's cities

		CheckBox c2 = new CheckBox("2. Thessaloniki");

		CheckBox c3 = new CheckBox("3. Patras");

		CheckBox c4 = new CheckBox("4. Ioannina");

		CheckBox c5 = new CheckBox("5. Tirana");

		CheckBox c6 = new CheckBox("6. Skopje");

		CheckBox c7 = new CheckBox("7. Sofia");

		CheckBox c8 = new CheckBox("8. Podgorica");

		CheckBox c9 = new CheckBox("9. Bucharest");

		CheckBox c10 = new CheckBox("10. Belgrade");

		CheckBox c11 = new CheckBox("11. Sarajevo");

		CheckBox c12 = new CheckBox("12. Zagreb");

		CheckBox c13 = new CheckBox("13. Chisinau");

		CheckBox c14 = new CheckBox("14. Ljubljana");

		VBox cities = new VBox(15);

		cities.getChildren().addAll(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14);

		cities.setSpacing(10);

		Text selectedCitiesText = new Text("Selected cities: none");

		cities.getChildren().add(selectedCitiesText);

		EventHandler<ActionEvent> updateSelectedCities = e -> {

			StringBuilder selectedCities = new StringBuilder("Selected Cities: ");

			if (c1.isSelected())
				selectedCities.append("Athens, "); // checks if a city is selected

			if (c2.isSelected())
				selectedCities.append("Thessaloniki, ");

			if (c3.isSelected())
				selectedCities.append("Patras, ");

			if (c4.isSelected())
				selectedCities.append("Ioannina, ");

			if (c5.isSelected())
				selectedCities.append("Tirana, ");

			if (c6.isSelected())
				selectedCities.append("Skopje, ");

			if (c7.isSelected())
				selectedCities.append("Sofia, ");

			if (c8.isSelected())
				selectedCities.append("Podgorica, ");

			if (c9.isSelected())
				selectedCities.append("Bucharest, ");

			if (c10.isSelected())
				selectedCities.append("Belgrade, ");

			if (c11.isSelected())
				selectedCities.append("Sarajevo, ");

			if (c12.isSelected())
				selectedCities.append("Zagreb, ");

			if (c13.isSelected())
				selectedCities.append("Chisinau, ");

			if (c14.isSelected())
				selectedCities.append("Ljubljana, ");

			String result = selectedCities.toString().replaceAll(", $", "");

			if (result.equals("Selected Cities:")) {

				result = "Selected Cities: None";

			}

			selectedCitiesText.setText(result); // updates the text displaying the selected cities

		};

		c1.setOnAction(updateSelectedCities); // Any possible change will trigger the update logic

		c2.setOnAction(updateSelectedCities);

		c3.setOnAction(updateSelectedCities);

		c4.setOnAction(updateSelectedCities);

		c5.setOnAction(updateSelectedCities);

		c6.setOnAction(updateSelectedCities);

		c7.setOnAction(updateSelectedCities);

		c8.setOnAction(updateSelectedCities);

		c9.setOnAction(updateSelectedCities);

		c10.setOnAction(updateSelectedCities);

		c11.setOnAction(updateSelectedCities);

		c12.setOnAction(updateSelectedCities);

		c13.setOnAction(updateSelectedCities);

		c14.setOnAction(updateSelectedCities);

		Label originLabel = new Label("Please select your starting city");
		ComboBox<String> originComboBox = new ComboBox<>();
		originComboBox.getItems().addAll("Athens", "Thessaloniki", "Patras", "Ioannina", "Tirana", "Skopje", "Sofia", "Podgorica", "Bucharest", "Belgrade", "Sarajevo", "Zagreb", "Chisinau", "Ljubljana");
		originComboBox.setPromptText("Choose a city");
		VBox originCitySelection = new VBox(10);
		originCitySelection.getChildren().addAll(originLabel, originComboBox);
		
		
		Button submitCitiesBtn = new Button("Submit Cities");
		
		submitCitiesBtn.setDisable(true);
		
		originComboBox.setOnAction(e -> {
			submitCitiesBtn.setDisable(false);
		});
		
        submitCitiesBtn.setOnAction(e -> {
        	System.out.println("Starting City:" + originComboBox.getValue());
        	System.out.println(selectedCitiesText.getText());
        });
        cities.getChildren().add(submitCitiesBtn);
		
		VBox mainLayout = new VBox(20);
		mainLayout.getChildren().addAll(topContent, nameFields, originCitySelection, cities, submitCitiesBtn);
		root.setCenter(mainLayout);

	}

}