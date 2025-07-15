package com.hr.managemnt.repository;

import com.hr.managemnt.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {


    List<Employee> findByDepartment(String department);
    List<Employee> findByAgeGreaterThanEqualAndDepartment(Integer age, String department);
    List<Employee> findByNameIn(List<String> names);
    List<Employee> findByNameNotIn(List<String> names);
    List<Employee> findByAgeOrDepartment(Integer age, String department);
    List<Employee> findByAgeAndDepartment(Integer age, String department);
    long countByAgeLessThanEqual(Integer age);
    List<Employee> findByNameRegex(String regex);
    List<Employee> findAllByOrderByAgeAsc();
    List<Employee> findAllByOrderByAgeDesc();

}
