package twitter.GUI.service;

import twitter.GUI.dao.PostInfoDao;
import twitter.GUI.dao.UserDao;
import twitter.GUI.designs.PostJPanel;
import twitter.GUI.repository.PostRepository;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

public class PostService {
    private final PostRepository postRepository = new PostRepository();

    public void findPosts(JPanel midPanel, UserDao user, Connection con) {
        ArrayList<PostInfoDao> result = new ArrayList<>();
        result = postRepository.findAllPostInfoByUser(user, con);
        if (result == null) {
            JLabel text = new JLabel("Start Twitter Now!");
            text.setFont(new Font("sansSerif", Font.BOLD, 26));
            midPanel.add(text);
        } else {
            for (PostInfoDao postInfo : result) {
//                String[] parts = null;
//                ArrayList<String> images = new ArrayList<>();
//                if (postInfo.getImage_urls() != null) {
//                    parts = postInfo.getImage_urls().split(",");
//                    images = new ArrayList<>(Arrays.asList(parts));
//                }
//                PostJPanel post = new PostJPanel(postInfo.getPost_content(), images, postInfo.getWriter_profile_img_url(), postInfo.getWriter_id());
                PostJPanel post = new PostJPanel(postInfo);
                midPanel.add(post);
            }
        }
    }
}
