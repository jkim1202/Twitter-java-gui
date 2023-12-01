package twitter.GUI.service;

import twitter.GUI.dao.PostDao;
import twitter.GUI.dao.PostInfoDao;
import twitter.GUI.dao.UserDao;
import twitter.GUI.designs.ImageJLabel;
import twitter.GUI.designs.PostJPanel;
import twitter.GUI.repository.PostRepository;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Objects;

public class PostService {
    private final PostRepository postRepository = new PostRepository();

    public void findPosts(JPanel midPanel, UserDao userDao, Connection con, boolean bool) {
        ArrayList<PostInfoDao> result;
        if (bool) {
            result = postRepository.findAllPostInfoByUser(userDao, con);
        }
        else {
            JPanel fixedP = new JPanel(new BorderLayout());
            JPanel westP = new JPanel();
            fixedP.add(westP,"East");
            JPanel centerP = new JPanel();
            centerP.setBackground(new Color(255,255,255));
            fixedP.add(centerP,"Center");
            westP.setBackground(Color.white);
            westP.setLayout(new BorderLayout());
            midPanel.add(fixedP);
            ImageJLabel profileImg;
            profileImg = new ImageJLabel("../Fixed2.png", 20, 20);
            profileImg.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            profileImg.setVerticalAlignment(JLabel.TOP);
            westP.add(profileImg, "North");
            result = postRepository.findAllPostByUser(userDao, con);
        }
        if (result == null) {
            JLabel text = new JLabel("Start Twitter Now!");
            text.setFont(new Font("sansSerif", Font.BOLD, 26));
            midPanel.add(text);
        }
        else {
            int i = 0;
            for (PostInfoDao postInfo : result) {
                PostJPanel post = new PostJPanel(postInfo, userDao, con,bool);
                midPanel.add(post);
                if (i == result.size() - 1 && i < 2) {
                    midPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 300, 0));
                }
                i++;
            }
        }
    }


    public void createPost(ArrayList<String> urls, PostDao postDao, Connection con) {
        postRepository.createPostById(urls, con, postDao);
    }
}
//    public void findMyPosts(JPanel midPanel, UserDao userDao, Connection con) {
//        ArrayList<PostInfoDao> result = new ArrayList<>();
//        result = postRepository.findAllPostByUser(userDao, con);
//        if (result == null) {
//            JLabel text = new JLabel("Start Twitter Now!");
//            text.setFont(new Font("sansSerif", Font.BOLD, 26));
//            midPanel.add(text);
//        } else {
//            int i = 0;
//            for (PostInfoDao postInfo : result) {
//                PostJPanel post = new PostJPanel(postInfo, userDao, con);
//                midPanel.add(post);
//                if (i == result.size() - 1 && i < 2 ) {
//                    midPanel.setBorder(BorderFactory.createEmptyBorder(0,0,300,0));
//                }
//                i++;
//            }
//        }
//    }
//}
