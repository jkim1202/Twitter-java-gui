package twitter;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Twitter {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        // Connect to database
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/mydb";
            String user = "root", passwd = "Wjdrmf12!@";
            con = DriverManager.getConnection(url, user, passwd);
            System.out.println(con);
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        Statement stmt = null;
        ResultSet rs = null;

        // show all users in the database
        try {
            stmt = con.createStatement();
            String sql = "select * from user";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString(2);

                if (rs.wasNull()) id = "null";
                String password = rs.getString(3);

                if (rs.wasNull()) password = "null";
                System.out.printf("ID: %15s PW: %15s\n", id, password);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        stmt = null;
        ResultSet userRS = null;
        PreparedStatement pstm = null;

        // log in/ sign up
        try {
            stmt = con.createStatement();
            int op1;
            String id = null;
            String pw = null;


            while (true) {
                System.out.println("Input 0 to sign in, 1 to log in, 2 to quit");
                op1 = keyboard.nextInt();
                if (op1 == 2) break;
                else if (op1 == 0) {
                    String s1 = null;

                    System.out.println("Input userid/ password");
                    id = keyboard.next();
                    pw = keyboard.next();

                    stmt = con.createStatement();
                    String s2 = "select id from user where id = \"" + id + "\"";
                    userRS = stmt.executeQuery(s2);

                    if (userRS.next()) {
                        System.out.println("User name already exists. Please try again!");
                    } else {
                        LocalDate date = LocalDate.now();
                        s1 = "insert into user (id, password, start_date) values (\'" + id + "\', \'" + pw + "\', \'" + date + "\')";
                        pstm = con.prepareStatement(s1);
                        pstm.executeUpdate();
                    }
                } else if (op1 == 1) {
                    System.out.println("Input userid / password");
                    id = keyboard.next();
                    pw = keyboard.next();

                    stmt = con.createStatement();
                    String s1 = "select * from user where id = \"" + id + "\"and password = \"" + pw + "\"";
                    userRS = stmt.executeQuery(s1);

                    if (userRS.next()) {
                        System.out.println("Logged in!");
                        break;
                    } else {
                        System.out.println("wrong id/password. Please log in again.");
                    }
                }

            }
            if (op1 == 1) {
                while (true) {
                    System.out.println("0 to write post, 1 to write comment, 3 to like post, 4 to like comment," +
                            " 5 to see my followers, 6 to see my followings, 7 to follow someone");
                    int option = keyboard.nextInt();
                    if (option == 0) {
                        //  input "post content"
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Enter Post content: ");
                        String postContent = scanner.nextLine();

                        // SQL INSERT Query
                        String sql = "INSERT INTO post (post_content, post_view, User_user_no) VALUES (?, 0, ?)";

                        PreparedStatement preparedStatement = con.prepareStatement(sql);
                        preparedStatement.setString(1, postContent);
                        preparedStatement.setInt(2, userRS.getInt("user_no"));

                        // SQL execute
                        int rowsInserted = preparedStatement.executeUpdate();

                        if (rowsInserted > 0) {
                            System.out.println("new post has been inserted successfully.");
                        } else {
                            System.out.println("Error.");
                        }
                        // close
                        preparedStatement.close();
                    } else if (option == 1) {
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Enter Post ID: ");
                        int postID = scanner.nextInt();

                        System.out.print("Enter comment content: ");
                        String commentText = scanner.nextLine();

                        // SQL INSERT 문 작성
                        String sql = "INSERT INTO comment (comment_text, User_user_no, Post_id) VALUES (?, ?, ?)";

                        PreparedStatement preparedStatement = con.prepareStatement(sql);
                        preparedStatement.setString(1, commentText);
                        preparedStatement.setInt(2, userRS.getInt("user_no"));
                        preparedStatement.setInt(3, postID);

                        // SQL 문 실행
                        int rowsInserted = preparedStatement.executeUpdate();

                        if (rowsInserted > 0) {
                            System.out.println("New comment has been inserted successfully.");
                        } else {
                            System.out.println("Error.");
                        }
                    } else if (option == 3) {
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Post ID to like: ");
                        int postID = scanner.nextInt();

                        // check if exist already
                        String selectSQL = "SELECT * FROM like_post WHERE Post_id = ? AND User_user_no = ?";
                        PreparedStatement selectStatement = con.prepareStatement(selectSQL);
                        selectStatement.setInt(1, postID);
                        selectStatement.setInt(2, userRS.getInt("user_no"));

                        ResultSet resultSet = selectStatement.executeQuery();

                        if (resultSet.next()) {
                            System.out.println("Liked already.");
                        } else {
                            // insert like_post
                            String insertSQL = "INSERT INTO like_post (User_user_no, Post_id) VALUES (?, ?)";
                            PreparedStatement insertStatement = con.prepareStatement(insertSQL);
                            insertStatement.setInt(1, userRS.getInt("user_no"));
                            insertStatement.setInt(2, postID);

                            int rowsInserted = insertStatement.executeUpdate();

                            if (rowsInserted > 0) {
                                System.out.println("Like success.");
                            } else {
                                System.out.println("Error.");
                            }

                            insertStatement.close();
                        }
                    } else if (option == 4) {
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Enter Comment ID: ");
                        int commentID = scanner.nextInt();

                        // 이미 좋아요를 눌렀는지 확인
                        String selectSQL = "SELECT * FROM comment_like WHERE Comment_id = ? AND User_user_no = ?";
                        PreparedStatement selectStatement = con.prepareStatement(selectSQL);
                        selectStatement.setInt(1, commentID);
                        selectStatement.setInt(2, userRS.getInt("user_no"));

                        ResultSet resultSet = selectStatement.executeQuery();

                        if (resultSet.next()) {
                            System.out.println("Liked already.");
                        } else {
                            // 좋아요 추가
                            String insertSQL = "INSERT INTO comment_like (User_user_no, Comment_id) VALUES (?, ?)";
                            PreparedStatement insertStatement = con.prepareStatement(insertSQL);
                            insertStatement.setInt(1, userRS.getInt("user_no"));
                            insertStatement.setInt(2, commentID);

                            int rowsInserted = insertStatement.executeUpdate();

                            if (rowsInserted > 0) {
                                System.out.println("Add like comment.");
                            } else {
                                System.out.println("Error.");
                            }

                            insertStatement.close();
                        }
                    } else if (option == 5) {
                        // SQL SELECT 문 작성
                        String sql = "SELECT U.id FROM following F " +
                                "INNER JOIN user U ON F.user_follower_no = U.user_no " +
                                "WHERE F.user_following_no = ? and F.following_state = 1";

                        PreparedStatement preparedStatement = con.prepareStatement(sql);
                        preparedStatement.setInt(1, userRS.getInt("user_no"));

                        // SQL 문 실행 및 결과 가져오기
                        ResultSet resultSet = preparedStatement.executeQuery();

                        System.out.println("Follower list:");

                        while (resultSet.next()) {
                            String userID = resultSet.getString("id");
                            System.out.println(userID);
                        }

                    } else if (option == 6) {
                        // SQL SELECT 문 작성
                        String sql = "SELECT U.id FROM following F " +
                                "INNER JOIN user U ON F.user_following_no = U.user_no " +
                                "WHERE F.user_follower_no = ? and F.following_state = 1";

                        PreparedStatement preparedStatement = con.prepareStatement(sql);
                        preparedStatement.setInt(1, userRS.getInt("user_no"));

                        // SQL 문 실행 및 결과 가져오기
                        ResultSet resultSet = preparedStatement.executeQuery();

                        System.out.println("Following list:");

                        while (resultSet.next()) {
                            String userID = resultSet.getString("id");
                            System.out.println(userID);
                        }
                    } else if (option == 7) {
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Enter user_following_no: ");
                        int followingUserID = scanner.nextInt();

                        // 이미 팔로우 관계가 있는지 확인
                        String selectSQL = "SELECT * FROM following WHERE user_follower_no = ? AND user_following_no = ?";

                        PreparedStatement selectStatement = con.prepareStatement(selectSQL);
                        selectStatement.setInt(1, userRS.getInt("user_no"));
                        selectStatement.setInt(2, followingUserID);

                        ResultSet resultSet = selectStatement.executeQuery();

                        if (resultSet.next()) {
                            System.out.println("Already following.");
                        } else {
                            // 팔로우 관계 추가
                            String insertSQL = "INSERT INTO following (user_follower_no, user_following_no, following_state) VALUES (?, ?, 1)";
                            PreparedStatement insertStatement = con.prepareStatement(insertSQL);
                            insertStatement.setInt(1, userRS.getInt("user_no"));
                            insertStatement.setInt(2, followingUserID);

                            int rowsInserted = insertStatement.executeUpdate();

                            if (rowsInserted > 0) {
                                System.out.println("Following success.");
                            } else {
                                System.out.println("Error.");
                            }

                            insertStatement.close();
                        }
                    } else break;
                }

            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }


        // close connection
        try {
            if (stmt != null && !stmt.isClosed()) stmt.close();
            if (rs != null && !rs.isClosed()) rs.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
