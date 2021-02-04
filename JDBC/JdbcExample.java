package assignment;

import java.sql.*;
import java.util.*;

class Employee
{
	private int id, age;
	private String name, address, designation, salary;
	
	Employee(int id, String name, int age, String address, String designation, String salary){
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.designation = designation;
		this.salary = salary;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
}

public class JdbcExample {
	
	public static void main(String[] args) throws Exception {
		createTable();
		insertData();
		printData();
	}
	
	public static Connection getConnection() {
		try {
			 String url = "jdbc:mysql://localhost:3307/jdbc";
		     String username = "root";
			 String password = "1208";
			 Class.forName("com.mysql.jdbc.Driver");
			 
			 Connection connection = DriverManager.getConnection(url, username, password);
			 return connection;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static void createTable() throws Exception {
		PreparedStatement create = null;
		try {
			Connection connection = getConnection();
			final String sql = "CREATE TABLE IF NOT EXISTS Employee( ID int NOT NULL, NAME varchar(45) NOT NULL, AGE int NOT NULL, ADDRESS varchar(100), DESIGNATION varchar(45), SALARY varchar(10), PRIMARY KEY(ID));";
			create = connection.prepareStatement(sql);
			create.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			if(create != null)
				create.close();
		}
	}
	
	public static void insertData() throws Exception {
		PreparedStatement insert = null;
		try {
			Connection connection = getConnection();
			ArrayList<Employee> employees = new ArrayList<Employee>();
			employees.add(new Employee(1, "Ankit Kumar", 22, "Patna", "Developer", "50000"));
			employees.add(new Employee(2, "Mark Taylor", 34, "Dehradun", "Founder", "150000"));
			employees.add(new Employee(3, "Pankaj Raj", 20, "Varanasi", "Developer", "50000"));
			employees.add(new Employee(4, "Aditya Thakre", 28, "Mumbai", "Sr. Developer", "80000"));
			employees.add(new Employee(5, "Rashi Gupta", 23, "Delhi", "HR", "40000"));
			
			for(Employee e : employees) {
			final String sql = "INSERT INTO employee (ID, NAME, AGE, ADDRESS, DESIGNATION, SALARY) VALUES (?, ?, ?, ?, ?, ?);";
			insert = connection.prepareStatement(sql);
			
			insert.setInt(1, e.getId());
			insert.setString(2, e.getName());
			insert.setInt(3, e.getAge());
			insert.setString(4, e.getAddress());
			insert.setString(5, e.getDesignation());
			insert.setString(6, e.getSalary());
			insert.executeUpdate();
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			if(insert != null)
				insert.close();
		}
	}
	
	public static void printData() throws Exception {
		PreparedStatement print = null;
		ResultSet result = null;
		try {
			Connection connection = getConnection();
			final String sql = "SELECT * FROM employee ORDER BY AGE";
			print = connection.prepareStatement(sql);
			result = print.executeQuery(sql);
			System.out.printf("%2s%20s%10s%15s%20s%15s \n", "ID", "NAME", "AGE", "ADDRESS", "DESIGNATION", "SALARY");
			while(result.next()) {
				System.out.printf("%2d%20s%10d%15s%20s%15s \n", result.getInt("ID"), result.getString("NAME"), result.getInt("AGE"), result.getString("ADDRESS"), result.getString("DESIGNATION"), result.getString("SALARY"));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			if(print != null)
				print.close();
			if(result != null)
				result.close();
		}
	}
}