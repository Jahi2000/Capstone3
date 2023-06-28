package org.yearup.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.OrdersDAO;
import org.yearup.data.ProfileDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.mysql.MySqlProfileDao;

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

    public void checkout(int userId){
       // get userid from profile dao       profileDao.

        //take the userprofile and send to the orderdao

    }


}
