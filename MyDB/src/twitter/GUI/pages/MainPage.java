package twitter.GUI.pages;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {
    public MainPage() throws HeadlessException {
        super("Twitter Login");
        setTitle("Twitter Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        // panel 상하좌우로 10px씩 여백 패딩
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 상단 중단 하단 panel
        JPanel topPanel = new JPanel(new GridLayout(1,3));
        topPanel.setBackground(Color.BLUE);
        JPanel midPanel = new JPanel();
        midPanel.setPreferredSize(new Dimension(350,1000));
        midPanel.setBackground(Color.WHITE);
        JPanel botPanel = new JPanel(new GridLayout(1,3));
        botPanel.setBackground(Color.RED);

        // 중단 JScrollPane
        JScrollPane midScrollPane = new JScrollPane(midPanel);
        midScrollPane.setBorder(BorderFactory.createEmptyBorder());
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));

        JPanel midPanel1 = new JPanel();
        midPanel1.setSize(300,200);
        midPanel1.setBackground(Color.RED);
        JPanel midPanel2 = new JPanel();
        midPanel2.setSize(300,200);
        JPanel midPanel3 = new JPanel();
        midPanel3.setSize(300,200);
        JPanel midPanel4 = new JPanel();
        midPanel4.setSize(300,200);

        midPanel.add(midPanel1);
        midPanel.add(midPanel2);
        midPanel.add(midPanel3);
        midPanel.add(midPanel4);

        JLabel top = new JLabel("I am Zeus");
        JLabel mid = new JLabel("I am Faker");
        JLabel mid1 = new JLabel("I am Chovy");
        JLabel mid2 = new JLabel("I am ShowMaker");
        JLabel mid3 = new JLabel("I am BDD");
        JLabel bot  = new JLabel("I am Gumayusi");

        topPanel.add(top);
        midPanel1.add(mid);
        midPanel2.add(mid1);
        midPanel3.add(mid2);
        midPanel4.add(mid3);
        botPanel.add(bot);

        panel.add(topPanel,BorderLayout.NORTH);
        panel.add(midScrollPane,BorderLayout.CENTER);
        panel.add(botPanel,BorderLayout.SOUTH);

        add(panel);

        setSize(450, 550);
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainPage::new);
    }
}
