package model;

public class Reviewer extends User {
    private final String verified_email;

    public Reviewer(String username, String password, String verified_email) {
        super(username, password);
        this.verified_email = verified_email;
        isReviewer = true;
    }

    public String getVerifiedEmail() {
        return verified_email;
    }

}
