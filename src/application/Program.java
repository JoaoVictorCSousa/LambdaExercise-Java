package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {
	
	public static void main(String []args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter a full file path: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Employee> list = new ArrayList<>();
			String line = br.readLine();
			while(line != null) {
				String [] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				br.readLine();
			}
			
			System.out.println("Type a salary: ");
			double Salary = sc.nextDouble();
			
			List<String> emails = list.stream()
					.filter(x -> x.getSalary() > Salary)
					.map(x -> x.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			System.out.println("Email of people whose salary is more than: " + Salary + ":");
			emails.forEach(System.out::println);
			
			double sum = list.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			
			System.out.println("SUM of the salary of people whose name starts with the letter M" + sum);
			
		}catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
			
		}sc.close();
	}	

}
