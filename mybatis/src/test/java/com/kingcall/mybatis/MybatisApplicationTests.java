package com.kingcall.mybatis;

import com.kingcall.mybatis.entity.GardenPrice;
import com.kingcall.mybatis.entity.Monitor;
import com.kingcall.mybatis.mapper.GardenPriceMapper;
import com.kingcall.mybatis.mapper.MonitorDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {
    @Autowired
    GardenPriceMapper gardenPriceMapper;
    @Autowired
    MonitorDao monitorMapper;
    @Test
    public void getGardenPriceById() {
        GardenPrice gardenPrice = gardenPriceMapper.getGardenPriceById(181);
        System.out.println(gardenPrice);
    }
    @Test
    public void getMonitorById() {
        Monitor monitor = monitorMapper.getMonitorById(7);
        System.out.println(monitor);
    }


    @Test
    public void delGardenPriceById() {
       int result=gardenPriceMapper.delGardenPriceById(181);
        System.out.println(result);
    }
    @Test
    public void findAll() {
        List<GardenPrice> result=gardenPriceMapper.findAll();
        result.forEach((x)-> System.out.println(x));
    }

    @Test
    public void addOne() {
        GardenPrice gardenPrice = new GardenPrice();
        gardenPrice.setDistrict("kingcall").setCreatetime(new Date()).setGardenname("刘文强").setId(8).setPrice("1000");
        int result=gardenPriceMapper.addOne(gardenPrice);
        System.out.println(result);

    }

}
