package application;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StatiscalReport {

	private ObservableList<Employee> employeeData;

	private TableView<Employee> reportTable;

	public StatiscalReport(ObservableList<Employee> employeeData) {
		this.employeeData = employeeData;
		this.reportTable = new TableView<>();
	}

	public List<Employee> getHighestPaidEmployeesType() {

		HourlyEmployee highestHourly = null;
		double highestHourlyPay = 0;

		SalariedEmployee highestSalaried = null;
		double highestSalariedPay = 0;

		CommessionEmployee highestCommission = null;
		double highestCommissionPay = 0;

		EmployeeBasedComession highestCommissionBased = null;
		double highestCommissionBasedPay = 0;

		for (Employee emp : employeeData) {
			double salary = emp.Payment();

			if (emp instanceof HourlyEmployee) {
				if (highestHourly == null || salary > highestHourlyPay) {
					highestHourly = (HourlyEmployee) emp;
					highestHourlyPay = salary;
				}
			} else if (emp instanceof SalariedEmployee) {
				if (highestSalaried == null || salary > highestSalariedPay) {
					highestSalaried = (SalariedEmployee) emp;
					highestSalariedPay = salary;
				}
			} else if (emp instanceof CommessionEmployee && !(emp instanceof EmployeeBasedComession)) {
				if (highestCommission == null || salary > highestCommissionPay) {
					highestCommission = (CommessionEmployee) emp;
					highestCommissionPay = salary;
				}
			} else if (emp instanceof EmployeeBasedComession) {
				if (highestCommissionBased == null || salary > highestCommissionBasedPay) {
					highestCommissionBased = (EmployeeBasedComession) emp;
					highestCommissionBasedPay = salary;
				}
			}
		}

		List<Employee> result = new ArrayList<>();
		if (highestHourly != null) {
			result.add(highestHourly);
		}
		if (highestSalaried != null) {
			result.add(highestSalaried);
		}
		if (highestCommission != null) {
			result.add(highestCommission);
		}
		if (highestCommissionBased != null) {
			result.add(highestCommissionBased);
		}

		return result;
	}

	public double calculateTotalSalary() {
		double total = 0.0;
		for (Employee emp : employeeData) {
			total += emp.Payment();
		}
		return total;
	}

	public List<Employee> sortEmployees(String sortBy) {
		List<Employee> sortedList = new ArrayList<>(employeeData);

		switch (sortBy) {
		case "firstName":
			sortedList.sort(new Comparator<Employee>() {
				@Override
				public int compare(Employee e1, Employee e2) {
					return e1.getFirstName().compareTo(e2.getFirstName());
				}
			});
			break;

		case "lastName":
			sortedList.sort(new Comparator<Employee>() {
				@Override
				public int compare(Employee e1, Employee e2) {
					return e1.getLastName().compareTo(e2.getLastName());
				}
			});
			break;

		case "education":
			sortedList.sort(new Comparator<Employee>() {
				@Override
				public int compare(Employee e1, Employee e2) {
					return e1.getEducation().compareTo(e2.getEducation());
				}
			});
			break;

		case "salary":
			sortedList.sort(new Comparator<Employee>() {
				@Override
				public int compare(Employee e1, Employee e2) {
					return Double.compare(e1.Payment(), e2.Payment());
				}
			});
			break;

		default:
			break;
		}
		return sortedList;
	}

	public void fillReportTable(List<Employee> employees) {
		reportTable.getItems().clear();
		reportTable.getItems().addAll(employees);
	}

	public void displayAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Statistical Report");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public TableView<Employee> getReportTable() {
		return reportTable;
	}
}
