package twitter.GUI.pages;

import twitter.GUI.dao.UserDao;
import twitter.GUI.designs.RoundJButton;
import twitter.GUI.designs.RoundJPasswordField;
import twitter.GUI.designs.RoundJTextField;
import twitter.GUI.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.Objects;

public class LogInPage extends JFrame {
    private final UserService userService = new UserService();

    public LogInPage(Connection con) {
        super("Twitter Login");
        setTitle("Twitter Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBackground(Color.WHITE);
        // panel 상하좌우로 10px씩 여백 패딩
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 트위터 로고 JLabel
        JLabel twitterLogo = new JLabel();
        ImageIcon icon = new ImageIcon(
                Objects.requireNonNull(LogInPage.class.getResource("../TwitterLogo.png"))
        );
        Image img = icon.getImage();
        Image updateImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon updateIcon = new ImageIcon(updateImg);
        twitterLogo.setIcon(updateIcon);

        // Log in to Twitter Label
        JLabel logInToTwitter = new JLabel("Log in to Twitter");
        logInToTwitter.setFont(new Font("sans-serif", Font.BOLD, 26));
        logInToTwitter.setHorizontalAlignment(JLabel.CENTER);

        // username, phone, email 텍스트 필드
        RoundJTextField usernameField = new RoundJTextField("Phone,email or Username",true);
        usernameField.setFont(new Font("맑은 고딕",Font.PLAIN,16));


        // password 패스워드 필드, JLayeredPane
        /**
         * 패스워드 필드위에 텍스트 띄우는 기능 보류
         */
        RoundJPasswordField passwordField = new RoundJPasswordField("Password");


        // 버튼
        RoundJButton loginButton = new RoundJButton("Log in",60);

        // 비밀번호 찾기, 회원가입을 위한 panel
        JPanel panel2 = new JPanel(new GridLayout(1, 2, 10, 10));
        panel2.setBackground(Color.WHITE);

        // 비밀번호 찾기 JLabel
        JLabel findPassword = new JLabel("Forgot Password?");
        findPassword.setForeground(new Color(82, 182, 236));
        findPassword.setHorizontalAlignment(JLabel.RIGHT);
        findPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                findPassword.setForeground(new Color(82, 182, 236).darker());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                findPassword.setForeground(new Color(82, 182, 236));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                SwingUtilities.invokeLater(() -> {
                    UserDao userDao = null; // insert test userDao
                    MainPage mainPage = new MainPage(userDao,con);
                    mainPage.setVisible(true);
                });
            }
        });
        // 회원가입 JLabel
        JLabel signUp = new JLabel("Sign up for Twitter");
        signUp.setForeground(new Color(82, 182, 236));
        signUp.setHorizontalAlignment(JLabel.LEFT);
        signUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                signUp.setForeground(new Color(82, 182, 236).darker());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                signUp.setForeground(new Color(82, 182, 236));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                SwingUtilities.invokeLater(() -> {
                    SignUpPage signUpPage = new SignUpPage(con);
                });;
            }
        });

        panel.add(twitterLogo);
        panel.add(logInToTwitter);
        panel.add(usernameField);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(panel2);
        panel2.add(findPassword);
        panel2.add(signUp);


        loginButton.addActionListener(e -> {
            String userId = usernameField.getText();
            char[] password = passwordField.getPassword();
            String strPassword = new String(password);
//            setVisible(false);
            userService.logInUser(userId,strPassword,con,this);
        });

        add(panel);

        setSize(450, 550);
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null);
        setVisible(true);
    }

//    public static void main(String[] args) {
//
//        // page load
//        SwingUtilities.invokeLater(() -> {
//            UserDao userDao = null; // insert test userDao
//            LogInPage logInPage = new LogInPage(finalCon);
//        });
////        SwingUtilities.invokeLater(LogInPage::new);
//    }

}


