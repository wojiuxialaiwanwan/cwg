package com.example.cwg.controller;

import com.example.cwg.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/register")
    public String register(User user){
        try {
            jdbcTemplate.queryForMap("select id from `user` where phonenumber=" + user.getPhonenumber());
            return "此手机号已被注册!";
        }catch (Exception e){
            e.printStackTrace();
            String sql = "insert into `user`(phonenumber,password,image,nick,sex,qq,weixin) values('"+user.getPhonenumber()+"','"+user.getPassword()+
                    "','"+user.getImage()+"','"+user.getNick()+"','"+user.getSex()+"','"+user.getQq()+"','"+user.getWeixin()+"')";
            System.out.println(sql);
            jdbcTemplate.update(sql);
            return "注册成功!";
        }
    }
}
