package org.yearup.data;

import java.math.BigDecimal;

public interface OrdersDAO {

    void create(int userId, String date, String address, String City, String State, String Zip, BigDecimal ShippingAmount);



}