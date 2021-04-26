package swing_gui;

import database.DatabaseConnectionHandler;
import swing_gui.UserSingleton;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel errorText;

    private String inputtedUsername;
    private String inputtedPassword;

    public LoginPanel() {
//        UserSingleton.getInstance().getDCH().oracleLogin("ora_dsakaii", "a53042016");

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        usernameField.setPreferredSize(new Dimension(300, 30));
        passwordField.setPreferredSize(new Dimension(300, 30));

        JLabel cvrsLabel = new JLabel("CVRS");
        cvrsLabel.setHorizontalAlignment(JLabel.CENTER);
        cvrsLabel.setFont(new Font("Sans Serif", Font.PLAIN, 50));

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Sans Serif", Font.PLAIN, 13));

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Sans Serif", Font.PLAIN, 13));

        errorText = new JLabel();
        errorText.setText("");
        errorText.setForeground(Color.red);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputtedUsername = usernameField.getText();
                inputtedPassword = new String(passwordField.getPassword());
                //if successful login, return 1, else 0
                int loginStatus = getDCH().login(inputtedUsername, inputtedPassword);
                if (loginStatus == 1) {
                    // the User should be set using setUser(User user) upon success
                    User loginUser = new User (inputtedUsername, inputtedPassword);
                    setUser(loginUser);
                    loadIntermediateScreen();
                } else {
                    // If login fails, it should not loadIntermediateScreen and should instead
                    // display an error by using setErrorText("Error: <errormessage>");
                    setErrorText("Error: Invalid username or password");
                }
            }
        });

        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputtedUsername = usernameField.getText();
                inputtedPassword = new String(passwordField.getPassword());
                // If this is a brand new username, inserts the new user and returns 1.
                // Else, just returns 0.
                User newUser = new User(inputtedUsername, inputtedPassword);
                 int registrationStatus = getDCH().createAccount(newUser);
                //if (true) {
                if (registrationStatus == 1) {
                    // the User should be set using setUser(User user) upon success
                    setErrorText("Registration is successful!");
                    setUser(newUser);
                    loadIntermediateScreen();
                } else {
                    // If registration fails, it should not loadIntermediateScreen and should instead
                    // display an error by using setErrorText("Error: <errormessage>");
                    setErrorText("Error: Someone else is already using this username");
                }
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        add(cvrsLabel, constraints);
        add(usernameLabel, constraints);
        add(usernameField, constraints);
        add(passwordLabel, constraints);
        add(passwordField, constraints);
        add(loginButton, constraints);
        add(registerButton, constraints);
        add(errorText, constraints);
        setVisible(true);
    }

    // This should be called upon the successful login/creation of an account
    private void loadIntermediateScreen() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.add(new IntermediateScreen());
        topFrame.invalidate();
        topFrame.validate();
        topFrame.remove(this);
    }

    // This should be called upon the successful login/creation of an account
    private void setUser(User user) {
        UserSingleton.getInstance().setUser(user);
    }

    // Use this function when trying to display error messages on the login screen
    private void setErrorText(String input) {
        errorText.setText(input);
    }

    // For the purposes of using the database
    private DatabaseConnectionHandler getDCH() {
        return UserSingleton.getInstance().getDCH();
    }
}
