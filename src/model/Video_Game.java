package model;

public class Video_Game {
    private final String game_name;
    private final String description;
    private final int review_count;
    private final float average_review;

    public Video_Game(String game_name, String description, int review_count, float average_review) {
        this.game_name = game_name;
        this.description = description;
        this.review_count = review_count;
        this.average_review = average_review;
    }

    public String getGameName() {
        return game_name;
    }

    public String getDescription() {
        return description;
    }

    public int getReviewCount() {
        return review_count;
    }

    public float getAverageReview() {
        return average_review;
    }
}
