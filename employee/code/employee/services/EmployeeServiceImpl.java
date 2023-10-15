package employee.services;

import employee.models.Employee;
import employee.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<Employee> getEmployees() {

        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {

       return employeeRepository.findById(employeeId)
               .orElseThrow(()->new IllegalStateException("No such Employee exists"));
    }

    @Override
    public void registerNewEmployee(Employee employee) {

        Optional existsByEmail= employeeRepository.findEmployeeByEmail(employee.getEmail());

        if (!existsByEmail.isEmpty()) {

         throw new IllegalStateException("Oops ! An employee with similar email " + employee.getEmail() +" exists.");

        }

        employeeRepository.save(employee);

    }

    @Override
    public void deleteEmployee(Long employeeId) {
        //Check if employee exists
        Boolean exists=employeeRepository.existsById(employeeId);
        if(!exists){

            throw  new IllegalStateException("Sorry we couldn't delete this employee." +
                    " Employee with Id" + employeeId + "does not exist!" );
        }


        employeeRepository.deleteById(employeeId);
    }

    @Override
    @Transactional
    public void updateEmployee(Long employeeId, String name, String email) {

        //If the employee exists update
        Employee employee=employeeRepository.findById(employeeId)
                .orElseThrow(()->new IllegalStateException("No such employee exist!"));

        employee.setName(name);
        employee.setEmail(email);

    }
}
