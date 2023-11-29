package twitter.GUI.dao;

public class CommentUserDao {
    private Integer comment_id;
    private String comment_text;
    private Integer user_user_no;
    private Integer post_id;
    private Integer comment_like_cnt;
    private Integer user_no;
    private String id;
    private String nickname;
    private String profile_image_url;

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public void setUser_user_no(Integer user_user_no) {
        this.user_user_no = user_user_no;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public void setComment_like_cnt(Integer comment_like_cnt) {
        this.comment_like_cnt = comment_like_cnt;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public Integer getUser_user_no() {
        return user_user_no;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public Integer getComment_like_cnt() {
        return comment_like_cnt;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }
}
