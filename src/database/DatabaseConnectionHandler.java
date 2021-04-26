package database;

import java.sql.*;
import java.util.ArrayList;

import model.*;
import oracle.jdbc.proxy.annotation.Pre;

import javax.xml.stream.events.Comment;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
    // Use this version of the ORACLE_URL if you are running the code off of the server
    	//private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    private void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    ///
    /// BEGINNING OF INSERT METHODS
    ///
    public void insertUser(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO systemuser VALUES (?,?)");
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertReviewer(Reviewer reviewer) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO reviewer VALUES (?,?)");
            ps.setString(1,reviewer.getUsername());
            ps.setString(2,reviewer.getVerifiedEmail());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertCategory(Category cat) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO category VALUES (?,?)");

            ps.setString(1, cat.getCatName());
            ps.setString(2, cat.getDescription());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    // incomplete (because description?), just a basic one based off the tutorial 6 sample
    public void insertVideoGame(Video_Game game) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO videogame VALUES (?,?,?,?)");

            ps.setString(1, game.getGameName());
            ps.setString(2, game.getDescription());
            ps.setInt(3, game.getReviewCount());
            ps.setFloat(4, game.getAverageReview());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertGameDescription(Game_Description gdesc) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO gamedescription VALUES (?,?,?)");

            ps.setString(1, gdesc.getGameDescription());
            ps.setString(2, gdesc.getImageLink());
            ps.setInt(3, gdesc.getAgeRating());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error in GameDesc");
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertCommentWritesAbout(Comment_Writes_About cwa) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO commentwritesabout VALUES (?,?,?,?)");

            ps.setString(1, cwa.getUsername());
            ps.setInt(2, cwa.getCommentNum());
            ps.setString(3, cwa.getGameName());
            ps.setString(4, cwa.getDate());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertCommentString(Comment_String cs) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO commentstring VALUES (?,?,?)");

            ps.setString(1, cs.getUsername());
            ps.setString(2, cs.getDate());
            ps.setString(3, cs.getCommentString());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertContentAgeRating(Content_Age_Rating ageRating) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO contentagerating VALUES (?,?)");

            ps.setInt(1, ageRating.getAgeRating());
            ps.setString(2, ageRating.getDescription());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertVideoGamePlatform(Video_Game_Platform platform) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO videogameplatform VALUES (?,?)");

            ps.setString(1, platform.getPlatformType());
            ps.setString(2, platform.getDescription());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertTag(Tag tag) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tag VALUES (?,?)");

            ps.setString(1, tag.getTagName());
            ps.setString(2, tag.getDescription());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertGamePlayerType(Game_Player_Type pt) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO gameplayertype VALUES (?,?)");

            ps.setString(1, pt.getPlayerType());
            ps.setString(2, pt.getDescription());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertVideoGameStudio(Video_Game_Studio studio) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO videogamestudio VALUES (?,?)");

            ps.setInt(1, studio.getCompanyId());
            ps.setString(2, studio.getCompanyName());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    // Only call this one if its the first time the games have been inputted as similar
    public void insertSimilarTo(Video_Game game1, Video_Game game2, String username) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO similarto VALUES (?,?,?)");

            ps.setString(1, game1.getGameName());
            ps.setString(2, game2.getGameName());
            ps.setString(3, username);

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertCategorizedBy(Category cat, Video_Game game) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO categorizedby VALUES (?,?)");

            ps.setString(1, cat.getCatName());
            ps.setString(2, game.getGameName());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertOnPlatform(Video_Game_Platform platform, Video_Game game) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO onplatform VALUES (?,?)");

            ps.setString(1, platform.getPlatformType());
            ps.setString(2, game.getGameName());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertDescribes(Tag tag, Video_Game game) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO describes VALUES (?,?)");

            ps.setString(1, tag.getTagName());
            ps.setString(2, game.getGameName());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertPlaysType(Game_Player_Type pt, Video_Game game) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO playstype VALUES (?,?)");

            ps.setString(1, pt.getPlayerType());
            ps.setString(2, game.getGameName());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertCreatedBy(Video_Game_Studio studio, Video_Game game) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO createdby VALUES (?,?)");

            ps.setInt(1, studio.getCompanyId());
            ps.setString(2, game.getGameName());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertFavourites(User user, Video_Game game) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO favourites VALUES (?,?)");

            ps.setString(1, user.getUsername());
            ps.setString(2, game.getGameName());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertReviews(User user, String game_name, int rating) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO reviews VALUES (?,?,?)");

            ps.setString(1, user.getUsername());
            ps.setString(2, game_name);
            ps.setInt(3, rating);

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    ///
    /// END OF INSERT METHODS
    ///

    /// LOGIN SCREEN CONTROLLER METHODS
    public boolean oracleLogin(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    // If there is a match of username and password, return 1
    // If there is no match, return 0
    public int login(String username, String password){
        String username2 = "";
        String pw2 = "";
        int count = 0;
;        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username, password " +
                    "FROM systemuser ");

            while (rs.next()){
                username2 = removeWhiteSpaces(rs.getString("username"));
                pw2 = removeWhiteSpaces(rs.getString("password"));

                if (username.equals(username2) && password.equals(pw2)) {
                    // matched
                    count = 1;
                }
            }
            connection.commit();
            rs.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        //if count == 1, there is a match of username and password, success
        return count;
    }

    public String removeWhiteSpaces(String str){
        String noSpaceStr = str.trim();
        return noSpaceStr;
    }

    // calls insertUser if this is a brand new username
    // returns 1 if insertUser successful, else return 0
    public int createAccount(User newUser) {
        int registrationStatus = 1;
        // gotta check if there aren't any accounts with this name before it actually creates it
        String username1 = newUser.getUsername();
        String username2 ="";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username " +
                    "FROM systemuser ");

            while (rs.next()){
                if (username1.equals(removeWhiteSpaces(username2))) {
                    registrationStatus = 0;
                }
                username2 = rs.getString("username");
            }
            if (registrationStatus == 1) {
                insertUser(newUser);
            }
            connection.commit();
            rs.close();
            //ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return registrationStatus;
    }

    /// SETTINGS SCREEN METHODS
    // returns true if count is 1 -> already used
    public boolean checkDuplicateEmail(String email) {
        String queriedEmail = "";
        int count = 0;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT verified_email FROM reviewer");

            while(rs.next()){
                queriedEmail = removeWhiteSpaces(rs.getString(1));
                if (email.equals(queriedEmail)){
                    // count = 1 if same email already exists
                    count = 1;
                }
            }
            connection.commit();
            rs.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return (count == 1);
    }

    /// REVIEW SCREEN METHODS
    // returns true if count is 1 (there is 1 username that = reviewer name)
    public boolean checkUserStatus(String username) {
        int count = 0;
        String username2 = "";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username " +
                    "FROM systemuser NATURAL JOIN reviewer");

            while (rs.next()){
                username2 = removeWhiteSpaces(rs.getString(1));
                if (username.equals(username2)){
                    // there is a match => user is a reviewer
                    count = 1;
                }
            }
            connection.commit();
            rs.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return (count == 1);
    }

    // returns true if count is 1 (there is 1 name that = game name in table)
    public boolean checkGameInSys(String game_name){
        int count = 0;
        String game_name2 = "";
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT game_name " +
                    "FROM videogame");
            while(rs.next()){
                game_name2 = removeWhiteSpaces(rs.getString(1));
                if (game_name.equals(game_name2)){
                    // game is in system already
                    count = 1;
                }
            }
            connection.commit();
            rs.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return (count == 1);
    }

    // returns true if count is not 1 (there is not already a review for this game by this person)
    public boolean checkAlreadyReviewed(String username, String game_name){
        int count = 0;
        String game_name2 = "";
        String username2 = "";
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT username, game_name " +
                    "FROM reviews");
            while(rs.next()){
                username2 = removeWhiteSpaces(rs.getString(1));
                game_name2 = removeWhiteSpaces(rs.getString(2));
                if (username.equals(username2) && game_name.equals(game_name2)){
                    // user has already reviewed this game
                    count = 1;
                }
            }
            connection.commit();
            rs.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return (count != 1);
    }

    public int getNextCommentNum(String username, String game_name) {
        int count = 0;
        String username2;
        String game_name2;
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT username, game_name " +
                    "FROM commentwritesabout");
            while(rs.next()){
                username2 = rs.getString(1);
                game_name2 = rs.getString(2);
                if (username.equals(removeWhiteSpaces(username2))
                        && game_name.equals(removeWhiteSpaces(game_name2))){
                    PreparedStatement ps = connection.prepareStatement
                            ("SELECT MAX(comment_num) " +
                            "FROM commentwritesabout " +
                            "WHERE username = ? AND game_name = ?");
                    ps.setString(1, username2);
                    ps.setString(2,game_name2);
                    ResultSet rs2 = ps.executeQuery();
                    while (rs2.next()){
                        count = rs2.getInt(1);
                    }
                }
            }
            connection.commit();
            rs.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return count;
    }

    public String padString(String str, int n){
        String num = String.valueOf(n);
        String padded = String.format("%-"+ num + "s", str);
        return padded;
    }
    public void updateGameReviewInfo(String game_name, int ratingInt){
        int preRatingSum = 0;
        int totalNum = 0;
        game_name = padString(game_name, 50);

        // review_count increased
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("UPDATE videogame " +
                            "SET review_count = review_count + 1 " +
                            "WHERE game_name = ?");
            ps.setString(1, game_name);
            ps.executeUpdate();

            // get prev sum of ratings
            PreparedStatement ps2 = connection.prepareStatement
                    ("SELECT SUM(rating) " +
                            "FROM reviews " +
                            "WHERE game_name = ?");
            ps2.setString(1, game_name);
            ResultSet rs = ps2.executeQuery();
            while (rs.next()) {
                preRatingSum = rs.getInt(1);
            }

            // get new review_count
            PreparedStatement ps3 = connection.prepareStatement
                    ("SELECT review_count " +
                            "FROM videogame " +
                            "WHERE game_name = ?");
            ps3.setString(1, game_name);
            rs = ps3.executeQuery();
            while (rs.next()) {
                totalNum = rs.getInt(1);
            }

            // new rating =
            int newRatingAvg = (preRatingSum)/(totalNum);

            PreparedStatement ps4 = connection.prepareStatement
                    ("UPDATE videogame " +
                            "SET average_review = ? " +
                            "WHERE game_name = ?");
            ps4.setInt(1, newRatingAvg);
            ps4.setString(2, game_name);
            ps4.executeUpdate();

            connection.commit();
            ps.close();
            ps2.close();
            ps3.close();
            ps4.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    /// CHOOSE FAVOURITES FUNCTIONALITY (insert is above)

    public void increaseSimilarity(String game_name_1, String game_name_2, String username) {
        Video_Game game1 = new Video_Game(game_name_1, null, 0, 0);
        Video_Game game2 = new Video_Game(game_name_2, null, 0, 0);
        insertSimilarTo(game1, game2, username);
    }

    /// Find games to delete using getFavourites() method below
    public void decreaseSimilarity(String game_name_1, String game_name_2, String username) {
        int countPrev = 0;
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("DELETE " +
                            "FROM similarto " +
                            "WHERE game_name_1 = ? AND game_name_2 = ? AND username = ?");
            ps.setString(1, padString(game_name_1, 50));
            ps.setString(2, padString(game_name_2, 50));
            ps.setString(3, padString(username, 20));
            ResultSet rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteFavourites(String username) {
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("DELETE " +
                            "FROM favourites " +
                            "WHERE username = ?");
            ps.setString(1, padString(username, 20));
            ResultSet rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    /// GAME RECOMMENDATION FUNCTIONALITY

    public String[] getFavouriteGames(String username) {
        String[] favouriteGames = new String[5];
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT game_name " +
                            "FROM favourites " +
                            "WHERE username = ?");
            ps.setString(1, padString(username, 20));
            ResultSet rs = ps.executeQuery();
            if (rs == null) {
                return null;
            }
            for (int i = 0; i < 5; i++) {
                rs.next();
                if (rs.getString(1) == null) {
                    return favouriteGames;
                }
                favouriteGames[i] = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return favouriteGames;
    }

    public String[] makeRecommendations(String[] favouriteGames, UserSettings usettings) {
        String[] recommendedGames = new String[5];
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT game_name_2 " +
                            "FROM similarto " +
                            "WHERE game_name_1 = ? OR game_name_1 = ? OR game_name_1 = ? OR game_name_1 = ? " +
                                "OR game_name_1 = ? " +
                            "GROUP BY game_name_2 " +
                            "ORDER BY COUNT(*) DESC");
            for (int i = 0; i < 5; i++) {
                favouriteGames[i] = padString(favouriteGames[i], 50);
                ps.setString(i+1, favouriteGames[i]);
            }
            ResultSet rs = ps.executeQuery();
            int numRecs = 0;
            while (rs.next()) {
                if (numRecs >= 5) {
                    break;
                }
                if (!rs.getString(1).equals(favouriteGames[0]) && !rs.getString(1).equals(favouriteGames[1]) &&
                        !rs.getString(1).equals(favouriteGames[2]) && !rs.getString(1).equals(favouriteGames[3]) &&
                        !rs.getString(1).equals(favouriteGames[4]) && goodSettings(rs.getString(1), usettings)) {
                    recommendedGames[numRecs] = rs.getString(1);
                    numRecs++;
                }
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return recommendedGames;
    }

    public boolean goodSettings(String game_name, UserSettings usettings) {
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT op.platform_type, gd.age_rating " +
                            "FROM onplatform op, videogame vg, gamedescription gd " +
                            "WHERE vg.description = gd.game_description AND vg.game_name = ? AND vg.game_name = op.game_name");
            ps.setString(1, padString(game_name, 50));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (usettings.maxAgeExists()) {
                    if (rs.getInt(2) > usettings.getMaxAgeAllowed()) {
                        return false;
                    }
                }
                switch(removeWhiteSpaces(rs.getString(1))) {
                    case "PC":
                        if (usettings.isPcAllowed()) {
                            return true;
                        }
                    case "PlayStation":
                        if (usettings.isPlaystationAllowed()) {
                            return true;
                        }
                    case "Xbox":
                        if (usettings.isXboxAllowed()) {
                            return true;
                        }
                    case "Nintendo":
                        if (usettings.isNintendoAllowed()) {
                            return true;
                        }
                }
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return false;
    }

    // VideoGamePanel
    public ArrayList<Comment_String> getComments(String game_name, ArrayList<Comment_String> commentStringArray) {
        String [] username;
        String [] date;
        String [] comments = new String[0];
        int counter = 0;
        int count;
        int countCom = 0;
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT username, ymd, COUNT(*) " +
                            "FROM commentwritesabout " +
                            "WHERE game_name = ? ");
            ps.setString(1, padString(game_name,50));
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                count = rs.getInt(3);
                username = new String[count];
                date = new String[count];
                username[counter] = rs.getString(1);
                date[counter] = rs.getString(2);
                counter++;
                while (rs.next()) {
                    username[counter] = rs.getString(1);
                    date[counter] = rs.getString(2);
                    counter++;
                }

                for (int i = 0; i<count; i++){
                    PreparedStatement ps2 = connection.prepareStatement
                            ("SELECT commentstring " +
                                    "FROM commentstring "+
                                    "WHERE username = ? AND ymd = ?");
                    ps2.setString(1, padString(username[i], 20));
                    ps2.setString(2, date[i]);
                    ResultSet rs2 = ps.executeQuery();

                    if (rs2.next()){
                        for (int j = 0; rs2.next() ; j++){
                            Comment_String newCS = new Comment_String(username[j], date[j], rs2.getString(1));
                            commentStringArray.set(j, newCS);
                        }
                    }
                }
            }
            connection.commit();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return commentStringArray;
    }

    // my game panel
    public Video_Game getVidGame(String name){
        Video_Game vg = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT description, review_count, average_review " +
                    "FROM videogame WHERE game_name = ?");

            ps.setString(1, padString(name, 50));
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                vg = new Video_Game(name, rs.getString(1), rs.getInt(2), rs.getInt(3));
            }
            connection.commit();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return vg;
    }

    // find your game panel
    public ArrayList<Video_Game> gameSearch(String keyword){
        ArrayList<Video_Game> games = null;
        int count = 0;
        Video_Game vg;
        int counter = 0;
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT game_name, description, review_count, average_review " +
                    "FROM videogame " +
                            "GROUP BY game_name, description, review_count, average_review "+
                            "HAVING game_name LIKE ?");

            ps.setString(1, "%"+keyword+"%");
            ResultSet rs = ps.executeQuery();

            PreparedStatement ps2 = connection.prepareStatement
                    ("SELECT COUNT(*) FROM "
                    + "(SELECT COUNT(game_name) FROM videogame GROUP BY game_name)");
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()){
                count = rs2.getInt(1);
                games = new ArrayList<>(count);
            }

                while (rs.next()){
                    vg = new Video_Game
                            (rs.getString(1), rs.getString(2),
                                    rs.getInt(3), rs.getInt(4));
                    games.add(counter, vg);
                    counter++;
                }
            connection.commit();
                rs2.close();
                ps2.close();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return games;
    }

    public String[] getGameComments(String game_name) {
        String[] CommentStrings = new String[5];
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT commentstring " +
                            "FROM commentstring cs, commentwritesabout cwa " +
                            "WHERE cwa.game_name = ? AND cs.username = cwa.username");
            ps.setString(1, padString(game_name, 50));
            ResultSet rs = ps.executeQuery();
            if (rs == null) {
                return null;
            }
            for (int i = 0; i < 5; i++) {
                rs.next();
                if (rs.getString(1) == null) {
                    return CommentStrings;
                }
                CommentStrings[i] = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return CommentStrings;
    }

}

