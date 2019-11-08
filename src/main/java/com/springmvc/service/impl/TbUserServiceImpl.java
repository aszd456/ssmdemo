package com.springmvc.service.impl;

import com.springmvc.dao.TbUserMapper;
import com.springmvc.domain.TbUser;
import com.springmvc.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    TbUserMapper tbUserMapper;

    @Override
    public int insert(TbUser tbUser) {
        return tbUserMapper.insert(tbUser);
    }
}
