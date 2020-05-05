package com.umuzi.springBootJdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner{

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args){

		System.out.println("\n-----------Querying customer records----------\n");
		for (Customers customer:findAll()) {
			System.out.println(
							customer.getCustomerID() + "\t" +
							customer.getFirstName() + "\t" +
							customer.getLastName() + "\t" +
							customer.getGender() + "\t" +
							customer.getEmail() + "\t" +
							customer.getPhone() + "\t" +
							customer.getAddress() + "\t" +
							customer.getCity() + "\t" +
							customer.getCountry() + "\t"
			);
		}

		System.out.println("\n-----------First Name of customers------------\n");
		for (Customers customer:findAll()) {
			System.out.println(
							customer.getFirstName() + "\t" +
							customer.getLastName()
			);
		}

		System.out.println("\n-----------First Name of the first customer------------\n");
		for (Customers customer:firstCustomerName()) {
			System.out.println(
							customer.getFirstName() + "\t" +
							customer.getLastName()
			);
		}

		System.out.println("\n-----------Update the first customer------------\n");		System.out.println(update());
		System.out.println(update());
		System.out.println("\n-----------Delete the customer------------\n");
		System.out.println(delete());
		System.out.println("\n-----------Number of status from Orders------------\n");
		System.out.println(count());
		System.out.println("\n-----------Maximum amount------------\n");
		System.out.println(getMaximum());

		System.out.println("\n-----------Querying customer records ordered by country----------\n");
		for (Customers customer:orderByCountry()) {
			System.out.println(
							customer.getFirstName() + "\t" +
							customer.getLastName() + "\t" +
							customer.getGender() + "\t" +
							customer.getEmail() + "\t" +
							customer.getPhone() + "\t" +
							customer.getAddress() + "\t" +
							customer.getCity() + "\t" +
							customer.getCountry() + "\t"
			);
		}

		System.out.println("\n-----------BuyPrice BETWEEN 100 AND 600----------\n");
		for (Products products:between()) {
			System.out.println(
							products.getProductID() + "\t" +
							products.getProductName() + "\t" +
							products.getDescription() + "\t" +
							products.getBuyPrice()
			);
		}
		System.out.println("");
	}
	public List<Customers> findAll() { //Returns all the data from customer table

			String sql = "SELECT * FROM CUSTOMERS";
			List<Customers> customers = jdbcTemplate.query(sql, new CustomerRowMapper());

		return customers;
	}
	public List<Customers> firstCustomerName() { //Returns all data with a selected customer from customer table
		String sql = "SELECT * FROM CUSTOMERS WHERE customerID = 1";
		List<Customers> customers = jdbcTemplate.query(sql, new CustomerRowMapper());

		return customers;

	}
	public String update() { //Updates only the selected fields from customer table
		String status = null;
		String sql = "UPDATE Customers SET FirstName = 'Lerato', LastName = 'Mabitso' WHERE CustomerID = 1";
		int customers = jdbcTemplate.update(sql);
		if(customers == 1){
			status = "Update Successfully executed!";
		}
		return status;
	}
	public String delete() { //Deletes only the selected customer from customer table
		String status;
		String sql = "DELETE FROM Customers WHERE CustomerID = 2";
		int customers = jdbcTemplate.update(sql);

		if(customers == 1){
			status = "Delete Successfully executed!";
		}else{
			status = "Selected record not available";
		}
		return status;
	}
	public int count() {//Counts the number of statuses from orders table

		String query = "SELECT COUNT(Status) from Orders";
		int total = jdbcTemplate.queryForObject(query,Integer.class);
		return total;
	}
	public Double getMaximum() { //Returns the maximum amount from payments table

		String query = "SELECT MAX(Amount) FROM Payments";
		Double maximum = jdbcTemplate.queryForObject(query,Double.class);
		return maximum;
	}
	public List<Customers> orderByCountry() { //Returns all the customers from customer table sorted by country

		String sql = "SELECT * FROM Customers ORDER BY Country";
		List<Customers> customers = jdbcTemplate.query(sql, new CustomerRowMapper());

		return customers;
	}
	public List<Products> between() { //Returns all products with the price between a specified range from products table

		String sql = "SELECT * FROM Products WHERE BuyPrice BETWEEN 100 AND 600";
		List<Products> products = jdbcTemplate.query(sql, new ProductRowMapper());

		return products;
	}
}
