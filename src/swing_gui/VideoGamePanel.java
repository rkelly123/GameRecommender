package swing_gui;

import database.DatabaseConnectionHandler;
import model.Comment_String;
import model.Comment_Writes_About;
import model.User;
import model.Video_Game;
import swing_gui.UserSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VideoGamePanel extends JPanel {

    private Video_Game game;
    private JList comments;
    private String[] CommentStrings = new String[5];

    public VideoGamePanel(Video_Game game) {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        User user = UserSingleton.getInstance().getUser();

        this.game = game;
        JPanel addButtonAndDesc = new JPanel();
        JButton addButton = new JButton("Favourite");
        addButtonAndDesc.add(addButton);
        addButtonAndDesc.add(new JLabel(game.getDescription()));
        JLabel favouriteMessage = new JLabel();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseConnectionHandler dbc = UserSingleton.getInstance().getDCH();
                String[] alreadyFavourites = dbc.getFavouriteGames(UserSingleton.getInstance().getUser().getUsername());
                dbc.insertFavourites(user, game);

                // Similarity
                for (int i = 0; i < alreadyFavourites.length; i++) {
                    if (alreadyFavourites[i] != null) {
                        dbc.increaseSimilarity(alreadyFavourites[i], game.getGameName());
                        dbc.increaseSimilarity(game.getGameName(), alreadyFavourites[i]);
                    }
                }

                favouriteMessage.setText("Favourited!");
                favouriteMessage.setForeground(new Color(61, 145, 58));
            }
        });
        add(addButtonAndDesc, constraints);

//        if (user.isReviewer()) {
//            JPanel ratePanel = new JPanel();
//            JLabel message = new JLabel();
//            ratePanel.add(new JLabel("Rate: "));
//            JTextField rateBar = new JTextField();
//            rateBar.setPreferredSize(new Dimension(20,20));
//            rateBar.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    String getRating = rateBar.getText();
//                    if (getRating.equals("5") || getRating.equals("4") || getRating.equals("3") || getRating.equals("2") || getRating.equals("1")) {
//                        int rating = -1;
//                        try {
//                            rating = Integer.parseInt(getRating);
//                        } catch (NumberFormatException err) {
//                            System.out.println(err);
//                        }
//                        DatabaseConnectionHandler dbc = UserSingleton.getInstance().getDCH();
//                        // dbc.insertReviews(user, game.getGameName(), rating);
//                        message.setText("Rated a " + getRating);
//                        message.setForeground(new Color(61, 145, 58));
//                    } else {
//                        message.setText("Input must be 5/4/3/2/1");
//                        message.setForeground(Color.red);
//                    }
//                }
//            });
//            ratePanel.add(rateBar);
//            ratePanel.add(message);
//            add(ratePanel, constraints);
//        }

        JPanel ratingsPanel = new JPanel();
        ratingsPanel.add(new JLabel("Number of Ratings: " + game.getReviewCount() + "    "));
        ratingsPanel.add(new JLabel("Average Rating: " + game.getAverageReview()));
        add(ratingsPanel, constraints);

//        if (user.isReviewer()) {
//            JTextField addCommentField = new JTextField();
//            addCommentField.setPreferredSize(new Dimension(300, 27));
//            addCommentField.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    String commentStr = addCommentField.getText();
//                    DatabaseConnectionHandler dbc = UserSingleton.getInstance().getDCH();
//                    Comment_String cs = new Comment_String(user.getUsername(), "00001111", commentStr);
//                    int nextNum = dbc.getNextCommentNum(user.getUsername(), game.getGameName());
//                    Comment_Writes_About cwa = new Comment_Writes_About(user.getUsername(), nextNum, game.getGameName(), "00001111");
//                    dbc.insertCommentString(cs);
//                    dbc.insertCommentWritesAbout(cwa);
//                    updateComments();
//                }
//            });
//            add(new JLabel("Make a comment"), constraints);
//            add(addCommentField, constraints);
//        }

        CommentStrings = getCommentStrings();

        comments = new JList(CommentStrings);
        comments.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        comments.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        comments.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(comments);
        listScroller.setPreferredSize(new Dimension(250, 80));

        add(comments, constraints);

        setVisible(true);
    }

    private String[] getCommentStrings() {
        CommentStrings = getCommentsFromDatabase();
        if (CommentStrings == null) {
            return null;
        }
        return CommentStrings;
    }

    private String[] getCommentsFromDatabase() {
        // Retrieve game comments from database
        return getDCH().getGameComments(game.getGameName());
    }

/*    private void updateComments() {
        comments.setModel(getCommentsForGame(game));
    }

    private DefaultListModel<String> getCommentsForGame(Video_Game game) {
        DefaultListModel<String> strs = new DefaultListModel<String>();
        ArrayList<Comment_String> comments = getCommentsFromDatabase(game);
        for (int i = 0; i < comments.size(); i++) {
            Comment_String comment = comments.get(i);
            String str = comment.getDate() + "-" + comment.getUsername() + ": " + comment.getCommentString();
            strs.add(i, str);
        }
        return strs;
    }

    // Get comments from the database for this game
    private ArrayList<Comment_String> getCommentsFromDatabase(Video_Game game) {
        ArrayList<Comment_String> comments = new ArrayList<>();
        comments = getDCH().getComments(game.getGameName(), comments);
        return comments;
    }*/

    // For the purposes of using the database
    private DatabaseConnectionHandler getDCH () {
        return UserSingleton.getInstance().getDCH();
    }
}
