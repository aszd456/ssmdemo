package com.springmvc.controller;

import com.springmvc.dao.TbUserMapper;
import com.springmvc.domain.TbUser;
import com.springmvc.service.impl.TbUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/user")
public class TbUserController {
    @Autowired
    TbUserServiceImpl tbUserService;
    @Autowired
    TbUserMapper tbUserMapper;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.addObject("test", "丢那星，麻花藤");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/add", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addUserData() {
        TbUser tb_user = new TbUser();
        tb_user.setAge(16);
//        tb_user.setBirthday(Date);
        tb_user.setName("鹏仔");
        tb_user.setPassword("123456");
        tb_user.setSex(1);
        tb_user.setUserName("shadiao");
//        tb_user.setCreated(DateUtils.createNow().getTime());
        int effectNum = tbUserMapper.insert(tb_user);

        int id = tb_user.getId();
        System.out.println();

        return "影响行数：" + effectNum + "  返回主键id：" + id;
    }

    public static void main(String[] args) {
//        System.out.println(DateUtils.createNow().getTime());
    }

}
