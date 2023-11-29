package twitter.GUI.dao;

public class LikePostDao {
    private Integer user_user_no;

    private Integer post_id;

    public LikePostDao(Integer user_user_no, Integer post_id) {
        this.user_user_no = user_user_no;
        this.post_id = post_id;
    }

    public void setUser_user_no(Integer user_user_no) {
        this.user_user_no = user_user_no;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public Integer getUser_user_no() {
        return user_user_no;
    }

    public Integer getPost_id() {
        return post_id;
    }
}
