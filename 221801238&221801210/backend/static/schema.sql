drop table if exists keyword_to_conference;
drop table if exists keyword_to_paper;
drop table if exists author;
drop table if exists paper;
drop table if exists favorite;
drop table if exists keyword;
drop table if exists conference;
drop table if exists user;
CREATE TABLE user
(
    user_id  INT PRIMARY KEY AUTO_INCREMENT,
    email varchar(32) UNIQUE NOT NULL,
    username varchar(16) NOT NULL,
    password varchar(16) NOT NULL
);

CREATE TABLE conference
(
    conference_id  INT PRIMARY KEY,
    name varchar(8) UNIQUE NOT NULL
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
    conference_id int not null,
    abstract TEXT,
    favorite_id int,
    accessionNumber int,
    foreign key (favorite_id) references favorite(favorite_id),
    foreign key (conference_id) references conference(conference_id)
);

CREATE TABLE paper_to_favorite
(
    id int primary key auto_increment,
    paper_id int not null ,
    favorite_id int not null,
    foreign key (paper_id) references paper(paper_id),
    foreign key (favorite_id) references  favorite(favorite_id)
);

CREATE TABLE author
(
    author_id  INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(64) NOT NULL
);

CREATE TABLE author_to_paper
(
  id int primary key auto_increment,
  author_id int not null ,
  paper_id int not null ,
  foreign key (author_id) references author(author_id),
  foreign key (paper_id) references paper(paper_id)
);

CREATE TABLE keyword
(
    keyword_id  INT PRIMARY KEY,
    content varchar(128) unique NOT NULL,

);

CREATE TABLE keyword_to_conference
(
    id int primary key AUTO_INCREMENT,
    keyword_id  int not null,
    conference_id int not null,
    foreign key (conference_id) references conference(conference_id),
    foreign key (keyword_id) references keyword(keyword_id)
);

CREATE TABLE keyword_to_paper
(
    id int primary key auto_increment,
    keyword_id  int not null ,
    paper_id int not null,
    foreign key (keyword_id) references keyword(keyword_id) ,
    foreign key (paper_id) references paper(paper_id)
);

delete from keyword_to_paper;
delete from keyword_to_conference;
delete from keyword;
delete from paper;

select keyword_id,count(keyword_id) as num from keyword_to_paper group by keyword_id order by num desc limit 10;

select keyword_id as 最热词, count(keyword_id) as 出现次数
from keyword_to_conference
where conference_id=1
group by keyword_id
order by 出现次数
limit 1000;
