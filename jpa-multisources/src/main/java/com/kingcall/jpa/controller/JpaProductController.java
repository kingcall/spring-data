package com.kingcall.jpa.controller;
import com.kingcall.jpa.entity.product.Product;
import com.kingcall.jpa.service.product.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 为了学习测试方便，直接将dao 接口注入进来使用—— 这是不符合规范的
 */
@Api(value = "JpaController", description = "Jpa 接口")
@RestController
@RequestMapping("/jpaProduct")
public class JpaProductController {
    @Autowired
    ProductService productService;
    @RequestMapping(value = "/addProduct")
    public String addProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("dsf");
        product.setPrice(1.0);
        productService.addProduct(product);
        return product.toString();
    }
}
