package com.kingcall.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description TODO
 * @Date 2018/10/16 12:36
 * @Author kingcall
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Monitor {
    int id;
    String url;
    String source;
    String destination;
    String user;
    String passwd;
    String receive;
}
