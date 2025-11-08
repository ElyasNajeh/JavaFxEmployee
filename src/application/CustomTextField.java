package application;

import javafx.scene.control.TextField;

public class CustomTextField extends TextField {
	public CustomTextField() {
		this.setStyle("-fx-border-color: #C6E2FF; -fx-font-size: 14px;");
		this.setPrefSize(300, 40);
	}
}
