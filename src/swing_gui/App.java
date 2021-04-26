package swing_gui;

import database.DatabaseConnectionHandler;
import swing_gui.UserSingleton;
import model.User;
import model.UserSettings;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App extends JFrame {

    public static void main(String[] args) {
        UserSingleton.getInstance().setUser(new User("example", "example"));
        UserSingleton.getInstance().setDCH(new DatabaseConnectionHandler());
        UserSingleton.getInstance().setSettings(new UserSettings());
        UserSingleton.getInstance().getDCH().oracleLogin("", "");

        new App();

    }

    public App() {
        setSize(600, 400);
        add(new LoginPanel());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                super.windowClosing(e);
                 //Do your disconnect from the DB here.
                 UserSingleton.getInstance().getDCH().close();
                 //UserSingleton.getInstance().getDCH() = null;
        System.exit(0);
            }
        });
    }

//    public void terminalTransactionsFinished() {
//        UserSingleton.getInstance().getDCH().close();
//        UserSingleton.getInstance().getDCH() = null;
//
//        System.exit(0);
//    }


}
