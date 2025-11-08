package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddEmployee {
	private String photoPathTemp = null;
	Employee employee;
	Stage stage = new Stage();
	MenuBar menuBar = Main.creatmenuBar(stage);
	private static final String DEFAULTPHOTO = "https://avatars.hsoubcdn.com/9e457730e2940e1e66d3d00f22642207?s=256";
	AlertTypes alert = new AlertTypes();
	ImageView empPho = new ImageView(new Image(DEFAULTPHOTO));
	EmployeeComboBox ecB = new EmployeeComboBox();

	public boolean validateInput(String firstName, String lastName, LocalDate dob1, String desgi, String em,
			String phone, String nati, String street, String city, String country, RadioButton Education, String empTy,
			Image photo) {
		if (firstName.isEmpty()) {
			alert.ErrorAlert("Error", "First Name Cannot be Empty.");
			return false;
		}
		if (lastName.isEmpty()) {
			alert.ErrorAlert("Error", "last Name Cannot be Empty.");
			return false;
		}
		if (dob1 == null) {
			alert.ErrorAlert("Error", "Date of Birthday Cannot be Empty.");
			return false;
		}

		if (desgi.isEmpty()) {
			alert.ErrorAlert("Error", "Designation Cannot be Empty.");
			return false;
		}
		if (em.isEmpty()) {
			alert.ErrorAlert("Error", "Email Cannot be Empty.");
			return false;
		}
		if (phone.isEmpty()) {
			alert.ErrorAlert("Error", "Phone Number Cannot be Empty.");
			return false;
		}
		if (nati.isEmpty()) {
			alert.ErrorAlert("Error", "Nationality Cannot be Empty. ");
			return false;
		}
		if (street.isEmpty()) {
			alert.ErrorAlert("Error", "Street Cannot be Empty.");
			return false;
		}
		if (city.isEmpty()) {
			alert.ErrorAlert("Error", "City Cannot be Empty.");
			return false;
		}
		if (country.isEmpty()) {
			alert.ErrorAlert("Error", "Country Cannot be Empty.");
			return false;
		}

		if (Education == null) {
			alert.ErrorAlert("Error", "Education Cannot be Empty");
			return false;
		}

		if (empTy == null || empTy.isEmpty()) {
			alert.ErrorAlert("Error", "Please select an Employee Type.");
			return false;
		}

		if (photo.getUrl().equals(DEFAULTPHOTO)) {
			alert.ErrorAlert("Error", "Photo of Employee Cannot be Empty.");
			return false;
		}
		return true;
	}

	public boolean validateSetEmployee(String firstName, String lastName, LocalDate dob1, String phone, String em) {
		Employee tempEmployee = new SalariedEmployee();

		if (!firstName.matches("[a-zA-Z]+")) {
			alert.ErrorAlert("Error", "First Name must contain characters only.");
			return false;
		}
		tempEmployee.setFirstName(firstName);

		if (!lastName.matches("[a-zA-Z]+")) {
			alert.ErrorAlert("Error", "Last Name must contain characters only.");
			return false;
		}
		tempEmployee.setLastName(lastName);

		LocalDate currentDate = LocalDate.now();
		int age = Period.between(dob1, currentDate).getYears();
		if (age < 16) {
			alert.ErrorAlert("Error", "The Employee must be at least 16 years old.");
			return false;
		}
		tempEmployee.setDateOfBirth(dob1.toString());

		if (!phone.matches("^(059|056)\\d{6}$")) {
			alert.ErrorAlert("Error", "Phone Number must start with 059 or 056 and the length must be 9.");
			return false;
		}
		tempEmployee.setPhoneNum(phone);

		if (!em.matches("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+$")) {
			alert.ErrorAlert("Error", "Invalid Email format.");
			return false;
		}
		tempEmployee.setEmail(em);

		return true;
	}

	public void Display() {
		Stage stage = new Stage();
		stage.setWidth(1000);
		stage.setHeight(800);
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(10));
		gp.setHgap(10);
		gp.setVgap(10);

		CustomLabel empNo = new CustomLabel("Employee No");
		CustomTextField empnoF = new CustomTextField();
		empnoF.setText(String.valueOf(Employee.getCount()));
		empnoF.setDisable(true);
		empNo.setMinWidth(100);
		empnoF.setMinWidth(200);
		gp.add(empNo, 0, 0);
		gp.add(empnoF, 1, 0);

		CustomLabel fName1 = new CustomLabel("First Name :");
		CustomTextField fNamef1 = new CustomTextField();
		gp.add(fName1, 0, 1);
		gp.add(fNamef1, 1, 1);

		CustomLabel fName2 = new CustomLabel("Last Name :");
		CustomTextField fNamef2 = new CustomTextField();
		gp.add(fName2, 0, 2);
		gp.add(fNamef2, 1, 2);

		CustomLabel dBirth = new CustomLabel("Date of Birth :");
		DatePicker datePicker = new DatePicker();
		datePicker.setPromptText("Select Date Of Birthday :");
		gp.add(dBirth, 0, 3);
		gp.add(datePicker, 1, 3);

		CustomLabel Desg = new CustomLabel("Desgination :");
		CustomTextField Desgf = new CustomTextField();
		gp.add(Desg, 0, 4);
		gp.add(Desgf, 1, 4);

		CustomLabel Email = new CustomLabel("Email :");
		CustomTextField Emailf = new CustomTextField();
		gp.add(Email, 0, 5);
		gp.add(Emailf, 1, 5);

		CustomLabel Phone = new CustomLabel("Phone No :");
		CustomTextField Phonef = new CustomTextField();
		gp.add(Phone, 0, 6);
		gp.add(Phonef, 1, 6);

		CustomLabel Natio = new CustomLabel("Nationality :");
		CustomTextField Natiof = new CustomTextField();
		gp.add(Natio, 0, 7);
		gp.add(Natiof, 1, 7);

		CustomLabel Street = new CustomLabel("Street :");
		CustomTextField Streetf = new CustomTextField();
		gp.add(Street, 0, 8);
		gp.add(Streetf, 1, 8);

		CustomLabel City = new CustomLabel("City :");
		CustomTextField Cityf = new CustomTextField();
		gp.add(City, 0, 9);
		gp.add(Cityf, 1, 9);

		CustomLabel Country = new CustomLabel("Country :");
		CustomTextField Countryf = new CustomTextField();
		gp.add(Country, 0, 10);
		gp.add(Countryf, 1, 10);

		CustomLabel educaLabel = new CustomLabel("Education :");
		RadioButtonChoices rbC = new RadioButtonChoices();
		VBox vbC = new VBox(10, rbC.b1, rbC.b2, rbC.b3, rbC.b4, rbC.b5);
		gp.add(educaLabel, 0, 16);
		gp.add(vbC, 1, 16);

		CustomLabel typeEmp = new CustomLabel("Employee Type :");

		typeEmp.setMinWidth(108);
		ecB.setMinWidth(130);
		gp.add(typeEmp, 54, 16);
		gp.add(ecB, 55, 16);

		HBox hb = new HBox(150);
		hb.setAlignment(Pos.CENTER);
		hb.setPadding(new Insets(20, 0, 50, 0));

		CustomButton clearButton = new CustomButton("Clear");
		clearButton.setOnAction(x -> {
			fNamef1.clear();
			fNamef2.clear();
			datePicker.setValue(null);
			Desgf.clear();
			Emailf.clear();
			Phonef.clear();
			Natiof.clear();
			Streetf.clear();
			Cityf.clear();
			Countryf.clear();
			rbC.educationGroup.selectToggle(null);
			ecB.getSelectionModel().clearSelection();
			empPho.setImage(new Image(DEFAULTPHOTO));
			photoPathTemp = null;
			gp.getChildren().remove(ecB);
			ecB = new EmployeeComboBox();
			ecB.setPromptText("Select Employee Type :");
			gp.add(ecB, 55, 16);

		});

		CustomButton addButton = new CustomButton("Add Details");
		addButton.setOnAction(x -> {
			try {
				String empN = empnoF.getText();
				String firstName = fNamef1.getText().trim();
				String lastName = fNamef2.getText().trim();
				LocalDate dob1 = datePicker.getValue();
				String dob;
				if (dob1 == null) {
					alert.ErrorAlert("Error", "Date of Birthday Cannot be Empty.");
					return;
				} else {
					dob = dob1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				}
				String desgi = Desgf.getText().trim();
				String em = Emailf.getText().trim();
				String phone = Phonef.getText().trim();
				String nati = Natiof.getText().trim();
				String street = Streetf.getText().trim();
				String city = Cityf.getText().trim();
				String country = Countryf.getText().trim();
				RadioButton Education = (RadioButton) rbC.educationGroup.getSelectedToggle();
				String empTy = ecB.getSelectionModel().getSelectedItem();
				Image photo = empPho.getImage();
				boolean isValid1 = validateInput(firstName, lastName, dob1, desgi, em, phone, nati, street, city,
						country, Education, empTy, photo);
				if (!isValid1) {
					return;
				}
				boolean isValid = validateSetEmployee(firstName, lastName, dob1, phone, em);
				if (!isValid) {
					return;
				}
				Address address = new Address(street, city, country);

				Employee employee = null;

				switch (empTy) {
				case "Hourly Employee":
					employee = new HourlyEmployee(firstName, lastName, dob, address, phone, em, nati, desgi,
							Education.getText(), new Image(new File("photos/" + photoPathTemp).toURI().toString()),
							(short) 1, 2.5f);
					break;
				case "Salaried Employee":
					employee = new SalariedEmployee(firstName, lastName, dob, address, phone, em, nati, desgi,
							Education.getText(), new Image(new File("photos/" + photoPathTemp).toURI().toString()),
							4075);
					break;
				case "Commession Employee":
					employee = new CommessionEmployee(firstName, lastName, dob, address, phone, em, nati, desgi,
							Education.getText(), new Image(new File("photos/" + photoPathTemp).toURI().toString()), 0);
					break;
				case "Commession Based Employee":
					employee = new EmployeeBasedComession(firstName, lastName, dob, address, phone, em, nati, desgi,
							Education.getText(), new Image(new File("photos/" + photoPathTemp).toURI().toString()), 0,
							0);
					break;
				default:
					alert.ErrorAlert("Error", "Invalid Employee Type. ");
					return;
				}
				employee.setPhotoPath(photoPathTemp);
				empnoF.setText(String.valueOf(Employee.getCount()));
				boolean confirmed = alert.ConfirmationAlert("Confirmation",
						"Are you sure you want to Add the Employee ? ");
				if (!confirmed) {
					return;
				}
				EmployeeData.employeeList.add(employee);
				alert.InfoAlert("Success", "Employee Added Successfully!");
				fNamef1.clear();
				fNamef2.clear();
				datePicker.setValue(null);
				Desgf.clear();
				Emailf.clear();
				Phonef.clear();
				Natiof.clear();
				Streetf.clear();
				Cityf.clear();
				Countryf.clear();
				rbC.educationGroup.selectToggle(null);
				ecB.getSelectionModel().clearSelection();
				empPho.setImage(new Image("https://avatars.hsoubcdn.com/9e457730e2940e1e66d3d00f22642207?s=256"));
				gp.getChildren().remove(ecB);
				ecB = new EmployeeComboBox();
				ecB.setPromptText("Select Employee Type :");
				gp.add(ecB, 55, 16);
			} catch (Exception e) {
				alert.ErrorAlert("Error", e.toString());
			}

		});

		CustomButton backButton = new CustomButton("Back");
		backButton.setOnAction(new BackButtonHandler(stage));

		hb.getChildren().addAll(clearButton, addButton, backButton);

		empPho.setFitHeight(200);
		empPho.setPreserveRatio(true);
		CustomButton addPho = new CustomButton("Add Employee Photo");
		addPho.setOnAction(x -> {
			FileChooser fc = new FileChooser();
			fc.setTitle("Choose Employee Photo");
			File f = fc.showOpenDialog(stage);
			if (f != null) {
				try {
					String fileName = empnoF.getText() + "_" + f.getName();
					File desFile = new File("photos/" + fileName);

					Files.copy(f.toPath(), desFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

					empPho.setImage(new Image(desFile.toURI().toString()));

					photoPathTemp = fileName;

				} catch (IOException e) {
					e.printStackTrace();
					alert.ErrorAlert("Error", "Failed to save the photo. Please try again.");
				}
			}
		});
		VBox vb = new VBox(10, empPho, addPho);
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(0, 30, 0, 0));
		gp.add(vb, 3, 5);

		BorderPane bp = new BorderPane();
		bp.setTop(menuBar);
		bp.setCenter(gp);
		bp.setBottom(hb);
		bp.setRight(vb);

		Scene scene = new Scene(bp);
		stage.getIcons().add(new Image("/application/icons8-employee-50.png"));
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.showAndWait();

	}

}
