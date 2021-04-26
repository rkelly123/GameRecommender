package swing_gui;

import database.DatabaseConnectionHandler;
import swing_gui.UserSingleton;
import model.Reviewer;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class EmailComponent extends JPanel {
    JLabel verifiedLabel;
    JTextField emailField;
    JLabel errorLabel;

    public EmailComponent() {
        JLabel emailLabel = new JLabel("Email Verification:");
        emailLabel.setFont(new Font("Sans Serif", Font.PLAIN, 25));;
        add(emailLabel);
        User user = UserSingleton.getInstance().getUser();
        verifiedLabel = new JLabel("Verified!");
        verifiedLabel.setForeground(Color.green);
        emailField = new JTextField();
        emailField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEmailEntered();
            }
        });
        emailField.setPreferredSize(new Dimension(200, 20));
        if (user.isReviewer()) {
            add(verifiedLabel);
        } else {
            add(emailField);
        }
        errorLabel = new JLabel();
        add(errorLabel);
        errorLabel.setVisible(false);
        setVisible(true);
    }

    public void onEmailEntered() {
        boolean emailIsValid = emailCorrectForm(emailField.getText());
        if (!emailIsValid) {
            setErrorText("Error: Inputted email is invalid");
            return;
        }
        if (emailAlreadyExists(emailField.getText())) {
            setErrorText("Error: Inputted email already used");
            return;
        }
        User user = UserSingleton.getInstance().getUser();
        addUserAsReviewerToDatabase(user, emailField.getText());
        upgradeStatusOfUser(user, emailField.getText());
        errorLabel.setVisible(false);
        emailField.setVisible(false);
        remove(errorLabel);
        remove(emailField);
        add(verifiedLabel);
        verifiedLabel.setVisible(true);
        invalidate();
        validate();
    }

    private void setErrorText(String str) {
        errorLabel.setVisible(true);
        errorLabel.setText(str);
    }

    // true = already used
    private boolean emailAlreadyExists(String emailStr) {
//        return false;
        return getDCH().checkDuplicateEmail(emailStr);
    }

    private void addUserAsReviewerToDatabase(User user, String emailStr) {
        Reviewer newReviewer = new Reviewer(user.getUsername(), user.getPassword(), emailStr);
        getDCH().insertReviewer(newReviewer);
    }

    private void upgradeStatusOfUser(User user, String emailStr) {
        UserSingleton.getInstance().setUser(new Reviewer(user.getUsername(), user.getPassword(), emailStr));
        // verifiedLabel.setVisible(true);
        emailField.setVisible(false);
    }

    private boolean emailCorrectForm(String emailStr) {
        // Citation for email verifying variable "regexPattern": https://stackoverflow.com/a/1373724
        String regexPattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        return Pattern.matches(regexPattern, emailStr);
    }

    // For the purposes of using the database
    private DatabaseConnectionHandler getDCH() {
        return UserSingleton.getInstance().getDCH();
    }
}
