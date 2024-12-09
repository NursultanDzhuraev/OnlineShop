package Dao.impl;

import Dao.ProductDao;
import databasa.Database;
import enam.Category;
import models.Product;

import java.util.Arrays;

public class ProductDaoImpl implements ProductDao {

    @Override
    public void save(Product product) {
        Database.products = Arrays.copyOf(Database.products, Database.products.length + 1);
        Database.products[Database.products.length - 1] = product;
        System.out.println("Successfully product");
    }

    @Override
    public Product[] findAllProduct() {
        return Database.products;
    }

    @Override
    public Product[] update(long id, Product newProduct) {
        return Database.products;
    }

    @Override
    public Product[] deleteProduct(long id) {
        return Database.products;
    }

    @Override
    public Product[] getProductById(long id) {
       return Database.products;
    }

    @Override
    public Product[] getProductByCategory() {
        return Database.products;
    }


}
