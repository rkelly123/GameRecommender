package model;

public class Game_Description {
    private final String game_description;
    private final String image_link;
    private final int age_rating;

    public Game_Description(String game_description, String image_link, int age_rating) {
        this.game_description = game_description;
        this.image_link = image_link;
        this.age_rating = age_rating;
    }

    public String getGameDescription() {
        return game_description;
    }

    public String getImageLink() {
        return image_link;
    }

    public int getAgeRating() {
        return age_rating;
    }
}
