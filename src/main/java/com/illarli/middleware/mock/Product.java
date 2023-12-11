package com.illarli.middleware.mock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private Integer quantity;
    private Double price;

    public Product(String name, Integer quantity, Double price) {
        BigDecimal bd = BigDecimal.valueOf(price);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        this.name = name;
        this.quantity = quantity;
        this.price = bd.doubleValue();
        System.out.println(this.price);
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
