package twitter.GUI.repository;

import twitter.GUI.dao.PostDao;
import twitter.GUI.dao.PostInfoDao;
import twitter.GUI.dao.UserDao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PostRepository {

    public ArrayList<PostInfoDao> findAllPostInfoByUser(UserDao userDao, Connection con) {
        ArrayList<PostInfoDao> result = new ArrayList<>();

        try {
            String setSqlModeQuery = "SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''))";

            try (PreparedStatement preparedStatement = con.prepareStatement(setSqlModeQuery)) {
                preparedStatement.executeUpdate();
                System.out.println("sql_mode set successfully");
            }
            String sql = "SELECT * FROM Post_Info WHERE user_no = ? ORDER BY ID DESC";

            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, userDao.getUser_no());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
//                        PostInfoDao postInfoDao = new PostInfoDao();
                        PostInfoDao postInfoDao = new PostInfoDao(
                                resultSet.getInt("user_no"),
                                resultSet.getString("writer_id"),
                                resultSet.getString("writer_profile_img_url"),
                                resultSet.getInt("id"),
                                resultSet.getString("post_content"),
                                1 + resultSet.getInt("post_view"),
                                resultSet.getDate("post_time"),
                                resultSet.getInt("user_user_no"),
                                resultSet.getInt("post_like_count"),
                                resultSet.getString("image_urls")
                        );
                        result.add(postInfoDao);
                    }
                    updatePostViews(result, con);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void updatePostViews(ArrayList<PostInfoDao> posts, Connection con) {
        try {
            String updateSql = "UPDATE Post SET post_view = post_view + 1 WHERE id = ?";

            try (PreparedStatement updateStatement = con.prepareStatement(updateSql)) {
                for (PostInfoDao post : posts) {
                    updateStatement.setInt(1, post.getId());
                    updateStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PostDao createPostById(ArrayList<String> urls, Connection con, PostDao postDao) {
        try {
            // Create a new post in the database
            String createPostQuery = "INSERT INTO post (post_content, User_user_no, post_view, post_time) VALUES (?, ?, 0, ?)";
            try (PreparedStatement createPostStatement = con.prepareStatement(createPostQuery, Statement.RETURN_GENERATED_KEYS)) {
                createPostStatement.setString(1, postDao.getContent());
                createPostStatement.setInt(2, postDao.getUser_user_no());
                createPostStatement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                int affectedRows = createPostStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating post failed, no rows affected.");
                }

                // Get the auto-generated post ID
                try (ResultSet generatedKeys = createPostStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        postDao.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating post failed, no ID obtained.");
                    }
                }
            }

            // Insert image URLs into the image table
            if (urls != null && !urls.isEmpty()) {
                String insertImageQuery = "INSERT INTO image (Image_url, Post_id) VALUES (?, ?)";
                try (PreparedStatement insertImageStatement = con.prepareStatement(insertImageQuery)) {
                    for (String url : urls) {
                        insertImageStatement.setString(1, url);
                        insertImageStatement.setInt(2, postDao.getId());
                        insertImageStatement.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return postDao;
    }

    // find all my post
    public ArrayList<PostInfoDao> findAllPostByUser(UserDao userDao, Connection con) {
        ArrayList<PostInfoDao> result = new ArrayList<>();
        String sql = "SELECT * FROM POST_URL WHERE USER_USER_NO = ? ORDER BY ID DESC";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, userDao.getUser_no());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                int i = 0;
                while (resultSet.next()) {
                    System.out.println("i = " + i++);
//                    PostInfoDao postInfoDao = new PostInfoDao();
                    PostInfoDao postInfoDao = new PostInfoDao(
                            resultSet.getInt("id"),
                            userDao.getId(),
                            userDao.getProfile_image_url(),
                            resultSet.getInt("ID"),
                            resultSet.getString("post_content"),
                            resultSet.getInt("post_view"),
                            resultSet.getDate("post_time"),
                            userDao.getUser_no(),
                            resultSet.getInt("post_like_count"),
                            resultSet.getString("image_urls")
                    );
                    postInfoDao.setPost_fixed(resultSet.getBoolean("post_fixed"));
                    if (postInfoDao.getPost_fixed())
                        result.add(0, postInfoDao);
                    else
                        result.add(postInfoDao);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return result;

    }

    public void updateFixed(Integer post_id, Connection con) {
        try {
            // Create a new post in the database
            String createPostQuery1 = "UPDATE POST SET POST_FIXED = 0 WHERE POST_FIXED = 1";
            Statement statement = con.createStatement();
            int affectedRows1 = statement.executeUpdate(createPostQuery1);
            if (affectedRows1 == 0) {
                System.out.println("unfix failed");
            }

            String createPostQuery = "UPDATE POST SET POST_FIXED = 1 WHERE ID = ?";
            PreparedStatement createPostStatement = con.prepareStatement(createPostQuery, Statement.RETURN_GENERATED_KEYS);
            createPostStatement.setInt(1, post_id);
            System.out.println("post_id = " + post_id);
            int affectedRows = createPostStatement.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("fix failed");
                throw new SQLException("Post Fix Failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

