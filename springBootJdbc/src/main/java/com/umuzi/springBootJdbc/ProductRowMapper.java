package com.umuzi.springBootJdbc;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Products> {

    @Override
    public Products mapRow(ResultSet rs, int rowNum) throws SQLException {

        Products product = new Products();
        product.setProductID(rs.getInt("productID"));
        product.setProductName(rs.getString("productName"));
        product.setDescription(rs.getString("description"));
        product.setBuyPrice(rs.getDouble("buyPrice"));

        return product;
    }
}
