import java.sql.*;
import org.postgresql.Driver;

public class Application {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/umuzi";
        String user = "user";
        String password = "pass";
        Connection connection = null;
        ResultSet resultSet;
        int results;

        try {
            if(!Driver.isRegistered()){
                Driver.register();//Register postgres
            }

            connection = DriverManager.getConnection(url, user, password);//Connects to the database
            Statement statement = connection.createStatement();

            System.out.println("\n-----------Customer information------------\n");
            resultSet = statement.executeQuery("select * from customers");//query data from the customer table
            while (resultSet.next()) { //Printing customer data from customer table
                System.out.println(
                        resultSet.getString("customerid") + "\t"
                                + resultSet.getString("firstname") + "\t"
                                + resultSet.getString("lastname") + "\t"
                                + resultSet.getString("gender") + "\t"
                                + resultSet.getString("address") + "\t"
                                + resultSet.getString("phone") + "\t"
                                + resultSet.getString("email") + "\t"
                                + resultSet.getString("city") + "\t"
                                + resultSet.getString("country")
                );
            }

            System.out.println("\n-----------First Name of customers------------\n");
            resultSet = statement.executeQuery("SELECT FirstName, LastName FROM Customers");//query data from the customer table
            while (resultSet.next()) { //Printing customer data from customer table
                System.out.println(
                        resultSet.getString("firstname") + "\t"
                                + resultSet.getString("lastname")
                );
            }

            System.out.println("\n-----------First Name of the first customer------------\n");
            resultSet = statement.executeQuery("SELECT FirstName, LastName FROM Customers WHERE CustomerID = 1");//query data from the customer table
            while (resultSet.next()) { //Printing customer data from customer table
                System.out.println(
                        resultSet.getString("firstname") + "\t"
                                + resultSet.getString("lastname")
                );
            }

            System.out.println("\n-----------Update the first customer------------\n");
            results = statement.executeUpdate("UPDATE Customers\n" +
                    "SET FirstName = 'Lerato', LastName = 'Mabitso'\n" +
                    "WHERE CustomerID = 1");//Updating from customer table

            if(results == 1){
                System.out.println("Update Successfully executed!");
            }

            System.out.println("\n-----------Delete the customer------------\n");
            results = statement.executeUpdate("DELETE FROM Customers WHERE CustomerID = 2");//Deleting from customer table

            if(results == 1){
                System.out.println("Delete Successfully executed!");
            }else{
                System.out.println("Selected record not available");
            }

            System.out.println("\n-----------Number of status from Orders------------\n");
            resultSet = statement.executeQuery("SELECT COUNT(Status) FROM Orders");//Counting statuses from orders table
            resultSet.next();
            System.out.println("Total number = " + resultSet.getInt(1));

            System.out.println("\n-----------Maximum amount------------\n");
            resultSet = statement.executeQuery("SELECT MAX(Amount) FROM Payments");//getting maximum amount from payments table
            resultSet.next();
            System.out.println("Maximum amount = " + resultSet.getDouble(1));


        }catch(SQLException e){
            e.printStackTrace();//Handle errors for JDBC
        }catch (Exception ex){
            ex.printStackTrace();//Handle errors for Drivers
        }finally {
            try{
                if(connection!=null)
                    connection.close();//Close database connection
            }catch(SQLException se){
                se.printStackTrace();//Handle errors for JDBC
            }
        }

    }
}