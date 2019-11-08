package com.springmvc.dao;

import com.springmvc.domain.TbOrderDetail;

public interface TbOrderDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbOrderDetail record);

    int insertSelective(TbOrderDetail record);

    TbOrderDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbOrderDetail record);

    int updateByPrimaryKey(TbOrderDetail record);
}
