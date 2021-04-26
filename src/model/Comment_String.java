package model;

public class Comment_String {
    private final String username;
    private final String date;
    private final String comment_string;

    public Comment_String(String username, String date, String comment_string) {
        this.username = username;
        this.date = date;
        this.comment_string = comment_string;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    public String getCommentString() {
        return comment_string;
    }
}
