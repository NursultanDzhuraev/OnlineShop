package Service.impl;
import Dao.ProductDao;
import Service.ProductService;

import models.Product;

import java.util.Arrays;

import static databasa.Database.products;

public class ProductServiceImpl implements ProductService {
    public final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }
    @Override
    public void addProduct(Product product) {
        productDao.save(product);
    }
    @Override
    public Product[] findAllProduct() {
        return products;
    }
    @Override
    public void updateProduct(long id, Product newProduct) {
        for (Product product : products) {
            if (product.getId()== id){
                product.setName(newProduct.getName());
                product.setPrice(newProduct.getPrice());
                product.setSizes(newProduct.getSizes());
                product.setColor(newProduct.getColor());
                product.setImegeUrl(newProduct.getImegeUrl());
                return;
            }
        }

    }
    @Override
    public String deleteProduct(long id) {
        boolean foundProduct = false;
        for (int i = 0; i <products.length; i++) {
            if (products[i].getId() == id) {
                foundProduct = true;
                for (int j = 0; j < products.length- 1; j++) {
                    products[j] = products[j + 1];
                }

            }
        }
        products= Arrays.copyOf(products, products.length - 1);
        return foundProduct ? "Successfully daleted" : "Product with id" + id + "not found !";
    }
}
