package com.example.cwg.controller.Good;

import com.example.cwg.Utils.TimeUtils;
import com.example.cwg.bean.Active;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/active")
public class ActiveController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("insert")
    public Map<String,Object> insert(Active active,int count){
        String sql;
        String imgs = "";
        for(int i=0;i<count;i++){
            imgs+=(TimeUtils.getPicName()+".jpg"+",");
        }
        sql = "insert into active(userid,content,imgs,sta) values("+active.getUserid()+",'"+active.getContent()+"','"+imgs+"','"+active.getSta()+"');";
        System.out.println(sql);
        jdbcTemplate.update(sql);
        sql = "select id,imgs from active where userid='"+active.getUserid()+"' order by id desc limit 1;";
        System.out.println(sql);
        return jdbcTemplate.queryForMap(sql);
    }
}
