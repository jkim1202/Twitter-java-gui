package twitter.GUI.repository;

import twitter.GUI.dao.FollowDao;
import twitter.GUI.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserRepository {
    public UserDao findUserByIdAndPassword(Connection con, String input_id_or_email, String input_password) {
        ResultSet rs = null;
        PreparedStatement pstm = null;

        try {
            String sql = "SELECT * FROM USER WHERE (id = ? OR email = ?) AND password = ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, input_id_or_email);
            pstm.setString(2, input_id_or_email);
            pstm.setString(3, input_password);
            rs = pstm.executeQuery();

            if (rs.next()) {
                UserDao user = new UserDao(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7));
                System.out.println("user.getUser_no() = " + user.getUser_no());
                System.out.println("user.getId() = " + user.getId());
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public UserDao findUserByIdAndEmail(String input_id, String input_email,Connection con) {
        ResultSet rs = null;
        PreparedStatement pstm = null;

        try {
            String sql = "SELECT * FROM USER WHERE (id = ? AND email = ?)";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, input_id);
            pstm.setString(2, input_email);
            rs = pstm.executeQuery();

            if (rs.next()) {
                UserDao user = new UserDao(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7),rs.getString(8));
                System.out.println("user.getUser_no() = " + user.getUser_no());
                System.out.println("user.getId() = " + user.getId());
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserDao createUser(UserDao userDao, Connection con) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        PreparedStatement pstm1 = null;

        try {
            String findSql = "select id from user where id = ?";
            pstm = con.prepareStatement(findSql);
            pstm.setString(1, userDao.getId());
            rs = pstm.executeQuery();
            if (rs.next()) {
                return null;
            } else {
                String insertSql = "INSERT INTO USER (ID, PASSWORD, ANSWER, START_DATE, EMAIL) VALUES (?, ?, ?, ?, ?)";
                pstm1 = con.prepareStatement(insertSql);
                pstm1.setString(1, userDao.getId());
                pstm1.setString(2, userDao.getPassword());
                pstm1.setString(3, userDao.getAnswer());

                // Use setDate to set the date value
                pstm1.setDate(4, java.sql.Date.valueOf(LocalDate.now()));

                pstm1.setString(5, userDao.getEmail());

                int rowsInserted = pstm1.executeUpdate();
                if (rowsInserted > 0) {
                    return userDao;
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int changePassword(UserDao userDao, Connection con) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            String findSql = "select id from user where id = ? and email = ?";
            pstm = con.prepareStatement(findSql);
            pstm.setString(1, userDao.getId());
            pstm.setString(2, userDao.getEmail());
            rs = pstm.executeQuery();
            if (rs.next()) {
                String insertSql = "UPDATE USER SET PASSWORD = ? WHERE ID = ? AND EMAIL = ?";
                pstm = con.prepareStatement(insertSql);
                pstm.setString(1, userDao.getPassword());
                pstm.setString(2, userDao.getId());
                pstm.setString(3, userDao.getEmail());

                return pstm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<FollowDao> findFollowings(UserDao userDao, Connection con) {
        ArrayList<FollowDao> followings = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM FOLLOWING_INFO WHERE USER_NO = ? AND FOLLOWING_STATE = 1";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, userDao.getUser_no());
            rs = pstm.executeQuery();
            while (rs.next()) {
                FollowDao followDao = new FollowDao(rs.getInt("following_no"), rs.getString("user_id"), rs.getString("user_profile"), rs.getString("user_profile_image_url"));
                followings.add(followDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return followings;
        }
        return followings;
    }

    public ArrayList<FollowDao> findFollowers(UserDao userDao, Connection con) {
        ArrayList<FollowDao> followings = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM FOLLOWER_INFO WHERE MY_USER_NO = ? AND FOLLOWING_STATE = 1";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, userDao.getUser_no());
            rs = pstm.executeQuery();
            while (rs.next()) {
                FollowDao followDao = new FollowDao(rs.getInt("follower_no"), rs.getString("user_id"), rs.getString("user_profile"), rs.getString("user_profile_image_url"));
                followings.add(followDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return followings;
        }
        return followings;
    }

    public boolean removeFollower(UserDao userDao, FollowDao followDao, Connection con) {
        PreparedStatement pstm = null;
        try {
            String findSql = "delete from following where following_no = ? and user_no = ?";
            pstm = con.prepareStatement(findSql);
            pstm.setInt(1, userDao.getUser_no());
            pstm.setInt(2, followDao.getUser_no());
            int a = pstm.executeUpdate();
            return a > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeFollowing(UserDao userDao, FollowDao followDao, Connection con) {
        PreparedStatement pstm = null;
        try {
            String findSql = "delete from following where following_no = ? and user_no = ?";
            pstm = con.prepareStatement(findSql);
            pstm.setInt(1, followDao.getUser_no());
            pstm.setInt(2, userDao.getUser_no());
            int a = pstm.executeUpdate();
            return a > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int updateUser(UserDao userDao, Connection con) {
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            String findSql = "select * from user where user_no = ?";
            pstm = con.prepareStatement(findSql);
            pstm.setInt(1, userDao.getUser_no());
            rs = pstm.executeQuery();
            if (rs.next()) {
                String insertSql = "UPDATE USER SET PASSWORD = ?, ID = ?, EMAIL = ?, PROFILE = ?, PROFILE_IMAGE_URL = ? WHERE USER_NO = ?";
                pstm = con.prepareStatement(insertSql);
                pstm.setString(1, userDao.getPassword());
                pstm.setString(2, userDao.getId());
                pstm.setString(3, userDao.getEmail());
                pstm.setString(4, userDao.getProfile());
                pstm.setString(5, userDao.getProfile_image_url());
                pstm.setInt(6, userDao.getUser_no());

                return pstm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int createFollowing(String Id, UserDao userDao, Connection con) {
        int followingUserNo = getUserNoByUserId(Id,con);

        if (followingUserNo != -1) {
            if (!isFollowing(userDao.getUser_no(), followingUserNo,con)) {
                followUser(userDao.getUser_no(), followingUserNo,con);
                return 1; //성공
            } else {
                return 0; // 이미 존재
            }
        } else {
            return -1; // 없는 유저
        }
    }

    private static int getUserNoByUserId(String userId, Connection con) {
        String query = "SELECT user_no FROM user WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("user_no");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1; // User not found
    }

    private static boolean isFollowing(int followerUserNo, int followingUserNo, Connection con) {
        String query = "SELECT * FROM following WHERE USER_NO = ? AND FOLLOWING_NO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, followerUserNo);
            preparedStatement.setInt(2, followingUserNo);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // If result set is not empty, user is following
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Error or user is not following
    }

    private static void followUser(int followerUserNo, int followingUserNo,Connection con) {
        String query = "INSERT INTO following (FOLLOWING_NO, USER_NO, following_state) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, followingUserNo);
            preparedStatement.setInt(2, followerUserNo);
            preparedStatement.setInt(3, 1); // Assuming 1 represents an active follow
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
