package model;

public class Category {
    private final String cat_name;
    private final String description;

    public Category(String cat_name, String description) {
        this.cat_name = cat_name;
        this.description = description;
    }

    public String getCatName() {
        return cat_name;
    }

    public String getDescription() {
        return description;
    }
}
