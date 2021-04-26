package swing_gui;

import javax.swing.*;
import java.awt.*;

public class AddGamesPanel extends JSplitPane {
    public AddGamesPanel() {
        setLeftComponent(new MyGamesPanel());
        setRightComponent(new FindYourGamesPanel());
        setDividerLocation(290);
        setBackground(Color.BLACK);
    }

}
