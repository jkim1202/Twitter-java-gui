package twitter.GUI.dao;

public class CommentLikeDao {
    private Integer user_no;
    private Integer comment_id;

    public CommentLikeDao(Integer user_no, Integer comment_id) {
        this.user_no = user_no;
        this.comment_id = comment_id;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public Integer getComment_id() {
        return comment_id;
    }
}
