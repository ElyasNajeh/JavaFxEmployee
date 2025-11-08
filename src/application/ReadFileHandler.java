package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ReadFileHandler implements EventHandler<ActionEvent> {
	AlertTypes alert = new AlertTypes();

	@Override
	public void handle(ActionEvent o) {
		FileChooser fc = new FileChooser();
		fc.setTitle("Select Employees File");
		fc.setInitialDirectory(new File("C:\\Users\\HP\\eclipse-workspace\\FxEmployee"));
		Stage stage = new Stage();
		File f = fc.showOpenDialog(stage);

		if (f != null) {
			try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
				String line;
				EmployeeData.employeeList.clear();

				while ((line = reader.readLine()) != null) {
					System.out.println("Reading line: " + line);
					String[] data = line.split(",");
					if (data.length < 14) {
						alert.ErrorAlert("Error", "Invalid data format in file.");
						continue;
					}

					try {
						Employee employee;
						String employeeType = data[12].trim().toLowerCase();

						switch (employeeType) {
						case "hourlyemployee":
						case "hourly employee":
							employee = new HourlyEmployee();
							break;
						case "salariedemployee":
						case "salaried employee":
							employee = new SalariedEmployee();
							break;
						case "commessionemployee":
						case "commession employee":
							employee = new CommessionEmployee();
							break;
						case "employeebasedcomession":
						case "commession based employee":
							employee = new EmployeeBasedComession();
							break;
						default:
							alert.ErrorAlert("Error", "Invalid Employee Type: " + data[12]);
							continue;
						}

						employee.setEmpNo(data[0].trim());
						employee.setFirstName(data[1].trim());
						employee.setLastName(data[2].trim());
						employee.setDateOfBirth(data[3].trim());
						employee.setDesignation(data[4].trim());
						employee.setPhoneNum(data[5].trim());
						employee.setEmail(data[6].trim());
						employee.setNationality(data[7].trim());
						Address address = new Address(data[8].trim(), data[9].trim(), data[10].trim());
						employee.setAddress(address);

						switch (data[11].trim().toLowerCase()) {
						case "phd":
							employee.setEducation("PhD");
							break;
						case "master":
							employee.setEducation("Master");
							break;
						case "b.a":
							employee.setEducation("B.A");
							break;
						case "secondary school":
							employee.setEducation("Secondary School");
							break;
						case "primary school":
							employee.setEducation("Primary School");
							break;
						default:
							alert.ErrorAlert("Error", "Invalid Education Type: " + data[11]);
							continue;
						}

						String photoPath = data[13].trim();
						employee.setPhotoPath(photoPath);
						File photoFile = new File(photoPath);

						if (photoFile.isAbsolute()) {
							photoPath = photoFile.getName();
						}

						photoFile = new File("photos/" + photoPath);

						if (photoFile.exists()) {
							employee.setPhotoPath(photoPath);
						}

						EmployeeData.employeeList.add(employee);
					} catch (Exception e) {
						alert.ErrorAlert("Error", "Cannot read the file.");
						e.printStackTrace();
					}
				}
				alert.InfoAlert("Success", "File read successfully!");
			} catch (IOException e) {
				alert.ErrorAlert("Error", "Error while reading the file.");
				e.printStackTrace();
			}
		} else {
			alert.ErrorAlert("Error", "No file selected. Please select a file.");
		}
	}
}
