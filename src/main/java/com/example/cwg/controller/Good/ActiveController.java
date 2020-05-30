package com.example.cwg.controller.Good;

import com.example.cwg.Utils.TimeUtils;
import com.example.cwg.bean.Active;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
        sql = "insert into active(userid,content,imgs,sta,type,time,nick,image) values("+active.getUserid()+",'"+active.getContent()+"','"+imgs+"','"
                +active.getSta()+"','"+active.getType()+"','"+active.getTime()+"','"+active.getNick()+"','"+active.getImage()+"');";
        System.out.println(sql);
        jdbcTemplate.update(sql);
        sql = "select id,imgs from active where userid='"+active.getUserid()+"' order by id desc limit 1;";
        System.out.println(sql);
        return jdbcTemplate.queryForMap(sql);
    }

    @PostMapping("/list")
    public List<Map<String,Object>> list(String id,int userid){
        String sql,param = "";
        String type[] = new String[]{"","","衣物搭配","护肤美妆","书籍","其他"};
        switch (id){
            case "0":
                param = "order by likes desc,id desc"; break;
            case "1":
                param = "where userid in(select followed from follow where follower="+userid+")"; break;
            default:
                param = "where type='"+type[Integer.parseInt(id)]+"'";
        }
        sql = "select * from active "+param;
        System.out.println(sql);
        return jdbcTemplate.queryForList(sql);
    }
}
