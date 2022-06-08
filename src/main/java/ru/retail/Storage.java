package ru.retail;

import java.sql.Connection;
import java.sql.DriverManager;

public class Storage {

    private static Storage instance;
    private Connection conn;

    private Storage(){
        this.conn = null;
        init();
    }

    public static Storage getInstance() {
        if (instance == null){
            instance = new Storage();
        }
        return instance;
    }

    private void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.println("Connection successful!");
            String url = "jdbc:mysql://localhost/magazin";
            String username = "magazin_robot";
            String password = "magazin_password";
            try{
                conn = DriverManager.getConnection(url, username, password);
            }
            catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        catch (Exception exc) {
            System.out.println("Connection failed...");
            exc.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }
}