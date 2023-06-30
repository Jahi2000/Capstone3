package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.data.OrdersDAO;
import org.yearup.data.UserDao;
import org.yearup.models.Category;
import org.yearup.models.Order;
import org.yearup.models.ShoppingCart;
import org.yearup.models.User;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlOrdersDAO extends MySqlDaoBase implements OrdersDAO {

    public MySqlOrdersDAO(DataSource dataSource){
        super(dataSource);
    }



    @Override
    public Order getbyId(int order_id){
        String query = "SELECT * FROM orders WHERE order_id = ?";
        Order order = new Order();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, order_id);

            try(ResultSet set = preparedStatement.executeQuery()){
                while(set.next()){
                    order = new Order(
                            set.getInt("order_id"),
                            set.getInt("user_id"),
                            set.getString("date"),
                            set.getString("address"),
                            set.getString("city"),
                            set.getString("state"),
                            set.getString("zip"),
                            set.getBigDecimal("shipping_amount")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return order;
    }



    @Override
    //this method returns an integer stored as the primary key.
        public Order create(int userId, String date, String address, String City, String State, String Zip, BigDecimal ShippingAmount){


        String query = "INSERT INTO easyshop.orders(user_id,date,address,city,state,zip,shipping_amount) values\n" +
                "(?,?,?,?,?,?,?);";


            try (Connection connection = getConnection())
            {
                PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setInt(1, userId);
                statement.setString(2, date);
                statement.setString(3, address);
                statement.setString(4, City);
                statement.setString(5, State);
                statement.setString(6, Zip);
                statement.setBigDecimal(7, ShippingAmount);

                int rowsAffected = statement.executeUpdate();

               if (rowsAffected > 0) {
                    // Retrieve the generated keys
                    ResultSet generatedKeys = statement.getGeneratedKeys();

                    if (generatedKeys.next()) {
                        // Retrieve the auto-incremented ID
                        int orderId = generatedKeys.getInt(1);

                        // get the newly inserted category
                       return getbyId(orderId);
                    }
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return null;
    }

/*
    //method inserting in the orderline table
    @Override
    public Order order_line(ShoppingCart shoppingCart){

        String query = "INSERT INTO easyshop.order_line_items(order_id,product_id,sales_price,quantity,discount) values\n" +
                "(?,?,?,?,?,?,?);";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, );
            statement.setString(2, date);
            statement.setString(3, address);
            statement.setString(4, City);
            statement.setString(5, State);
            statement.setString(6, Zip);
            statement.setBigDecimal(7, ShippingAmount);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated keys
                ResultSet generatedKeys = statement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    // Retrieve the auto-incremented ID
                    int orderId = generatedKeys.getInt(1);

                    // get the newly inserted category
                    return getbyId(orderId);
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

*/

}
