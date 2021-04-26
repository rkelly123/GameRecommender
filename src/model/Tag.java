package model;

public class Tag {
    protected String tag_name;
    protected String description;

    public Tag(String tag_name, String description) {
        this.tag_name = tag_name;
        this.description = description;
    }

    public String getTagName() {
        return tag_name;
    }

    public String getDescription() {
        return description;
    }
}
