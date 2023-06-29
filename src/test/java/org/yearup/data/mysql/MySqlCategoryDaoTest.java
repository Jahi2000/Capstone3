package org.yearup.data.mysql;
import org.apache.commons.dbcp2.BasicDataSource;
import org.yearup.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MySqlCategoryDaoTest {
private MySqlCategoryDao mySqlCategoryDao;
    @BeforeEach
    void setUp() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:mysql://localhost:3306/easyshop");
        dataSource.setUsername("root");
        dataSource.setPassword("R@wboyj!23");

        this.mySqlCategoryDao = new MySqlCategoryDao(dataSource);
    }

    @Test // Test to get a category by ID
    void getById() {
        Category category = mySqlCategoryDao.getById(3);

        //Assert
        assertEquals("Home & Kitchen",category.getName());


  }

  @Test // Test to create a new Category
    void create(){
        //Arrange
       Category createCategory = new Category();


          createCategory.setCategoryId(1);
          createCategory.setName("Boxing101");
          createCategory.setDescription("Come shop for the latest and best boxing equipment. Before you know it , you'll be kicking as*");

          //ACT
         mySqlCategoryDao.create(createCategory);

          //Assert
          assertEquals("Boxing101", createCategory.getName());

  }
  @Test // Test to run all categories
    void getAllCategories(){
        Category getAllCategories = new Category();

        List<Category> allCategories = mySqlCategoryDao.getAllCategories();

        //Assert
      assertEquals(4, allCategories.size());
    }

    @Test
    void updateCategory(){
        // Created a dataSource bot in order for tests to run successful
        DataSource dataSource = mock(DataSource.class);
        MySqlCategoryDao update = new MySqlCategoryDao(dataSource);

        Category categoryUpdate = new Category(5, "Garden & Outdoors", "Best tools and items for your garden and backyard. Do the job right the first time.");


        //ACT
        mySqlCategoryDao.update(categoryUpdate.getCategoryId(),categoryUpdate);
        Category updated = mySqlCategoryDao.getById(categoryUpdate.getCategoryId());

        // Assert
        assertEquals("Garden & Outdoors", updated.getName());
    }

    @Test
    void deleteCategory() throws SQLException {
        // will delete any category I choose
        int categoryId = 4;
        //ACT
        mySqlCategoryDao.delete(categoryId);

    }
}