package com.hr.managemnt.controller;

import com.hr.managemnt.dto.DepartmentCountDTO;
import com.hr.managemnt.dto.EmployeeVehicleDTO;
import com.hr.managemnt.dto.TotalExperienceDTO;
import com.hr.managemnt.model.Employee;
import com.hr.managemnt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

        // CRUD

       @GetMapping("/")
       public List<Employee> getEmployee() {
        return employeeService.getAllEmployees();
    }

        @GetMapping("/{id}")
        public Employee getEmployeeById(@PathVariable String id) {
            return employeeService.getById(id);
        }

        @PostMapping
        public Employee createEmployee(@RequestBody Employee employee) {
            return employeeService.save(employee);
        }

        @DeleteMapping("/{id}")
        public void deleteEmployee(@PathVariable String id) {
            employeeService.delete(id);
        }

        // Custom queries
        @GetMapping("/department/{department}")
        public List<Employee> getByDepartment(@PathVariable String department) {
            return employeeService.findByDepartment(department);
        }

        @GetMapping("/age-department")
        public List<Employee> getByAgeAndDepartment(@RequestParam int age, @RequestParam String department) {
            return employeeService.findByAgeGteAndDepartment(age, department);
        }

        @GetMapping("/name-in")
        public List<Employee> getByNameIn(@RequestParam List<String> names) {
            return employeeService.findByNameIn(names);
        }

        @GetMapping("/name-not-in")
        public List<Employee> getByNameNotIn(@RequestParam List<String> names) {
            return employeeService.findByNameNotIn(names);
        }

        @GetMapping("/age-or-department")
        public List<Employee> getByAgeOrDepartment(@RequestParam int age, @RequestParam String department) {
            return employeeService.findByAgeOrDepartment(age, department);
        }


        @GetMapping("/count")
        public long countEmployees() {
            return employeeService.countEmployees();
        }

        @GetMapping("/count/age-lte/{age}")
        public long countEmployeesByAgeLte(@PathVariable int age) {
            return employeeService.countEmployeesByAgeLte(age);
        }

        @GetMapping("/sort/age-asc")
        public List<Employee> getAllSortedByAgeAsc() {
            return employeeService.findAllSortedByAgeAsc();
        }

        @GetMapping("/sort/age-desc")
        public List<Employee> getSortedByAgeDescLimit(@RequestParam(defaultValue = "2") int limit) {
            return employeeService.findAllSortedByAgeDescLimit(limit);
        }

        @GetMapping("/regex")
        public List<Employee> getByNameRegex(@RequestParam String pattern) {
            return employeeService.findByNameRegex(pattern);
        }

        // Aggregations
        @GetMapping("/with-vehicles")
        public List<EmployeeVehicleDTO> getEmployeesWithVehicles() {
            return employeeService.getEmployeesWithVehicles();
        }

        @GetMapping("/count-by-department")
        public List<DepartmentCountDTO> getEmployeeCountByDepartment() {
            return employeeService.countEmployeesByDepartment();
        }


}