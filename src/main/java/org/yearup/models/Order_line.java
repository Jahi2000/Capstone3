package org.yearup.models;

import java.math.BigDecimal;

public class Order_line {

    private int orderLineid;
    private int orderid;
    private int productid;
    private BigDecimal price;
    private int quantity;
    private BigDecimal discount;

    public Order_line(int orderLineid, int orderid, int productid, BigDecimal price, int quantity, BigDecimal discount) {
        this.orderLineid = orderLineid;
        this.orderid = orderid;
        this.productid = productid;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
    }

    public Order_line(){

    }

    public int getOrderLineid() {
        return orderLineid;
    }

    public void setOrderLineid(int orderLineid) {
        this.orderLineid = orderLineid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
