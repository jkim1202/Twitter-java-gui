package twitter.GUI.pages;

import twitter.GUI.dao.UserDao;
import twitter.GUI.designs.ActionJLabel;
import twitter.GUI.designs.ImageJLabel;
import twitter.GUI.service.PostService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.util.Map;

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
        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.setBackground(Color.white);
        JPanel topNav = new JPanel(new GridLayout(1, 2));
        topNav.setBackground(Color.WHITE);
        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        topContainer.add(topPanel,"Center");
        topContainer.add(topNav, "South");

        // topNav 패널
        JPanel leftP = new JPanel();
        var leftT = new JLabel("Follower Post");
        Font font = leftT.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        leftT.setFont(font.deriveFont(attributes));
        leftT.setForeground(new Color(0,0,0).darker());
        leftP.add(leftT);
        leftT.setBackground(new Color(141, 141, 141));
        JPanel rightP = new JPanel();
        var rightT = new ActionJLabel("My Post",new Color(130,130,130));
        rightT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainPage.this.dispose();
                MainPage page = new MainPage(userDao,con,true);
                page.setVisible(true);
            }
        });
        rightP.add(rightT);
        topNav.add(leftP);
        topNav.add(rightP);

        // 좌측 상단 profile image
        String imageURL = "../TwitterLogo.png";
        ImageJLabel userProfileImage = null;
        if(userDao.getProfile_image_url()==null)
             userProfileImage = new ImageJLabel(imageURL,40,40);
        else
            userProfileImage = new ImageJLabel(userDao.getProfile_image_url(),40,40);
        JPanel but1Panel = new JPanel();
        but1Panel.setBackground(Color.white);
        but1Panel.add(userProfileImage);
        topPanel.add(but1Panel);
        userProfileImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                AccountPage accountPage = new AccountPage();
                accountPage.setVisible(true);
            }
        });

        ActionJLabel writeLabel = new ActionJLabel("Write");
        writeLabel.setFont(new Font("SanSerif", Font.BOLD, 16));
        writeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                PostWritePage page = new PostWritePage(con, userDao);
                page.setVisible(true);
            }
        });
        JPanel writePanel = new JPanel();
        writePanel.setBackground(Color.WHITE);
        writePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        writePanel.add(writeLabel,gbc);
        topPanel.add(writePanel);


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
        GridBagConstraints gbc1 = new GridBagConstraints();
        followPanel.add(followLabel,gbc1);
        topPanel.add(followPanel);


        JPanel midPanel = new JPanel();
        midPanel.setBackground(Color.WHITE);
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));

//        JPanel botPanel = new JPanel(new GridLayout(1, 3));
//        botPanel.add(new JButton("test button 1"));
//        botPanel.add(new JButton("test button 2"));
//        botPanel.add(new JButton("test button 3"));


        postService.findPosts(midPanel,userDao,con,true);

        // 중단 JScrollPane
        JScrollPane midScrollPane = new JScrollPane(midPanel);
        midScrollPane.setBorder(BorderFactory.createLineBorder(new Color(234, 232, 232, 173),1));


//        add(topPanel, "North");
        add(topContainer,"North");
        container.add(midScrollPane, BorderLayout.CENTER);
//        add(botPanel, "South");

        setSize(450, 550);
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public MainPage(UserDao userDao, Connection con, boolean bool) throws HeadlessException {
        super("Twitter Main");
        setTitle("Twitter Main");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = getContentPane();

        // panel 상하좌우로 10px씩 여백 패딩
//        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 상단 panel
        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.setBackground(Color.white);
        JPanel topNav = new JPanel(new GridLayout(1, 2));
        topNav.setBackground(Color.white);
        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        topContainer.add(topPanel,"Center");
        topContainer.add(topNav, "South");

        // topNav 패널
        JPanel leftP = new JPanel();
        var leftT = new ActionJLabel("Follower Post",new Color(130,130,130));
        var rightT = new JLabel("My Post");
        leftP.add(leftT);
        leftT.setBackground(new Color(141, 141, 141));
        JPanel rightP = new JPanel();
        Font font = rightT.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        rightT.setFont(font.deriveFont(attributes));
        rightT.setForeground(new Color(0,0,0).darker());
        leftT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainPage.this.dispose();
                MainPage page = new MainPage(userDao,con);
                page.setVisible(true);
            }
        });
        rightP.add(rightT);
        topNav.add(leftP);
        topNav.add(rightP);

        // 좌측 상단 profile image
        String imageURL = "../TwitterLogo.png";
        ImageJLabel userProfileImage = null;
        if(userDao.getProfile_image_url()==null)
            userProfileImage = new ImageJLabel(imageURL,40,40);
        else
            userProfileImage = new ImageJLabel(userDao.getProfile_image_url(),40,40);
        JPanel but1Panel = new JPanel();
        but1Panel.setBackground(Color.white);
        but1Panel.add(userProfileImage);
        topPanel.add(but1Panel);
        userProfileImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                AccountPage accountPage = new AccountPage();
                accountPage.setVisible(true);
            }
        });

        ActionJLabel writeLabel = new ActionJLabel("Write");
        writeLabel.setFont(new Font("SanSerif", Font.BOLD, 16));
        writeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                PostWritePage page = new PostWritePage(con, userDao);
                page.setVisible(true);
            }
        });
        JPanel writePanel = new JPanel();
        writePanel.setBackground(Color.WHITE);
        writePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        writePanel.add(writeLabel,gbc);
        topPanel.add(writePanel);


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
        GridBagConstraints gbc1 = new GridBagConstraints();
        followPanel.add(followLabel,gbc1);
        topPanel.add(followPanel);


        JPanel midPanel = new JPanel();
        midPanel.setBackground(Color.WHITE);
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));

//        JPanel botPanel = new JPanel(new GridLayout(1, 3));
//        botPanel.add(new JButton("test button 1"));
//        botPanel.add(new JButton("test button 2"));
//        botPanel.add(new JButton("test button 3"));


        postService.findPosts(midPanel,userDao,con,false);

        // 중단 JScrollPane
        JScrollPane midScrollPane = new JScrollPane(midPanel);
        midScrollPane.setBorder(BorderFactory.createLineBorder(new Color(234, 232, 232, 173),1));


//        add(topPanel, "North");
        add(topContainer,"North");
        container.add(midScrollPane, BorderLayout.CENTER);
//        add(botPanel, "South");

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
