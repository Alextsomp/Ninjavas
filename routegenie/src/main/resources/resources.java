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

		//ImageView to display the starting city image
		ImageView cityImageView = new ImageView();
		cityImageView.setFitWidth(300); // Set width for the image
	    cityImageView.setPreserveRatio(true); // Maintain the aspect ratio
		
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

		CheckBox[] checkboxes = {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14};

	        // Initially disable all checkboxes
	        for (CheckBox checkbox : checkboxes) {
	            checkbox.setDisable(true);
	        }
		
		VBox cities = new VBox(15);

		cities.getChildren().addAll(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14);

		cities.setSpacing(10);

		Text selectedCitiesText = new Text("Selected cities: none");

		cities.getChildren().add(selectedCitiesText);

		// Event handler for updating selected cities
        EventHandler<ActionEvent> updateSelectedCities = e -> {
            StringBuilder selectedCities = new StringBuilder("Selected Cities: ");
            for (CheckBox checkbox : checkboxes) {
                if (checkbox.isSelected()) {
                    selectedCities.append(checkbox.getText().substring(3)).append(", ");
                }
            }
            String result = selectedCities.toString().replaceAll(", $", "");
            if (result.equals("Selected Cities:")) {
                result = "Selected Cities: None";
            }
            selectedCitiesText.setText(result);
        };

        for (CheckBox checkbox : checkboxes) {
            checkbox.setOnAction(updateSelectedCities);
        }

        // ComboBox for starting city
        Label originLabel = new Label("Please select your starting city");
        ComboBox<String> originComboBox = new ComboBox<>();
        originComboBox.getItems().addAll("Athens", "Thessaloniki", "Patras", "Ioannina", "Tirana", "Skopje", "Sofia", "Podgorica", "Bucharest", "Belgrade", "Sarajevo", "Zagreb", "Chisinau", "Ljubljana");
        originComboBox.setPromptText("Choose a city");
        VBox originCitySelection = new VBox(10);
        originCitySelection.getChildren().addAll(originLabel, originComboBox);


        originComboBox.setOnAction(e -> {
        	String selectedCity = originComboBox.getValue();

            if (selectedCity != null) {
                System.out.println("Starting City: " + selectedCity);

                // Enable all checkboxes except the selected starting city
                for (CheckBox checkbox : checkboxes) {
                    checkbox.setDisable(false);
                }

                // Disable the checkbox corresponding to the selected starting city
                switch (selectedCity) {
                    case "Athens": 
                    	c1.setDisable(true); 
                    	cityImageView.setImage(loadCityImage("https://ipanematravels.com/wp-content/uploads/2023/10/Athens-Greece.jpg"));
                    	break;
                    case "Thessaloniki": 
                    	c2.setDisable(true); 
                    	cityImageView.setImage(loadCityImage("https://www.letsdrive.gr/sites/default/files/Thessaloniki%20750x500.jpg"));
                    	break;
                    case "Patras": 
                    	c3.setDisable(true); 
                    	cityImageView.setImage(loadCityImage("https://v9c9u8s9.delivery.rocketcdn.me/wp-content/uploads/2021/09/Upper-town-Patras-.jpg"));
                    	break;
                    case "Ioannina": 
                    	c4.setDisable(true); 
                    	cityImageView.setImage(loadCityImage("https://www.olympion-rehab.com/cache/files/fb10ef16219a69a5a02cb436efdb5ceda2fd0742.jpg"));
                    	break;
                    case "Tirana": 
                    	c5.setDisable(true); 
                    	cityImageView.setImage(loadCityImage("https://eia476h758b.exactdn.com/wp-content/uploads/2023/08/Tirana-1.jpg"));
                    	break;
                    case "Skopje": 
                    	c6.setDisable(true); 
                    	cityImageView.setImage(loadCityImage("https://tripjive.com/wp-content/uploads/2024/07/Is-Skopje-worth-visiting.jpg"));
                    	break;
                    case "Sofia": 
                    	c7.setDisable(true); 
                    	cityImageView.setImage(loadCityImage("https://cdn.getyourguide.com/img/tour/5c5424b437891.jpeg/146.jpg"));
                    	break;
                    case "Podgorica": 
                    	c8.setDisable(true); 
                    	cityImageView.setImage(loadCityImage("https://assets.enuygun.com/media/lib/570x400/uploads/image/podgorica-59036.jpeg"));
                    	break;
                    case "Bucharest": 
                    	c9.setDisable(true); 
                    	cityImageView.setImage(loadCityImage("https://www.wanderlustmagazine.com/wp-content/uploads/2023/11/2-palace-of-parliament-at-sunrise-shutterstock_1788823946-web.jpg"));
                    	break;
                    case "Belgrade": 
                    	c10.setDisable(true); 
                    	cityImageView.setImage(loadCityImage("https://i0.wp.com/mytravelation.com/wp-content/uploads/2023/12/Belgrade-Serbia.jpg"));
                    	break;
                    case "Sarajevo": 
                    	c11.setDisable(true); 
                    	cityImageView.setImage(loadCityImage("https://web-assets.transavia.com/78ae936f-d39d-01b0-c3ef-dc738304142f/472cea8d-c842-4343-8c2f-1e5ee7f43acd/Sarajevo.jpg"));
                    	break;
                    case "Zagreb": 
                    	c12.setDisable(true); 
                    	cityImageView.setImage(loadCityImage("https://www.travel.gr/wp-content/uploads/2021/12/zagreb-kentriki-scaled.jpg"));
                    	break;
                    case "Chisinau": 
                    	c13.setDisable(true); 
                    	cityImageView.setImage(loadCityImage("https://static.independent.co.uk/2021/09/24/16/iStock-1066138896.jpg"));
                    	break;
                    case "Ljubljana": 
                    	c14.setDisable(true); 
                    	cityImageView.setImage(loadCityImage("https://cdn.britannica.com/79/124479-050-DD359499/Ljubljana-Slovenia.jpg"));
                    	break;
                }
            }
        });
       
        // Button to submit the selected cities
        Button submitSelectedCitiesBtn = new Button("Submit Selected Cities");
        submitSelectedCitiesBtn.setOnAction(e -> {
            System.out.println(selectedCitiesText.getText());
        });
		
		
		VBox mainLayout = new VBox(20);
		mainLayout.getChildren().addAll(topContent, nameFields, originCitySelection, cityImageView, cities, submitSelectedCitiesBtn);
		root.setCenter(mainLayout);

	}

	public Image loadCityImage(String imageUrl) {
	    try {
	        return new Image(imageUrl);
	    } catch (Exception e) {
	        System.out.println("Image not found: " + imageUrl);
	        return null;
	    }
	}

}