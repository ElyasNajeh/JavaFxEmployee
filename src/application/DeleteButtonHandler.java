package application;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;

public class DeleteButtonHandler implements EventHandler<ActionEvent> {
	public List<Employee> employeeList = EmployeeData.employeeList;
	public int currentIndex;
	public AlertTypes alert;
	TableView<Employee> tableView = new TableView<>();

	public DeleteButtonHandler(List<Employee> employeeList, int currentIndex, AlertTypes alert) {
		this.employeeList = employeeList;
		this.currentIndex = currentIndex;
		this.alert = alert;
	}

	@Override
	public void handle(ActionEvent o) {
		if (employeeList.isEmpty()) {
			alert.ErrorAlert("Error", "No Employee Avilable to delete");
			return;
		}
		boolean Confirmed = alert.ConfirmationAlert("Confirmation", "Are you Sure you want to Delete this Employee ?");

		if (!Confirmed) {
			return;
		}
		employeeList.remove(currentIndex);
		if (!employeeList.isEmpty() && currentIndex >= employeeList.size()) {
			currentIndex = employeeList.size() - 1;
		} else if (employeeList.isEmpty()) {
			currentIndex = -1;
		}
		alert.InfoAlert("Success", "Employee deleted successfully!");
	}

}
