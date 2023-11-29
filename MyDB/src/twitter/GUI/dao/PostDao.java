package twitter.GUI.dao;

import java.util.Date;

public class PostDao {
    private Integer id;
    private String content;
    private Date time;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public void setUser_user_no(Integer user_user_no) {
        User_user_no = user_user_no;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Date getTime() {
        return time;
    }

    public Integer getView() {
        return view;
    }

    public Integer getUser_user_no() {
        return User_user_no;
    }

    public PostDao(Integer id, String content, Date time, Integer view, Integer user_user_no) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.view = view;
        User_user_no = user_user_no;
    }

    private Integer view;
    private Integer User_user_no;

}
