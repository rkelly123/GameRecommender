-- CREATE TABLE Setup
drop table categorizedby;
drop table onplatform;
drop table describes;
drop table playstype;
drop table createdby;
drop table favourites;
drop table reviews;
drop table similarto;
drop table videogameplatform;
drop table commentstring;
drop table commentwritesabout;
drop table reviewer;
drop table systemuser;
drop table category;
drop table videogame;
drop table gamedescription;
drop table contentagerating;
drop table tag;
drop table gameplayertype;
drop table videogamestudio;

CREATE TABLE systemuser
(
    username CHAR(20),
    password CHAR(20) NOT NULL,
    PRIMARY KEY (username)
);

grant select on systemuser to public;



CREATE TABLE reviewer
(
    username       CHAR(20),
    verified_email CHAR(64) UNIQUE NOT NULL,
    PRIMARY KEY (username),
    FOREIGN KEY (username)
        REFERENCES systemuser
        ON DELETE CASCADE
);

grant select on reviewer to public;


CREATE TABLE category
(
    cat_name    CHAR(20),
    description CHAR(50) NOT NULL,
    PRIMARY KEY (cat_name)
);

grant select on category to public;




CREATE TABLE contentagerating
(
    age_rating INTEGER,
	description		CHAR(50) NOT NULL,
	PRIMARY KEY (age_rating)
);

grant select on contentagerating to public;



CREATE TABLE gamedescription
(
    game_description CHAR(100),
    image_link       CHAR(200),
    age_rating       INTEGER NOT NULL,
    PRIMARY KEY (game_description),
    FOREIGN KEY (age_rating)
        REFERENCES contentagerating
);

grant select on gamedescription to public;


CREATE TABLE videogame
(
    game_name      CHAR(50),
    description    CHAR(100) NOT NULL,
    review_count   INTEGER NOT NULL,
    average_review DECIMAL   NOT NULL,
    PRIMARY KEY (game_name),
    FOREIGN KEY (description)
        REFERENCES gamedescription
        ON DELETE CASCADE
);

grant select on videogame to public;




CREATE TABLE commentwritesabout
(
    username    CHAR(20),
    comment_num INTEGER,
    game_name   CHAR(50),
    ymd         CHAR(8) NOT NULL,
    PRIMARY KEY (username, comment_num, game_name),
    FOREIGN KEY (username)
        REFERENCES systemuser
        ON DELETE CASCADE,
    FOREIGN KEY (game_name)
        REFERENCES videogame
        ON DELETE CASCADE
);

grant select on commentwritesabout to public;


CREATE TABLE commentstring
(
    username CHAR(20),
    ymd      CHAR(8) NOT NULL,
    commentstring CHAR (100),
    PRIMARY KEY (username, ymd),
    FOREIGN KEY (username)
        REFERENCES systemuser
        ON DELETE CASCADE
);

grant select on commentstring to public;



CREATE TABLE videogameplatform
(
    platform_type CHAR(20),
    description   CHAR(50) NOT NULL,
    PRIMARY KEY (platform_type)
);

grant select on videogameplatform to public;


CREATE TABLE tag
(
    tag_name    CHAR(20),
    description CHAR(50) NOT NULL,
    PRIMARY KEY (tag_name)
);

grant select on tag to public;


CREATE TABLE gameplayertype
(
    player_type CHAR(20),
    description CHAR(50) NOT NULL,
    PRIMARY KEY (player_type)
);

grant select on gameplayertype to public;


CREATE TABLE videogamestudio
(
    company_id   INTEGER,
    company_name CHAR(50) NOT NULL,
    PRIMARY KEY (company_id)
);

grant select on videogamestudio to public;


CREATE TABLE similarto
(
    game_name_1 CHAR(50),
    game_name_2 CHAR(50),
    Count       INTEGER NOT NULL,
    PRIMARY KEY (game_name_1, game_name_2),
    FOREIGN KEY (game_name_1)
        REFERENCES videogame
        ON DELETE CASCADE,
    FOREIGN KEY (game_name_2)
        REFERENCES videogame
        ON DELETE CASCADE
);

