package swing_gui;

import javax.swing.*;
import java.awt.*;

public class NewGameScreen extends JPanel {
    public NewGameScreen() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        NewGamePanel newGamePanel = new NewGamePanel();
        newGamePanel.setPreferredSize(new Dimension(550, 345));

        FindTopArea topArea = new FindTopArea("Add new game to DB");
        topArea.setPreferredSize(new Dimension(550, 30));

        add(topArea, constraints);
        add(newGamePanel, constraints);

        setVisible(true);
    }
}
