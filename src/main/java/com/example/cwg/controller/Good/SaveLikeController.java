package com.example.cwg.controller.Good;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/savelike")
public class SaveLikeController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/insert")
    public JSONObject insert(int activeid,int userid,int type){
        String sql;
        JSONObject jsonObject = new JSONObject();
        String data;
        int sta;
        try{
            sql = "select * from savelike where activeid="+activeid+" and userid="+userid+" and type="+type;
            System.out.println(sql);
            jdbcTemplate.queryForMap(sql);
            if(type==1) data= "您已收藏过";
            else data= "您已点赞过";
            sta=0;
        }catch (Exception e){
            sql = "insert into savelike(activeid,userid,type) values("+activeid+","+userid+","+type+");";
            System.out.println(sql);
            jdbcTemplate.update(sql);
            if(type==1){
                sql = "update active set saves=saves+1 where id="+activeid;
                System.out.println(sql);
                jdbcTemplate.update(sql);
                data= "收藏成功";
                sta=1;
            }
            else {
                sql = "update active set likes=likes+1 where id="+activeid;
                System.out.println(sql);
                jdbcTemplate.update(sql);
                data= "点赞成功";
                sta=2;
            }
        }
        jsonObject.put("sta",sta);
        jsonObject.put("data",data);
        return jsonObject;
    }
}
