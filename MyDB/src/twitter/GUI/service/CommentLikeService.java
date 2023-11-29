package twitter.GUI.service;

import twitter.GUI.dao.CommentLikeDao;
import twitter.GUI.dao.LikePostDao;
import twitter.GUI.repository.CommentLikeRepository;
import twitter.GUI.repository.LikePostRepository;

import javax.swing.*;
import java.sql.Connection;

public class CommentLikeService {
    CommentLikeRepository commentLikeRepository = new CommentLikeRepository();

    public Integer updateLike(Connection con, CommentLikeDao commentLikeDao){
        if(!commentLikeRepository.findLikeById(con, commentLikeDao)){
            JOptionPane.showMessageDialog(null, "Like This Post!", "Like Added", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Removed Like from this post", "Like Removed", JOptionPane.INFORMATION_MESSAGE);
        }
        return commentLikeRepository.getLikeCountById(con, commentLikeDao);
    }
}
