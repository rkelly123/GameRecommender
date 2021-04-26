package swing_gui;

import database.DatabaseConnectionHandler;
import model.Video_Game;
import swing_gui.UserSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FindYourGamesPanel extends JPanel {
    private JTextField searchBar;
    private JList list;

    private ArrayList<Video_Game> searchedGames;

    public FindYourGamesPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel findLabelAndAddGameButton = new JPanel();

        JButton newGame = new JButton("New game");
        findLabelAndAddGameButton.add(newGame);

        JLabel findLabel = new JLabel("Find Your Games");
        findLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        findLabelAndAddGameButton.add(findLabel);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadNewGameScreen();
            }
        });

        add(findLabelAndAddGameButton, constraints);



        JPanel searchArea = new JPanel();
        JLabel searchLabel = new JLabel("Search");
        searchArea.add(searchLabel);

        searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(175, 25));
        searchBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchForGames();
            }
        });
        searchArea.add(searchBar);
        add(searchArea, constraints);

        list = new JList();
        list.setPreferredSize(new Dimension(150, 250));
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                JList list = (JList) event.getSource();
                if (event.getClickCount() == 2) {
                    int index = list.locationToIndex(event.getPoint());
                    loadVideoGameScreen(searchedGames.get(index));
                }
            }
        });

        add(list, constraints);
    }

    private void searchForGames() {
        String searchQuery = searchBar.getText();
        if (searchQuery.equals("")) {
            return;
        }
        ArrayList<Video_Game> games = getSearchedVideoGames(searchQuery);
        searchedGames = games;
        list.setModel(convertGamesToStrings(games));
    }

    private DefaultListModel<String> convertGamesToStrings(ArrayList<Video_Game> games) {
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i) != null) {
                Video_Game curr = games.get(i);
                model.add(i, curr.getGameName() + " (" + curr.getAverageReview() + ")" );
            }
        }
        return model;
    }

    private ArrayList<Video_Game> getSearchedVideoGames(String searchQuery) {
        ArrayList<Video_Game> games = getDCH().gameSearch(searchQuery);
        // Retrieve an ArrayList of Video_Game based on the searchQuery (similar game_name)
        // games.add(new Video_Game("Counter Strike Global Offensive", "Precision-based Multiplayer First-person Shooter", 10, 3.3f));
        return games;
    }

    private void loadVideoGameScreen(Video_Game game) {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.add(new VideoGameScreen(game));
        topFrame.invalidate();
        topFrame.validate();
        topFrame.remove(this.getParent().getParent());
    }

    private void loadNewGameScreen() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.add(new NewGameScreen());
        topFrame.invalidate();
        topFrame.validate();
        topFrame.remove(this.getParent().getParent());
    }

    // For the purposes of using the database
    private DatabaseConnectionHandler getDCH () {
        return UserSingleton.getInstance().getDCH();
    }
}
