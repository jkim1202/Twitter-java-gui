package twitter.GUI.designs;

import twitter.GUI.pages.LogInPage;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ImageJLabel extends JLabel {
    public ImageJLabel(String url, int width, int height) {
// 트위터 로고 JLabel
//        JLabel twitterLogo = new JLabel();
//        ImageIcon icon = new ImageIcon(
//                Objects.requireNonNull(LogInPage.class.getResource("../TwitterLogo.png"))
//        );
//        Image img = icon.getImage();
//        Image updateImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
//        ImageIcon updateIcon = new ImageIcon(updateImg);
//        twitterLogo.setIcon(updateIcon);

        super();
        ImageIcon icon = new ImageIcon(
                Objects.requireNonNull(LogInPage.class.getResource(url))
        );
        Image img = icon.getImage();
        Image updateImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon updateIcon = new ImageIcon(updateImg);
        setIcon(updateIcon);
    }
}
