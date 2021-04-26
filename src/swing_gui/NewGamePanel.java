package swing_gui;

import database.DatabaseConnectionHandler;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class NewGamePanel extends JPanel {

    private JTextField gameName;
    private JTextField description;
    private JTextField imageLink;
    private JTextField category;
    private JTextField contentAgeRating;

    private JCheckBox playstation;
    private JCheckBox xbox;
    private JCheckBox pc;
    private JCheckBox nintendo;


    private JTextField studio;
    private JTextField gamePlayerType;
    private JButton addToDatabase;
    private JLabel message;

    public NewGamePanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        gameName = new JTextField();
        description = new JTextField();
        imageLink = new JTextField();
        category = new JTextField();
        contentAgeRating = new JTextField();

        playstation = new JCheckBox("PlayStation");
        xbox = new JCheckBox("Xbox");
        pc = new JCheckBox("PC");
        nintendo = new JCheckBox("Nintendo");

        studio = new JTextField();
        gamePlayerType = new JTextField();
        addToDatabase = new JButton("Add to database");
        message = new JLabel();

        addToDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAndAddToDatabase();
            }
        });

        add(new JLabel("Game Name:"));
        gameName.setPreferredSize(new Dimension(300, 30));
        add(gameName, constraints);

        add(new JLabel("Description:"));
        description.setPreferredSize(new Dimension(300, 30));
        add(description, constraints);

        add(new JLabel("Image Link:"));
        imageLink.setPreferredSize(new Dimension(300, 30));
        add(imageLink, constraints);

        add(new JLabel("Category:"));
        category.setPreferredSize(new Dimension(300, 30));
        add(category, constraints);

        add(new JLabel("Content Age Rating:"));
        contentAgeRating.setPreferredSize(new Dimension(300, 30));
        add(contentAgeRating, constraints);

        add(new JLabel("Platform:"));
        JPanel platforms = new JPanel();
        platforms.add(playstation);
        platforms.add(xbox);
        platforms.add(pc);
        platforms.add(nintendo);
        add(platforms, constraints);

        add(new JLabel("Studio:"));
        studio.setPreferredSize(new Dimension(300, 30));
        add(studio, constraints);

        add(new JLabel("Game Player Type:"));
        gamePlayerType.setPreferredSize(new Dimension(300, 30));
        add(gamePlayerType, constraints);

        add(addToDatabase, constraints);
        add(message, constraints);
    }

    private void checkAndAddToDatabase() {
        String gameNameStr = gameName.getText();
        if (checkIfGameAlreadyExists(gameNameStr)) {
            message.setText("Error: Game with this name already exists in DB!");
            message.setForeground(Color.red);
            return;
        }
        String desc = description.getText();
        if (desc.equals("")) {
            message.setText("Error: Game should have a description");
            message.setForeground(Color.red);
            return;
        }
        String imgURL = imageLink.getText();
        if (imgURL.equals("")) {
            message.setText("Error: Game should have a link to an image of the game");
            message.setForeground(Color.red);
            return;
        }

        String cat = category.getText();
        if (cat.equals("")) {
            message.setText("Error: Game should have a category");
            message.setForeground(Color.red);
            return;
        }

        String ageRating = contentAgeRating.getText();
        int age = -1;
        if (ageRating.equals("")) {
            message.setText("Error: Game should have an ageRating");
            message.setForeground(Color.red);
            return;
        } else {
            try {
                age = Integer.parseInt(ageRating);
            } catch (NumberFormatException err) {
                message.setText("Error: Age rating inputted is not a number");
                message.setForeground(Color.red);
                return;
            }
        }

        boolean playstationSelected = playstation.isSelected();
        boolean xboxSelected = xbox.isSelected();
        boolean pcSelected = pc.isSelected();
        boolean nintendoSelected = nintendo.isSelected();
        ArrayList<String> consoles = new ArrayList<>();

        if (!(playstationSelected || xboxSelected || pcSelected || nintendoSelected)) {
            message.setText("Error: Game should be on at least one console");
            message.setForeground(Color.red);
            return;
        } else {
            if (playstationSelected) {
                consoles.add("PlayStation");
            }
            if (xboxSelected) {
                consoles.add("Xbox");
            }
            if (pcSelected) {
                consoles.add("PC");
            }
            if (nintendoSelected) {
                consoles.add("Nintendo");
            }
        }


        String type = gamePlayerType.getText();
        if (type.equals("")) {
            message.setText("Error: Game should have a type describing SinglePlayer/Multiplayer nature");
            message.setForeground(Color.red);
            return;
        }
        String gameStudio = studio.getText();
        if (gameStudio.equals("")) {
            message.setText("Error: Game should have a game studio");
            message.setForeground(Color.red);
            return;
        }
        // input is assumed to be valid

        DatabaseConnectionHandler dch = UserSingleton.getInstance().getDCH();
        // some sort of insert video game for insert video game

        Game_Player_Type ty = new Game_Player_Type(type, type);
        dch.insertGamePlayerType(ty);
        Category category = new Category(cat, "A category of games in which " + cat + " is the focus");
        dch.insertCategory(category);
        dch.insertContentAgeRating(new Content_Age_Rating(age, "Age rating of " + age));
        Game_Description gd = new Game_Description(desc, imgURL, age);
        dch.insertGameDescription(gd);
        Random rand = new Random();
        Video_Game_Studio game_studio = new Video_Game_Studio(rand.nextInt(10000), gameStudio);
        dch.insertVideoGameStudio(game_studio);
        Video_Game game = new Video_Game(gameNameStr, desc, 0, 0.0f); // we only need game name
        dch.insertVideoGame(game);
        dch.insertCreatedBy(game_studio, game);
        dch.insertCategorizedBy(category, game);
        dch.insertPlaysType(ty, game);

        dch.insertVideoGame(game);

        dch.insertCreatedBy(game_studio, game);
        dch.insertCategorizedBy(category, game);
        dch.insertPlaysType(ty, game);

        for (int i = 0; i < consoles.size(); i++) {
            Video_Game_Platform plat = new Video_Game_Platform(consoles.get(i), consoles.get(i));
            dch.insertVideoGamePlatform(plat);
            dch.insertOnPlatform(plat, game);
        }

        message.setText(game.getGameName() + " added to the database!");
        message.setForeground(Color.green);

    }

    private boolean checkIfGameAlreadyExists(String gameName) {
        DatabaseConnectionHandler dch = UserSingleton.getInstance().getDCH();
        return dch.checkGameInSys(gameName);
    }

}
