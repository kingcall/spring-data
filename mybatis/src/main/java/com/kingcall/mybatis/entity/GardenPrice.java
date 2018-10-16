package com.kingcall.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description TODO
 * @Date 2018/10/15 12:51
 * @Author kingcall
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class GardenPrice implements Serializable {
    int id;
    String price;
    String district;
    String gardenname;
    Date createtime;

}
