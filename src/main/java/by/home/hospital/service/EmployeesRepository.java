package by.home.hospiital.service;

import by.home.hospiital.domain.Employee;

import java.util.List;

public interface EmployeesRepository {

	List<Employee> getEmployees();

	void addEmployees(Employee employee);

	void deleteEmployees(Integer number);

}
