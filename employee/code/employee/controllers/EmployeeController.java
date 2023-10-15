package employee.controllers;
import employee.models.Employee;
import employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    public List<Employee> getEmployees(){

        return employeeService.getEmployees();

    }

    @GetMapping(path="{employeeId}")
    public Employee getStudentById(@PathVariable("employeeId") Long employeeId){

        return employeeService.getEmployeeById(employeeId);

    }

    @PostMapping
    public String registerNewStudent(@RequestBody Employee employee){

        employeeService.registerNewEmployee(employee);

        return "registerNewStudent Successful";

    }

    @DeleteMapping(path="{employeeId}")
        public String deleteStudent(@PathVariable("employeeId") Long employeeId){


        employeeService.deleteEmployee(employeeId);

        return "deleteStudent successful";

    }

    @PutMapping(path="{employeeId}")
    public String updateStudent(@PathVariable("employeeId") Long employeeId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){

        employeeService.updateEmployee(employeeId,name,email);

       return "updateStudent successful";

    }

}
