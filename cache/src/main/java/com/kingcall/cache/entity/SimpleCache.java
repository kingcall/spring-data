package com.kingcall.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SimpleCache implements Serializable {
    String name;
    int age;
}
