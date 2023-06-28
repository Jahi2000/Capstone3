package org.yearup.data.mysql;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yearup.models.Category;

import static org.junit.jupiter.api.Assertions.*;

class MySqlCategoryDaoTest {

    private MySqlCategoryDao MySqlCategoryDao;
    @BeforeEach
    void setUp() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:mysql://localhost:3306/easyshop");
        dataSource.setUsername("root");
        dataSource.setPassword("fxbqd318");

        this.MySqlCategoryDao = new MySqlCategoryDao(dataSource);
    }

    @Test
    void getById() {
        Category category = MySqlCategoryDao.getById(1);

        //Assert
        assertEquals("Electronics",category.getName());

    }


}



