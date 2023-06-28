package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.data.OrdersDAO;
import org.yearup.data.UserDao;
import org.yearup.models.Order;
import org.yearup.models.User;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlOrdersDAO extends MySqlDaoBase implements OrdersDAO {

    DataSource dataSource;
    public MySqlOrdersDAO(DataSource dataSource){
        super(dataSource);
        this.dataSource = dataSource;
    }


    //this method returns an integer stored as the primary key.
        public Order create(int userId, String date, String address, String City, String State, String Zip, BigDecimal ShippingAmount){


        String query = "INSERT INTO easyshop.orders(user_id,date,address,city,state,zip,shipping_amount) values\n" +
                "(?,?,?,?,?,?,?);";
/*
            try (Connection connection = getConnection())
            {
                PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1, product.getName());
                statement.setBigDecimal(2, product.getPrice());
                statement.setInt(3, product.getCategoryId());
                statement.setString(4, product.getDescription());
                statement.setString(5, product.getColor());
                statement.setString(6, product.getImageUrl());
                statement.setInt(7, product.getStock());
                statement.setBoolean(8, product.isFeatured());

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    // Retrieve the generated keys
                    ResultSet generatedKeys = statement.getGeneratedKeys();

                    if (generatedKeys.next()) {
                        // Retrieve the auto-incremented ID
                        int orderId = generatedKeys.getInt(1);

                        // get the newly inserted category
                       // return orderId;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

 */
            return null;


    }




}
