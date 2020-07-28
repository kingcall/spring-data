package com.kingcall.mybatis.controller;

import com.kingcall.mybatis.mapper.MonitorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Date 2018/10/15 14:08
 * @Author kingcall
 */
@RestController
@RequestMapping("/garden")
public class GardenPriceController {

    @Autowired
    MonitorDao monitorDao;

}
