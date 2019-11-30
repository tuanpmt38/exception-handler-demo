-- liquibase formatted sql
-- changeset tuanpm:1.1

DROP TABLE IF EXISTS news;

CREATE TABLE news(
    id serial,
    title varchar (255) not null unique,
    content VARCHAR (255) NOT NULL,
    image_url VARCHAR (255) NOT NULL,
    description varchar (255) null,
    PRIMARY KEY (id)
)
