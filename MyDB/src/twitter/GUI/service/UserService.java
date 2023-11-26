package twitter.GUI.service;


import twitter.GUI.dao.UserDao;
import twitter.GUI.pages.LogInPage;
import twitter.GUI.pages.MainPage;
import twitter.GUI.pages.SignUpPage;
import twitter.GUI.repository.UserRepository;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    public void logInUser(String userId, String userPassword, Connection con, JFrame logInPage){
        UserDao userDao = userRepository.findUserByIdAndPassword(con, userId,userPassword);
        if(userDao == null){
            JOptionPane.showMessageDialog(null, "Check your ID and password please.", "Log In Failed", JOptionPane.ERROR_MESSAGE);
        }
        else {
            SwingUtilities.invokeLater(() -> {
                MainPage mainPage = new MainPage(userDao, con);
            });
        }
    }

    public void signUpUser(String userId, String userNickname, String userEmail, String userPassword, Connection con, SignUpPage signUpPage){
        UserDao userDao = new UserDao(null, userId, userPassword, userNickname, null, userEmail,null);
        UserDao CreatedUserDao = userRepository.createUser(userDao, con);
        if(CreatedUserDao == null){
            JOptionPane.showMessageDialog(null, "Your ID already exists. Try another one.", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, "Your account has been created!", "Sign Up Success", JOptionPane.INFORMATION_MESSAGE);
            SwingUtilities.invokeLater(() -> {
                LogInPage logInPage = new LogInPage(con);
            });
            signUpPage.setVisible(false);
        }
    }
}
