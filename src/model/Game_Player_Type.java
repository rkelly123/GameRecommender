package model;

public class Game_Player_Type {
    protected String player_type;
    protected String description;

    public Game_Player_Type(String player_type, String description) {
        this.player_type = player_type;
        this.description = description;
    }

    public String getPlayerType() {
        return player_type;
    }

    public String getDescription() {
        return description;
    }
}
