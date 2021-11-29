package com.nayan.employee;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nayan.employee.entity.Employee;
import com.nayan.employee.repository.IEmployeeRepo;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class EmployeeRestApiApplicationTests {

	@Autowired
	IEmployeeRepo employeeRepo;

	@Test
	@Order(1)
	public void testCreate() {
		Employee e = new Employee();
		e.setName("Nayan");
		e.setDepartment("Cloud");
		e.setProject("Test");
		employeeRepo.save(e);
		List<Employee> empList=employeeRepo.findByProject("Test");
		assertThat(empList).size().isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void testReadAll() {
		List list =employeeRepo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}

	@Test
	@Order(3)
	public void testRead() {
		List<Employee> empList=employeeRepo.findByProject("Test");
		assertThat(empList.get(0).getName()).contains("Nayan");
	}


	@Test
	@Order(4)
	public void testDelete() {
		List<Employee> employeeList = employeeRepo.findByProject("Test");
		Employee employeeToDelete = null;
		for (Employee employeeName : employeeList) {
			if (employeeName.getProject().equals("Test") && employeeName.getDepartment().equals("Cloud")
					&& employeeName.getName().equals("Nayan")) {
				employeeToDelete = employeeName;
			}
		}
		employeeRepo.delete(employeeToDelete);
		List<Employee> employeeFinalList = employeeRepo.findByProject("Test");
		assertThat(employeeToDelete.getName()).isNotIn(employeeFinalList);
	}
}
