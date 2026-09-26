package distance.calcu;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DistanceCalcu extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        //main-layout
        BorderPane root = new BorderPane();

        Label titleLabel = new Label("Road Distance Calculator");
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        titleLabel.getStyleClass().add("title");

        primaryStage.setTitle("ROAD DISTANCE CALCULATOR");

        // grid-layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        Label lbl1 = new Label("Origin Location");
        lbl1.getStyleClass().add("label");

        TextField txt1 = new TextField();
        txt1.setPromptText("City or Address");
        txt1.setPrefHeight(40);
        txt1.setPrefWidth(250);

        Label lbl2 = new Label("Destination Location");
        lbl2.getStyleClass().add("label");

        TextField txt2 = new TextField();
        txt2.setPromptText("City or Address");
        txt2.setPrefHeight(40);

        Label lbl3 = new Label("Petrol Cost");
        lbl3.getStyleClass().add("label");

        TextField txt3 = new TextField();
        txt3.setPromptText("Price:");
        txt3.setPrefHeight(40);

        Label lbl4 = new Label("Fuel Consumption (L/100 km)");
        lbl4.getStyleClass().add("label");

        TextField txt4 = new TextField();
        txt4.setPromptText("Fuel consumption:");
        txt4.setPrefHeight(40);

        Label lbl5 = new Label("Average Speed (km/h)");
        lbl5.getStyleClass().add("label");

        TextField txt5 = new TextField();
        txt5.setPromptText("Average speed:");
        txt5.setPrefHeight(40);

        // BUTTONS
        Button btn1 = new Button("Calculate");
        btn1.getStyleClass().add("calculate-button");

        Button btn2 = new Button("Reset");
        btn2.getStyleClass().add("reset-button");

        Label resultsTitle = new Label("RESULTS:");
        resultsTitle.getStyleClass().add("result");

        Label resultDistance = new Label("Distance: ");
        resultDistance.getStyleClass().add("result");

        Label resultTime = new Label("Travel Time: ");
        resultTime.getStyleClass().add("result");

        Label resultFuel = new Label("Fuel Required: ");
        resultFuel.getStyleClass().add("result");

        Label resultCost = new Label("Petrol Cost: ");
        resultCost.getStyleClass().add("result");
         
        resultDistance.setStyle(
                "-fx-border-color: #333333;"
                + "-fx-border-width: 1;"
                + "-fx-padding: 15;"
                + "-fx-min-width: 150;"
                + "-fx-min-height: 70;"
                + "-fx-alignment: center;"
                + "-fx-font-weight: bold;"
        );
        resultTime.setStyle(
                "-fx-border-color: #333333;"
                + "-fx-border-width: 1;"
                + "-fx-padding: 15;"
                + "-fx-min-width: 150;"
                + "-fx-min-height: 70;"
                + "-fx-alignment: center;"
                + "-fx-font-weight: bold;"
        );

        resultFuel.setStyle(
                "-fx-border-color: #333333;"
                + "-fx-border-width: 1;"
                + "-fx-padding: 15;"
                + "-fx-min-width: 150;"
                + "-fx-min-height: 70;"
                + "-fx-alignment: center;"
                + "-fx-font-weight: bold;"
        );

        resultCost.setStyle(
                "-fx-border-color: #333333;"
                + "-fx-border-width: 1;"
                + "-fx-padding: 15;"
                + "-fx-min-width: 150;"
                + "-fx-min-height: 70;"
                + "-fx-alignment: center;"
                + "-fx-font-weight: bold;"
        );

        VBox originBox = new VBox(1);
        originBox.getChildren().addAll(lbl1, txt1);
        
        VBox destinationBox = new VBox(1);
        destinationBox.getChildren().addAll(lbl2, txt2);

        VBox petrolBox = new VBox(1);
        petrolBox.getChildren().addAll(lbl3, txt3);

        VBox fuelBox = new VBox(1);
        fuelBox.getChildren().addAll(lbl4, txt4);

        VBox averageBox = new VBox(1);
        averageBox.getChildren().addAll(lbl5, txt5);

        HBox buttons = new HBox(5);
        buttons.getChildren().addAll(btn1, btn2);

        HBox resultsBox = new HBox(5);
        resultsBox.getChildren().addAll(
                resultDistance,
                resultTime,
                resultFuel,
                resultCost
        );

        VBox resultBox = new VBox(1);
        resultBox.getStyleClass().add("results-container");
        resultBox.getChildren().addAll(resultsTitle, resultsBox);

        gridPane.add(originBox, 0, 0);
        gridPane.add(destinationBox, 1, 0);
        
        gridPane.add(petrolBox, 0, 1);
        gridPane.add(fuelBox, 1, 1);

        gridPane.add(averageBox, 0, 2);
        gridPane.add(buttons, 0, 3);

        btn1.setOnAction(e -> {

            if (txt1.getText().trim().isEmpty()
                    || txt2.getText().trim().isEmpty()
                    || txt3.getText().trim().isEmpty()
                    || txt4.getText().trim().isEmpty()
                    || txt5.getText().trim().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all the fields.");
                alert.showAndWait();

                return;
            }
            try {
                String origin = txt1.getText().trim();
                String destination = txt2.getText().trim();

                double petrolPrice;
                try {
                    petrolPrice = Double.parseDouble(txt3.getText().trim());
                } catch (NumberFormatException ex) {
                        showError("Petrol Cost is not a valid number.\n" + "You entered: " + txt3.getText()
                    );
                    return;
                }
                double fuelConsumption;
                try {
                    fuelConsumption = Double.parseDouble(txt4.getText().trim());
                } catch (NumberFormatException ex) {
                    showError("Fuel Consumption is not a valid number.\n " + "You entered: "+ txt4.getText()
                    );
                    return;
                } 
                double averageSpeed;
                try {
                    averageSpeed = Double.parseDouble(txt5.getText().trim());
                } catch (NumberFormatException ex) {
                    showError("Average Speed is not a valid number.\n" + "You entered: " + txt5.getText());
                    return;
                }
                
                if (petrolPrice <= 0 || fuelConsumption <= 0 || averageSpeed <= 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Values must be positive.");
                    alert.showAndWait();
                    return;
                }
                //get origin location coordinates 
                double[] originCoordinates = locationService.getCoordinates(origin);
                if (originCoordinates == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Location Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Could not find the origin location.");
                    alert.showAndWait();
                    return;
                }
                double[] destinationCoordinates = locationService.getCoordinates(destination);

                if (destinationCoordinates == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Location Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Could not find the destination location.");
                    alert.showAndWait();
                    return;
                }
                double distance = RouteServe.getDistance(
                                originCoordinates[0],
                                originCoordinates[1],
                                destinationCoordinates[0],
                                destinationCoordinates[1]
                        );

                double travelTime = distance / averageSpeed;
                double fuelRequired = (distance * fuelConsumption) / 100;
                double totalCost = fuelRequired * petrolPrice;

                resultDistance.setText("Distance\n" + String.format("%.2f", distance)+ " Km");
                resultTime.setText("Travel Time\n " + String.format("%.1f", travelTime)+ " Hours");
                resultFuel.setText("Fuel Required\n" + String.format("%.1f", fuelRequired) + " L");
                resultCost.setText("Petrol Cost\nR" + String.format("%.2f", totalCost));

            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Connection Error");
                alert.setHeaderText("Something went wrong");
                alert.setContentText(ex.getClass().getSimpleName()+ "\n"+ ex.getMessage());
                alert.showAndWait();
                
                Logger.getLogger(DistanceCalcu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn2.setOnAction(e -> {

            txt1.clear();
            txt2.clear();
            txt3.clear();
            txt4.clear();
            txt5.clear();

            resultDistance.setText("Distance:");
            resultTime.setText("Travel Time:");
            resultFuel.setText("Fuel Required:");
            resultCost.setText("Petrol Cost:");
        });
        root.setTop(titleLabel);
        root.setCenter(gridPane);
        root.setBottom(resultBox);

        Scene scene = new Scene(root, 750, 600);

        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm()
        );
        BorderPane.setMargin(titleLabel,new Insets(0, 0, 20, 0)
        );
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // error message method
    private void showError(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
}