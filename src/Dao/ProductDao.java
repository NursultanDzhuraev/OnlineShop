package Dao;

import enam.Category;
import models.Product;

public interface ProductDao {
    void save(Product product);

    Product[] findAllProduct();

    Product[] update(long id, Product newProduct);

    Product[] deleteProduct(long id);

    Product[] getProductById(long id);
    Product[] getProductByCategory();
}
