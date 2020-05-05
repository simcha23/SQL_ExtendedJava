package com.umuzi.springBootJdbc;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customers> {

    @Override
    public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {

        Customers customer = new Customers();
        customer.setCustomerID(rs.getLong("customerID"));
        customer.setFirstName(rs.getString("FirstName"));
        customer.setLastName(rs.getString("LastName"));
        customer.setGender(rs.getString("Gender"));
        customer.setEmail(rs.getString("Email"));
        customer.setPhone(rs.getString("Phone"));
        customer.setAddress(rs.getString("Address"));
        customer.setCity(rs.getString("City"));
        customer.setCountry(rs.getString("Country"));

        return customer;
    }
}