package application;
	
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
	public void start(Stage primaryStage) {
		try {
			root = new BorderPane();
			Scene scene = new Scene(root, 700,700);
			
            // Set up the scene
            primaryStage.setScene(scene);
            primaryStage.setTitle("RouteGenie App");
            primaryStage.show();
            
			load();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public void load() { // creates the necessary components
		
		Label title = new Label("RouteGenie");
		title.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: darkblue;");
		
		Label welcome = new Label("Welcome to RouteGenie!");
		welcome.setStyle("-fx-font-size: 20px;");
		
		VBox topContent = new VBox(10);
		topContent.setAlignment(Pos.CENTER);
		topContent.getChildren().addAll(title, welcome);
		
		//ImageView to display the starting city image
		ImageView cityImageView = new ImageView();
		cityImageView.setFitWidth(500); // Set width for the image
	    cityImageView.setPreserveRatio(true); // Maintain the aspect ratio
	    cityImageView.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
	    
		Label info = new Label("Please enter your first and last name");
		info.setStyle("-fx-font-size: 16px; -fx-text-fill: purple; -fx-font-weight: bold;");
		
		Label firstName = new Label("First Name:");
		firstName.setStyle("-fx-font-weight: bold; -fx-font-size: 13px;");
		
		Label lastName = new Label("Last Name:");
		lastName.setStyle("-fx-font-weight: bold; -fx-font-size: 13px;");
		
		TextField tf1 = new TextField();
		tf1.setMaxWidth(300);
		
		TextField tf2 = new TextField();
		tf2.setMaxWidth(300);
		
		Button btn = new Button("Submit");
		btn.setStyle("-fx-background-color: darkblue; -fx-text-fill: white; -fx-font-size: 13px;");
		
		btn.setOnAction(e -> { // Assigns an action to the button when clicked
			System.out.println("First Name:" + tf1.getText() + " Last Name:" + tf2.getText());
			tf1.setText("");
			tf2.setText("");
		});
		
		VBox nameFields = new VBox(20);
		nameFields.setStyle("-fx-font-size: 14px;");
		nameFields.setAlignment(Pos.CENTER);
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
		
		VBox cities = new VBox(10);
		cities.setStyle("-fx-font-size: 14px");
		cities.setAlignment(Pos.CENTER);
		cities.getChildren().addAll(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14);
		
		Text selectedCitiesText = new Text("Selected cities: none");
		selectedCitiesText.setStyle("-fx-font-size: 14px");
		
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
        originLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: purple; -fx-font-weight: bold;");
        
        ComboBox<String> originComboBox = new ComboBox<>();
        originComboBox.getItems().addAll("Athens", "Thessaloniki", "Patras", "Ioannina", "Tirana", "Skopje", "Sofia", "Podgorica", "Bucharest", "Belgrade", "Sarajevo", "Zagreb", "Chisinau", "Ljubljana");
        originComboBox.setPromptText("Choose a city");
        originComboBox.setPrefWidth(200);
        
        VBox originCitySelection = new VBox(10);
        originCitySelection.setAlignment(Pos.BOTTOM_CENTER);
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
        submitSelectedCitiesBtn.setStyle("-fx-background-color: darkblue; -fx-text-fill: white; -fx-font-size: 13px;");

		Label totalDistLabel = new Label("Total Distance: ");
		totalDistLabel.setStyle("-fx-font-size: 14px;");

		Label bestRouteLabel = new Label("Best Route: ");
		bestRouteLabel.setStyle("-fx-font-size: 14px;");

		VBox resultsBox = new VBox(10);
		resultsBox.setAlignment(Pos.CENTER);
		resultsBox.getChildren().addAll(totalDistLabel, bestRouteLabel);
		resultsBox.setVisible(false);

		submitSelectedCitiesBtn.setOnAction(e -> {
			System.out.println(selectedCitiesText.getText());

			List<Integer> selectedCitiesList = new ArrayList<>();
			String selectedCity = originComboBox.getValue();
			if (selectedCity != null) {
				// Βρίσκουμε το index της πόλης εκκίνησης στη λίστα των πόλεων
				String[] cityNames = CitiesAndDistances.getCities();
				int startCityIndex = Arrays.asList(cityNames).indexOf(selectedCity); // Προσοχή στην περίπτωση που το
																						// selectedCity δεν υπάρχει στη
																						// λίστα
				if (startCityIndex != -1 && !selectedCitiesList.contains(startCityIndex)) {
					selectedCitiesList.add(startCityIndex); // Προσθέτουμε την πόλη εκκίνησης
				}
			}
			for (CheckBox checkbox : checkboxes) {
				if (checkbox.isSelected()) {
					String cityText = checkbox.getText().trim();

					// Βρίσκουμε το πρώτο κενό για να διαχωρίσουμε αριθμό και όνομα
					int spaceIndex = cityText.indexOf(' '); // Βρίσκουμε το πρώτο κενό
					if (spaceIndex != -1) {
						// Παίρνουμε το νούμερο της πόλης και αφαιρούμε την τελεία αν υπάρχει
						String cityNumberStr = cityText.substring(0, spaceIndex).trim();

						// Αφαιρούμε την τελεία αν υπάρχει
						cityNumberStr = cityNumberStr.replace(".", "").trim();

						try {
							// Μετατρέπουμε το νούμερο σε ακέραιο και αφαιρούμε 1 για μηδενική αναφορά
							int cityIndex = Integer.parseInt(cityNumberStr) - 1;

							// Παίρνουμε το όνομα της πόλης
							String cityName = cityText.substring(spaceIndex).trim();

							// Εμφανίζουμε για έλεγχο
							System.out.println("City: " + cityName + ", Index: " + cityIndex);
							selectedCitiesList.add(cityIndex);
						} catch (NumberFormatException ex) {
							// Αν αποτύχει η μετατροπή σε αριθμό, εμφανίζουμε το σφάλμα
							System.out.println("Invalid city number format: " + cityNumberStr);
						}
					}
				}
			}

			if (!selectedCitiesList.isEmpty()) {
				String[] cityNames = CitiesAndDistances.getCities();
				int selectedIndex = originComboBox.getItems().indexOf(selectedCity);
				Solver solver = new Solver();
				double[][] distances = CitiesAndDistances.distances;

				List<Integer> bestRouteSolver = solver.solve(distances, selectedIndex, selectedCitiesList);
				double totalDistSolver = solver.totalDist(bestRouteSolver, distances);

				List<Integer> bestRoutesNN = solver.nearestNeighbour(selectedIndex, distances, selectedCitiesList);
				double totalDistNN = solver.totalDist(bestRoutesNN, distances);

				if (totalDistNN > totalDistSolver) {
					System.out.println("The best route for your trip is: ");
					for (int i = 0; i < bestRouteSolver.size() - 1; i++) {
						System.out.println(i + 1 + cityNames[bestRouteSolver.get(i)]);
					}
					System.out.printf("And then back to ", cityNames[selectedIndex]);
					System.out.println("In this order, the distance covered is only going to be " + totalDistSolver
							+ " kilometers!");

				} else if (totalDistSolver > totalDistNN) {
					System.out.println("The best route for your trip is: ");
					for (int i = 0; i < bestRoutesNN.size() - 1; i++) {
						System.out.println(i + 1 + cityNames[bestRoutesNN.get(i)]);
					}
					System.out.printf("And then back to ", cityNames[selectedIndex]);
					System.out.println(
							"In this order, the distance covered is only going to be " + totalDistNN + " kilometers!");
				} else {
					System.out.println("The best route for your trip is: ");
					for (int i = 0; i < bestRoutesNN.size() - 1; i++) {
						System.out.println(i + 1 + cityNames[bestRoutesNN.get(i)]);
					}
					System.out.printf("And then back to ", cityNames[selectedIndex]);
					System.out.println(
							"In this order, the distance covered is only going to be " + totalDistNN + " kilometers!");
				}
			}

			resultsBox.setVisible(true);
		});

		VBox mainLayout = new VBox(20);
		mainLayout.setAlignment(Pos.CENTER);

		mainLayout.setStyle("-fx-background-color: blanchedalmond;");
		mainLayout.setPadding(new Insets(15));
		mainLayout.getChildren().addAll(topContent, nameFields, originCitySelection, cityImageView, cities,
				submitSelectedCitiesBtn, resultsBox);

		// Wrap the layout in a ScrollPane for overflow handling
		ScrollPane scrollPane = new ScrollPane(mainLayout);

		scrollPane.setFitToWidth(true);

		root.setCenter(scrollPane);

	}

	// method for loading the images
	public Image loadCityImage(String imageUrl) {
		try {
			return new Image(imageUrl);
		} catch (Exception e) {
			System.out.println("Image not found: " + imageUrl);
			return null;
		}
	}

}