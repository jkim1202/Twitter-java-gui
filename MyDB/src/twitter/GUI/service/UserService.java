package twitter.GUI.service;


import twitter.GUI.dao.UserDao;
import twitter.GUI.pages.MainPage;
import twitter.GUI.repository.UserRepository;

import javax.swing.*;
import java.sql.*;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    public void logInUser(String input_name, String input_password, Connection con, JFrame logInPage){
        UserDao userDao = userRepository.findUserByIdAndPassword(con, input_name,input_password);
        if(userDao == null){
            JOptionPane.showMessageDialog(null, "Check your ID and password please.", "Log In Failed", JOptionPane.ERROR_MESSAGE);
        }
        else {
            SwingUtilities.invokeLater(() -> {
                MainPage mainPage = new MainPage(userDao);
            });
        }

    }
}
