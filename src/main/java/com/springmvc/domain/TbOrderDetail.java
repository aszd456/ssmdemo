package com.springmvc.domain;

import java.io.Serializable;

public class TbOrderDetail implements Serializable {
    private static final long serialVersionUID = 9157318905761044293L;
    private Integer id;

    private Integer orderId;

    private Long totalPrice;

    private Integer itemId;

    private Byte state;

    private TbItem item;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public TbItem getItem() {
        return item;
    }

    public void setItem(TbItem item) {
        this.item = item;
    }
}
