package application;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class CustomButton extends Button {
	public CustomButton(String text) {
		super(text);
		this.setStyle("-fx-background-color: #C6E2FF; -fx-text-fill: #696969 ; -fx-font-size: 15px; -fx-padding: 5px;");
		this.setOnMouseEntered(e -> this.setStyle("-fx-background-color: #00509E; -fx-text-fill: white;"));
		this.setOnMouseExited(e -> this.setStyle("-fx-background-color: #C6E2FF; -fx-text-fill: #101010;"));
		this.setPrefSize(300, 50);
	}
}
