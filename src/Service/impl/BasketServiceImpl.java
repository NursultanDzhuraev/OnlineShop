package Service.impl;

import Service.BasketService;
import databasa.Database;
import models.Product;

import java.util.Arrays;

import static databasa.Database.products;

public class BasketServiceImpl implements BasketService {

    Product[] basket = new Product[0];
    @Override
    public void addFavoriteProductId(long id) {

        for (Product product : products) {
            if (product.getId()==id){
                basket = Arrays.copyOf(basket,basket.length+1);
                basket[basket.length-1]= product;
            }
        }
        System.out.println("Successfully product: ");
    }

    @Override
    public Product[] allFavoriteProductId() {

        return basket;
    }

    @Override
    public void deleteFavoriteProductId(long id) {
        boolean foundCar = false;
        for (int i = 0; i < basket.length; i++) {
            if (basket[i].getId() == id) {
                foundCar = true;
                for (int j = 0; j < basket.length - 1; j++) {
                    basket[j] = basket[j + 1];
                }

            }
        }
        basket= Arrays.copyOf(basket, basket.length - 1);
        System.out.println(foundCar ? "Successfully deleted" : "Product with id" + id + "not found !");

    }
}

