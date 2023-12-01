package twitter.GUI.dao;

import java.util.Date;

public class UserDao {
    private Integer user_no;
    private String id;
    private String password;
    private String profile;
    private Date start_date;
    private String email;
    private String profile_image_url;
    private String answer;

    public UserDao(Integer user_no, String id, String password, String profile, Date start_date, String email, String profile_image_url, String answer) {
        this.user_no = user_no;
        this.id = id;
        this.password = password;
        this.profile = profile;
        this.start_date = start_date;
        this.email = email;
        this.profile_image_url = profile_image_url;
        this.answer = answer;
    }

    public int getUser_no() {
        return user_no;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getProfile() {
        return profile;
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

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public UserDao(Integer user_no, String user_id, String user_password, String user_profile, Date user_start_date, String user_email, String user_profile_image_url) {
        this.user_no = user_no;
        this.id = user_id;
        this.password = user_password;
        this.profile = user_profile;
        this.start_date = user_start_date;
        this.email = user_email;
        this.profile_image_url = user_profile_image_url;
    }

    public UserDao(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public UserDao(Integer user_no,String id, String profile_image_url) {
        this.user_no = user_no;
        this.id = id;
        this.profile_image_url = profile_image_url;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getAnswer() {
        return answer;
    }
}
