package com.example.cwg.controller.Lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/type1")
public class type1 {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/get")
    public List<Map<String,Object>> get(){
        String sql = "select * from type1;";
        System.out.println(sql);
        return jdbcTemplate.queryForList(sql);
    }

    @PostMapping("/update")
    public void update(int id,String name){
        String sql = "update type1 set name='"+name+"' where id="+id;
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }
}
