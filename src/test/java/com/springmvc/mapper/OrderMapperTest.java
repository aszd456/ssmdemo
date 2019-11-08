package com.springmvc.mapper;

import com.springmvc.domain.TbOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext.xml"})
public class OrderMapperTest {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void queryOrderWithUserByOrderNumber() {
        TbOrder tbOrder = orderMapper.queryOrderWithUserByOrderNumber("201807010001");
        System.out.println(tbOrder.getTbUser().getId() + " " + tbOrder.getTbUser().getName());
    }

    @Test
    public void queryOrderWithUserAndDetailByOrderNumber() {
        TbOrder tbOrder = orderMapper.queryOrderWithUserAndDetailByOrderNumber("201807010001");
        System.out.println(tbOrder.getTbUser().getId() + " " + tbOrder.getTbUser().getName());
        tbOrder.getOrderDetailList().forEach(tbOrderDetail -> System.out.println(tbOrderDetail.getTotalPrice()));
        tbOrder.getOrderDetailList().forEach(tbOrderDetail -> System.out.println(tbOrderDetail.getItem().getItemDetail()));
    }
}
