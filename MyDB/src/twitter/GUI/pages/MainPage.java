package twitter.GUI.pages;

import twitter.GUI.dao.UserDao;
import twitter.GUI.designs.ActionJLabel;
import twitter.GUI.designs.ImageJLabel;
import twitter.GUI.service.PostService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.util.Map;

public class MainPage extends JFrame {
    private final PostService postService = new PostService();
    public MainPage(UserDao userDao, Connection con) throws HeadlessException {
        super("Twitter Main");
        setTitle("Twitter Main");

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int flag = JOptionPane.showConfirmDialog(null, "Are You Sure To Exit Twitter?","Confirm", JOptionPane.OK_CANCEL_OPTION);
                if(flag == 0){
                    System.exit(0);
                }
            }
        });

        Container container = getContentPane();

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
        leftP.setBackground(Color.white);
        rightP.setBackground(Color.white);
        topNav.add(leftP);
        topNav.add(rightP);

        // 좌측 상단 profile image
        String imageURL = "../defaultProfile.png";
        ImageJLabel userProfileImage;
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
                AccountPage accountPage = new AccountPage(userDao,con);
                accountPage.setVisible(true);
            }
        });

        ImageJLabel writeLabel = new ImageJLabel("../Add2.png",40,40);
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
        ImageJLabel followLabel = new ImageJLabel("../Follow3.png",40,40);
        followLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                FollowPage followPage = new FollowPage(userDao,con);
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

        postService.findPosts(midPanel,userDao,con,true);

        // 중단 JScrollPane
        JScrollPane midScrollPane = new JScrollPane(midPanel);
        midScrollPane.setBorder(BorderFactory.createLineBorder(new Color(234, 232, 232, 173),1));

        add(topContainer,"North");
        container.add(midScrollPane, BorderLayout.CENTER);

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
        leftP.setBackground(Color.white);
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
        rightP.setBackground(Color.white);
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
                AccountPage accountPage = new AccountPage(userDao,con);
                accountPage.setVisible(true);
            }
        });

        ImageJLabel writeLabel = new ImageJLabel("../Add2.png",40,40);
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
        ImageJLabel followLabel = new ImageJLabel("../Follow3.png",40,40);
        followLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                FollowPage followPage = new FollowPage(userDao, con);
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

        postService.findPosts(midPanel,userDao,con,false);

        // 중단 JScrollPane
        JScrollPane midScrollPane = new JScrollPane(midPanel);
        midScrollPane.setBorder(BorderFactory.createLineBorder(new Color(234, 232, 232, 173),1));

        add(topContainer,"North");
        container.add(midScrollPane, BorderLayout.CENTER);

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
