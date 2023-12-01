package twitter.GUI.service;


import twitter.GUI.dao.FollowDao;
import twitter.GUI.dao.UserDao;
import twitter.GUI.designs.FollowPanel;
import twitter.GUI.pages.LogInPage;
import twitter.GUI.pages.MainPage;
import twitter.GUI.pages.SignUpPage;
import twitter.GUI.repository.UserRepository;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public void logInUser(String userId, String userPassword, Connection con, JFrame logInPage) {
        UserDao userDao = userRepository.findUserByIdAndPassword(con, userId, userPassword);
        if (userDao == null) {
            JOptionPane.showMessageDialog(null, "Check your ID and password please.", "Log In Failed", JOptionPane.ERROR_MESSAGE);
        } else {
            logInPage.setVisible(false);
            SwingUtilities.invokeLater(() -> {
                MainPage mainPage = new MainPage(userDao, con);
            });
        }
    }

    public void signUpUser(String userId, String userAnswer, String userEmail, String userPassword, Connection con, SignUpPage signUpPage) {
        UserDao userDao = new UserDao(null, userId, userPassword, null, null, userEmail, null);
        userDao.setAnswer(userAnswer);
        UserDao CreatedUserDao = userRepository.createUser(userDao, con);
        if (CreatedUserDao == null) {
            JOptionPane.showMessageDialog(null, "Your ID already exists. Try another one.", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Your account has been created!", "Sign Up Success", JOptionPane.INFORMATION_MESSAGE);
            SwingUtilities.invokeLater(() -> {
                LogInPage logInPage = new LogInPage(con);
            });
            signUpPage.dispose();
        }
    }

    public void changePassword(JFrame frame, UserDao userDao, Connection con) {
        if (userRepository.changePassword(userDao, con) > 0) {
            JOptionPane.showMessageDialog(null, "Your password has been changed!", "Success Changing Password", JOptionPane.INFORMATION_MESSAGE);
            SwingUtilities.invokeLater(() -> {
                LogInPage logInPage = new LogInPage(con);
                logInPage.setVisible(true);
            });
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Check your ID or Email!", "Changing Password Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean findFollowings(JPanel midPanel, UserDao userDao, Connection con) {
        ArrayList<FollowDao> results = userRepository.findFollowings(userDao, con);
        if (results.size() == 0) return false;
        else {
            for (FollowDao result : results) {
                FollowPanel followPanel = new FollowPanel(userDao, result, con);
                midPanel.add(followPanel);
            }
            return true;
        }
    }

    public boolean findFollowers(JPanel midPanel, UserDao userDao, Connection con) {
        ArrayList<FollowDao> results = userRepository.findFollowers(userDao, con);
        if (results.size() == 0) return false;
        else {
            for (FollowDao result : results) {
                FollowPanel followPanel = new FollowPanel(userDao, result, con, true);
                midPanel.add(followPanel);
            }
            return true;
        }
    }

    public void createFollow(String Id, UserDao userDao, Connection con) {
        int result = userRepository.createFollowing(Id, userDao, con);
        if (result == 1)
            JOptionPane.showMessageDialog(null, "Success Follow!", "Success", JOptionPane.INFORMATION_MESSAGE);
        else if (result == 0)
            JOptionPane.showMessageDialog(null, "Already Following!", "Following Already", JOptionPane.INFORMATION_MESSAGE);
        else if (result == -1)
            JOptionPane.showMessageDialog(null, "Can not find user. Check Id again.", "Not Exist", JOptionPane.WARNING_MESSAGE);

    }
    public UserDao findUserByIdAndEmail(String id, String email, Connection con){
        return  userRepository.findUserByIdAndEmail(id,email,con);
    }
}
