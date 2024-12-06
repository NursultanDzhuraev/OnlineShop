package Dao.impl;

import Dao.ProductDao;
import databasa.Database;
import models.Product;

import java.util.Arrays;

public class ProductDaoImpl implements ProductDao {

    @Override
    public void save(Product product) {
        Database.products = Arrays.copyOf(Database.products,Database.products.length+1);
        Database.products[Database.products.length-1] = product;
        System.out.println("Successfully product");
    }

    @Override
    public Product[] findAllProduct() {
        return Database.products;
    }

    @Override
    public void update(long id, Product newProduct) {

    }

    @Override
    public String deleteProduct(long id) {
        return "delet";
    }


}
