package com.example.nthatisii;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;

public class HelloApplication extends Application {

    private String[] imagePaths = new String[]{
            "riri.jpg",
            "riri1.jpg",
            "riri2.jpg",
            "riri3.jpg",
            "riri4.jpg",
            "riri5.jpg"
    };

    private int currentImageIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        ImageGallery imageGallery = new ImageGallery();
        Scene scene = new Scene(imageGallery, 800, 600);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setTitle("Bad Girl Riri Gallery");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    class ImageGallery extends BorderPane {

        private ImageView imageView;
        private Button prevButton;
        private Button nextButton;

        public ImageGallery() {
            imageView = new ImageView();
            imageView.setFitWidth(600);
            imageView.setFitHeight(400);
            imageView.getStyleClass().add("full-size-image");

            prevButton = new Button("Previous");
            prevButton.setOnAction(e -> showPreviousImage());
            nextButton = new Button("Next");
            nextButton.setOnAction(e -> showNextImage());

            HBox buttonsBox = new HBox(10, prevButton, nextButton);
            buttonsBox.setAlignment(Pos.CENTER);
            buttonsBox.getStyleClass().add("navigation-buttons");

            StackPane centerPane = new StackPane(imageView);
            centerPane.getStyleClass().add("center-pane");

            setCenter(centerPane);
            setBottom(buttonsBox);

            showImage(currentImageIndex);
        }

        private void showImage(int index) {
            if (index >= 0 && index < imagePaths.length) {
                Image image = new Image(new File(imagePaths[index]).toURI().toString());
                imageView.setImage(image);
                currentImageIndex = index;
            }
        }

        private void showNextImage() {
            currentImageIndex = (currentImageIndex + 1) % imagePaths.length;
            showImage(currentImageIndex);
        }

        private void showPreviousImage() {
            currentImageIndex = (currentImageIndex - 1 + imagePaths.length) % imagePaths.length;
            showImage(currentImageIndex);
        }
    }
}