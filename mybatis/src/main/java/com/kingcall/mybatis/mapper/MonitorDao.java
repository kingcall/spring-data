package com.kingcall.mybatis.mapper;

import com.kingcall.mybatis.entity.GardenPrice;
import com.kingcall.mybatis.entity.Monitor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * @Description TODO
 * @Date 2018/10/16 12:39
 * @Author kingcall
 */
public interface MonitorDao {
    List<Monitor> findAll();
    Monitor getMonitorById(int id);
    int delMonitorById(int id);
    int addOne(Monitor monitor);

}
