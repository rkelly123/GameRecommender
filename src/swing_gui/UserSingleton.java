package swing_gui;

import database.DatabaseConnectionHandler;
import model.User;
import model.UserSettings;

public final class UserSingleton {
    private User singleton;
    private DatabaseConnectionHandler dch;
    private UserSettings settings;
    private final static UserSingleton USER_SINGLETON = new UserSingleton();

    private UserSingleton() {

    }

    public static UserSingleton getInstance() {
        return USER_SINGLETON;
    }

    public User getUser() {
        return singleton;
    }

    public void setUser(User user) {
        singleton = user;
    }

    public DatabaseConnectionHandler getDCH() {
        return this.dch;
    }

    public void setDCH(DatabaseConnectionHandler dch) {
        this.dch = dch;
    }

    public UserSettings getSettings() { return settings; }

    public void setSettings(UserSettings settings) { this.settings = settings; }


}
