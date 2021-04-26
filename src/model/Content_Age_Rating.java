package model;

public class Content_Age_Rating {
    private final Integer age_rating;
    private final String description;

    public Content_Age_Rating(Integer age_rating, String description) {
        this.age_rating = age_rating;
        this.description = description;
    }

    public Integer getAgeRating() {
        return age_rating;
    }

    public String getDescription() {
        return description;
    }
}