grant select on similarto to public;


CREATE TABLE categorizedby
(
    cat_name  CHAR(20),
    game_name CHAR(50),
    PRIMARY KEY (cat_name, game_name),
    FOREIGN KEY (cat_name)
        REFERENCES category
        ON DELETE CASCADE,
    FOREIGN KEY (game_name)
        REFERENCES videogame
        ON DELETE CASCADE
);

grant select on categorizedby to public;


CREATE TABLE onplatform
(
    platform_type CHAR(20),
    game_name     CHAR(50),
    PRIMARY KEY (platform_type, game_name),
    FOREIGN KEY (platform_type)
        REFERENCES videogameplatform
        ON DELETE CASCADE,
    FOREIGN KEY (game_name)
        REFERENCES videogame
        ON DELETE CASCADE
);

grant select on onplatform to public;


CREATE TABLE describes
(
    tag_name  CHAR(20),
    game_name CHAR(50),
    PRIMARY KEY (tag_name, game_name),
    FOREIGN KEY (tag_name)
        REFERENCES tag
        ON DELETE CASCADE,
    FOREIGN KEY (game_name)
        REFERENCES videogame
        ON DELETE CASCADE
);

grant select on describes to public;



CREATE TABLE playstype
(
    player_type CHAR(20),
    game_name   CHAR(50),
    PRIMARY KEY (player_type, game_name),
    FOREIGN KEY (player_type)
        REFERENCES gameplayertype
        ON DELETE CASCADE,
    FOREIGN KEY (game_name)
        REFERENCES videogame
        ON DELETE CASCADE
);

grant select on playstype to public;



CREATE TABLE createdby
(
    company_id INTEGER,
    game_name  CHAR(50),
    PRIMARY KEY (company_id, game_name),
    FOREIGN KEY (company_id)
        REFERENCES videogamestudio
        ON DELETE CASCADE,
    FOREIGN KEY (game_name)
        REFERENCES videogame
        ON DELETE CASCADE
);

grant select on createdby to public;



CREATE TABLE favourites
(
    username  CHAR(20),
    game_name CHAR(50),
    PRIMARY KEY (username, game_name),
    FOREIGN KEY (username)
        REFERENCES systemuser
        ON DELETE CASCADE,
    FOREIGN KEY (game_name)
        REFERENCES videogame
        ON DELETE CASCADE
);

grant select on favourites to public;



CREATE TABLE reviews
(
    username  CHAR(20),
    game_name CHAR(50),
    rating    INTEGER NOT NULL,
    PRIMARY KEY (username, game_name),
    FOREIGN KEY (username)
        REFERENCES reviewer
        ON DELETE CASCADE,
    FOREIGN KEY (game_name)
        REFERENCES videogame
        ON DELETE CASCADE
);

grant select on reviews to public;


-- Mock Values Setup
-- sample :
-- INSERT INTO branch VALUES (1, "ABC", "123 Charming Ave", "Vancouver", "6041234567");
-- INSERT INTO branch VALUES (2, "DEF", "123 Coco Ave", "Vancouver", "6044567890");
INSERT INTO videogameplatform VALUES ('PlayStation', 'Sonys premier gaming console');
INSERT INTO videogameplatform VALUES ('PC', 'A flexible way to play almost any game');
INSERT INTO videogameplatform VALUES ('Xbox', 'Microsofts flagship gaming console');
INSERT INTO videogameplatform VALUES ('Nintendo', 'A household favorite');


-- Smash Bros Ultimate
INSERT INTO videogamestudio VALUES (0, 'Nintendo Games');
INSERT INTO gameplayertype VALUES ('Single/Multiplayer', 'Single/Multiplayer functionality');
INSERT INTO contentagerating VALUES (6, 'Age rating of 6');
INSERT INTO gamedescription VALUES ('A game for the whole family', 'fakelinktosmash.com', 6);
INSERT INTO videogame VALUES ('Super Smash Bros Ultimate', 'A game for the whole family', 0, 0);
INSERT INTO category VALUES ('Platform Brawler', 'Fighting games with platforms');

