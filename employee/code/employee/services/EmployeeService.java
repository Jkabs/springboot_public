package employee.services;

import employee.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee getEmployeeById(Long employeeId);

    void registerNewEmployee(Employee employee);

    void deleteEmployee(Long employeeId);

    void updateEmployee(Long employeeId, String name, String email);
}
