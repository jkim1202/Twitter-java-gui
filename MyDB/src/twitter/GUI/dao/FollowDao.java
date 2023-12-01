package twitter.GUI.dao;

public class FollowDao {
    private int user_no;
    private String id;
    private String profile;

    private String profile_image_url;

    public String getProfile() {
        return profile;
    }

    public FollowDao(int user_no, String id, String profile, String profile_image_url) {
        this.user_no = user_no;
        this.id = id;
        this.profile = profile;
        this.profile_image_url = profile_image_url;
    }

    public FollowDao(int user_no, String id, String profile_image_url) {
        this.user_no = user_no;
        this.id = id;
        this.profile_image_url = profile_image_url;
    }

    public int getUser_no() {
        return user_no;
    }

    public String getId() {
        return id;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }
}
