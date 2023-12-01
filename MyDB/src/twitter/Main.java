package twitter;

import twitter.GUI.dao.UserDao;
import twitter.GUI.pages.LogInPage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Connect to database
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/mydb";
            String user = "root", passwd = "1234";
            con = DriverManager.getConnection(url, user, passwd);
            System.out.println(con);
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }

        // Start Twitter
        Connection finalCon = con;
        SwingUtilities.invokeLater(() -> {
            LogInPage logInPage = new LogInPage(finalCon);
        });
    }
}
