package Dao;

import models.Product;

public interface ProductDao {
    void save(Product product);
    Product[] findAllProduct();
    void update(long id,Product newProduct);
    public String deleteProduct(long id);
}
