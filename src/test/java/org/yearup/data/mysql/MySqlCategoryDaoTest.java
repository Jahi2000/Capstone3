package org.yearup.data.mysql;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yearup.models.Category;
import org.yearup.models.Order;
import org.yearup.models.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MySqlCategoryDaoTest {

    private MySqlCategoryDao MySqlCategoryDao;
    private MySqlOrdersDAO mySqlOrdersDAO;

    private MySqlProductDao mySqlProductDao;
    @BeforeEach
    void setUp() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:mysql://localhost:3306/easyshop");
        dataSource.setUsername("root");
        dataSource.setPassword("fxbqd318");

        this.MySqlCategoryDao = new MySqlCategoryDao(dataSource);
        this.mySqlOrdersDAO = new MySqlOrdersDAO(dataSource);
    }

    @Test
    void getById() {
        Category category = MySqlCategoryDao.getById(1);

        //Assert
        assertEquals("Electronics",category.getName());

    }

    @Test
    void getById1(){
        Order order = mySqlOrdersDAO.getbyId(1);

        //Assert
        assertEquals("FL",order.getState());
    }

    @Test
    void create(){
        Order order = mySqlOrdersDAO.create(2,"2024-05-12 12:12:45", "3452Foxcroft Road", "miramar", "NY", "33025", BigDecimal.valueOf(1000000));

        assertEquals(2,order.getUserId());
    }




}



