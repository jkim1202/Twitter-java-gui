package twitter.GUI.repository;

import twitter.GUI.dao.CommentUserDao;
import twitter.GUI.dao.PostInfoDao;

import java.sql.*;
import java.time.LocalDate;
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
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return result;
    }
    public void createComment(CommentUserDao commentUserDao, Connection con){
        try {
            // Create a new post in the database
            String createPostQuery = "INSERT INTO comment (comment_text, User_user_no, post_id) VALUES (?, ?, ?)";
            try (PreparedStatement createPostStatement = con.prepareStatement(createPostQuery, Statement.RETURN_GENERATED_KEYS)) {
                createPostStatement.setString(1, commentUserDao.getComment_text());
                createPostStatement.setInt(2, commentUserDao.getUser_user_no());
                createPostStatement.setInt(3, commentUserDao.getPost_id());
                int affectedRows = createPostStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating post failed, no rows affected.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
