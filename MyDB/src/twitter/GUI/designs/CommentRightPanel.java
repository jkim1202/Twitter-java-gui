package twitter.GUI.designs;

import twitter.GUI.dao.*;
import twitter.GUI.pages.ChildCommentPage;
import twitter.GUI.service.CommentLikeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class CommentRightPanel extends JPanel {
    private final CommentLikeService commentLikeService = new CommentLikeService();
    public CommentUserDao commentUserDao;
    public CommentRightPanel(PostInfoDao postInfoDao, CommentUserDao commentUserDao, JFrame frame, Connection con) {
        this.commentUserDao = commentUserDao;
        setLayout(new BorderLayout());

        // comment user ID JLabel
        JPanel idLabelP = new JPanel(new BorderLayout());
        idLabelP.setBackground(Color.white);
        idLabelP.setBorder(BorderFactory.createEmptyBorder(5,5,0,0));
        JLabel idLabel = new JLabel(commentUserDao.getId());
        idLabelP.add(idLabel,"West");
        idLabel.setFont(new Font("sansSerif",Font.BOLD,12));


        // comment text JTextArea
        JTextArea eq_Fields = new JTextArea(1,30);
        eq_Fields.setLineWrap(true);
        eq_Fields.setText(commentUserDao.getComment_text());
        eq_Fields.setEditable(false);


        JPanel t = new JPanel();
        t.setBackground(Color.white);
        t.setLayout(new FlowLayout());
        t.add(eq_Fields);

        // 버튼 panel
        var butsPanel = new JPanel(new FlowLayout());
        butsPanel.setBackground(Color.white);

        // like 이미지
        JPanel t2 = new JPanel(new GridLayout(0, 1, 0, 2));
        JPanel likeP = new JPanel();
        likeP.setBackground(Color.white);
        ImageJLabel like = new ImageJLabel("../Like.png", 20, 20);
        JLabel likeCnt = new JLabel(Integer.toString(commentUserDao.getComment_like_cnt()));
        like.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int newNo = commentLikeService.updateLike(con, new CommentLikeDao(postInfoDao.getUser_no(),commentUserDao.getComment_id()));
                likeCnt.setText(Integer.toString(newNo));
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                likeP.setBackground(Color.white.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                likeP.setBackground(Color.white);
            }
        });
        likeP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int newNo = commentLikeService.updateLike(con, new CommentLikeDao(postInfoDao.getUser_no(),commentUserDao.getComment_id()));
                likeCnt.setText(Integer.toString(newNo));
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                likeP.setBackground(Color.white.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                likeP.setBackground(Color.white);
            }
        });

        likeP.add(like);
        likeP.add(likeCnt);
        butsPanel.add(likeP);

        // comment 이미지
        var commentP = new JPanel();
        commentP.setBackground(Color.white);
        commentP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                ChildCommentPage childCommentPage = new ChildCommentPage(postInfoDao.getUser_no(),frame, commentUserDao, con);
                childCommentPage.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                commentP.setBackground(Color.white.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                commentP.setBackground(Color.white);
            }
        });
        var comment = new ImageJLabel("../Comment.png",20,20);
        commentP.add(comment);
        butsPanel.add(commentP);
        t.add(t2);

        add(idLabelP, "North");
        add(t,"Center");
        add(butsPanel, "South");
    }

    public CommentRightPanel(ChildCommentUserDao childCommentUserDao, JFrame frame, Connection con) {
        setLayout(new BorderLayout());

        // comment user ID JLabel
        JPanel idLabelP = new JPanel(new BorderLayout());
        idLabelP.setBackground(Color.white);
        idLabelP.setBorder(BorderFactory.createEmptyBorder(5,5,0,0));
        JLabel idLabel = new JLabel(childCommentUserDao.getUser_id());
        idLabelP.add(idLabel,"West");
        idLabel.setFont(new Font("sansSerif",Font.BOLD,12));


        // comment text JTextArea
        JTextArea eq_Fields = new JTextArea(1,30);
        eq_Fields.setLineWrap(true);
        eq_Fields.setText(childCommentUserDao.getText());
        eq_Fields.setEditable(false);


        JPanel t = new JPanel();
        t.setBackground(Color.white);
        t.setLayout(new FlowLayout());
        t.add(eq_Fields);

        add(idLabelP, "North");
        add(t,"Center");
    }
}
