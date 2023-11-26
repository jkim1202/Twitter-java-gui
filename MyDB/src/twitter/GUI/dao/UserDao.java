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
    private int user_no;
    private String user_id;
    private String user_password;
    private String user_nickname;
    private Date user_start_date;
    private String user_email;
    private String user_profile_image_url;

    public int getUser_no() {
        return user_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public Date getUser_start_date() {
        return user_start_date;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_profile_image_url() {
        return user_profile_image_url;
    }

    public UserDao(int user_no, String user_id, String user_password, String user_nickname, Date user_start_date, String user_email, String user_profile_image_url) {
        this.user_no = user_no;
        this.user_id = user_id;
        this.user_password = user_password;
        this.user_nickname = user_nickname;
        this.user_start_date = user_start_date;
        this.user_email = user_email;
        this.user_profile_image_url = user_profile_image_url;
    }
}
