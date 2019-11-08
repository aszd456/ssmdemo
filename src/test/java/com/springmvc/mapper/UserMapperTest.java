package com.springmvc.mapper;

import com.springmvc.domain.TbUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext.xml"})
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void saveUser() {
        TbUser tbUser = new TbUser();
        tbUser.setName("你大爷");
//        tbUser.setCreated(DateUtils.createNow().getTime());
        tbUser.setAge(50);
//        tbUser.setBirthday(DateUtils.createToday().getTime());
        tbUser.setPassword("123456");
        tbUser.setUserName("ndy");
        tbUser.setSex(1);
        userMapper.saveUser(tbUser);
        System.out.println(tbUser.getId());
    }

    @Test
    public void removeUser() {
        userMapper.removeUser(4);
    }

    @Test
    public void modifyUser() {
        TbUser user = userMapper.selectUserById(8);
        user.setName("大屌");
        user.setSex(1);
        user.setAge(60);
        userMapper.modifyUser(user);
        System.out.println(user.getName());
    }

    @Test
    public void selectUserById() {
        TbUser user = userMapper.selectUserById(2);
        System.out.println(user);
        System.out.println(user.getName()+user.getUserName());
    }

    @Test
    public void selectAllUser() {
        List<TbUser> userList = userMapper.selectAllUser();
        userList.forEach(tbUser -> System.out.println(tbUser.getName()));
    }
}
