package twitter.GUI.service;

import twitter.GUI.dao.CommentLikeDao;
import twitter.GUI.dao.CommentUserDao;
import twitter.GUI.dao.PostInfoDao;
import twitter.GUI.designs.ImageJLabel;
import twitter.GUI.repository.CommentRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;

public class CommentService {
    private final CommentRepository commentRepository = new CommentRepository();
    private final CommentLikeService commentLikeService = new CommentLikeService();
    public void findAllCommentsByPost(JPanel labels, JPanel fields, PostInfoDao postInfoDao, Connection con){
        ArrayList<CommentUserDao> result = commentRepository.findAllByPost(postInfoDao, con);

        for (CommentUserDao commentUserDao : result){
            // 오른쪽 JPanel
            var fieldsContainer = new JPanel(new BorderLayout());
            fields.add(fieldsContainer);

            // comment user profile image
            JLabel lbl = new ImageJLabel(commentUserDao.getProfile_image_url(), 40, 40);

            // comment user ID JLabel
            JPanel idLabelP = new JPanel(new BorderLayout());
            idLabelP.setBackground(Color.white);
            idLabelP.setBorder(BorderFactory.createEmptyBorder(5,5,0,0));
            JLabel idLabel = new JLabel(commentUserDao.getId());
            idLabelP.add(idLabel,"West");
            idLabel.setFont(new Font("sansSerif",Font.BOLD,12));


            // comment text JTextArea
            JTextArea eq_Fields = new JTextArea(4, 30);
            eq_Fields.setText(commentUserDao.getComment_text());
            eq_Fields.setEditable(false);

            JPanel j = new JPanel();
            j.setBackground(Color.white);
            j.add(lbl);

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
            labels.add(j);

            fieldsContainer.add(idLabelP, "North");
            fieldsContainer.add(t,"Center");
            fieldsContainer.add(butsPanel, "South");
        }
    }
}
