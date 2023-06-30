package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.Services.OrderService;
import org.yearup.data.UserDao;
import org.yearup.data.mysql.MySqlOrdersDAO;
import org.yearup.models.Order;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@RequestMapping("orders")
@CrossOrigin
public class OrderController {

    MySqlOrdersDAO mySqlOrdersDAO;
    OrderService orderService;
    UserDao userDao;
    @Autowired
    public OrderController(OrderService orderService, UserDao userDao, MySqlOrdersDAO mySqlOrdersDAO){
        this.orderService = orderService;
        this.userDao = userDao;
        this.mySqlOrdersDAO = mySqlOrdersDAO;
    }

    @PostMapping()
    public void getOrder(Principal principal){
        try
        {
            // get the currently logged in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            // use the shoppingcartDao to get all items in the cart and return the cart
           orderService.checkout(userId);

        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }







}
