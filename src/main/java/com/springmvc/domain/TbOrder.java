package com.springmvc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TbOrder implements Serializable {
    private static final long serialVersionUID = 7009317774367829371L;
    private Integer id;

    private Integer userId;

    private String orderNumber;

    private Date createTime;

    private Date updateTime;

    private TbUser tbUser;

    private List<TbOrderDetail> orderDetailList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public TbUser getTbUser() {
        return tbUser;
    }

    public void setTbUser(TbUser tbUser) {
        this.tbUser = tbUser;
    }

    public List<TbOrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<TbOrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
