package com.example.cwg.controller.Good;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follow")
public class FollowController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/test")
    public String test(int follower,int followed){
        String sql;
        try{
            sql = "select * from follow where follower="+follower+" and followed="+followed;
            System.out.println(sql);
            jdbcTemplate.queryForMap(sql);
            return "true";
        }catch (Exception e){return "false";}
    }

    @PostMapping("/insert")
    public void insert(int follower,int followed){
        String sql;
        sql = "insert into follow values("+follower+","+followed+");";
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }
}
