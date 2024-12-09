package Service;

import models.Product;

public interface BasketService {
     void addFavoriteProductId(Product productId);
     Product[] allFavoriteProductId(Product productId);
     void deleteFavoriteProductId(long id);
}
