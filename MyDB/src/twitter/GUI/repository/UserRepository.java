package twitter.GUI.repository;

import twitter.GUI.dao.UserDao;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class UserRepository {
    public UserDao findUserByIdAndPassword(Connection con, String input_name, String input_password){
        ResultSet rs = null;
        PreparedStatement pstm = null;

        try{
            String sql = "SELECT * FROM USER WHERE id = ? AND password = ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1,input_name);
            pstm.setString(2, input_password);
            rs = pstm.executeQuery();

            if(rs.next()){
                UserDao user = new UserDao(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7));
                return user;
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public UserDao createUser(UserDao userDao, Connection con){
        ResultSet rs = null;
        PreparedStatement pstm = null;
        PreparedStatement pstm1= null;

        try{
            String findSql = "select id from user where id = ?";
            pstm = con.prepareStatement(findSql);
            pstm.setString(1,userDao.getUser_id());
            rs = pstm.executeQuery();
            if (rs.next()){
                return null;
            }
            else {
                String insertSql = "INSERT INTO USER (ID, PASSWORD, NICKNAME, START_DATE, EMAIL) VALUES (?, ?, ?, ?, ?)";
                pstm1 = con.prepareStatement(insertSql);
                pstm1.setString(1, userDao.getUser_id());
                pstm1.setString(2, userDao.getUser_password());
                pstm1.setString(3, userDao.getUser_nickname());

                // Use setDate to set the date value
                pstm1.setDate(4, java.sql.Date.valueOf(LocalDate.now()));

                pstm1.setString(5, userDao.getUser_email());

                int rowsInserted = pstm1.executeUpdate();
                if(rowsInserted > 0){
                    return userDao;
                }
                else{
                    return null;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
