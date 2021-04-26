package swing_gui;

import javax.swing.*;
import java.awt.*;

public class ReviewScreen extends JPanel {

    public ReviewScreen() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        ReviewPanel reviewPanel = new ReviewPanel();
        reviewPanel.setPreferredSize(new Dimension(550, 345));

        TopArea topArea = new TopArea("Leave a review");
        topArea.setPreferredSize(new Dimension(550, 30));

        add(topArea, constraints);
        add(reviewPanel, constraints);

        setVisible(true);
    }
}
