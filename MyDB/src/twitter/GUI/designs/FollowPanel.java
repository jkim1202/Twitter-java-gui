package twitter.GUI.designs;

import twitter.GUI.pages.LogInPage;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FollowPanel extends JPanel {
    public FollowPanel(String url, String id, String nickname) {
        super();
        setSize(new Dimension(350,70));
        setLayout(new BorderLayout());
        // Profile Image JLabel
        JLabel profileImg = new JLabel();
        ImageIcon icon = new ImageIcon(
                Objects.requireNonNull(LogInPage.class.getResource(url))
        );
        Image img = icon.getImage();
        Image updateImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon updateIcon = new ImageIcon(updateImg);
        profileImg.setIcon(updateIcon);
        profileImg.setHorizontalAlignment(JLabel.LEFT);

        // Container panels
        JPanel infoContainer = new JPanel();
        infoContainer.setLayout(new BoxLayout(infoContainer,BoxLayout.Y_AXIS));
        JPanel imgContainer = new JPanel();
        JPanel buttonContainer = new JPanel();

        // Follower,Following user info JLabel
        JLabel userId = new JLabel("id: "+id);
        JLabel userNickname = new JLabel("nickname: "+nickname);
//        userId.setSize(new Dimension(400, userId.getPreferredSize().height));

        JButton button = new  JButton("Unfollow");
        button.setSize(100,50);

        infoContainer.add(userId);
        infoContainer.add(userNickname);
        imgContainer.add(profileImg);
        buttonContainer.add(button);

        add(imgContainer,"West");
        add(infoContainer,"Center");

        add(buttonContainer,"East");
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    }
}
