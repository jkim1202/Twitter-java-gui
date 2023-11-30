package twitter;

import twitter.GUI.dao.UserDao;
import twitter.GUI.pages.LogInPage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
/**
 * 메인 페이지:
 *  남은 기능 -->     본인 게시글 고정(db수정해야함)
 * 코멘트 페이지:
 * 팔로우/팔로잉 페이지:
 *  남은 기능 -->     팔로우 요청할 user 찾기 Read
 * 요청 페이지:
 *  남은 기능 -->      팔로우 요청 찾기 Read
 *                    팔로우 요청 accept/deny Update/Delete
 * 비밀번호 찾기 페이지:
 *                     새로운 비밀번호로 변경 Update
 * 개인정보 페이지(미구현):
 *  남은 기능 -->       프사, 닉네임, 변경 Update
 */
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
