package org.yearup.data.mysql;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        mySqlShoppingCart.getByUserId(1);

    }
}