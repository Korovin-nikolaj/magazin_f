package ru.retail;

public class Product {

    private final String name;
    private final int id;
    private final float price;
    private final String productCategory;
    private final String productCountry;
    private final boolean discounted;


    public Product(String name, int id, float price, String productCategory, String productCountry, boolean discounted) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.productCategory = productCategory;
        this.productCountry = productCountry;
        this.discounted = discounted;
    }

    @Override
    public String toString() {
        return "" + id + " " + name + " по цене " + price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductCountry() {
        return productCountry;
    }

    public boolean isDiscounted() {
        return discounted;
    }
}