package twitter.GUI.dao;

import java.util.Date;

/*user_no int AI PK
id varchar(45)
password varchar(20)
nickname varchar(20)
start_date date
email varchar(40)
profile_image_url varchar(45)*/

public class UserDao {
    private Integer user_no;
    private String id;
    private String password;
    private String nickname;
    private Date start_date;
    private String email;
    private String profile_image_url;

    public int getUser_no() {
        return user_no;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public Date getStart_date() {
        return start_date;
    }

    public String getEmail() {
        return email;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public UserDao(Integer user_no, String user_id, String user_password, String user_nickname, Date user_start_date, String user_email, String user_profile_image_url) {
        this.user_no = user_no;
        this.id = user_id;
        this.password = user_password;
        this.nickname = user_nickname;
        this.start_date = user_start_date;
        this.email = user_email;
        this.profile_image_url = user_profile_image_url;
    }
}
