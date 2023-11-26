package twitter.GUI.repository;

import twitter.GUI.dao.UserDao;

import javax.swing.*;
import java.sql.*;

public class UserRepository {
    public UserDao findUserByIdAndPassword(Connection con, String input_name, String input_password){
        Statement stmt = null;
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
}
