package Service;

import models.Product;

public interface ProductService {
    void addProduct(Product product);

    Product[] findAllProduct();

    void updateProduct(long id, Product newProduct);

    void deleteProduct(long id);

    Product getProductByid(long id);

    Product getProductByCategory(String category);
}
