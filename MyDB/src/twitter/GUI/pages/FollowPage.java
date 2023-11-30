package twitter.GUI.pages;

import twitter.GUI.dao.UserDao;
import twitter.GUI.designs.*;
import twitter.GUI.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.util.Map;
import java.util.Objects;

public class FollowPage extends JFrame {
    private final UserService userService = new UserService();
    public FollowPage(UserDao userDao, Connection con) throws HeadlessException {
        super();
        setLocationByPlatform(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FollowPage.this.dispose();
                MainPage mainPage = new MainPage(userDao, con);
                mainPage.setVisible(true);
            }
        });


        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        JPanel midPanel = new JPanel();
//        midPanel.setLayout(new BoxLayout(midPanel,BoxLayout.Y_AXIS));
        midPanel.setLayout(new GridLayout(0,1,1,1));

        // 중단 JScrollPane
        JScrollPane midScrollPane = new JScrollPane(midPanel);

        // 상단 JPanel
        JPanel topContainer = new JPanel(new GridLayout(2, 1));
        topContainer.setBorder(BorderFactory.createEmptyBorder(5,5,2,0));
        JPanel topNav = new JPanel(new GridLayout(1,2));
        topNav.setBackground(Color.white);
        JPanel leftP = new JPanel();
        JPanel searchP = new JPanel(new BorderLayout());
        searchP.setBackground(Color.white);

        IconTextField search = new IconTextField(new JTextField(),"Image","Search user to follow by ID");
        RoundJButton searchBut = new RoundJButton("Search",10,14);
        searchP.add(search,"Center");
        searchP.add(searchBut,"East");

        var leftT = new JLabel("Followings");
        Font font = leftT.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        leftT.setFont(font.deriveFont(attributes));
        leftT.setForeground(new Color(0,0,0).darker());
        leftP.add(leftT);
        leftT.setBackground(new Color(141, 141, 141));

        JPanel rightP = new JPanel();
        var rightT = new ActionJLabel("Followers",new Color(130,130,130));
        rightT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FollowPage.this.dispose();
                FollowPage page = new FollowPage(userDao,con,true);
                page.setVisible(true);
            }
        });
        rightP.add(rightT);
        topNav.add(leftP);
        topNav.add(rightP);

        topContainer.add(searchP);
        topContainer.add(topNav);

        container.add(topContainer,"North");
        container.add(midScrollPane,"Center");

        boolean flag = userService.findFollowings(midPanel,userDao, con);

        pack();
        Dimension d = getSize();
        int h = (int) d.getHeight();
        h = (Math.min(h, 400));
        Dimension shrinkHeight = new Dimension(400, h);
        if(flag) {
            setSize(shrinkHeight);
        } else{
            setSize(400,100);
            JLabel noFollowing = new JLabel("Start Following!");
            noFollowing.setHorizontalAlignment(SwingConstants.CENTER);
            noFollowing.setSize(400,300);
            container.add(noFollowing, "Center");
        }
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public FollowPage(UserDao userDao, Connection con, boolean bool) throws HeadlessException {
        super();
        setLocationByPlatform(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FollowPage.this.dispose();
                MainPage mainPage = new MainPage(userDao, con);
                mainPage.setVisible(true);
            }
        });


        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        JPanel midPanel = new JPanel();
//        midPanel.setLayout(new BoxLayout(midPanel,BoxLayout.Y_AXIS));
        midPanel.setLayout(new GridLayout(0,1,1,1));

        // 중단 JScrollPane
        JScrollPane midScrollPane = new JScrollPane(midPanel);

        // 상단 JPanel
        JPanel topContainer = new JPanel(new GridLayout(2,1));
        topContainer.setBorder(BorderFactory.createEmptyBorder(5,5,2,0));
        JPanel searchP = new JPanel(new BorderLayout());
        searchP.setBackground(Color.white);
        JPanel topNav = new JPanel(new GridLayout(1, 2));
        topNav.setBackground(Color.white);
        JPanel leftP = new JPanel();

        IconTextField search = new IconTextField(new JTextField(),"Image","Search user to follow by ID");
        RoundJButton searchBut = new RoundJButton("Search",10,14);
        searchP.add(search,"Center");
        searchP.add(searchBut,"East");

        var leftT = new ActionJLabel("Followings",new Color(130,130,130));
        leftP.add(leftT);
        leftT.setBackground(new Color(141, 141, 141));
        JPanel rightP = new JPanel();
        var rightT = new JLabel("Followers");
        Font font = rightT.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        rightT.setFont(font.deriveFont(attributes));
        rightT.setForeground(new Color(0,0,0).darker());
        leftT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FollowPage.this.dispose();
                FollowPage page = new FollowPage(userDao,con);
                page.setVisible(true);
            }
        });
        rightP.add(rightT);
        topNav.add(leftP);
        topNav.add(rightP);
        topContainer.add(searchP);
        topContainer.add(topNav);

        container.add(topContainer,"North");
        container.add(midScrollPane,"Center");

        boolean flag = userService.findFollowers(midPanel,userDao, con);

        pack();
        Dimension d = getSize();
        int h = (int) d.getHeight();
        h = (Math.min(h, 400));
        Dimension shrinkHeight = new Dimension(400, h);
        if(flag) {
            setSize(shrinkHeight);
        } else{
            setSize(400,100);
            JLabel noFollowing = new JLabel("Start Following!");
            noFollowing.setHorizontalAlignment(SwingConstants.CENTER);
            noFollowing.setSize(400,300);
            container.add(noFollowing, "Center");
        }
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null);
        setVisible(true);    }

}
