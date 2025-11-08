package application;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class IconButton extends Button {
	public IconButton(String text, String imagePath) {
		super(text);
		Image icon = new Image(imagePath);
		ImageView iconView = new ImageView(icon);
		iconView.setFitWidth(25);
		iconView.setFitHeight(25);
		this.setGraphic(iconView);
		this.setStyle("-fx-background-color: #C6E2FF; -fx-text-fill: #696969 ; -fx-font-size: 15px; -fx-padding: 10px;");
		this.setOnMouseEntered(e -> this.setStyle("-fx-background-color: #00509E; -fx-text-fill: white;"));
		this.setOnMouseExited(e -> this.setStyle("-fx-background-color: #C6E2FF; -fx-text-fill: #101010;"));
		this.setEffect(new DropShadow(10, Color.BLACK));
	}
}
