create table paper(
    paperID int primary key AUTO_INCREMENT,
    title varchar(500),
    abstractContent TEXT,
    year varchar(10),
    authors varchar(500),
    meeyting varchar(10),
    link varchar(10)
    );

create table keywordPaper(
    keywordPaperID int primary key AUTO_INCREMENT,
    paperID int,
    keyword varchar(500),
    frequency int,
    year varchar(10),
    meeyting varchar(10)
    );