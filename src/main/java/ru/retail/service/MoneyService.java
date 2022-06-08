package ru.retail.service;

import ru.retail.Storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MoneyService {

    public static float getClientBalance(int clientId) {
        if (clientId != 0 ) {
            try(Statement statement = Storage.getInstance().getConn().createStatement()) {
                String sqlCommand = "select sum(sum) sum from wallets where client = " + clientId + ";";
                System.out.println(sqlCommand);
                ResultSet resultSet = statement.executeQuery(sqlCommand);
                if (resultSet.next()) {
                    return resultSet.getFloat("sum");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static int enterMoney(String clientId, String sum, String comments) {
        if (clientId != null) {
            if (!clientId.isEmpty()) {
                try(Statement statement = Storage.getInstance().getConn().createStatement()) {
                    String sqlCommand = "insert into wallets (client, sum, comment) values (" + clientId + ", " + sum + ", '" + comments + "');";
                    System.out.println(sqlCommand);
                    return statement.executeUpdate(sqlCommand);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }
}