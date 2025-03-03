package com.example.firstassignment;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    private int currentIndex = 0;
    private String[] paths = {
            "/images/download(1).jpg", "/images/download(2).jpg", "/images/download(3).jpg",
            "/images/download(4).jpg", "/images/download(5).jpg", "/images/download(6).jpg",
            "/images/download(7).jpg", "/images/download(8).jpg", "/images/download(9).jpg",
            "/images/download(10).jpg", "/images/download(11).jpg", "/images/download(12).jpg",
            "/images/download(13).jpg", "/images/download(14).jpg", "/images/download(15).jpg"
    };

    private Pane pane;
    private GridPane grid;
    private ImageView fullImageView;
    private Button prevButton, nextButton, backButton;

    @Override
    public void start(Stage stage) throws IOException {
        Pane main = new Pane();
        pane = new Pane();
        Label topLabel = new Label("Photos Gallery");
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        topLabel.setAlignment(Pos.CENTER);
        topLabel.setFont(new Font("Arial", 24));
        topLabel.setStyle("-fx-text-fill: white;");
        main.getChildren().add(topLabel);

        pane.setPrefSize(900, 530);
        pane.setBackground(Background.fill(Color.GRAY));
        main.setBackground(Background.fill(Color.GRAY));

        topLabel.setLayoutX(550);  // Centering the label
        topLabel.setLayoutY(50);

        pane.relocate(240, 150);

        int cols = 5;

        for (int i = 0; i < paths.length; i++) {
            Image image = new Image(Objects.requireNonNull(getClass().getResource(paths[i])).toString());
            ImageView imageView = new ImageView(image);

            imageView.setFitWidth(160);
            imageView.setFitHeight(160);

            // Hover effect for thumbnails
            imageView.setStyle("-fx-cursor: hand;");

            Pane imageContainer = new Pane();
            imageContainer.setPrefSize(160, 160);
            imageContainer.getChildren().add(imageView);

            grid.add(imageContainer, i % cols, i / cols);

            int index = i;
            imageView.setOnMouseClicked(event -> openFullImage(index));
        }

        grid.setLayoutX(30);
        grid.setLayoutY(100);

        pane.getChildren().add(grid);
        main.getChildren().add(pane);

        Scene scene = new Scene(main, 1300, 700);
        scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());  // Load external CSS
        stage.setTitle("Image Gallery");
        stage.setScene(scene);
        stage.show();
    }

    private void openFullImage(int index) {
        pane.getChildren().clear();
        currentIndex = index;

        fullImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(paths[currentIndex])).toString()));
        fullImageView.setPreserveRatio(true);
        fullImageView.setFitWidth(500);  // Set a fixed width
        fullImageView.setFitHeight(500);  // Set a fixed height

        prevButton = new Button("Previous");
        nextButton = new Button("Next");
        backButton = new Button("Back to Gallery");

        prevButton.setOnAction(e -> {
            if (currentIndex > 0) {
                currentIndex--;
                fullImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource(paths[currentIndex])).toString()));
            }
        });

        nextButton.setOnAction(e -> {
            if (currentIndex < paths.length - 1) {
                currentIndex++;
                fullImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource(paths[currentIndex])).toString()));
            }
        });

        backButton.setOnAction(e -> showGallery());

        // Controls layout
        HBox controls = new HBox(10, prevButton, nextButton, backButton);
        controls.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10, controls, fullImageView);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefSize(900, 530);

        pane.getChildren().add(layout);
    }

    private void showGallery() {
        pane.getChildren().clear();
        pane.getChildren().add(grid);
    }

    public static void main(String[] args) {
        launch();
    }
}
