package swing_gui;

import database.DatabaseConnectionHandler;
import swing_gui.UserSingleton;
import model.Comment_String;
import model.Comment_Writes_About;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReviewPanel extends JPanel {

    private JTextField game_nameField;
    private JTextField commentField;
    private JTextField ratingField;
    private JButton enterButton;
    private JLabel errorText;
    private JLabel message;


    private boolean haveAccess = false;
    private User user = UserSingleton.getInstance().getUser();
    private String gameToReview;
    private String commentStr;
    private String ratingStr;
    private int ratingInt;

    public ReviewPanel() {
        game_nameField = new JTextField();
        commentField = new JTextField();
        ratingField = new JTextField();
        game_nameField.setPreferredSize(new Dimension(300, 30));
        commentField.setPreferredSize((new Dimension(300, 100)));
        ratingField.setPreferredSize(new Dimension(30,30));

        JLabel titleLabel = new JLabel("Leave reviews here");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Sans Serif", Font.PLAIN, 40));

        JLabel game_nameLabel = new JLabel("Enter game name to review: ");
        game_nameLabel.setFont(new Font("Sans Serif", Font.PLAIN, 13));

        JLabel ratingLabel = new JLabel("Enter your rating out of 5: ");
        ratingLabel.setFont(new Font("Sans Serif", Font.PLAIN, 13));

        JLabel commentLabel = new JLabel("Enter your comment: ");
        commentLabel.setFont(new Font("Sans Serif", Font.PLAIN, 13));

        errorText = new JLabel();
        errorText.setText("");
        errorText.setForeground(Color.red);

        enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        message = new JLabel();
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message.setText("");
                errorText.setText("");
                gameToReview = game_nameField.getText();
                commentStr = commentField.getText();
                ratingStr = ratingField.getText();

                int check = makeReview();
                // loadIntermediateScreen();
                if (check == 1) {
                    message.setText("Review has been added!");
                    message.setForeground(Color.green);
                }
            }


        });

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        add(titleLabel, constraints);
        add(game_nameLabel, constraints);
        add(game_nameField, constraints);
        add(ratingLabel, constraints);
        add(ratingField, constraints);
        add(commentLabel, constraints);
        add(commentField, constraints);
        add(enterButton, constraints);
        add(message, constraints);
        add(errorText, constraints);
        setVisible(true);
    }

        /**
         *  HELPERS AHEAD
         */

        private void updateGameRatingInfo() {
            getDCH().updateGameReviewInfo(gameToReview,ratingInt);
        }

        private void changeErrorMsg(String msg){
            errorText.setText(msg);
        }

        // This should be called upon the successful login/creation of an account
        private void loadIntermediateScreen () {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.add(new IntermediateScreen());
            topFrame.invalidate();
            topFrame.validate();
            topFrame.remove(this);
        }

        // checks if user is a reviewer and gives access to reviewer
        public void checkReviewer () {
            haveAccess = getDCH().checkUserStatus(user.getUsername());
        }

        // leave review
        // user has inputted a game name to review through swing text box
        // 1. check if user is a reviewer
        // 2. checks if that game is in system
        // 3. make comment and make rating
        public int makeReview (){
            checkReviewer();
            if (haveAccess) {
                if (getDCH().checkGameInSys(gameToReview)) {
                    makeComment();
                    makeRating();
                    updateGameRatingInfo();
                    return 1;
                } else {
                    changeErrorMsg("Error: We do not have this game in system");
                    return 0;
                }
            } else {
                changeErrorMsg("No access error: You are not a verified reviewer");
                return 0;
            }
        }

        // assumed that commentStr has been set to user's input comment
        public void makeComment () {
            Comment_String cs =
                    new Comment_String(user.getUsername(), getCommentIssueDate(), commentStr);
            getDCH().insertCommentString(cs);

            int CommentNum = 1 + getDCH().getNextCommentNum(user.getUsername(), gameToReview);
            Comment_Writes_About cws =
                    new Comment_Writes_About(user.getUsername(), CommentNum, gameToReview, getCommentIssueDate());
            getDCH().insertCommentWritesAbout(cws);

        }

        // review out of 5 stars
        // gives error msg if rating not out of 5
        // assumed that ratingStr has been set to user's input rating
        public void makeRating () {
            ratingInt = Integer.parseInt(ratingStr);
            if (ratingInt < 0 || ratingInt > 5) {
                changeErrorMsg("Error: invalid rating");
            } else {
                getDCH().insertReviews(user, gameToReview, ratingInt);
            }

        }

        public String getCommentIssueDate () {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDateTime commentIssueDate = LocalDateTime.now();
            return dtf.format(commentIssueDate);
        }


        // For the purposes of using the database
        private DatabaseConnectionHandler getDCH () {
            return UserSingleton.getInstance().getDCH();
        }

}
