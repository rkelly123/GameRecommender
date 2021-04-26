package swing_gui;

import javax.swing.*;

public class AgeAndEmailScreen extends JSplitPane {
    public AgeAndEmailScreen() {
        setOrientation(JSplitPane.VERTICAL_SPLIT);
        setTopComponent(new AgeComponent());
        setBottomComponent(new EmailComponent());
        setDividerLocation(166);
    }
}
