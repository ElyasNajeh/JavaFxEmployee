package application;

import javafx.scene.control.Label;

public class CustomLabel extends Label {
	public CustomLabel(String text) {
		super(text);
		this.setStyle("-fx-font-size: 15px; -fx-text-fill: #333;");
	}
}
