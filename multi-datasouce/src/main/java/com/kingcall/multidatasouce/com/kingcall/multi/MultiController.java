package com.kingcall.multidatasouce.com.kingcall.multi;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MultiController {
    @Resource
    JdbcTemplate fooJdbcTemplate;

    @Resource
    JdbcTemplate barJdbcTemplate;

    @RequestMapping("/foo")
    public void showFooData() {
        fooJdbcTemplate.queryForList("SELECT * FROM FOO").forEach(line-> System.out.println(line.toString()));
    }

    @RequestMapping("/bar")
    public void showBarData() {
        barJdbcTemplate.queryForList("SELECT * FROM BAR").forEach(line-> System.out.println(line.toString()));
    }
}
