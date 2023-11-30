package twitter.GUI.designs;

import twitter.GUI.dao.ChildCommentUserDao;
import twitter.GUI.dao.CommentUserDao;

import javax.swing.*;
import java.awt.*;

public class CommentLeftPanel extends JPanel {
    public CommentUserDao commentUserDao;
    public CommentLeftPanel(CommentUserDao commentUserDao) {
        this.commentUserDao = commentUserDao;
        JLabel lbl = new ImageJLabel(commentUserDao.getProfile_image_url(), 40, 40);
        add(lbl);
        setBackground(Color.white);
    }
    public CommentLeftPanel(ChildCommentUserDao childCommentUserDao) {
        JLabel lbl = new ImageJLabel(childCommentUserDao.getProfile_image_url(), 40, 40);
        add(lbl);
        setBackground(Color.white);
    }
}
