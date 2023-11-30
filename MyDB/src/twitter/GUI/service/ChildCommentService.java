package twitter.GUI.service;

import twitter.GUI.dao.ChildCommentUserDao;
import twitter.GUI.dao.CommentUserDao;
import twitter.GUI.designs.CommentLeftPanel;
import twitter.GUI.designs.CommentRightPanel;
import twitter.GUI.repository.ChildCommentRepository;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;

public class ChildCommentService {
    private final ChildCommentRepository childCommentRepository= new ChildCommentRepository();
    public boolean findAllChildCommentsByComment(JFrame frame, JPanel labels, JPanel fields, CommentUserDao commentUserDao, Connection con){
        ArrayList<ChildCommentUserDao> result = childCommentRepository.findAllByComment(commentUserDao, con);
        if(result.size()==0){
            return false;
        }
        for (ChildCommentUserDao childCommentUserDao : result){

            // 왼쪽 파트
            CommentLeftPanel j = new CommentLeftPanel(childCommentUserDao);
            labels.add(j);

            // 오른쪽 파트
            CommentRightPanel fieldsContainer = new CommentRightPanel(childCommentUserDao,frame, con);
            fields.add(fieldsContainer);

        }
        return true;
    }
    public void createChildComment(ChildCommentUserDao commentUserDao, Connection con){
        childCommentRepository.createChildComment(commentUserDao, con);
    }
}
