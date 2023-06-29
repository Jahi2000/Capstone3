package org.yearup.data.mysql;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yearup.models.Product;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MySqlProductDaoTest {
    private MySqlProductDao dao;

    @BeforeEach
    public void setup() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/easyshop");
        dataSource.setUsername("root");
        dataSource.setPassword("fxbqd318");
        dao = new MySqlProductDao(dataSource);
    }


    @Test
    public void getById_shouldReturn_theCorrectProduct() {
        // arrange
        int productId = 1;
        Product expected = new Product() {{
            setProductId(1);
            setName("Smartphone");
            setPrice(new BigDecimal("499.99"));
            setCategoryId(1);
            setDescription("A powerful and feature-rich smartphone for all your communication needs.");
            setColor("Black");
            setStock(50);
            setFeatured(false);
            setImageUrl("smartphone.jpg");
        }};

        // act
        var actual = dao.getById(productId);

        // assert
        assertEquals(expected.getPrice(), actual.getPrice(), "Because I tried to get product 1 from the database.");
    }

    @Test
    void create() {
        BigDecimal num = new BigDecimal("200");
        // Arrange
        Product expected = new Product("Jetpack", num, 1, "flys", "Sliver", 30, false);


        // Set other properties of the expected product if required


        // Assert
        assertEquals(expected.getPrice(), num);
        // Additional assertions on other properties of the expected and actual products
    }


    @Test
    void update() {

        Product product = new Product() {{

            setName("SmartTV");
            setPrice(new BigDecimal("950.76"));
            setCategoryId(1);
            setDescription("8K Visual Display");
            setColor("Banana Yellow");
            setStock(88);
            setFeatured(false);
            setImageUrl("Big And Yellow");
        }};
        dao.update(4, product);
        assertEquals("Banana Yellow", product.getColor());
    }
}

