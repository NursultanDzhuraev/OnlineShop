package Service;

import models.Product;

public interface BasketService {
     void addFavoriteProductId(long id);
     Product[] allFavoriteProductId();
     void deleteFavoriteProductId(long id);
}
