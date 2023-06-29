package org.yearup.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.OrdersDAO;
import org.yearup.data.ProfileDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.mysql.MySqlProfileDao;
import org.yearup.models.Order;
import org.yearup.models.Profile;

import java.math.BigDecimal;

@Component
public class OrderService {

    private ShoppingCartDao ShoppingCartDao;
    private ProfileDao  ProfileDao;

    private OrdersDAO OrdersDAO;


    @Autowired
    public OrderService(ShoppingCartDao shoppingCartDao, ProfileDao profileDao,OrdersDAO orderDao){
        this.ShoppingCartDao = shoppingCartDao;
        this.ProfileDao = profileDao;
        this.OrdersDAO = orderDao;
    }

    public Order checkout(int userId){
      Profile profile = this.ProfileDao.getById(userId);
      this.OrdersDAO.create(profile.getUserId(),"2024-05-12 12:12:45", profile.getAddress(), profile.getCity(), profile.getState(), profile.getZip(),new BigDecimal(10000));

      this.ShoppingCartDao.getByUserId(userId);



      return null;

    }


}
