package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.data.OrdersDAO;
import org.yearup.data.UserDao;
import org.yearup.models.User;

import javax.sql.DataSource;
import java.math.BigDecimal;

@Component
public class MySqlOrdersDAO extends MySqlDaoBase implements OrdersDAO {

    DataSource dataSource;
    public MySqlOrdersDAO(DataSource dataSource){
        super(dataSource);
        this.dataSource = dataSource;
    }


        public void create(int userId, String date, String address, String City, String State, String Zip, BigDecimal ShippingAmount){



            String query = "INSERT INTO orders (  ";



    }
}
