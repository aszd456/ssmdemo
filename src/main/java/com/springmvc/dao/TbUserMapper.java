package com.springmvc.dao;

import com.springmvc.domain.TbUser;

public interface TbUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbUser tbUser);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);
}
