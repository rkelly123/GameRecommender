package swing_gui;

import database.DatabaseConnectionHandler;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameRecsScreen extends JPanel {
    private JList addedList;
    private JLabel recommendationLabel;

    public GameRecsScreen(String[] gameList) {
        for (String elem : gameList) {
            System.out.println(elem);
        }
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        recommendationLabel = new JLabel("Our top 5 recommendations: ");
        recommendationLabel.setFont(new Font("Sans Serif", Font.PLAIN, 25));

        addedList = new JList(gameList);
        addedList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        addedList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        addedList.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(addedList);
        listScroller.setPreferredSize(new Dimension(250, 80));

        add(recommendationLabel, constraints);
        add(addedList, constraints);

        setVisible(true);
    }
}
