package swing_gui;

import swing_gui.UserSingleton;
import model.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgeComponent extends JPanel {
    JCheckBox maxAgeNeeded;
    JTextField ageInput;
    JLabel errorText;

    public AgeComponent() {
        maxAgeNeeded = new JCheckBox();
        maxAgeNeeded.setText("Max Age");
        ageInput = new JTextField();
        ageInput.setPreferredSize(new Dimension(50, 25));

        UserSettings settings = UserSingleton.getInstance().getSettings();

        maxAgeNeeded.setSelected(settings.maxAgeExists());
        ageInput.setText("" + settings.getMaxAgeAllowed());

        errorText = new JLabel();

        maxAgeNeeded.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onNoAgeClicked();
            }
        });

        ageInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAgeEntered();
            }
        });

        add(maxAgeNeeded);
        add(ageInput);
        add(errorText);

        errorText.setVisible(false);
    }

    private void onNoAgeClicked() {
        UserSettings settings = UserSingleton.getInstance().getSettings();
        boolean maxNeeded = maxAgeNeeded.isSelected();
        if (maxNeeded) {
            settings.setDoesMaxAgeExist(true);
            ageInput.setText("" + settings.getMaxAgeAllowed());
        } else {
            settings.setDoesMaxAgeExist(false);
        }
    }

    private void onAgeEntered() {
        String ageEntered = ageInput.getText();
        int val;
        try {
            val = Integer.parseInt(ageEntered);
            UserSingleton.getInstance().getSettings().setMaxAgeAllowed(val);
            hideErrorText();
        } catch (NumberFormatException e) {
            setErrorText("Error: Inputted age is not a number");
        }
    }

    private void setErrorText(String str) {
        errorText.setVisible(true);
        errorText.setText(str);
        errorText.setForeground(Color.red);
    }

    private void hideErrorText() {
        errorText.setVisible(false);
    }
}
