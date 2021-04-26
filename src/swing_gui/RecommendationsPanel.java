package swing_gui;

import database.DatabaseConnectionHandler;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecommendationsPanel extends JPanel {
    private String gameTop1;
    private String gameTop2;
    private String gameTop3;
    private String gameTop4;
    private String gameTop5;

    private JLabel title;
    private JLabel game1;
    private JLabel game2;
    private JLabel game3;
    private JLabel game4;
    private JLabel game5;
    private JLabel recommendationLabel;

    private User user = UserSingleton.getInstance().getUser();

    public RecommendationsPanel() {
        title = new JLabel("Game Recommendations");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Sans Serif", Font.PLAIN, 40));

        game1 = new JLabel(gameTop1);
        game1.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        game2 = new JLabel(gameTop2);
        game2.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        game3 = new JLabel(gameTop3);
        game3.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        game4 = new JLabel(gameTop4);
        game4.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        game5 = new JLabel(gameTop5);
        game5.setFont(new Font("Sans Serif", Font.PLAIN, 20));

        getGameNames();

        recommendationLabel = new JLabel("Our top5 recommendations: ");
        recommendationLabel.setFont(new Font("Sans Serif", Font.PLAIN, 25));

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        add(title,constraints);
        add(game1,constraints);
        add(game2,constraints);
        add(game3,constraints);
        add(game4,constraints);
        add(game5,constraints);



        setVisible(true);
    }

    private void displayGames() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(title,constraints);
        add(recommendationLabel, constraints);
    }

    private void getGameNames() {
        //fetch top 5 game names that are calculated from alg and set the global variables
        String [] gameList = getDCH().makeRecommendations
                (getDCH().getFavouriteGames(user.getUsername()), UserSingleton.getInstance().getSettings());
        if (gameList[0] != null) {
            game1.setText("1. " + gameList[0]);
        }

        if (gameList[1] != null) {
            game2.setText("2. " + gameList[1]);
        }

        if (gameList[2] != null) {
            game3.setText("3. " + gameList[2]);
        }

        if (gameList[3] != null) {
            game4.setText("4. " + gameList[3]);
        }

        if (gameList[4] != null) {
            game5.setText("5. " + gameList[4]);
        }

//        game1.setText(gameList[0] + "1. Smash Brothers Ultimate");
//        game2.setText(gameList[1] + "2. Apex Legends");
//        game3.setText(gameList[2] + "3. Brawlhalla");
//        game4.setText(gameList[3] + "4. Garry's Mod");
//        game5.setText(gameList[4] + "5. Grand Theft Auto V");
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
}
