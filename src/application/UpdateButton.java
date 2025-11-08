package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UpdateButton {
	public Employee employee;
	public Address address;
	AlertTypes alert = new AlertTypes();
	EmployeeComboBox ecB = new EmployeeComboBox();
	RadioButtonChoices rbC = new RadioButtonChoices();
	TableView<Employee> tableView = new TableView<>();
	private int currentIndex = -1;

	public UpdateButton(Employee employee) {
		this.employee = employee;
	}

	public void Display() {

		Stage stage = new Stage();
		MenuBar menuBar = Main.creatmenuBar(stage);
		stage.setTitle("Update Employee Details");

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

		ImageView employeePhoto = new ImageView();
		employeePhoto.setFitWidth(200);
		employeePhoto.setFitHeight(200);
		employeePhoto.setPreserveRatio(true);

		Image defaultImage = new Image("https://avatars.hsoubcdn.com/9e457730e2940e1e66d3d00f22642207?s=256");
		employeePhoto.setImage(defaultImage);
		employeePhoto.setUserData(null);

		empnoF.setText(employee.getEmpNo());
		fNamef1.setText(employee.getFirstName());
		fNamef2.setText(employee.getLastName());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		datePicker.setValue(LocalDate.parse(employee.getDateOfBirth(), formatter));
		Desgf.setText(employee.getDesignation());
		Emailf.setText(employee.getEmail());
		Phonef.setText(employee.getPhoneNum());
		Natiof.setText(employee.getNationality());
		Streetf.setText(employee.getAdress().getStreet());
		Cityf.setText(employee.getAdress().getCity());
		Countryf.setText(employee.getAdress().getCountry());

		switch (employee.getEducation().toLowerCase()) {
		case "phd":
			rbC.b1.setSelected(true);
			break;

		case "master":
			rbC.b2.setSelected(true);
			break;

		case "b.a":
			rbC.b3.setSelected(true);
			break;

		case "secondary school":
			rbC.b4.setSelected(true);
			break;

		case "primary school":
			rbC.b5.setSelected(true);
			break;

		}
		ecB.setValue(employee.getClass().getSimpleName());

		String photoPath = employee.getPhotoPath();
		if (photoPath != null && !photoPath.isEmpty() && new File("photos/" + photoPath).exists()) {
			employeePhoto.setImage(new Image(new File("photos/" + photoPath).toURI().toString()));
			employeePhoto.setUserData(photoPath);
		} else {
			employeePhoto.setImage(defaultImage);
			employeePhoto.setUserData(null);
		}
		
		currentIndex = EmployeeData.employeeList.indexOf(employee);
		
		Runnable updateForm = () -> {
			empnoF.setText(employee.getEmpNo());
			fNamef1.setText(employee.getFirstName());
			fNamef2.setText(employee.getLastName());
			datePicker.setValue(LocalDate.parse(employee.getDateOfBirth(), formatter));
			Desgf.setText(employee.getDesignation());
			Emailf.setText(employee.getEmail());
			Phonef.setText(employee.getPhoneNum());
			Natiof.setText(employee.getNationality());
			Streetf.setText(employee.getAdress().getStreet());
			Cityf.setText(employee.getAdress().getCity());
			Countryf.setText(employee.getAdress().getCountry());
			rbC.b1.setSelected(false);
			rbC.b2.setSelected(false);
			rbC.b3.setSelected(false);
			rbC.b4.setSelected(false);
			rbC.b5.setSelected(false);
			switch (employee.getEducation().toLowerCase()) {
			case "phd":
				rbC.b1.setSelected(true);
				break;
	
			case "master":
				rbC.b2.setSelected(true);
				break;
	
			case "b.a":
				rbC.b3.setSelected(true);
				break;
	
			case "secondary school":
				rbC.b4.setSelected(true);
				break;
	
			case "primary school":
				rbC.b5.setSelected(true);
				break;
	
			}
			ecB.setValue(employee.getClass().getSimpleName());
			String photoPathInner = employee.getPhotoPath();
			if (photoPathInner != null && !photoPathInner.isEmpty() && new File("photos/" + photoPathInner).exists()) {
				employeePhoto.setImage(new Image(new File("photos/" + photoPathInner).toURI().toString()));
				employeePhoto.setUserData(photoPathInner);
			} else {
				employeePhoto.setImage(defaultImage);
				employeePhoto.setUserData(null);
			}
		};
		
		CustomButton previousB = new CustomButton("Previous");
		CustomButton nextB = new CustomButton("Next");
		
		Runnable updateNavButtons = () -> {
			previousB.setDisable(currentIndex <= 0);
			nextB.setDisable(currentIndex >= EmployeeData.employeeList.size() - 1);
		};
		
		previousB.setOnAction(x -> {
			if (currentIndex > 0) {
				currentIndex--;
				employee = EmployeeData.employeeList.get(currentIndex);
				updateForm.run();
				updateNavButtons.run();
			}
		});
		
		nextB.setOnAction(x -> {
			if (currentIndex < EmployeeData.employeeList.size() - 1) {
				currentIndex++;
				employee = EmployeeData.employeeList.get(currentIndex);
				updateForm.run();
				updateNavButtons.run();
			}
		});
		
		updateNavButtons.run();

		CustomButton changePhoto = new CustomButton("Change Photo");
		changePhoto.setOnAction(x -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose New Employee Photo");
			File selectedFile = fileChooser.showOpenDialog(stage);
			if (selectedFile != null) {
				try {
					String fileName = empnoF.getText() + "_" + selectedFile.getName();
					File destinationFile = new File("photos/" + fileName);

					Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

					employeePhoto.setImage(new Image(destinationFile.toURI().toString()));

					employee.setPhotoPath(fileName);
					employeePhoto.setUserData(fileName);

				} catch (IOException e) {
					e.printStackTrace();
					alert.ErrorAlert("Error", "Failed to update the photo. Please try again.");
				}
			}
		});

		CustomButton updateB = new CustomButton("Update Employee");
		updateB.setOnAction(x -> {
			AddEmployee ee = new AddEmployee();
			boolean isValid1 = ee.validateInput(fNamef1.getText().trim(), fNamef2.getText().trim(),
					datePicker.getValue(), Desgf.getText().trim(), Emailf.getText().trim(), Phonef.getText().trim(),
					Natiof.getText().trim(), Streetf.getText().trim(), Cityf.getText().trim(),
					Countryf.getText().trim(), (RadioButton) rbC.educationGroup.getSelectedToggle(),
					ecB.getSelectionModel().getSelectedItem(), employeePhoto.getImage());
			if (!isValid1) {
				return;
			}
			boolean isValid = ee.validateSetEmployee(fNamef1.getText().trim(), fNamef2.getText().trim(),
					datePicker.getValue(), Phonef.getText().trim(), Emailf.getText().trim());
			if (!isValid) {
				return;
			}
			employee.setFirstName(fNamef1.getText().trim());
			employee.setLastName(fNamef2.getText().trim());
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formattedDate = datePicker.getValue().format(formatter1);
			employee.setDateOfBirth(formattedDate);
			employee.setDesignation(Desgf.getText().trim());
			employee.setEmail(Emailf.getText().trim());
			employee.setPhoneNum(Phonef.getText().trim());
			employee.setNationality(Natiof.getText().trim());
			employee.getAdress().setStreet(Streetf.getText().trim());
			employee.getAdress().setCity(Cityf.getText().trim());
			employee.getAdress().setCountry(Countryf.getText().trim());
			employee.setPhotoPath((String) employeePhoto.getUserData());

			if (rbC.b1.isSelected()) {
				employee.setEducation("phD");
			} else if (rbC.b2.isSelected()) {
				employee.setEducation("Master");
			} else if (rbC.b3.isSelected()) {
				employee.setEducation("B.A");
			} else if (rbC.b4.isSelected()) {
				employee.setEducation("Secondary School");
			} else if (rbC.b5.isSelected()) {
				employee.setEducation("Primary School");
			}
			String selectType = ecB.getValue();
			if (selectType != null) {
				switch (selectType) {
				case "Hourly Employee":
					if (!(employee instanceof HourlyEmployee)) {
						HourlyEmployee hourlyEmployee = new HourlyEmployee();
						hourlyEmployee.setEmpNo(employee.getEmpNo());
						hourlyEmployee.setFirstName(employee.getFirstName());
						hourlyEmployee.setLastName(employee.getLastName());
						hourlyEmployee.setDateOfBirth(employee.getDateOfBirth());
						hourlyEmployee.setDesignation(employee.getDesignation());
						hourlyEmployee.setEmail(employee.getEmail());
						hourlyEmployee.setPhoneNum(employee.getPhoneNum());
						hourlyEmployee.setNationality(employee.getNationality());
						hourlyEmployee.setAddress(employee.getAdress());
						hourlyEmployee.setEducation(employee.getEducation());
						employee = hourlyEmployee;
					}
					break;
				case "SalariedEmployee":
					if (!(employee instanceof SalariedEmployee)) {
						SalariedEmployee salariedEmployee = new SalariedEmployee();
						salariedEmployee.setEmpNo(employee.getEmpNo());
						salariedEmployee.setFirstName(employee.getFirstName());
						salariedEmployee.setLastName(employee.getLastName());
						salariedEmployee.setDateOfBirth(employee.getDateOfBirth());
						salariedEmployee.setDesignation(employee.getDesignation());
						salariedEmployee.setEmail(employee.getEmail());
						salariedEmployee.setPhoneNum(employee.getPhoneNum());
						salariedEmployee.setNationality(employee.getNationality());
						salariedEmployee.setAddress(employee.getAdress());
						salariedEmployee.setEducation(employee.getEducation());
						employee = salariedEmployee;
					}
					break;
				case "CommessionEmployee":
					if (!(employee instanceof CommessionEmployee)) {
						CommessionEmployee commessionEmployee = new CommessionEmployee();
						commessionEmployee.setEmpNo(employee.getEmpNo());
						commessionEmployee.setFirstName(employee.getFirstName());
						commessionEmployee.setLastName(employee.getLastName());
						commessionEmployee.setDateOfBirth(employee.getDateOfBirth());
						commessionEmployee.setDesignation(employee.getDesignation());
						commessionEmployee.setEmail(employee.getEmail());
						commessionEmployee.setPhoneNum(employee.getPhoneNum());
						commessionEmployee.setNationality(employee.getNationality());
						commessionEmployee.setAddress(employee.getAdress());
						commessionEmployee.setEducation(employee.getEducation());
						employee = commessionEmployee;
					}
					break;
				case "EmployeeBasedComession":
					if (!(employee instanceof EmployeeBasedComession)) {
						EmployeeBasedComession employeeBasedComession = new EmployeeBasedComession();
						employeeBasedComession.setEmpNo(employee.getEmpNo());
						employeeBasedComession.setFirstName(employee.getFirstName());
						employeeBasedComession.setLastName(employee.getLastName());
						employeeBasedComession.setDateOfBirth(employee.getDateOfBirth());
						employeeBasedComession.setDesignation(employee.getDesignation());
						employeeBasedComession.setEmail(employee.getEmail());
						employeeBasedComession.setPhoneNum(employee.getPhoneNum());
						employeeBasedComession.setNationality(employee.getNationality());
						employeeBasedComession.setAddress(employee.getAdress());
						employeeBasedComession.setEducation(employee.getEducation());
						employee = employeeBasedComession;

					}
					break;
				}
			}
			int index = EmployeeData.employeeList.indexOf(employee);
			if (index != -1) {
				EmployeeData.employeeList.set(index, employee);
			}
			boolean confirmed = alert.ConfirmationAlert("Confirmation",
					"Are you sure you want to Update the Information for this Employee ? ");
			if (!confirmed) {
				return;
			}
			alert.InfoAlert("Success", "Employee Information Updated Successfully!");
		});

		CustomButton backB = new CustomButton("Back");
		backB.setOnAction(x -> {
			stage.close();
		});

		HBox hb = new HBox(50, previousB, updateB, nextB, backB);
		hb.setAlignment(Pos.CENTER);
		hb.setPadding(new Insets(20));
		VBox photoBox = new VBox(15);
		photoBox.setAlignment(Pos.CENTER);
		CustomLabel photoLabel = new CustomLabel("Employee Photo");
		photoLabel.setAlignment(Pos.CENTER);
		photoBox.getChildren().addAll(employeePhoto, photoLabel, changePhoto);
		BorderPane bp = new BorderPane();
		bp.setTop(menuBar);
		bp.setCenter(gp);
		bp.setBottom(hb);
		bp.setRight(photoBox);
		Scene scene = new Scene(bp);
		stage.getIcons().add(new Image("/application/icons8-employee-50.png"));
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.showAndWait();
	}

}
