package swing_gui;

import database.DatabaseConnectionHandler;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecommendationsScreen extends JPanel {

    private JLabel title;

    public RecommendationsScreen() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        RecommendationsPanel recPanel = new RecommendationsPanel();
        recPanel.setPreferredSize(new Dimension(550, 345));

        TopArea topArea = new TopArea("Receive game recommendations");
        topArea.setPreferredSize(new Dimension(550, 30));

        add(topArea, constraints);
        add(recPanel, constraints);

        setVisible(true);
    }
}
