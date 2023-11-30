package twitter.GUI.dao;

import java.util.Date;

public class PostInfoDao {
    // post를 검색한 user의 user_no
    private Integer user_no;
    // user id
    private String writer_id;
    private String writer_profile_img_url;
    // post id
    private Integer id;
    private String post_content;
    private Integer post_view;
    private Date post_time;
    // writer user_no
    private Integer user_user_no;
    private Integer post_like_count;
    private String image_urls;

    public Integer getUser_no() {
        return user_no;
    }

    public String getWriter_id() {
        return writer_id;
    }

    public String getWriter_profile_img_url() {
        return writer_profile_img_url;
    }

    public Integer getId() {
        return id;
    }

    public String getPost_content() {
        return post_content;
    }

    public Integer getPost_view() {
        return post_view;
    }

    public Date getPost_time() {
        return post_time;
    }

    public Integer getUser_user_no() {
        return user_user_no;
    }

    public Integer getPost_like_count() {
        return post_like_count;
    }

    public String getImage_urls() {
        return image_urls;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public void setWriter_id(String writer_id) {
        this.writer_id = writer_id;
    }

    public void setWriter_profile_img_url(String writer_profile_img_url) {
        this.writer_profile_img_url = writer_profile_img_url;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public void setPost_view(Integer post_view) {
        this.post_view = post_view;
    }

    public void setPost_time(Date post_time) {
        this.post_time = post_time;
    }

    public void setUser_user_no(Integer user_user_no) {
        this.user_user_no = user_user_no;
    }

    public void setPost_like_count(Integer post_like_count) {
        this.post_like_count = post_like_count;
    }

    public void setImage_urls(String image_urls) {
        this.image_urls = image_urls;
    }
}
