package ru.retail.service;

import ru.retail.Storage;
import ru.retail.User;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

public class UserService {

    public static boolean isPasswordCorrect(String clientName, String password, HttpSession session) {
        if (clientName != null) {
            if (!clientName.isEmpty()) {
                Connection conn = Storage.getInstance().getConn();
                try(Statement statement = conn.createStatement()) {
                    String sqlCommand = "select id, phone, hashPassword from clients where name = '" + clientName + "';";
                    System.out.println(sqlCommand);
                    ResultSet resultSet = statement.executeQuery(sqlCommand);
                    if (resultSet.next()) {
                        if (resultSet.getInt("hashPassword") == password.hashCode()) {
                            session.setAttribute("clientName", clientName);
                            session.setAttribute("clientId", resultSet.getInt("id"));
                            session.setAttribute("clientPhone", resultSet.getInt("phone"));
                            return true;
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static LinkedHashMap<Integer, String> getAllClients (){
        Connection conn = Storage.getInstance().getConn();
        LinkedHashMap<Integer, String> allClients = new LinkedHashMap<>();
        try(Statement statement = conn.createStatement()) {
            String sqlCommand = "select id, name, phone from clients;";
            System.out.println(sqlCommand);
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String clientView = name + " (" + phone + ")";
                allClients.put(id, clientView);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allClients;
    }

    public static User getUser(String clientId) {
        if (clientId != null) {
            if (!clientId.isEmpty()) {
                try(Statement statement = Storage.getInstance().getConn().createStatement()) {
                    String sqlCommand = "select name, phone, hashPassword from clients where id = " + clientId + ";";
                    System.out.println(sqlCommand);
                    ResultSet resultSet = statement.executeQuery(sqlCommand);
                    if (resultSet.next()) {
                        String clientName = resultSet.getString("name");
                        String phone = resultSet.getString("phone");
                        int hashPassword = resultSet.getInt("hashPassword");
                        return new User(clientName, Integer.parseInt(clientId), phone, hashPassword);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static int updateUser(User user, HttpSession session) {
        if (user != null) {
            Connection conn = Storage.getInstance().getConn();
            try(Statement statement = conn.createStatement()) {
                String sqlCommand = "update clients set name = '" + user.getName() +
                        "', phone = '" + user.getPhone() +
                        "', hashPassword = " + user.getHashPassword() + " where id = " + user.getId() + ";";
                session.setAttribute("clientName", user.getName());
                session.setAttribute("clientId", user.getId());
                session.setAttribute("clientPhone", user.getPhone());
                System.out.println(sqlCommand);
                return statement.executeUpdate(sqlCommand);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}