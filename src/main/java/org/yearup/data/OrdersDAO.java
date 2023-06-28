package org.yearup.data;

import org.yearup.models.Order;

import java.math.BigDecimal;

public interface OrdersDAO {

    Order create(int userId, String date, String address, String City, String State, String Zip, BigDecimal ShippingAmount);



}