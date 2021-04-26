package swing_gui;

import model.Video_Game;

import javax.swing.*;
import java.awt.*;

public class AddGamesScreen extends JPanel {

    public AddGamesScreen() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        AddGamesPanel addPanel = new AddGamesPanel();
        addPanel.setPreferredSize(new Dimension(550, 345));

        TopArea topArea = new TopArea("Find and Add Games");
        topArea.setPreferredSize(new Dimension(550, 30));

        add(topArea, constraints);
        add(addPanel, constraints);

        setVisible(true);
    }
}
