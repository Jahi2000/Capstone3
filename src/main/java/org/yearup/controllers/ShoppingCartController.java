package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.User;

import java.security.Principal;

// convert this class to a REST controller
// only logged in users should have access to these actions
@RestController
@RequestMapping("cart")
@CrossOrigin
public class ShoppingCartController
{
    // a shopping cart requires
    private ShoppingCartDao shoppingCartDao;
    private UserDao userDao;
    private ProductDao productDao;

    @Autowired
    public ShoppingCartController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao){
        this.shoppingCartDao = shoppingCartDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }



    @GetMapping("")
    // each method in this controller requires a Principal object as a parameter
    public ShoppingCart getCart(Principal principal)
    {
        try
        {
            // get the currently logged in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            // use the shoppingcartDao to get all items in the cart and return the cart
            return this.shoppingCartDao.getByUserId(userId);

        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @PostMapping("product/{id}")
    // each method in this controller requires a Principal object as a parameter
    public ShoppingCart create(@PathVariable int id, Principal principal)
    {
        try
        {
            // get the currently logged in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();


           /* if(this.shoppingCartDao.getByUserId(userId).contains(id)){
                return null;
            }else {
              return this.shoppingCartDao.create(userId, id);
            }
            */
            return this.shoppingCartDao.create(userId, id);

        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @DeleteMapping("")
    // each method in this controller requires a Principal object as a parameter
    public void delete(Principal principal)
    {
        try
        {
            // get the currently logged in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            this.shoppingCartDao.delete(userId);

        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @PutMapping("product/{id}")
    // each method in this controller requires a Principal object as a parameter
    public void update(@PathVariable int id, Principal principal)
    {
        try
        {
            // get the currently logged in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();


            this.shoppingCartDao.update(userId, id);

        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }


    // add a POST method to add a product to the cart - the url should be
    // http://localhost:8080/cart/product/15 (15 is the productId to be added


    // add a PUT method to update an existing product in the cart - the url should be
    // http://localhost:8080/cart/product/15 (15 is the productId to be updated)
    // the BODY should be a ShoppingCartItem - quantity is the only value that will be updated


    // add a DELETE method to clear all products from the current users cart
    // http://localhost:8080/cart

}
