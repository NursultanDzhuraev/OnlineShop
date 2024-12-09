package Service.impl;

import Service.BasketService;
import databasa.Database;
import models.Product;

import java.util.Arrays;

import static databasa.Database.basket;
import static databasa.Database.products;

public class BasketServiceImpl implements BasketService {


    @Override
    public void addFavoriteProductId(Product productId) {
        Database.basket = Arrays.copyOf(Database.basket, Database.basket.length + 1);
        Database.basket[Database.basket.length - 1] = productId;

    }

    @Override
    public Product[] allFavoriteProductId(Product productId) {
        return Database.basket;
    }

    @Override
    public void deleteFavoriteProductId(long id) {
        for (int i = 0; i < basket.length; i++) {
        }
    }
}

