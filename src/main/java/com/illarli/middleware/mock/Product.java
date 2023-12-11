package com.illarli.middleware.mock;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private Integer quantity;
    private Double price;

    public Product(String name, Integer quantity, Double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public static List<Product> getMockProducts() {
        return new ArrayList<Product>(List.of(
                new Product("GUAYABA", 1, 1.60),
                new Product("220V", 1, 1.20)
        ));
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
