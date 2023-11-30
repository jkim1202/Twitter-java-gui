package twitter.GUI.dao;

public class ChildCommentUserDao {
/*    cc.id as child_comment_id,
    cc.child_commnet_text as child_comment_text,
    cc.User_user_no,
    cc.comment_id,
    u.id,
    u.nickname,
    u.profile_image_url*/
    private Integer id;
    private String text;
    private Integer user_user_no;
    private Integer comment_id;
    private String user_id;
    private String nickname;
    private String profile_image_url;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser_user_no(Integer user_user_no) {
        this.user_user_no = user_user_no;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Integer getUser_user_no() {
        return user_user_no;
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }
}
