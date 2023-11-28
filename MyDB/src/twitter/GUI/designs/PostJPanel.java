package twitter.GUI.designs;

import twitter.GUI.dao.LikePostDao;
import twitter.GUI.dao.PostInfoDao;
import twitter.GUI.repository.LikePostRepository;
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
    private LikePostService likePostService = new LikePostService();

    public PostJPanel(PostInfoDao postInfoDao, Connection con) {
        super();
        /*
         * 게시물 정보 쿼리로 조회해서 넘겨 받으면 패널 안에 게시물 형태로 생성 시키기*/

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
        add(westP,"West");

        // center panel --> text, content images, id
        JPanel centerP = new JPanel();
        centerP.setBackground(Color.white);
        centerP.setLayout(new BoxLayout(centerP, BoxLayout.Y_AXIS));
        add(centerP,"Center");

        // profile image 생성
        ImageJLabel profileImg;
        profileImg = new ImageJLabel(Objects.requireNonNullElse(proUrl, "../TwitterLogo.png"), 40, 40);
        profileImg.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        profileImg.setVerticalAlignment(JLabel.TOP);
        westP.add(profileImg,"North");


        // id 생성
        var userId = new JLabel(id);
        userId.setFont(new Font("sansSerif",Font.BOLD,16));
        userId.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 10));
        JPanel idP = new JPanel();
        idP.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx  = 1;
        gbc.anchor = GridBagConstraints.WEST; // 왼쪽 정렬을 위한 anchor 설정
        idP.add(userId,gbc);
        idP.setBackground(Color.white);
        centerP.add(idP);


        // text 생성
        JTextArea text = new JTextArea(postText);

        text.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 10));
        text.setSize(new Dimension(350,text.getPreferredSize().height));
        text.setLineWrap(true);
        text.setEditable(false);
        centerP.add(text);

        // 이미지 생성
        for(String url : urls){
            JPanel p = new JPanel();
            p.setBackground(Color.WHITE);
            ImageJLabel contentImg = new ImageJLabel(url,150,150);
            p.add(contentImg);
            centerP.add(p);
        }

        // 하단 JPanel (좋아요, 댓글)
        // 댓글은 누르면 댓글 페이지로 전환
        JPanel bottomP = new JPanel();
        bottomP.setLayout(new GridLayout(1,2));
        JPanel likeP = new JPanel();
        likeP.setBackground(Color.white);
        JPanel commentP = new JPanel();
        commentP.setBackground(Color.white);
        bottomP.add(likeP);
        bottomP.add(commentP);
        centerP.add(bottomP);

        // 좋아요, 댓글 이미지
        /**
         * 미완성. 액션이벤트 추가
         */
        ImageJLabel like = new ImageJLabel("../Like.png", 20,20);
        JLabel likeCnt = new JLabel(postInfoDao.getPost_like_count().toString());
        like.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int newNo = likePostService.updateLike(con, new LikePostDao( postInfoDao.getUser_no(),postInfoDao.getId()));
                likeCnt.setText(Integer.toString(newNo));
            }
        });
        likeP.add(like);
        likeP.add(likeCnt);
        ImageJLabel comment = new ImageJLabel("../Comment.png", 20,20);
        comment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int newNo = likePostService.updateLike(con, new LikePostDao( postInfoDao.getUser_no(),postInfoDao.getId()));
                likeCnt.setText(Integer.toString(newNo));
            }
        });
        commentP.add(comment);

        // 테두리
        setBorder(BorderFactory.createLineBorder(new Color(234, 232, 232, 173), 1));
    }
}
