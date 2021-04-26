package swing_gui;

import javax.swing.*;

public class SettingsPanel extends JSplitPane {

    public SettingsPanel() {
        setLeftComponent(new ConsoleChecksScreen());
        setRightComponent(new AgeAndEmailScreen());
        setDividerLocation(230);
    }

}
