package twitter.GUI.repository;

import twitter.GUI.dao.ChildCommentUserDao;
import twitter.GUI.dao.CommentUserDao;

import java.sql.*;
import java.util.ArrayList;

public class ChildCommentRepository {
    public ArrayList<ChildCommentUserDao> findAllByComment(CommentUserDao commentUserDao, Connection con){
        String sql = "SELECT * FROM CHILD_COMMENT_USER WHERE COMMENT_ID = ?";
        ArrayList<ChildCommentUserDao> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, commentUserDao.getComment_id());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ChildCommentUserDao childCommentUserDao = new ChildCommentUserDao();
                    childCommentUserDao.setId(resultSet.getInt("child_comment_id"));
                    childCommentUserDao.setText(resultSet.getString("child_comment_text"));
                    childCommentUserDao.setUser_user_no(resultSet.getInt("user_user_no"));
                    childCommentUserDao.setComment_id(resultSet.getInt("comment_id"));
                    childCommentUserDao.setUser_id(resultSet.getString("id"));
                    childCommentUserDao.setNickname(resultSet.getString("nickname"));
                    childCommentUserDao.setProfile_image_url(resultSet.getString("profile_image_url"));

                    result.add(childCommentUserDao);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return result;
    }
    public void createChildComment(ChildCommentUserDao childCommentUserDao, Connection con){
        try {
            // Create a new post in the database
            // todo: 데이터베이스 attribute 오타 수정 필요
            String createPostQuery = "INSERT INTO child_comment (child_commnet_text, User_user_no, comment_id) VALUES (?, ?, ?)";
            try (PreparedStatement createPostStatement = con.prepareStatement(createPostQuery, Statement.RETURN_GENERATED_KEYS)) {
                createPostStatement.setString(1, childCommentUserDao.getText());
                createPostStatement.setInt(2, childCommentUserDao.getUser_user_no());
                createPostStatement.setInt(3, childCommentUserDao.getComment_id());
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
