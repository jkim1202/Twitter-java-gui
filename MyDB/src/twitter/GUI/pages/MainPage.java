package twitter.GUI.pages;

import twitter.GUI.dao.UserDao;
import twitter.GUI.designs.*;
import twitter.GUI.service.PostService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;

public class MainPage extends JFrame {
    private final PostService postService = new PostService();
    public MainPage(UserDao userDao, Connection con) throws HeadlessException {
        super("Twitter Main");
        setTitle("Twitter Main");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = getContentPane();

        // panel 상하좌우로 10px씩 여백 패딩
//        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 상단 panel
        JPanel topPanel = new JPanel(new GridLayout(1, 3));

        // 좌측 상단 profile image
        ImageJLabel userProfileImage = new ImageJLabel("../T2.png",40,40);
        JPanel but1Panel = new JPanel();
        but1Panel.setBackground(Color.white);
        but1Panel.add(userProfileImage);
        topPanel.add(but1Panel);

        // 상단 가운데 공백
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.white);
        topPanel.add(emptyPanel);

        // 우측 상단 follow JLabel
        ActionJLabel followLabel = new ActionJLabel("Follow");
        followLabel.setFont(new Font("SanSerif", Font.BOLD, 16));
        followLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                /**
                 * 미완성
                 * service에서 DAO로 follow 하는 사람들 쿼리로 찾기.
                 */
                FollowPage followPage = new FollowPage();
            }
        });
        JPanel followPanel = new JPanel();
        followPanel.setBackground(Color.WHITE);
        followPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        followPanel.add(followLabel,gbc);
        topPanel.add(followPanel);


        JPanel midPanel = new JPanel();
        midPanel.setBackground(Color.WHITE);
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));

        JPanel botPanel = new JPanel(new GridLayout(1, 3));
        botPanel.add(new JButton("test button 1"));
        botPanel.add(new JButton("test button 2"));
        botPanel.add(new JButton("test button 3"));

//        ArrayList<String> testURL = new ArrayList<>();
//        testURL.add("../TwitterLogo.png");
//        testURL.add("../TwitterLogo.png");
//        PostJPanel post1 = new PostJPanel("Sample post text: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", testURL,"../TwitterLogo.png","Sample ID: jkim");
//        PostJPanel post2 = new PostJPanel("Sample post text: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", testURL,"../TwitterLogo.png","Sample ID: jkim");
//        PostJPanel post3 = new PostJPanel("Sample post text: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", testURL,"../TwitterLogo.png","Sample ID: jkim");
//        PostJPanel post4 = new PostJPanel("Sample post text: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", testURL,"../TwitterLogo.png","Sample ID: jkim");
//
//        midPanel.add(post1);
//        midPanel.add(post2);
//        midPanel.add(post3);
//        midPanel.add(post4);

        postService.findPosts(midPanel,userDao,con);

        // 중단 JScrollPane
        JScrollPane midScrollPane = new JScrollPane(midPanel);
        midScrollPane.setBorder(BorderFactory.createLineBorder(new Color(234, 232, 232, 173),1));



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
//        SwingUtilities.invokeLater(() -> {
//            UserDao userDao = null; // insert test userDao
//            MainPage mainPage = new MainPage(userDao,null);
//        });
    }
}
