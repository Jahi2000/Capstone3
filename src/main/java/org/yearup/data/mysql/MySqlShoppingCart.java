package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlShoppingCart extends MySqlDaoBase implements ShoppingCartDao {


    public MySqlShoppingCart(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int userId) {

        String query = "SELECT * FROM shopping_cart\n" +
                "        JOIN products on shopping_cart.product_id = products.product_id\n" +
                "        WHERE user_id = ?;";
        ShoppingCart shoppingCart = new ShoppingCart();


        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, userId);

            try(ResultSet set = preparedStatement.executeQuery()){
                while(set.next()){
                    ShoppingCartItem item = new ShoppingCartItem();
                    Product product = new Product(
                            set.getInt("product_id"),
                            set.getString("name"),
                            set.getBigDecimal("price"),
                            set.getInt("category_id"),
                            set.getString("description"),
                            set.getString("color"),
                            set.getInt("stock"),
                            set.getBoolean("featured"),
                            set.getString("image_url")
                    );

                    item.setQuantity(set.getInt("quantity"));
                    item.setProduct(product);
                    shoppingCart.add(item);
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCart create(int userId, int productId) {

        if (getByUserId(userId).contains(productId)){
           update1(userId,productId);
            return null;
        }
        else {
            String query = "INSERT INTO shopping_cart (user_id,product_id,quantity) values (?,?,1);";

            ShoppingCart shoppingCart = new ShoppingCart();


            try (Connection connection = getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, productId);

                //Notice how this part is a bit different
                int rowsAffected = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }

    @Override
    public void update(int userId, int productId, ShoppingCartItem shoppingCartItem)
    {
        String sql = "UPDATE shopping_cart SET quantity = ? WHERE user_id = ? AND product_id = ?;";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, shoppingCartItem.getQuantity());
            statement.setInt(2,userId);
            statement.setInt(3, productId);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update1(int userId, int productId)
    {
        String sql = "UPDATE shopping_cart SET quantity = quantity + 1 WHERE user_id = ? AND product_id = ?;";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2,productId);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void delete(int userId)
    {
        String sql = "DELETE FROM shopping_cart " +
                "WHERE user_id = ?;";
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }





}
