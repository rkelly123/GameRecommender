package swing_gui;

import javax.swing.*;
import java.awt.*;

public class SettingsScreen extends JPanel {
    public SettingsScreen() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        SettingsPanel settingsPanel = new SettingsPanel();
        settingsPanel.setPreferredSize(new Dimension(550, 345));

        TopArea topArea = new TopArea("User Settings");
        topArea.setPreferredSize(new Dimension(550, 30));

        add(topArea, constraints);
        add(settingsPanel, constraints);

        setVisible(true);
    }
}
