package com.example.cwg.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/login")
    public JSONObject login(String phonenumber, String password){
        JSONObject jsonObject = new JSONObject();
        int sta;
        String msg;
        Map<String,Object> map;
        try {
            String sql = "select * from `user` where phonenumber=" + phonenumber + " and password=" + password;
            System.out.println(sql);
            map = jdbcTemplate.queryForMap(sql);
        }catch (Exception e){
            map=null;
        }
        if(map==null){
            sta=1;
            msg="手机号或密码错误";
        }
        else{
            sta=0;
            msg="登录成功";
            jsonObject.put("id",map.get("id"));
            jsonObject.put("phonenumber",map.get("phonenumber"));
            jsonObject.put("password",map.get("password"));
            jsonObject.put("image",map.get("image"));
            jsonObject.put("nick",map.get("nick"));
            jsonObject.put("sex",map.get("sex"));
            jsonObject.put("qq",map.get("qq"));
            jsonObject.put("weixin",map.get("weixin"));
        }
        jsonObject.put("sta",sta);
        jsonObject.put("msg",msg);
        return jsonObject;
    }
}
