package ru.retail.service;

import ru.retail.BasketRow;
import ru.retail.BuyHistoryRow;
import ru.retail.Storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;

public class OrderService {

    public static boolean enterOrder(String clientId, String sum, String comments, String address, HashMap<Integer, Integer> basketWithQuantity) throws SQLException {
        if (clientId != null) {
            if (!clientId.isEmpty()) {
                Connection conn = Storage.getInstance().getConn();
                conn.setAutoCommit(false);
                try (Statement statement = conn.createStatement()) {
                    ResultSet resultSet = statement.executeQuery("select ifnull(max(id),0)+1 as maxId from orders;");
                    if (resultSet.next()) {
                        int maxId = resultSet.getInt("maxId");
                        int res = statement.executeUpdate("insert into orders (id, client, sum, address, comments, status) " +
                                "values (" + maxId + ", " + clientId + ", " + sum + ",'" + address + "', '" + comments + "', 1); ");
                        if (res != 0) {
                            StringBuilder sqlCommand = new StringBuilder();
                            sqlCommand.append("insert into orderComposition (orderId, productId, quantity) values ");
                            boolean first = true;
                            for (Entry entry : basketWithQuantity.entrySet()) {
                                if (first) {
                                    first = false;
                                } else {
                                    sqlCommand.append(",");
                                }
                                sqlCommand.append("(");
                                sqlCommand.append(maxId).append(",");
                                sqlCommand.append(entry.getKey().toString()).append(",");
                                sqlCommand.append(entry.getValue().toString());
                                sqlCommand.append(")");
                            }
                            sqlCommand.append(";");
                            System.out.println(sqlCommand);
                            res = statement.executeUpdate(sqlCommand.toString());
                            if (res != 0) {
                                res = statement.executeUpdate("insert into wallets (client, sum, comment) values (" + clientId + ", -" + sum + ", concat('Оплата заказа ', '" + maxId + "'));");
                                if (res != 0) {
                                    conn.commit();
                                    conn.setAutoCommit(true);
                                    return true;
                                }
                            }
                        }
                    }
                    conn.rollback();
                    conn.setAutoCommit(true);
                    return false;
                } catch (SQLException e) {
                    e.printStackTrace();
                    conn.rollback();
                    conn.setAutoCommit(true);
                }
            }
        }
        return false;
    }

    public static HashMap<Integer, BuyHistoryRow> getBuyHistory(String clientId) {
        HashMap<Integer, BuyHistoryRow> buyHistory = new HashMap<>();
        if (clientId != null) {
            if (!clientId.isEmpty()) {
                try (Statement statement = Storage.getInstance().getConn().createStatement()) {
                    ResultSet resultSet = statement.executeQuery("select a.id orderId, a.sum sum, a.address address, a.comments comments, b.name statusName from orders a left join" +
                            " statuses b on (a.status=b.id) where a.client = " + clientId + ";");
                    while (resultSet.next()) {
                        int orderId = resultSet.getInt("orderId");
                        float sum = resultSet.getFloat("sum");
                        String address = resultSet.getString("address");
                        String comments = resultSet.getString("comments");
                        String status = resultSet.getString("statusName");
                        HashMap<Integer, BasketRow> products = new HashMap<>();
                        try (Statement subStatement = Storage.getInstance().getConn().createStatement()) {
                            ResultSet res = subStatement.executeQuery("select a.productId productId, b.productName productName, a.quantity quantity, b.price price from orderComposition a left join products b on (a.productId=b.id) where a.orderId=" + orderId);
                            int i = 1;
                            while (res.next()) {
                                int productId = res.getInt("productId");
                                String productName = res.getString("productName");
                                float quantity = res.getFloat("quantity");
                                BasketRow basketRow = new BasketRow(productId, productName, quantity, 0);
                                products.put(i++, basketRow);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        BuyHistoryRow row = new BuyHistoryRow(orderId, sum, address, comments, status, products);
                        buyHistory.put(orderId, row);
                    }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }
        }
        return buyHistory;
    }
}