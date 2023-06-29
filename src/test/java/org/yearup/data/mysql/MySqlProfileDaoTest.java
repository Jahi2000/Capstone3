package org.yearup.data.mysql;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yearup.models.Profile;

import static org.junit.jupiter.api.Assertions.*;

class MySqlProfileDaoTest {
     private MySqlProfileDao profileDao;

    @BeforeEach
    void setUp() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/easyshop");
        dataSource.setUsername("root");
        dataSource.setPassword("fxbqd318");
        profileDao = new MySqlProfileDao(dataSource);
    }

    @Test
    void getById_Succeeds() {
        //ACT
        Profile profile = profileDao.getById(1);
        //ASSERT
        assertEquals("Joe", profile.getFirstName());
    }
    @Test
    void delete_Succeeds() {
        //ACT
        profileDao.delete(9);

        //ASSERT
        assertNull(profileDao.getById(9));
    }
    @Test
    void create_Succeeds() {
        //ARRANGE
        Profile profile = new Profile()
        {{
            setUserId(7);
            setFirstName("Brad");
            setLastName("Bradlerson");
            setPhone("123-234-4567");
            setEmail("asdf@gmai.com");
            setAddress("123 NE SW st");
            setCity("Birmingham");
            setState("AL");
            setZip("12345");

        }};

        //ACT
        profileDao.create(profile);

        //ASSERT
        assertEquals("Brad", profile.getFirstName());
    }
    @Test
    void update_Succeeds() {
        //ARRANGE
        Profile profile = new Profile()
        {{
            setUserId(9);
            setFirstName("Bradster");
            setLastName("Bradlerson");
            setPhone("123-234-4567");
            setEmail("asdf@gmai.com");
            setAddress("901 Shelby Dr.");
            setCity("Memphis");
            setState("TN");
            setZip("82179");

        }};

        //ACT
        profileDao.update(9,profile);

        //ASSERT
        assertEquals("Bradster", profile.getFirstName());
    }

}

