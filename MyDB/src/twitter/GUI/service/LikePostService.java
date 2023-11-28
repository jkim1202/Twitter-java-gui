package twitter.GUI.service;

import twitter.GUI.dao.LikePostDao;
import twitter.GUI.dao.UserDao;
import twitter.GUI.repository.LikePostRepository;

import javax.swing.*;
import java.sql.Connection;

public class LikePostService {
    LikePostRepository likePostRepository = new LikePostRepository();

    public Integer updateLike(Connection con, LikePostDao likePostDao){
        if(!likePostRepository.findLikeById(con, likePostDao)){
            JOptionPane.showMessageDialog(null, "Like This Post!", "Like Added", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Removed Like from this post", "Like Removed", JOptionPane.INFORMATION_MESSAGE);
        }
        return likePostRepository.getLikeCountByPostId(con, likePostDao.getPost_id());
    }
}
