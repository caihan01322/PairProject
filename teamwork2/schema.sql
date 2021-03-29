drop table if exists user;
drop table if exists favorite;
drop table if exists paper;
drop table if exists author;
drop table if exists keyword;
drop table if exists keyword_to_paper;

CREATE TABLE user
(
    user_id  INT PRIMARY KEY AUTO_INCREMENT,
    email varchar(32) UNIQUE NOT NULL,
    username varchar(16) NOT NULL,
    password varchar(16) NOT NULL
);

CREATE TABLE favorite
(
    favorite_id INT PRIMARY KEY AUTO_INCREMENT ,
    name varchar(255) NOT NULL,
    user_id INT NOT NULL,
    foreign key (user_id) references user (user_id)
);

CREATE TABLE paper(
    paper_id INT PRIMARY KEY AUTO_INCREMENT,
    isbn varchar(32),
    title varchar(255) NOT NULL,
    url varchar(255),
    time varchar(32),
    conference varchar(8),
    abstract TEXT,
    favorite_id int,
    foreign key (favorite_id) references favorite(favorite_id)
);


CREATE TABLE author
(
    author_id  INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(32) NOT NULL,
    paper_id INT,
    FOREIGN KEY (paper_id) REFERENCES paper(paper_id)
);

CREATE TABLE keyword
(
    keyword_id  INT PRIMARY KEY AUTO_INCREMENT,
    content varchar(32) NOT NULL
);

CREATE TABLE keyword_to_paper
(
    id int primary key auto_increment,
    keyword_id  int not null ,
    paper_id int not null,
    foreign key (keyword_id) references keyword(keyword_id) ,
    foreign key (paper_id) references paper(paper_id)
);
