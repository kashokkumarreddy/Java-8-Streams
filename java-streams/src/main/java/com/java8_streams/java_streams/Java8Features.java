package com.java8_streams.java_streams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.sound.midi.SysexMessage;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// git hub link https://github.com/Java-Techie-jt/java8/blob/master/java8/Java8MethodCheatSheet.java
// you tube link : https://www.youtube.com/watch?v=-RHvF37el-A

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class Java8Features {

	public static void main(String[] args) {
		SpringApplication.run(Java8Features.class, args);

		System.out.println("Hello");
		List<Employee> employees = EmployeeDataBase.getAllEmployees();
		// forEach
	//	employees.forEach(e -> System.out.println(e.getName() + " : salary : "+e.getSalary()));
		//Method reference with for each
	//	employees.stream().forEach(System.out::println);

//		 filter
//		List<Employee> developmentEmp = employees.stream()
//				.filter(e -> e.getDept().equals("Development") && e.getSalary() == 95000)
//				.collect(Collectors.toList());
		Map<Integer, String> developmentEmp1 = 	employees.stream()
				.filter(e -> e.getDept().equals("Development") && e.getSalary() > 80000)
				.collect(Collectors.toMap(Employee::getId,Employee::getDept));
//		System.out.println("development : "+ developmentEmp);

		// Map
		// distinct
		List<String> empDept =	employees.stream()
				.map(Employee::getDept)
				.distinct()
				.collect(Collectors.toList());
		System.out.println(empDept);

		List<Stream<String>> empProjectNames =		employees.stream()
					.map(e -> e.getProjects()
							.stream()
							.map(Project::getName))
					.collect(Collectors.toList());
//		System.out.println(empProjectNames);

		// flatmap - Incase of nested class we use flatmap..

		List<String> projectName = employees.stream().flatMap(e -> e.getProjects().stream())
				.map(p -> p.getName())
				.distinct()
				.collect(Collectors.toList());

//		System.out.println(projectName);

		// sorting - instead of comparator we can use sorting
		List<Employee> ascEmployeeSalarySort = employees.stream()
				.sorted(Comparator.comparing(e -> e.getSalary()))
				.collect(Collectors.toList());
//        ascEmployeeSalarySort.forEach(System.out::println);
//        System.out.println(ascEmployeeSalarySort.get(0)); // lowest salaried employee

        List<Employee> descEmployeeSalary = employees.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing((Employee::getSalary))))
                .collect(Collectors.toList());
//        descEmployeeSalary.forEach(System.out::println);
//        System.out.println(descEmployeeSalary.get(0)); // Highest salaried employee;


		// min & map
		Optional<Employee>  lowestSalary = employees.stream()
				.min(Comparator.comparingDouble(Employee::getSalary));

//			System.out.println(lowestSalary);

		Optional<Employee> highestSalary = employees.stream()
				.max(Comparator.comparingDouble(Employee::getSalary));
//			System.out.println(highestSalary);

		//groupingBy gender
		Map<String, List<Employee>> employeeGroup = employees.stream()
				.collect(Collectors.groupingBy(Employee::getGender));

//		System.out.println(employeeGroup);


		// groupingBy gender gender -> [names]

		Map<String, List<String>> employeeGroupByGender = employees.stream()
				.collect(Collectors.groupingBy(Employee::getGender,
						Collectors.mapping(Employee::getName, Collectors.toList())));

//		System.out.println(employeeGroupByGender);

		Map<String, Long> employeeGenderCount = employees.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

//		System.out.println(employeeGenderCount);

		// findFist()
		Employee findFirstEmployee = employees.stream()
				.filter(e -> e.getDept().equals("Development"))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Employee Not found"));
//			System.out.println(findFirstEmployee.get()); //NPE

//		if(findFirstEmployee.isPresent()){
//			System.out.println(findFirstEmployee.get());
//		}
//		findFirstEmployee.ifPresent(e -> System.out.println(e.getName()));
		System.out.println(findFirstEmployee.getName());


		// findAny() - To check whether the record is coming or not.

		Employee findAnyEmployee = employees.stream()
				.filter(e -> e.getDept().equals("Development"))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Employee Not found"));

	System.out.println("find any "+findAnyEmployee);

	// anyMatch(predicate), allMatch(predicate), noneMatch(predicate)

		boolean employeeAnyMatch = employees.stream()
				.anyMatch(e -> e.getDept().equals("Development"));
//				System.out.println("Is there any employee match with development dept "+employeeAnyMatch);

		boolean employeeAllMatch = employees.stream()
				.allMatch(e -> e.getSalary() > 50000);

//		System.out.println("all match "+employeeAllMatch);

		boolean employeeNoneMatch = employees.stream()
				.noneMatch(e -> e.getDept().equals("abc"));
//		System.out.println("none match "+employeeNoneMatch);

		// Limit(long)
		List<Employee> topPaidEmployees = employees.stream()
				.sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
				.limit(3)
				.collect(Collectors.toList());
//		topPaidEmployees.forEach(e-> System.out.println(e.getName()));

		// skip(long)
		List<Employee> skipFirst3Elements = employees.stream().skip(3)
				.collect(Collectors.toList());

		skipFirst3Elements.forEach(e -> System.out.println(e.getName()));

		System.out.println("Checking PR");


	}

}
