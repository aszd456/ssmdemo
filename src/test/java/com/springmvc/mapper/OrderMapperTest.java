package com.springmvc.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springmvc.domain.Employee;
import com.springmvc.domain.TbOrder;
import com.springmvc.domain.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@ExtendWith(SpringExtension.class)//junit5示范代码
//@ContextConfiguration(locations = {"classpath*:spring/applicationContext.xml",
//        "classpath*:spring/spring-servlet.xml"})
@SpringJUnitConfig(locations = {"classpath*:spring/applicationContext.xml",
        "classpath*:spring/spring-servlet.xml"})
public class OrderMapperTest {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private MpUserMapper mpUserMapper;

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

    @Test
    public void testDataSource() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testInsert() {
        Employee employee = new Employee();
        employee.setLastName("东方不败");
        employee.setEmail("dfbb@163.com");
        employee.setGender(1);
        employee.setAge(20);
        employeeMapper.insert(employee);
        //mybatisplus会自动把当前插入对象在数据库中的id写回到该实体中
        System.out.println(employee.getId());
    }

    @Test
    public void testUpdate() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setLastName("更新测试");
        employeeMapper.updateById(employee);
    }

    @Test
    public void testSelect() {
        //根据id查询
        Employee employee = employeeMapper.selectById(1);
        System.out.println(employee.getLastName());

        //根据条件查询一条数据

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        Map map = new HashMap<>();
        map.put("id", 1);
        map.put("last_name", "更新测试");
        queryWrapper.allEq(map);
        Employee employee1 = employeeMapper.selectOne(queryWrapper);
        System.out.println(employee1.getEmail());
    }

    /**
     * 名字中包含“雨”并且年龄小于40
     */
    @Test
    public void selectByWrapper1() {
        //名字中包含“雨”并且年龄小于40
        //name like '%雨%' and age<40
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨").lt("age", 40);
        List<User> userList = mpUserMapper.selectList(queryWrapper);
        userList.forEach(user -> System.out.println(user.getName()));

        //名字中包含“雨”并且年龄大于等于20且小于等于40并且email不为空
        //name like '%雨%' and age between 20 and 40 and email is not null
        queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨").between("age", 20, 40).isNotNull("email");
        userList = mpUserMapper.selectList(queryWrapper);
        userList.forEach(user -> System.out.println(user.getName()));

        //名字为“王”姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
        //name like '王%' or age>=25 order by age desc,id,asc
        queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").or().ge("age", 25)
                .orderByDesc("age").orderByAsc("id");
        userList = mpUserMapper.selectList(queryWrapper);
        userList.forEach(user -> System.out.println(user.getName()));

        //创建日期为2019年2月14日并且直属上级名字为王姓
        //date_format(create_time,'%Y-%m-%d') and manager_id in (select id from user where name like '王%')
        queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-02-14")
                .inSql("manager_id", "select id from user where name like '王%'");
        userList = mpUserMapper.selectList(queryWrapper);
        userList.forEach(user -> System.out.println(user.getName()));

        System.out.println("*-**********************");
        //名字为“王”姓并且（年龄小于40或邮箱不为空）
        //name like '王%' and (age<40 or email is not null)
        queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").and(wq -> wq.lt("age", 40).or().isNotNull("email"));
        userList = mpUserMapper.selectList(queryWrapper);
        userList.forEach(user -> System.out.println(user.getName()));

        /**
         * 名字为“王”姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
         * name like '王%' or (age<40 and age>20 and email is not null)
         */
        System.out.println("*****************");
        queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));
        userList = mpUserMapper.selectList(queryWrapper);
        userList.forEach(user -> System.out.println(user.getName()));

        /**
         * （年龄小于40或邮箱不为空）并且名字为王姓
         * (age<40 or email is not null) and name like '王%'
         *
         */
        System.out.println("**************");
        queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(wq -> wq.lt("age", 40).or().isNotNull("email"))
                .likeRight("name", "王");
        userList = mpUserMapper.selectList(queryWrapper);
        userList.forEach(user -> System.out.println(user.getName()));
        System.out.println("*************");

        /**
         * 年龄为30，31，34，35
         * age in (30,31,34,35)
         */
        queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35));
        userList = mpUserMapper.selectList(queryWrapper);
        userList.forEach(user -> System.out.println(user.getName()));
        System.out.println("*************");

        /**
         * // 无视优化规则直接拼接到 sql 的最后
         *         // 注意事项:
         *         // 只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用
         *         limit 1
         *         queryWrapper.in("age", Arrays.asList(30, 31, 34, 35)).last("limit 1");
         */


    }
}
