package twitter.GUI.pages;

import twitter.GUI.designs.RoundJPasswordField;
import twitter.GUI.designs.RoundJTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class LogInPage extends JFrame {

    public LogInPage() {
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
        RoundJTextField usernameField = new RoundJTextField("Phone,email or Username");
        usernameField.setFont(new Font("맑은 고딕",Font.PLAIN,16));
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(usernameField.getText().equals("")||usernameField.getText().equals("Phone,email or Username"))
                    usernameField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(usernameField.getText().equals(""))
                    usernameField.setText("Phone,email or Username");
            }
        });


        // password 패스워드 필드
        RoundJPasswordField passwordField = new RoundJPasswordField("Password");
        passwordField.setText("");
        passwordField.requestFocus();
        JLabel passwordLabel = new JLabel("Password:");

        JButton loginButton = new JButton("Log in");

        panel.add(twitterLogo);
        panel.add(logInToTwitter); // Empty label for spacing
        panel.add(usernameField);
        panel.add(passwordField);
        panel.add(passwordLabel); // 버튼으로 바꿔야함
        panel.add(loginButton); // 두개로 나눠야함.

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] password = passwordField.getPassword();

            // Perform login logic here (not implemented in this example)
            System.out.println("Username: " + username);
            System.out.println("Password: " + new String(password));

            // You should add your own logic to handle the login process
        });

        add(panel);

        setSize(450, 550);
        setResizable(false); // Make the frame not resizable
//        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LogInPage::new);
    }

}


