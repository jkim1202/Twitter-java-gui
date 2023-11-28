package twitter.GUI.repository;

import twitter.GUI.dao.LikePostDao;
import twitter.GUI.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikePostRepository {
    public boolean findLikeById(Connection con, LikePostDao likePostDao) {
        boolean exists = false;

        try {
            // 좋아요 기록 확인
            String selectQuery = "SELECT * FROM like_post WHERE user_user_no = ? AND post_id = ?";
            try (PreparedStatement selectStatement = con.prepareStatement(selectQuery)) {
                selectStatement.setInt(1, likePostDao.getUser_user_no());
                selectStatement.setInt(2, likePostDao.getPost_id());

                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    exists = resultSet.next();
                }
            }

            // 좋아요 기록이 존재하면 삭제, 그렇지 않으면 생성
            if (exists) {
                deleteLike(con, likePostDao);
            } else {
                createLike(con, likePostDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    private void deleteLike(Connection con, LikePostDao likePostDao) throws SQLException {
        String deleteQuery = "DELETE FROM like_post WHERE user_user_no = ? AND post_id = ?";
        try (PreparedStatement deleteStatement = con.prepareStatement(deleteQuery)) {
            deleteStatement.setInt(1, likePostDao.getUser_user_no());
            deleteStatement.setInt(2, likePostDao.getPost_id());
            deleteStatement.executeUpdate();
            System.out.println("Like record deleted successfully");
        }
    }

    private void createLike(Connection con, LikePostDao likePostDao) throws SQLException {
        String insertQuery = "INSERT INTO like_post (user_user_no, post_id) VALUES (?, ?)";
        try (PreparedStatement insertStatement = con.prepareStatement(insertQuery)) {
            insertStatement.setInt(1, likePostDao.getUser_user_no());
            insertStatement.setInt(2, likePostDao.getPost_id());
            insertStatement.executeUpdate();
            System.out.println("Like record created successfully");
        }
    }

    public int getLikeCountByPostId(Connection con, int postId) {
        int likeCount = 0;

        try {
            String selectQuery = "SELECT COUNT(*) AS like_count FROM like_post WHERE post_id = ?";
            try (PreparedStatement selectStatement = con.prepareStatement(selectQuery)) {
                selectStatement.setInt(1, postId);

                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        likeCount = resultSet.getInt("like_count");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return likeCount;
    }
}
