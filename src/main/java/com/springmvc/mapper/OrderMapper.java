package com.springmvc.mapper;

import com.springmvc.domain.TbItem;
import com.springmvc.domain.TbOrder;
import com.springmvc.domain.TbOrderDetail;
import com.springmvc.domain.TbUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface OrderMapper {

    @Select("select * from tb_item where id = #{itemId}")
    TbItem queryItemById(int itemId);

    @Select("select * from tb_orderdetail where orderId = #{orderId}")
    @Results({
            @Result(property = "item",column = "itemId",
            one = @One(select = "queryItemById",fetchType = FetchType.EAGER))
    })
    List<TbOrderDetail> queryDetailAllById(int orderId);

    @Select("select * from tb_order where orderNumber = #{number}")
    @Results({
            @Result(column = "userId", property = "tbUser", javaType = TbUser.class,
                    one = @One(select = "com.springmvc.mapper.UserMapper.selectUserById", fetchType = FetchType.EAGER))
    })
    TbOrder queryOrderWithUserByOrderNumber(@Param("number") String number);

    @Select("select * from tb_order where orderNumber = #{number}")
    @Results({
            @Result(column = "userId", property = "tbUser", javaType = TbUser.class,
                    one = @One(select = "com.springmvc.mapper.UserMapper.selectUserById", fetchType = FetchType.EAGER)),
            @Result(column = "id", property = "orderDetailList", javaType = List.class,
                    many = @Many(select = "queryDetailAllById", fetchType = FetchType.LAZY))
    })
    TbOrder queryOrderWithUserAndDetailByOrderNumber(@Param("number") String number);

    @Select("select * from tb_order where orderNumber = #{number}")
    @Results({
            @Result(column = "userId", property = "tbUser", javaType = TbUser.class,
                    one = @One(select = "com.springmvc.mapper.UserMapper.selectUserById", fetchType = FetchType.EAGER))
    })
    TbOrder queryOrderWithUserAndDetailItemByOrderNumber(@Param("number") String number);
}
