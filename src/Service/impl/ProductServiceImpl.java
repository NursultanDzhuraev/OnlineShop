package Service.impl;


import Dao.ProductDao;
import Service.ProductService;

public class ProductServiceImpl implements ProductService {
    public final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }
}
