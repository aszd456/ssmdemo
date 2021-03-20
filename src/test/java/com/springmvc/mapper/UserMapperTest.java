package com.springmvc.mapper;

import com.springmvc.domain.TbUser;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * @Before变成了@BeforeEach。
 * @After变成了@AfterEach。
 * @BeforeClass变成了@BeforeAll。
 * @AfterClass变成了@AfterAll。
 * @Ignore变成了@Disabled。
 * @Category变成了@Tag。
 * @Rule和@ClassRule没有了，用@ExtendWith和@RegisterExtension代替。
 */

@SpringJUnitConfig(locations = {"classpath*:spring/applicationContext.xml",
        "classpath*:spring/spring-servlet.xml"})
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @BeforeEach
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
        userMapper.removeUser(17);
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
