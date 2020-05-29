package com.example.cwg.controller.Good;

import com.example.cwg.Utils.TimeUtils;
import com.example.cwg.bean.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/good")
public class GoodController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/list")
    public List<Map<String,Object>> list(String param){
        if(param==null) param="";
        String sql = "select * from good "+param;
        System.out.println(sql);
        return jdbcTemplate.queryForList(sql);
    }

    @PostMapping("/insert")
    public Map<String,Object> insert(Good good){
        String sql,pic = TimeUtils.getPicName()+".jpg";
        if(good.getId()==0){
            sql = "insert into good(userid,name,type,loc,color,date,other,pic) values('"+good.getUserid()+"','"+good.getName()+"','"
                    +good.getType()+"','"+good.getLoc()+"','"+good.getColor()+"','"+good.getDate()+"','"+good.getOther()+"','"+pic+"');";
            System.out.println(sql);
            jdbcTemplate.update(sql);
        }
        else{
            sql = "update good set userid="+good.getUserid()+",name='"+good.getName()+"',type='"
                    +good.getType()+"',loc='"+good.getLoc()+"',color='"+good.getColor()+"',date='"+good.getDate()+"',other='"+good.getOther()+"',pic='"+pic+"' where id="+good.getId();
            System.out.println(sql);
            jdbcTemplate.update(sql);
        }
        sql = "select id,pic from good where userid='"+good.getUserid()+"' order by id desc limit 1;";
        System.out.println(sql);
        return jdbcTemplate.queryForMap(sql);
    }
    @PostMapping("/delete")
    public void delete(Good good){
        String sql;
        sql="delete from good where id="+good.getId();
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }
}
