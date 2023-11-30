package twitter.GUI.service;

import twitter.GUI.dao.CommentLikeDao;
import twitter.GUI.dao.CommentUserDao;
import twitter.GUI.dao.PostInfoDao;
import twitter.GUI.dao.UserDao;
import twitter.GUI.designs.CommentLeftPanel;
import twitter.GUI.designs.CommentRightPanel;
import twitter.GUI.designs.ImageJLabel;
import twitter.GUI.pages.ChildCommentPage;
import twitter.GUI.repository.CommentRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;

public class CommentService {
    private final CommentRepository commentRepository = new CommentRepository();

    public boolean findAllCommentsByPost(JFrame frame,JPanel labels, JPanel fields, PostInfoDao postInfoDao,UserDao userDao, Connection con){
        ArrayList<CommentUserDao> result = commentRepository.findAllByPost(postInfoDao, con);
        if(result.size()==0){
            return false;
        }
        for (CommentUserDao commentUserDao : result){

            // 왼쪽 파트
            CommentLeftPanel j = new CommentLeftPanel(commentUserDao);
            labels.add(j);

            // 오른쪽 파트
            CommentRightPanel fieldsContainer = new CommentRightPanel(postInfoDao,commentUserDao,frame, con);
            fields.add(fieldsContainer);

        }
        return true;
    }
    public void createComment(CommentUserDao commentUserDao, Connection con) {
        commentRepository.createComment(commentUserDao, con);
    }
}
