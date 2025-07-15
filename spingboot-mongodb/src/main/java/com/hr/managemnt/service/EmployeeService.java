package com.hr.managemnt.service;

import com.hr.managemnt.dto.DepartmentCountDTO;
import com.hr.managemnt.dto.EmployeeVehicleDTO;
import com.hr.managemnt.dto.TotalExperienceDTO;
import com.hr.managemnt.model.Employee;
import com.hr.managemnt.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;
import  org.springframework.data.mongodb.core.query.Query;
import  org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public Employee getById(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee save(Employee e) {
        return employeeRepository.save(e);
    }

    public void delete(String id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getOneEmployee() {
        return employeeRepository.findAll().stream().findFirst().orElse(null);
    }

    public List<Employee> findByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    public List<Employee> findByAgeGteAndDepartment(Integer age, String department) {
        return employeeRepository.findByAgeGreaterThanEqualAndDepartment(age, department);
    }

    public List<Employee> findByNameIn(List<String> names) {
        return employeeRepository.findByNameIn(names);
    }

    public List<Employee> findByNameNotIn(List<String> names) {
        return employeeRepository.findByNameNotIn(names);
    }

    public List<Employee> findByAgeOrDepartment(Integer age, String department) {
        return employeeRepository.findByAgeOrDepartment(age, department);
    }

    public List<Employee> findByAgeAndDepartment(Integer age, String department) {
        return employeeRepository.findByAgeAndDepartment(age, department);
    }

    public long countEmployees() {
        return employeeRepository.count();
    }

    public long countEmployeesByAgeLte(Integer age) {
        return employeeRepository.countByAgeLessThanEqual(age);
    }

    public List<Employee> findAllSortedByAgeAsc() {
        return employeeRepository.findAllByOrderByAgeAsc();
    }

    public List<Employee> findAllSortedByAgeDescLimit(int limit) {
        return mongoTemplate.find(
              Query.query(new Criteria())
                        .with(Sort.by(Sort.Direction.DESC, "age"))
                        .limit(limit),
                Employee.class);
    }

    public List<Employee> findByNameRegex(String regex) {
        return employeeRepository.findByNameRegex(regex);
    }



    // Aggregation: Join employee with vehicle collection
    public List<EmployeeVehicleDTO> getEmployeesWithVehicles() {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.lookup("vehicle", "_id", "employeeId", "vehicles"),
                Aggregation.project("name", "department", "hobbies")
                        .and("vehicles.model").as("vehiclesModel")
                        .and("vehicles.registration").as("vehiclesRegistration")
        );
        return mongoTemplate.aggregate(agg, "employee", EmployeeVehicleDTO.class).getMappedResults();
    }

    // Aggregation: Group employees by department and count
    public List<DepartmentCountDTO> countEmployeesByDepartment() {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.group("department").count().as("totalEmployees"),
                Aggregation.sort(Sort.Direction.DESC, "totalEmployees")
        );
        return mongoTemplate.aggregate(agg, "employee", DepartmentCountDTO.class).getMappedResults();
    }
}