package model;

public class Comment_Writes_About {
    private final String username;
    private final Integer comment_num;
    private final String game_name;
    private final String date;

    public Comment_Writes_About(String username, Integer comment_num, String game_name, String date) {
        this.username = username;
        this.comment_num = comment_num;
        this.game_name = game_name;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public Integer getCommentNum() { return comment_num; }

    public String getGameName() {
        return game_name;
    }

    public String getDate() {
        return date;
    }
}
