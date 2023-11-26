package twitter.GUI.pages;

import twitter.GUI.dao.UserDao;
import twitter.GUI.designs.PostJPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainPage extends JFrame {
    public MainPage(UserDao userDao) throws HeadlessException {
        super("Twitter Main");
        setTitle("Twitter Main");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = getContentPane();

        // panel 상하좌우로 10px씩 여백 패딩
//        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 상단 중단 하단 panel
        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        topPanel.add(new JButton("test button 1"));
        topPanel.add(new JButton("test button 2"));
        topPanel.add(new JButton("test button 3"));
        topPanel.setBackground(Color.BLUE);

        JPanel midPanel = new JPanel();
        midPanel.setBackground(Color.WHITE);
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));

        JPanel botPanel = new JPanel(new GridLayout(1, 3));
        botPanel.add(new JButton("test button 1"));
        botPanel.add(new JButton("test button 2"));
        botPanel.add(new JButton("test button 3"));
        botPanel.setBackground(Color.RED);

        ArrayList<String> testURL = new ArrayList<>();
        testURL.add("../TwitterLogo.png");
        testURL.add("../TwitterLogo.png");
        PostJPanel post1 = new PostJPanel("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", testURL,"../TwitterLogo.png","jkim");
        PostJPanel post2 = new PostJPanel("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", testURL,"../TwitterLogo.png","jkim");
        PostJPanel post3 = new PostJPanel("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", testURL,"../TwitterLogo.png","jkim");
        PostJPanel post4 = new PostJPanel("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", testURL,"../TwitterLogo.png","jkim");
//        PostJPanel post4 = new PostJPanel("", null);
//        JPanel post1 = new JPanel();
//        post1.setBackground(Color.orange);
//        var text1 = new JTextArea("");
//        text1.setSize(new Dimension(400,text1.getPreferredSize().height));
//        text1.setLineWrap(true);
//        post1.add(text1);
//
//        JPanel post2 = new JPanel();
//        post2.setBackground(Color.darkGray);
//        var text2 = new JTextArea("");
//        text2.setLineWrap(true);
//        post2.add(text2);
//
//        JPanel post3 = new JPanel();
//        post3.setBackground(Color.green);
//        var text3 = new JTextArea("");
//
//        text3.setLineWrap(true);
//        post3.add(text3);
//

        midPanel.add(post1);
        midPanel.add(post2);
        midPanel.add(post3);
        midPanel.add(post4);

        // 중단 JScrollPane
        JScrollPane midScrollPane = new JScrollPane(midPanel);
//        midScrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(topPanel, "North");
        container.add(midScrollPane, BorderLayout.CENTER);
        add(botPanel, "South");

        setSize(450, 550);
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createPostPanel(Color color, String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setLineWrap(true);


        JPanel postPanel = new JPanel();
        postPanel.setBackground(color);
        postPanel.add(new JScrollPane(textArea));

        // Adjust height based on the preferred size of the text area
        postPanel.setPreferredSize(new Dimension(300, textArea.getPreferredSize().height));

        return postPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserDao userDao = null; // insert test userDao
            MainPage mainPage = new MainPage(userDao);
        });
    }
}
