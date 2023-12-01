package twitter.GUI.designs;

import twitter.GUI.dao.FollowDao;
import twitter.GUI.dao.UserDao;
import twitter.GUI.pages.FollowPage;
import twitter.GUI.repository.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class FollowPanel extends JPanel {
    public UserDao userDao;
    public FollowDao followDao;
    public FollowPanel(UserDao userDao, FollowDao followDao, Connection con) {
        super();
        this.userDao = userDao;
        this.followDao = followDao;
        setSize(new Dimension(350,70));
        setLayout(new BorderLayout());
        // Profile Image JLabel
        ImageJLabel profileImg = new ImageJLabel(followDao.getProfile_image_url(),40,40);
        profileImg.setHorizontalAlignment(JLabel.LEFT);

        // Container panels
        JPanel infoContainer = new JPanel();
        infoContainer.setBackground(Color.white);
        infoContainer.setLayout(new BoxLayout(infoContainer,BoxLayout.Y_AXIS));
        JPanel imgContainer = new JPanel();
        imgContainer.setBackground(Color.white);
        JPanel buttonContainer = new JPanel();
        buttonContainer.setBackground(Color.white);

        // Follower,Following user info JLabel
        JLabel userId = new JLabel("id: "+followDao.getId());
        JLabel userProfile = new JLabel(followDao.getProfile());
        userProfile.setFont(new Font("sans-serif",Font.PLAIN,12));

        JButton button = new  JButton("Unfollow");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = new UserRepository().removeFollowing(userDao,followDao,con);
                if(flag) {
                    JOptionPane.showMessageDialog(null, "Unfollow!", "Unfollowing", JOptionPane.INFORMATION_MESSAGE);
                    SwingUtilities.invokeLater(() -> {
                        FollowPage page = new FollowPage(userDao,con);
                    });
                    Container parent = button.getParent();
                    while(!(parent instanceof JFrame)) {
                        parent = parent.getParent();
                    }
                    ((JFrame) parent).dispose();
                }
            }
        });
        button.setSize(100,50);

        infoContainer.add(userId);
        infoContainer.add(userProfile);
        imgContainer.add(profileImg);
        buttonContainer.add(button);

        add(imgContainer,"West");
        add(infoContainer,"Center");

        add(buttonContainer,"East");
//        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    }

    public FollowPanel(UserDao userDao, FollowDao followDao, Connection con, boolean bool) {
        super();
        this.userDao = userDao;
        this.followDao = followDao;
        setSize(new Dimension(350,70));
        setLayout(new BorderLayout());
        // Profile Image JLabel
        ImageJLabel profileImg = new ImageJLabel(followDao.getProfile_image_url(),40,40);
        profileImg.setHorizontalAlignment(JLabel.LEFT);

        // Container panels
        JPanel infoContainer = new JPanel();
        infoContainer.setBackground(Color.white);
        infoContainer.setLayout(new BoxLayout(infoContainer,BoxLayout.Y_AXIS));
        JPanel imgContainer = new JPanel();
        imgContainer.setBackground(Color.white);
        JPanel buttonContainer = new JPanel();
        buttonContainer.setBackground(Color.white);

        // Follower,Following user info JLabel
        JLabel userId = new JLabel("id: "+followDao.getId());
        JLabel userProfile = new JLabel(followDao.getProfile());
        userProfile.setFont(new Font("sans-serif",Font.PLAIN,12));

        JButton button = new  JButton("Remove");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = new UserRepository().removeFollower(userDao,followDao,con);
                if(flag) {
                    JOptionPane.showMessageDialog(null, "Removed!", "Remove Follower", JOptionPane.INFORMATION_MESSAGE);
                    SwingUtilities.invokeLater(() -> {
                        FollowPage page = new FollowPage(userDao,con);
                    });
                    Container parent = button.getParent();
                    while(!(parent instanceof JFrame)) {
                        parent = parent.getParent();
                    }
                    ((JFrame) parent).dispose();
                }
            }
        });
        button.setSize(100,50);

        infoContainer.add(userId);
        infoContainer.add(userProfile);
        imgContainer.add(profileImg);
        buttonContainer.add(button);

        add(imgContainer,"West");
        add(infoContainer,"Center");

        add(buttonContainer,"East");
    }
}
