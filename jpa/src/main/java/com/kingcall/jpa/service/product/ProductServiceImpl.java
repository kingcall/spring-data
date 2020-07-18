package com.kingcall.jpa.service.product;

import com.kingcall.jpa.dao.product.ProductRepository;
import com.kingcall.jpa.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productDao;
    @Override
    public int addProduct(Product product) {
        productDao.save(product);
        return product.getId();
    }
}
