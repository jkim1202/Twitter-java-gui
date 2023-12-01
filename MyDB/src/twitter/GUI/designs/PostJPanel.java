package twitter.GUI.designs;

import twitter.GUI.dao.LikePostDao;
import twitter.GUI.dao.PostInfoDao;
import twitter.GUI.dao.UserDao;
import twitter.GUI.pages.CommentPage;
import twitter.GUI.pages.MainPage;
import twitter.GUI.repository.PostRepository;
import twitter.GUI.service.LikePostService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PostJPanel extends JPanel {
    private PostInfoDao postInfoDao;
    private LikePostService likePostService = new LikePostService();

    public PostJPanel(PostInfoDao postInfoDao, UserDao user, Connection con, boolean bool) {
        super();
        this.postInfoDao = postInfoDao;

        String postText = postInfoDao.getPost_content();
        String[] parts;
        ArrayList<String> urls = new ArrayList<>();
        if (postInfoDao.getImage_urls() != null) {
            parts = postInfoDao.getImage_urls().split(",");
            urls = new ArrayList<>(Arrays.asList(parts));
        }
        String proUrl = postInfoDao.getWriter_profile_img_url();
        String id = postInfoDao.getWriter_id();


        setBackground(Color.white);
        setLayout(new BorderLayout());

        // west panel --> profile image
        JPanel westP = new JPanel();
        westP.setBackground(Color.white);
        westP.setLayout(new BorderLayout());
        add(westP, "West");

        // center panel --> text, content images, id
        JPanel centerP = new JPanel();
        centerP.setBackground(Color.white);
        centerP.setLayout(new BoxLayout(centerP, BoxLayout.Y_AXIS));
        add(centerP, "Center");

        // profile image 생성
        ImageJLabel profileImg;
        profileImg = new ImageJLabel(Objects.requireNonNullElse(proUrl, "../defaultProfile.png"), 40, 40);
        profileImg.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        profileImg.setVerticalAlignment(JLabel.TOP);
        westP.add(profileImg, "North");


        // id 생성
        var userId = new JLabel(id);
        userId.setFont(new Font("sansSerif", Font.BOLD, 16));
        userId.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 10));
        JPanel idP = new JPanel();
        idP.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST; // 왼쪽 정렬을 위한 anchor 설정
        idP.add(userId, gbc);
        idP.setBackground(Color.white);
        centerP.add(idP);


        // text 생성
        JTextArea text = new JTextArea(postText);

        text.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 10));
        text.setSize(new Dimension(350, text.getPreferredSize().height));
        text.setLineWrap(true);
        text.setEditable(false);
        centerP.add(text);

        // 이미지 생성
        for (String url : urls) {
            JPanel p = new JPanel();
            p.setBackground(Color.WHITE);
            ImageJLabel contentImg = null;
            contentImg = new ImageJLabel(Objects.requireNonNullElse(url, "../P2.png"), 150, 150);
            p.add(contentImg);
            centerP.add(p);
        }

        // 하단 JPanel (좋아요, 댓글, view)
        // 댓글은 누르면 댓글 페이지로 전환
        JPanel bottomP = new JPanel();
        bottomP.setBackground(Color.white);
        if(bool){
            bottomP.setLayout(new GridLayout(1, 3));
        }
        else {
            bottomP.setLayout(new GridLayout(1, 4));
        }

        bottomP.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JPanel fixP = new JPanel();
        fixP.setBackground(Color.white);
        JPanel likeP = new JPanel();
        likeP.setBackground(Color.white);
        JPanel commentP = new JPanel();
        commentP.setBackground(Color.white);
        JPanel viewP = new JPanel();
        viewP.setBackground(Color.white);
        bottomP.add(likeP);
        bottomP.add(commentP);
        if(!bool){
            bottomP.add(fixP);
        }
        bottomP.add(viewP);
        centerP.add(bottomP);

        // 좋아요, 댓글 이미지, view
        /**
         * 미완성. 코멘트 액션이벤트 추가
         */
        // like 있는지 확인
        // 있으면 빨간하트
        // 없으면 빈하트
        ImageJLabel like = new ImageJLabel("../Like.png", 20, 20);
        JLabel likeCnt = new JLabel(postInfoDao.getPost_like_count().toString());
        like.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int newNo = likePostService.updateLike(con, new LikePostDao(user.getUser_no(), postInfoDao.getId()));
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
                int newNo = likePostService.updateLike(con, new LikePostDao(user.getUser_no(), postInfoDao.getId()));
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
        ImageJLabel comment = new ImageJLabel("../Comment.png", 20, 20);
        comment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(PostJPanel.this);
                if (frame != null) {
                    frame.setVisible(false);
                    CommentPage commentPage =   new CommentPage(postInfoDao,user,con);
                    commentPage.setVisible(true);
                }
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
        commentP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(PostJPanel.this);
                if (frame != null) {
                    frame.setVisible(false);
                    CommentPage commentPage =   new CommentPage(postInfoDao,user, con);
                    commentPage.setVisible(true);
                }
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
        commentP.add(comment);
        ImageJLabel fix = new ImageJLabel("../Fixing.png", 20, 20);
        fix.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(PostJPanel.this);
                if (frame != null) {
                    new PostRepository().updateFixed(postInfoDao.getId(),con);
                    frame.dispose();
                    MainPage page = new MainPage(user,con,false);
                    page.setVisible(true);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                fixP.setBackground(Color.white.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                fixP.setBackground(Color.white);
            }
        });
        fixP.add(fix);
        commentP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(PostJPanel.this);
                if (frame != null) {
                    frame.setVisible(false);
                    CommentPage commentPage =   new CommentPage(postInfoDao,user, con);
                    commentPage.setVisible(true);
                }
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
        commentP.add(comment);
        JLabel view = new JLabel("View: " + postInfoDao.getPost_view());
        viewP.add(view);

        // 테두리
        setBorder(BorderFactory.createLineBorder(new Color(234, 232, 232, 173), 1));
    }
}
