package ru.retail.service;

import ru.retail.Product;
import ru.retail.Storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

public class ProductService {
    public static LinkedHashMap<Integer, String> getAllProducts (){
        return getProducts("");
    }

    public static LinkedHashMap<Integer, String> findProducts (String conditionText){
        return getProducts(conditionText);
    }

    public static LinkedHashMap<Integer, String> getProducts (String conditionText){
        LinkedHashMap<Integer, String> allProducts = new LinkedHashMap<>();
        if (!conditionText.isEmpty()) {
            conditionText = " where " + conditionText;
        }
        try(Statement statement = Storage.getInstance().getConn().createStatement()) {
            String sqlCommand = "select id, productName, price from products " + conditionText + ";";
            System.out.println(sqlCommand);
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                allProducts.put(resultSet.getInt("id"), resultSet.getString("productName") + " по цене " + resultSet.getFloat("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allProducts;
    }

    public static int deleteProduct(String productId) {
        if (productId != null) {
            if (!productId.isEmpty()) {
                try(Statement statement = Storage.getInstance().getConn().createStatement()) {
                    String sqlCommand = "delete from products where id = " + productId + ";";
                    System.out.println(sqlCommand);
                    return statement.executeUpdate(sqlCommand);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public static Product getProduct(String productId) {
        if (productId != null) {
            if (!productId.isEmpty()) {
                Connection conn = Storage.getInstance().getConn();
                try(Statement statement = conn.createStatement()) {
                    String sqlCommand = "select productName, price, productCategory, productCountry, discounted  from products where id = " + productId + ";";
                    System.out.println(sqlCommand);
                    ResultSet resultSet = statement.executeQuery(sqlCommand);
                    if (resultSet.next()) {
                        String productName = resultSet.getString("productName");
                        float price = resultSet.getFloat("price");
                        String productCategory = resultSet.getString("productCategory");
                        String productCountry = resultSet.getString("productCountry");
                        boolean discounted = resultSet.getBoolean("discounted");
                        return new Product(productName, Integer.parseInt(productId), price, productCategory, productCountry, discounted);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static int updateProduct(Product product) {
        if (product != null) {
            Connection conn = Storage.getInstance().getConn();
            try(Statement statement = conn.createStatement()) {
                String sqlCommand = "update products set productName = '" + product.getName() +
                        "', price = " + product.getPrice() +
                        ", productCategory = '" + product.getProductCategory() +
                        "', productCountry = '" + product.getProductCountry() +
                        "', discounted = " + product.isDiscounted() + " where id = " + product.getId() + ";";
                System.out.println(sqlCommand);
                return statement.executeUpdate(sqlCommand);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}