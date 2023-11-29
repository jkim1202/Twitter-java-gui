package twitter.GUI.repository;

import twitter.GUI.dao.CommentUserDao;
import twitter.GUI.dao.PostInfoDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentRepository {
    public ArrayList<CommentUserDao> findAllByPost(PostInfoDao postInfoDao, Connection con){
        String sql = "SELECT * FROM COMMENT_USER WHERE POST_ID = ?";
        ArrayList<CommentUserDao> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, postInfoDao.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CommentUserDao commentUserDao = new CommentUserDao();
                    commentUserDao.setComment_id(resultSet.getInt("comment_id"));
                    commentUserDao.setComment_text(resultSet.getString("comment_text"));
                    commentUserDao.setComment_like_cnt(resultSet.getInt("comment_like_cnt"));
                    commentUserDao.setUser_no(resultSet.getInt("user_no"));
                    commentUserDao.setId(resultSet.getString("id"));
                    commentUserDao.setPost_id(resultSet.getInt("post_id"));
                    commentUserDao.setUser_user_no(resultSet.getInt("user_user_no"));
                    commentUserDao.setProfile_image_url(resultSet.getString("profile_image_url"));

                    result.add(commentUserDao);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }
}
