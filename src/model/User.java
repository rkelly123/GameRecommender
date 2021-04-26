package model;

public class User {
    protected String username;
    protected String password;
    protected boolean isReviewer;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        isReviewer = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isReviewer() { return isReviewer; }
}
