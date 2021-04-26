package swing_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntermediateScreen extends JPanel {

    public IntermediateScreen() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JButton addButton = new JButton("Add your games");
        JButton recommendationsButton = new JButton("Find recommendations");
        JButton settingsButton = new JButton("Settings");
        JButton reviewButton = new JButton("Review and Rate Games");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAddScreen();
            }
        });

        recommendationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadRecommendations();
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSettings();
            }
        });

        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadReview();
            }
        });

        add(addButton, constraints);
        add(recommendationsButton, constraints);
        add(settingsButton, constraints);
        add(reviewButton, constraints);

        setVisible(true);
    }

    public void loadAddScreen() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.add(new AddGamesScreen());
        topFrame.invalidate();
        topFrame.validate();
        topFrame.remove(this);
    }

    public void loadRecommendations() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.add(new RecommendationsScreen());
        topFrame.invalidate();
        topFrame.validate();
        topFrame.remove(this);
    }

    public void loadSettings() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.add(new SettingsScreen());
        topFrame.invalidate();
        topFrame.validate();
        topFrame.remove(this);
    }

    public void loadReview() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.add(new ReviewScreen());
        topFrame.invalidate();
        topFrame.validate();
        topFrame.remove(this);
    }


}
