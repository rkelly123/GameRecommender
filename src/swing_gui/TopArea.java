package swing_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopArea extends JPanel {
    private JButton backButton;

    public TopArea(String title) {
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        JLabel settingsLabel = new JLabel(title);
        settingsLabel.setFont(new Font("Sans Serif", Font.PLAIN, 25));
        add(settingsLabel, BorderLayout.CENTER);

        backButton = new JButton("←");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadIntermediateScreen();
            }
        });
        add(backButton, BorderLayout.WEST);
    }

    // This should be called upon the successful login/creation of an account
    private void loadIntermediateScreen() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.add(new IntermediateScreen());
        topFrame.invalidate();
        topFrame.validate();
        topFrame.remove(this.getParent());
    }
}
