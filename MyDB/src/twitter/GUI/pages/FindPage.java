package twitter.GUI.pages;

import twitter.GUI.dao.UserDao;
import twitter.GUI.designs.ImageJLabel;
import twitter.GUI.designs.RoundJButton;
import twitter.GUI.designs.RoundJPasswordField;
import twitter.GUI.designs.RoundJTextField;
import twitter.GUI.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

public class FindPage extends JFrame {
    private final UserService userService = new UserService();

    public FindPage(Connection con) throws HeadlessException {
        super();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FindPage.this.dispose();
                LogInPage logInPage = new LogInPage(con);
                logInPage.setVisible(true);
            }
        });

        ImageJLabel twitterLogo = new ImageJLabel("../TwitterLogo.png", 40,40);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBackground(Color.WHITE);
        // panel 상하좌우로 10px씩 여백 패딩
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // header,id, nickname, email JLabel
        JLabel header = new JLabel("Change Password");
        header.setFont(new Font("sans-serif", Font.BOLD, 22));
        header.setHorizontalAlignment(JLabel.CENTER);
        RoundJTextField id = new RoundJTextField("Enter your ID here", true);
        RoundJTextField email = new RoundJTextField("Enter your email here", true);
        // password JPasswordField
        RoundJPasswordField password = new RoundJPasswordField();
        // Enter JButton
        RoundJButton button = new RoundJButton("Change Password", 60);

        button.addActionListener(e -> {
            String userId = id.getText();
            String userEmail = email.getText();
            char[] userPassword = password.getPassword();
            String strPassword = new String(userPassword);

            UserDao userDao = new UserDao(userId, strPassword, userEmail);

            userService.changePassword(FindPage.this,userDao, con);
        });

        panel.add(twitterLogo);
        panel.add(header);
        panel.add(id);
        panel.add(email);
        panel.add(password);
        panel.add(button);
        add(panel);

        setSize(450, 550);
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
