package twitter.GUI.pages;

import twitter.GUI.designs.RoundJButton;
import twitter.GUI.designs.RoundJPasswordField;
import twitter.GUI.designs.RoundJTextField;

import javax.swing.*;
import java.awt.*;

public class SignUpPage extends JFrame {
    public SignUpPage() throws HeadlessException {
        super("Sign up Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBackground(Color.WHITE);
        // panel 상하좌우로 10px씩 여백 패딩
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // header,id, nickname, email JLabel
        JLabel header = new JLabel("Create Twitter Account");
        header.setFont(new Font("sans-serif", Font.BOLD, 26));
        header.setHorizontalAlignment(JLabel.CENTER);
        RoundJTextField id =  new RoundJTextField("Enter your ID here",true);
        RoundJTextField nickName = new RoundJTextField("Enter your nickname here",true);
        RoundJTextField email = new RoundJTextField("Enter your email here",true);
        // password JPasswordField
        RoundJPasswordField password = new RoundJPasswordField();
        // Enter JButton
        RoundJButton button = new RoundJButton("Create",30);



        panel.add(header);
        panel.add(id);
        panel.add(nickName);
        panel.add(email);
        panel.add(password);
        panel.add(button);
        add(panel);

        setSize(450, 550);
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignUpPage::new);
    }

}
