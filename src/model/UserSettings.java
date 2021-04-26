package model;

public class UserSettings {
    private boolean pcAllowed;
    private boolean playstationAllowed;
    private boolean xboxAllowed;
    private boolean nintendoAllowed;
    private boolean doesMaxAgeExist;
    private int maxAgeAllowed;

    public UserSettings() {
        pcAllowed = true;
        playstationAllowed = true;
        xboxAllowed = true;
        nintendoAllowed = true;
        doesMaxAgeExist = false;
        maxAgeAllowed = 20;
    }

    public boolean isPcAllowed() {
        return pcAllowed;
    }

    public void setPcAllowed(boolean pcAllowed) {
        this.pcAllowed = pcAllowed;
    }

    public boolean isPlaystationAllowed() {
        return playstationAllowed;
    }

    public void setPlaystationAllowed(boolean playstationAllowed) {
        this.playstationAllowed = playstationAllowed;
    }

    public boolean isXboxAllowed() {
        return xboxAllowed;
    }

    public void setXboxAllowed(boolean xboxAllowed) {
        this.xboxAllowed = xboxAllowed;
    }

    public boolean isNintendoAllowed() {
        return nintendoAllowed;
    }

    public void setNintendoAllowed(boolean nintendoAllowed) {
        this.nintendoAllowed = nintendoAllowed;
    }

    public boolean maxAgeExists() {
        return doesMaxAgeExist;
    }

    public void setDoesMaxAgeExist(boolean doesMaxAgeExist) {
        this.doesMaxAgeExist = doesMaxAgeExist;
    }

    public int getMaxAgeAllowed() {
        return maxAgeAllowed;
    }

    public void setMaxAgeAllowed(int maxAgeAllowed) {
        this.maxAgeAllowed = maxAgeAllowed;
    }
}
