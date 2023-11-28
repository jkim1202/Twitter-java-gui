package twitter.GUI.repository;

import twitter.GUI.dao.PostInfoDao;
import twitter.GUI.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostRepository {
    public ArrayList<PostInfoDao> findAllPostInfoByUser(UserDao userDao, Connection con){
        ArrayList<PostInfoDao> result = new ArrayList<>();

        try {
            String setSqlModeQuery = "SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''))";

            try (PreparedStatement preparedStatement = con.prepareStatement(setSqlModeQuery)) {
                preparedStatement.executeUpdate();
                System.out.println("sql_mode set successfully");
            }
            String sql = "SELECT * FROM Post_Info WHERE user_no = ?";

            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, userDao.getUser_no());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        PostInfoDao postInfoDao = new PostInfoDao();
                        postInfoDao.setUser_no(resultSet.getInt("user_no"));
                        postInfoDao.setId(resultSet.getInt("id"));
                        postInfoDao.setWriter_id(resultSet.getString("writer_id"));
                        postInfoDao.setWriter_profile_img_url(resultSet.getString("writer_profile_img_url"));
                        postInfoDao.setPost_content(resultSet.getString("post_content"));
                        postInfoDao.setPost_view(resultSet.getInt("post_view"));
                        postInfoDao.setPost_time(resultSet.getDate("post_time"));
                        postInfoDao.setPost_like_count(resultSet.getInt("post_like_count"));
                        postInfoDao.setImage_urls(resultSet.getString("image_urls"));
                        result.add(postInfoDao);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
