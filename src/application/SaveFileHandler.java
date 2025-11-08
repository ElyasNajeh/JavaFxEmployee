package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SaveFileHandler implements EventHandler<ActionEvent> {
	private ArrayList<Employee> employeeList;
	private AlertTypes alert;

	public SaveFileHandler(ArrayList<Employee> employeeList) {
		this.employeeList = employeeList;
		this.alert = new AlertTypes();
	}

	@Override
	public void handle(ActionEvent o) {

		File file = new File("C:\\Users\\HP\\eclipse-workspace\\FxEmployee\\Employees File.txt");


		ArrayList<String> existingLines = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				existingLines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			alert.ErrorAlert("Error", "Failed to read existing data from the file.");
			return;
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for (Employee employee : employeeList) {
				boolean updated = false;

				for (int i = 0; i < existingLines.size(); i++) {
					String[] data = existingLines.get(i).split(",");
					if (data[0].equals(employee.getEmpNo())) {
						existingLines.set(i, buildEmployeeData(employee));
						updated = true;
						break;
					}
				}

				if (!updated) {
					existingLines.add(buildEmployeeData(employee));
				}
			}

			for (String line : existingLines) {
				writer.write(line);
				writer.newLine();
			}

			alert.InfoAlert("Success", "Employee data saved successfully!");
		} catch (IOException e) {
			e.printStackTrace();
			alert.ErrorAlert("Error", "Failed to save employee data.");
		}
	}

	private String buildEmployeeData(Employee employee) {
		StringBuilder employeeData = new StringBuilder();
		employeeData.append(employee.getEmpNo()).append(",");
		employeeData.append(employee.getFirstName()).append(",");
		employeeData.append(employee.getLastName()).append(",");
		employeeData.append(employee.getDateOfBirth()).append(",");
		employeeData.append(employee.getDesignation()).append(",");
		employeeData.append(employee.getPhoneNum()).append(",");
		employeeData.append(employee.getEmail()).append(",");
		employeeData.append(employee.getNationality()).append(",");
		employeeData.append(employee.getAdress().getStreet()).append(",");
		employeeData.append(employee.getAdress().getCity()).append(",");
		employeeData.append(employee.getAdress().getCountry()).append(",");
		employeeData.append(employee.getEducation()).append(",");
		employeeData.append(employee.getEmployeeType()).append(",");
		employeeData.append(employee.getPhotoPath());
		return employeeData.toString();
	}
}
