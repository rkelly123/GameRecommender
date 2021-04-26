package swing_gui;

import database.DatabaseConnectionHandler;
import model.User;
import model.Video_Game;
import swing_gui.UserSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGamesPanel extends JPanel {

    private JList addedList;
    private String[] myGamesStrings = new String[5];


    public MyGamesPanel() {
        myGamesStrings = getMyGames();

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel titleAndButton = new JPanel();

        JLabel playedLabel = new JLabel("Games you've played");
        playedLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        titleAndButton.add(playedLabel);
        JButton resetFavourites = new JButton("Reset");
        resetFavourites.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        resetFavourites.setPreferredSize(new Dimension(70, 25));
        resetFavourites.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseConnectionHandler dbc = UserSingleton.getInstance().getDCH();
                User user = UserSingleton.getInstance().getUser();

                String[] favorites = dbc.getFavouriteGames(user.getUsername());
                for (int i = 0; i < favorites.length; i++) {
                    String str = favorites[i];
                    if (str != null) {
                        for (int j = i; j < favorites.length; j++) {
                            String innerStr = favorites[j];
                            if (innerStr != null && innerStr != str) {
                                dbc.decreaseSimilarity(str, innerStr);
                                dbc.decreaseSimilarity(innerStr, str);
                            }
                        }
                    }
                }

                dbc.deleteFavourites(user.getUsername());
            }
        });
        titleAndButton.add(resetFavourites);

        add(titleAndButton, constraints); 

        
        addedList = new JList(myGamesStrings);
        addedList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        addedList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        addedList.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(addedList);
        listScroller.setPreferredSize(new Dimension(250, 80));

        add(playedLabel, constraints);
        add(addedList, constraints);
    }

    private String[] getMyGames() {
        myGamesStrings = getMyGamesFromDatabase(UserSingleton.getInstance().getUser());
        if (myGamesStrings == null) {
            return null;
        }
        return myGamesStrings;
    }

    private String[] getMyGamesFromDatabase(User user) {
        // Retrieve favourited games from the database and return an ArrayList of Video_Game
        return getDCH().getFavouriteGames(user.getUsername());
    }

    // For the purposes of using the database
    private DatabaseConnectionHandler getDCH () {
        return UserSingleton.getInstance().getDCH();
    }
}



//package swing_gui;
//
//import database.DatabaseConnectionHandler;
//import model.User;
//import model.Video_Game;
//import swing_gui.UserSingleton;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.ArrayList;
//
//public class MyGamesPanel extends JPanel {
//
//    private JList addedList;
//    private DefaultListModel<String> myGamesStrings;
//    private ArrayList<Video_Game> myGames;
//
//
//    public MyGamesPanel() {
//        addedList = new JList();
//        myGamesStrings = getMyGames();
//        addedList.setModel(myGamesStrings);
//        addedList.setFont(new Font("Sans Serif", Font.PLAIN, 13));
//
//        setLayout(new GridBagLayout());
//        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.gridwidth = GridBagConstraints.REMAINDER;
//        constraints.fill = GridBagConstraints.HORIZONTAL;
//
//        addedList.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent event) {
//                JList list = (JList) event.getSource();
//                if (event.getClickCount() == 2) {
//                    int index = list.locationToIndex(event.getPoint());
//                    loadVideoGameScreen(myGames.get(index));
//                }
//            }
//        });
//
//        JPanel titleAndButton = new JPanel();
//
//        JLabel playedLabel = new JLabel("Games you've played");
//        playedLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));
//
//        titleAndButton.add(playedLabel);
//        JButton resetFavourites = new JButton("Reset");
//        resetFavourites.setFont(new Font("Sans Serif", Font.PLAIN, 14));
//        resetFavourites.setPreferredSize(new Dimension(70, 25));
//        resetFavourites.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                DatabaseConnectionHandler dbc = UserSingleton.getInstance().getDCH();
//                User user = UserSingleton.getInstance().getUser();
//
//                String[] favorites = dbc.getFavouriteGames(user.getUsername());
//                for (int i = 0; i < favorites.length; i++) {
//                    String str = favorites[i];
//                    if (str != null) {
//                        for (int j = i; j < favorites.length; i++) {
//                            String innerStr = favorites[j];
//                            if (innerStr != null && innerStr != str) {
//                                dbc.decreaseSimilarity(str, innerStr);
//                                dbc.decreaseSimilarity(innerStr, str);
//                            }
//                        }
//                    }
//                }
//
//                dbc.deleteFavourites(user.getUsername());
//            }
//        });
//        titleAndButton.add(resetFavourites);
//
//        add(titleAndButton, constraints);
//
//        addedList.setPreferredSize(new Dimension(250, 250));
//        add(addedList, constraints);
//    }
//
//
//
//    private DefaultListModel getMyGames() {
//        myGames = getMyGamesFromDatabase(UserSingleton.getInstance().getUser());
//        return convertGamesToStrings(myGames);
//    }
//
//    private ArrayList<Video_Game> getMyGamesFromDatabase(User user) {
//        ArrayList<Video_Game> games = new ArrayList<>();
//        // games.add(new Video_Game("Super Smash Brothers Ultimate", "bros", 10, 3.4f));
//        // games.add(new Video_Game("Counter Strike Global Offensive", "bros", 10, 3.5f));
//
//        // Retrieve favourited games from the database and return an ArrayList of Video_Game
//        String [] favGameNames = getDCH().getFavouriteGames(user.getUsername());
//
//        for (int i = 0; i < favGameNames.length; i++) {
//            String str = favGameNames[i];
//            if (str != null) {
//                Video_Game vg = getDCH().getVidGame(str);
//                games.add(vg);
//            }
//        }
//        return games;
//    }
//
//    private DefaultListModel<String> convertGamesToStrings(ArrayList<Video_Game> games) {
//        DefaultListModel<String> model = new DefaultListModel<String>();
//        for (int i = 0; i < games.size(); i++) {
//            if (games.get(i) != null) {
//                Video_Game curr = games.get(i);
//                model.add(i, curr.getGameName() + " (" + curr.getAverageReview() + ")" );
//            }
//        }
//        return model;
//    }
//
//    private void loadVideoGameScreen(Video_Game game) {
//        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
//        topFrame.add(new VideoGameScreen(game));
//        topFrame.invalidate();
//        topFrame.validate();
//        topFrame.remove(this.getParent().getParent());
//    }
//
//    // For the purposes of using the database
//    private DatabaseConnectionHandler getDCH () {
//        return UserSingleton.getInstance().getDCH();
//    }
//}
