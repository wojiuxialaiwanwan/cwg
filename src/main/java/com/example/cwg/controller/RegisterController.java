package com.example.cwg.controller;

import com.example.cwg.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
                    "','123.jpg','"+user.getNick()+"','"+user.getSex()+"','"+user.getQq()+"','"+user.getWeixin()+"')";
            System.out.println(sql);
            jdbcTemplate.update(sql);
            return "注册成功!";
        }
    }

    // update message
    @PostMapping(value = "/updateNickname")
    public String updateNickname(@RequestParam("new_nickname")String new_nickname,@RequestParam("id")int id){
        StringBuilder stringBuilder = new StringBuilder("update user set ");
        if(new_nickname != "" || "".equals(new_nickname)){
            String sql = "update user set nick = '" + new_nickname + "' where id="+id;
            jdbcTemplate.update(sql);
        }
        return "";
    }
    // update message
    @PostMapping(value = "/updateSex")
    public String updateSex(@RequestParam("new_sex")String new_sex,@RequestParam("id")int id){
        StringBuilder stringBuilder = new StringBuilder("update user set ");
        if(new_sex != "" || "".equals(new_sex)){
            String sql = "update user set sex = '" + new_sex+ "' where id="+id;
            jdbcTemplate.update(sql);
        }
        return "";
    }

    @PostMapping(value = "/updatePic")
    public String updatePic(@RequestParam("pic")String pic,@RequestParam("id")int id){
        StringBuilder stringBuilder = new StringBuilder("update user set ");
        String sql = "update user set image = '" + pic+ "' where id="+id;
        jdbcTemplate.update(sql);
        return "";
    }

}
