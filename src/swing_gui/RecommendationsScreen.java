package swing_gui;

import database.DatabaseConnectionHandler;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecommendationsScreen extends JPanel {
    private String[] gameList = new String[5];

    private JLabel title;

    private User user = UserSingleton.getInstance().getUser();

    public RecommendationsScreen() {
        title = new JLabel("Game Recommendations");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Sans Serif", Font.PLAIN, 40));

        getGameNames();

        JButton generateButton = new JButton("Generate recommendations");
        generateButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGameRecsScreen();
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        RecommendationsPanel recommendationsPanel = new RecommendationsPanel();
        recommendationsPanel.setPreferredSize(new Dimension(550, 345));
    }

    private void getGameNames() {
        //fetch top 5 game names that are calculated from alg and set the global variables
        gameList = getDCH().makeRecommendations
                (getDCH().getFavouriteGames(user.getUsername()), UserSingleton.getInstance().getSettings());
    }

    // For the purposes of using the database
    private DatabaseConnectionHandler getDCH () {
        return UserSingleton.getInstance().getDCH();
    }

    // This should be called upon the successful login/creation of an account
    private void loadIntermediateScreen() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.add(new IntermediateScreen());
        topFrame.invalidate();
        topFrame.validate();
        topFrame.remove(this);
    }

    public void loadGameRecsScreen() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.add(new GameRecsScreen(gameList));
        topFrame.invalidate();
        topFrame.validate();
        topFrame.remove(this);
    }
}
