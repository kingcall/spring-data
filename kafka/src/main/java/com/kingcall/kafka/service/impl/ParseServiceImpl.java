package com.kingcall.kafka.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kingcall.kafka.model.UserModel;
import com.kingcall.kafka.service.ParseService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author liuwenqiang
 */
@Service
@Log
public class ParseServiceImpl implements ParseService {
    @Value("${spring.kafka.topic}")
    private String userTopic;

    @Autowired
    KafkaTemplate kafkaTemplate;


    @Override
    public UserModel parseUser(String jsonStr) {
        return JSON.parseObject(jsonStr, UserModel.class);
    }

    @Override
    public boolean send2Kafka(UserModel userModel) {
        boolean flag = true;
        ListenableFuture listenableFuture = kafkaTemplate.send(userTopic, JSONObject.toJSONString(userModel));
        try {
            listenableFuture.get(3L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException | TimeoutException e) {
            log.warning("发送失败:" + userModel);
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
