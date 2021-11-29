package com.nayan.employee.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nayan.employee.entity.Employee;
import com.nayan.employee.repository.IEmployeeRepo;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	IEmployeeRepo employeeRepo;

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		try {
			List<Employee> list = employeeRepo.findAll();

			if (list.isEmpty() || list.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/employees/{project}")
	public ResponseEntity<List<String>> getEmployee(@PathVariable("project") String project) {
		System.out.println("project");
		List<Employee> employee = employeeRepo.findByProject(project);
		List<String> nameList = new ArrayList<>();
		for (Employee employeeName : employee) {
			nameList.add(employeeName.getName());
		}
		if (employee.size() > 0) {
			return new ResponseEntity<>(nameList, HttpStatus.OK);
		}
		nameList.add("No Record Found!");
		return new ResponseEntity<>(nameList,HttpStatus.NOT_FOUND);
	}

	@PostMapping("/employees")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		System.out.println("Post employee");
		try {
			return new ResponseEntity<>(employeeRepo.save(employee), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@DeleteMapping("/employees/{department}/{project}/{name}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("department") String department,
			@PathVariable("project") String project, @PathVariable("name") String name) {
		try {
			System.out.println("Department:"+department + " Project: "+project +" Name: "+name);
			List<Employee> employeeList = employeeRepo.findByProject(project);
			Employee employeeToDelete = null;
			for (Employee employeeName : employeeList) {
				if (employeeName.getProject().equals(project) && employeeName.getDepartment().equals(department)
						&& employeeName.getName().equals(name)) {
					System.out.println("found employee!");
					employeeToDelete = employeeName;
				}
			}
			if (employeeToDelete!=null) {
				employeeRepo.delete(employeeToDelete);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
