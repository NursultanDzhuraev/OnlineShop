package Service.impl;
import Dao.ProductDao;
import Service.ProductService;

import enam.Category;
import exceptions.NotFoundException;
import models.Product;

import java.util.Arrays;

import static databasa.Database.products;

public record ProductServiceImpl(ProductDao productDao) implements ProductService {
    @Override
    public void addProduct(Product product) {
        productDao.save(product);
    }

    @Override
    public Product[] findAllProduct() {
        return productDao.findAllProduct();
    }

    @Override
    public void updateProduct(long id, Product newProduct) {
        for (Product product : productDao.update(id, newProduct)) {
            if (product.getId() == id) {
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
    public void deleteProduct(long id) {
        boolean foundProduct = false;
        for (int i = 0; i < productDao.deleteProduct(id).length; i++) {
            if (productDao.deleteProduct(id)[i].getId() == id) {
                foundProduct = true;
                for (int j = 0; j < productDao.deleteProduct(id).length - 1; j++) {
                    productDao.deleteProduct(id)[j] = productDao.deleteProduct(id)[j + 1];
                }

            }
        }
        products = Arrays.copyOf(productDao.deleteProduct(id), productDao.deleteProduct(id).length - 1);
        System.out.println(foundProduct? "Successfully deleted" : "Product with id" + id + "not found !");
    }

    @Override
    public Product getProductByid(long id) {
        for (Product product : productDao.getProductById(id)) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new NotFoundException("Product not found");
    }

    @Override
    public Product getProductByCategory(String category) {
        for (Product product : productDao.getProductByCategory()) {
            for (Category productCategory : product.getCategories()) {
                if (category.equals(String.valueOf(productCategory))){
                    return product;
                }
            }
        }
        throw new NotFoundException("Product not found");
    }
}
