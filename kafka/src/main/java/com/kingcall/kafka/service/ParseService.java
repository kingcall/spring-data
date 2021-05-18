package com.kingcall.kafka.service;

import com.kingcall.kafka.model.UserModel;
import org.springframework.stereotype.Service;

public interface ParseService {
    /**
     * 解析json 字符串
     * @param jsonStr
     * @return
     */
    public UserModel parseUser(String jsonStr);

    /**
     * 发送数据到kafka
     * @param userModel
     * @return
     */
    public boolean send2Kafka(String info);
}
