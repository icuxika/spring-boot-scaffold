CREATE TABLE author
(
    id                bigint(20) not null primary key auto_increment,
    username          VARCHAR(255) NOT NULL,
    password          VARCHAR(255) NOT NULL,
    email             VARCHAR(255) NOT NULL,
    favourite_section VARCHAR(25)
);

CREATE TABLE blog
(
    id        bigint(20) not null primary key auto_increment,
    author_id INT NOT NULL,
    title     VARCHAR(255)
);

CREATE TABLE post
(
    id         bigint(20) not null primary key auto_increment,
    blog_id    INT,
    author_id  INT          NOT NULL,
    created_on TIMESTAMP    NOT NULL,
    section    VARCHAR(25)  NOT NULL,
    subject    VARCHAR(255) NOT NULL,
    draft      INT          NOT NULL
);

CREATE TABLE tag
(
    id   bigint(20) not null primary key auto_increment,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE post_tag
(
    post_id INT NOT NULL,
    tag_id  INT NOT NULL,
    PRIMARY KEY (post_id, tag_id)
);

CREATE TABLE comment
(
    id      bigint(20) not null primary key auto_increment,
    post_id INT          NOT NULL,
    name    VARCHAR(255) NOT NULL,
    comment VARCHAR(255) NOT NULL
);

CREATE TABLE node
(
    id        bigint(20) not null primary key auto_increment,
    parent_id INT
);

INSERT INTO author (id, username, password, email, favourite_section)
VALUES (101, 'jim', '********', 'jim@ibatis.apache.org', 'NEWS');
INSERT INTO author (id, username, password, email, favourite_section)
VALUES (102, 'sally', '********', 'sally@ibatis.apache.org', 'VIDEOS');

INSERT INTO blog (id, author_id, title)
VALUES (1, 101, 'Jim Business');
INSERT INTO blog (id, author_id, title)
VALUES (2, 102, 'Bally Slog');

INSERT INTO post (id, blog_id, author_id, created_on, section, subject, draft)
VALUES (1, 1, 101, '2007-12-05-00.00.00', 'NEWS', 'Corn nuts', 1);
INSERT INTO post (id, blog_id, author_id, created_on, section, subject, draft)
VALUES (2, 1, 101, '2008-01-12-00.00.00', 'VIDEOS', 'Paul Hogan on Toy Dogs', 0);
INSERT INTO post (id, blog_id, author_id, created_on, section, subject, draft)
VALUES (3, 2, 102, '2007-12-05-00.00.00', 'PODCASTS', 'Monster Trucks', 1);
INSERT INTO post (id, blog_id, author_id, created_on, section, subject, draft)
VALUES (4, 2, 102, '2008-01-12-00.00.00', 'IMAGES', 'Tea Parties', 0);

INSERT INTO post (id, blog_id, author_id, created_on, section, subject, draft)
VALUES (5, null, 101, '2008-01-12-00.00.00', 'IMAGES', 'An orphaned post', 0);

INSERT INTO tag (id, name)
VALUES (1, 'funny');
INSERT INTO tag (id, name)
VALUES (2, 'cool');
INSERT INTO tag (id, name)
VALUES (3, 'food');

INSERT INTO post_tag (post_id, tag_id)
VALUES (1, 1);
INSERT INTO post_tag (post_id, tag_id)
VALUES (1, 2);
INSERT INTO post_tag (post_id, tag_id)
VALUES (1, 3);
INSERT INTO post_tag (post_id, tag_id)
VALUES (2, 1);
INSERT INTO post_tag (post_id, tag_id)
VALUES (4, 3);

INSERT INTO comment (id, post_id, name, comment)
VALUES (1, 1, 'troll', 'I disagree and think...');
INSERT INTO comment (id, post_id, name, comment)
VALUES (2, 1, 'anonymous', 'I agree and think troll is an...');
INSERT INTO comment (id, post_id, name, comment)
VALUES (3, 3, 'rider', 'I prefer motorcycles to monster trucks...');

INSERT INTO node (id, parent_id)
VALUES (1, null);
INSERT INTO node (id, parent_id)
VALUES (2, 1);
INSERT INTO node (id, parent_id)
VALUES (3, 1);
INSERT INTO node (id, parent_id)
VALUES (4, 2);
INSERT INTO node (id, parent_id)
VALUES (5, 2);
INSERT INTO node (id, parent_id)
VALUES (6, 3);
INSERT INTO node (id, parent_id)
VALUES (7, 3);