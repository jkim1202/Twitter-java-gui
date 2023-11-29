package twitter.GUI.repository;

import twitter.GUI.dao.CommentLikeDao;
import twitter.GUI.dao.LikePostDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentLikeRepository {
    public boolean findLikeById(Connection con, CommentLikeDao commentLikeDao) {
        boolean exists = false;
        try {
            // 좋아요 기록 확인
            String selectQuery = "SELECT * FROM comment_like WHERE user_user_no = ? AND comment_id = ?";
            try (PreparedStatement selectStatement = con.prepareStatement(selectQuery)) {
                selectStatement.setInt(1, commentLikeDao.getUser_no());
                selectStatement.setInt(2, commentLikeDao.getComment_id());

                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    exists = resultSet.next();
                }
            }

            // 좋아요 기록이 존재하면 삭제, 그렇지 않으면 생성
            if (exists) {
                deleteLike(con, commentLikeDao);
            } else {
                createLike(con, commentLikeDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    private void deleteLike(Connection con, CommentLikeDao commentLikeDao) {
        String deleteQuery = "DELETE FROM comment_like WHERE user_user_no = ? AND comment_id = ?";
        try (PreparedStatement deleteStatement = con.prepareStatement(deleteQuery)) {
            deleteStatement.setInt(1, commentLikeDao.getUser_no());
            deleteStatement.setInt(2, commentLikeDao.getComment_id());
            deleteStatement.executeUpdate();
            System.out.println("Like record deleted successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void createLike(Connection con, CommentLikeDao commentLikeDao) {
        String insertQuery = "INSERT INTO comment_like (user_user_no, comment_id) VALUES (?, ?)";
        try (PreparedStatement insertStatement = con.prepareStatement(insertQuery)) {
            insertStatement.setInt(1, commentLikeDao.getUser_no());
            insertStatement.setInt(2, commentLikeDao.getComment_id());
            insertStatement.executeUpdate();
            System.out.println("Like record created successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int getLikeCountById(Connection con, CommentLikeDao commentLikeDao) {
        int likeCount = 0;

        try {
            String selectQuery = "SELECT COUNT(*) AS like_count FROM comment_like WHERE comment_id = ?";
            try (PreparedStatement selectStatement = con.prepareStatement(selectQuery)) {
                selectStatement.setInt(1, commentLikeDao.getComment_id());

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
