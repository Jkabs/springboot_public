package employee.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name="employee",schema="rms")
public class Employee {

    @Id
    @SequenceGenerator(name="employee_sequence",
                       sequenceName = "employee_sequence",
                       allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
                     generator = "employee_sequence")
    private Long employeeId;
    private  String name;
    private String email;
    private LocalDate dob;

    @Transient
    private Integer age;

    public Employee(Long employeeId, String name, String email, LocalDate dob, Integer age) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }

    public Employee(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Employee() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
