package swing_gui;

import model.Video_Game;

import javax.swing.*;
import java.awt.*;

public class VideoGameScreen extends JPanel {

    public VideoGameScreen(Video_Game game) {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        VideoGamePanel addPanel = new VideoGamePanel(game);
        addPanel.setPreferredSize(new Dimension(550, 345));

        FindTopArea topArea = new FindTopArea(game.getGameName());
        topArea.setPreferredSize(new Dimension(550, 30));

        add(topArea, constraints);
        add(addPanel, constraints);

        setVisible(true);
    }

}
