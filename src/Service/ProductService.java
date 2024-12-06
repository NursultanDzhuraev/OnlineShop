package Service;

import models.Product;

public interface ProductService {
    void addProduct(Product product);
    Product[] findAllProduct();
    void updateProduct(long id, Product newProduct);
    public String deleteProduct(long id);
}
