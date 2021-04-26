package swing_gui;

import swing_gui.UserSingleton;
import model.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsoleChecksScreen extends JPanel {

    private JCheckBox pcCheckBox;
    private JCheckBox playstationCheckBox;
    private JCheckBox xboxCheckBox;
    private JCheckBox nintendoCheckBox;
    public ConsoleChecksScreen() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel consoleSettingsLabel = new JLabel("Console Settings");
        consoleSettingsLabel.setFont(new Font("Sans Serif", Font.PLAIN, 25));

        pcCheckBox = new JCheckBox();
        playstationCheckBox = new JCheckBox();
        xboxCheckBox = new JCheckBox();
        nintendoCheckBox = new JCheckBox();

        UserSettings settings = UserSingleton.getInstance().getSettings();
        pcCheckBox.setSelected(settings.isPcAllowed());
        playstationCheckBox.setSelected(settings.isPlaystationAllowed());
        xboxCheckBox.setSelected(settings.isXboxAllowed());
        nintendoCheckBox.setSelected(settings.isNintendoAllowed());

        pcCheckBox.setText("PC");
        playstationCheckBox.setText("PlayStation");
        xboxCheckBox.setText("Xbox");
        nintendoCheckBox.setText("Nintendo");

        pcCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserSettings settings = UserSingleton.getInstance().getSettings();
                settings.setPcAllowed(pcCheckBox.isSelected());
            }
        });
        pcCheckBox.setFont(new Font("Sans Serif", Font.PLAIN, 20));

        playstationCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserSettings settings = UserSingleton.getInstance().getSettings();
                settings.setPlaystationAllowed(playstationCheckBox.isSelected());
            }
        });
        playstationCheckBox.setFont(new Font("Sans Serif", Font.PLAIN, 20));

        xboxCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserSettings settings = UserSingleton.getInstance().getSettings();
                settings.setXboxAllowed(xboxCheckBox.isSelected());
            }
        });
        xboxCheckBox.setFont(new Font("Sans Serif", Font.PLAIN, 20));

        nintendoCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserSettings settings = UserSingleton.getInstance().getSettings();
                settings.setNintendoAllowed(nintendoCheckBox.isSelected());
            }
        });
        nintendoCheckBox.setFont(new Font("Sans Serif", Font.PLAIN, 20));

        add(consoleSettingsLabel, constraints);
        add(pcCheckBox, constraints);
        add(playstationCheckBox, constraints);
        add(xboxCheckBox, constraints);
        add(nintendoCheckBox, constraints);
    }
}
