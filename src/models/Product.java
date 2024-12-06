package models;

import enam.Size;

import java.math.BigDecimal;
import java.util.Arrays;

public class Product {
    private long id;
    private String name;
    private BigDecimal price;
    private Size[] sizes;
    private String color;
    private String imegeUrl;


    private static long generatID = 1;

    public Product() {
        this.id = generatID++;
    }

    public Product( String name, BigDecimal price, String color, String imegeUrl) {
        this.id = generatID++;
        this.name = name;
        this.price = price;
        this.color = color;
        this.imegeUrl = imegeUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Size[] getSizes() {
        return sizes;
    }

    public void setSizes(Size[] sizes) {
        this.sizes = sizes;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImegeUrl() {
        return imegeUrl;
    }

    public void setImegeUrl(String imegeUrl) {
        this.imegeUrl = imegeUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sizes=" + Arrays.toString(sizes) +
                ", color='" + color + '\'' +
                ", imegeUrl='" + imegeUrl + '\'' +
                '}';
    }
}
