package com.example.cwg.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FocusController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping(value = "/follow")
    public String getFocusData(@RequestParam("id")int userId){
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map;
        try{
            String sql = "select count(*) as follower_data from follow where follower = " + userId;
            System.out.println(sql);
            map = jdbcTemplate.queryForMap(sql);
            jsonObject.put("follower_data",map.get("follower_data"));
            sql = "select count(*) as followed_data from follow where followed = " + userId;
            System.out.println(sql);
            map = jdbcTemplate.queryForMap(sql);
            jsonObject.put("followed_data",map.get("followed_data"));
            System.out.println(jsonObject.getInteger("follower_data"));
            System.out.println(jsonObject.getInteger("followed_data"));
        }catch (Exception e){
            System.out.println("error for count  follow");
        }
        return JSON.toJSONString(jsonObject);
    }

}
