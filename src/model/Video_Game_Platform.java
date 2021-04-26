package model;

public class Video_Game_Platform {
    protected String platform_type;
    protected String description;

    public Video_Game_Platform(String platform_type, String description) {
        this.platform_type = platform_type;
        this.description = description;
    }

    public String getPlatformType() {
        return platform_type;
    }

    public String getDescription() {
        return description;
    }
}