INSERT INTO onplatform VALUES ('PlayStation', 'Super Smash Bros Ultimate');
INSERT INTO createdby VALUES (0, 'Super Smash Bros Ultimate');
INSERT INTO playstype VALUES ('Single/Multiplayer', 'Super Smash Bros Ultimate');
INSERT INTO categorizedby VALUES ('Platform Brawler', 'Super Smash Bros Ultimate');

-- Valorant
INSERT INTO videogamestudio VALUES (1, 'Riot Games');
INSERT INTO gameplayertype VALUES ('Multiplayer', 'Multiplayer functionality');
INSERT INTO contentagerating VALUES (13, 'Age rating of 13');
INSERT INTO gamedescription VALUES ('Tactical fantasy shooter', 'fakelinktovalorant.com', 13);
INSERT INTO videogame VALUES ('Valorant', 'Tactical fantasy shooter', 0, 0);
INSERT INTO category VALUES ('Tactical Shooter', 'Shooting games involving tactics');

INSERT INTO onplatform VALUES ('PC', 'Valorant');
INSERT INTO createdby VALUES (1, 'Valorant');
INSERT INTO playstype VALUES ('Multiplayer', 'Valorant');
INSERT INTO categorizedby VALUES ('Tactical Shooter', 'Valorant');

-- League of Legends
--INSERT INTO videogamestudio VALUES (1, 'Riot Games');
--INSERT INTO gameplayertype VALUES ('Multiplayer', 'Multiplayer functionality');
--INSERT INTO contentagerating VALUES (13, 'Age rating of 13');
INSERT INTO gamedescription VALUES ('MOBA with champions', 'fakelinktoleague.com', 13);
INSERT INTO videogame VALUES ('League of Legends', 'MOBA with champions', 0, 0);
INSERT INTO category VALUES ('MOBA', 'Multiplayer Online Battle Arena');

INSERT INTO onplatform VALUES ('PC', 'League of Legends');
INSERT INTO createdby VALUES (1, 'League of Legends');
INSERT INTO playstype VALUES ('Multiplayer', 'League of Legends');
INSERT INTO categorizedby VALUES ('MOBA', 'League of Legends');


-- Counter Strike: Global Offensive
INSERT INTO videogamestudio VALUES (2, 'Valve');
--INSERT INTO gameplayertype VALUES ('Multiplayer', 'Multiplayer functionality');
--INSERT INTO contentagerating VALUES (13, 'Age rating of 13');
INSERT INTO gamedescription VALUES ('Tactical realistic shooter', 'fakelinktocsgo.com', 13);
INSERT INTO videogame VALUES ('Counter Strike: Global Offensive', 'Tactical realistic shooter', 0, 0);
-- INSERT INTO category VALUES ('Tactical Shooter', 'Shooting games involving tactics');

INSERT INTO onplatform VALUES ('PC', 'Counter Strike: Global Offensive');
INSERT INTO createdby VALUES (2, 'Counter Strike: Global Offensive');
INSERT INTO playstype VALUES ('Multiplayer', 'Counter Strike: Global Offensive');
INSERT INTO categorizedby VALUES ('Tactical Shooter', 'Counter Strike: Global Offensive');


INSERT INTO systemuser VALUES ('pleaseOne', 'lordOne');
INSERT INTO reviewer VALUES ('pleaseOne', 'dansakai0@gmail.com');
INSERT INTO favourites VALUES ('pleaseOne', 'Super Smash Bros Ultimate');
INSERT INTO favourites VALUES ('pleaseOne', 'Valorant');

INSERT INTO systemuser VALUES ('pleaseTwo', 'lordTwo');
INSERT INTO favourites VALUES ('pleaseTwo', 'League of Legends');
INSERT INTO favourites VALUES ('pleaseTwo', 'Valorant');
