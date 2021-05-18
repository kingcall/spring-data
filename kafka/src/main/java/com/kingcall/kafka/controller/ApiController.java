package com.kingcall.kafka.controller;

import com.kingcall.kafka.model.UserModel;
import com.kingcall.kafka.service.ParseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/base")
@Slf4j
public class ApiController {

    @Autowired
    ParseService parseService;

    @RequestMapping(value = "/info", produces = "application/json;charset=UTF-8")
    public void getUser(@RequestBody String info, @RequestParam("tocken") String tocken) {
        // 一个简单的信息验证
        if ("lezkbigdataweblog".equals(tocken)) {
            parseService.send2Kafka(info);
        } else {
            System.out.println(info);
        }
    }
}
