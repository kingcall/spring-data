package com.kingcall.mybatis.mapper;

import com.kingcall.mybatis.entity.GardenPrice;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description TODO
 * @Date 2018/10/15 12:50
 * @Author kingcall
 */
public interface GardenPriceMapper {

    @Select("SELECT * FROM gardenprice")
    List<GardenPrice> findAll();

    /**
     * 查询记录
     * @param id
     */
    @Select("select * from gardenprice where id=#{id}")
    public GardenPrice getGardenPriceById(int id);

    /**
     * 删除记录
     *     删除成功返回删除的条数
     *     删除失败返回 0
     * @param id
     */

    @Delete("DELETE FROM gardenprice WHERE id=#{id}")
    int delGardenPriceById(int id);

    /**
     *  插入记录
     *      1. 成功则返回数字
     *      2. 失败则报错
     * @param gardenPrice
     * @return
     */
    @Insert("INSERT INTO gardenprice(id,price,district,gardenname,createtime) VALUES (#{id},#{price},#{district},#{gardenname},#{createtime})")
    int addOne(GardenPrice gardenPrice);
}
