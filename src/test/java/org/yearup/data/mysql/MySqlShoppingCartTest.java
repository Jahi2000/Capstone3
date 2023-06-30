package org.yearup.data.mysql;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import static org.junit.jupiter.api.Assertions.*;

class MySqlShoppingCartTest {

    private MySqlShoppingCart mySqlShoppingCart;

    @BeforeEach
    void setUp() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/easyshop");
        dataSource.setUsername("root");
        dataSource.setPassword("fxbqd318");
        this.mySqlShoppingCart = new MySqlShoppingCart(dataSource);
    }


    @Test
    void getId(){
       ShoppingCart cart = mySqlShoppingCart.getByUserId(2);
        assertEquals(true,cart.contains(13));
    }

    @Test
    void create(){
        mySqlShoppingCart.create(4,5);
        ShoppingCart cart = mySqlShoppingCart.getByUserId(4);
        assertEquals(true,cart.contains(5));
    }

    @Test
    void update(){
        ShoppingCartItem item = new ShoppingCartItem();
        item.setQuantity(8);
        mySqlShoppingCart.update(2,20,item);
        assertEquals(8,item.getQuantity());
    }

    @Test
    void delete(){
        ShoppingCart cart = mySqlShoppingCart.getByUserId(4);
        mySqlShoppingCart.delete(4);
        assertEquals(0,cart.getItems().size());
    }
}