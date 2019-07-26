CREATE TABLE IF NOT EXISTS author (
  id INTEGER IDENTITY PRIMARY KEY,
  author_id UUID NOT NULL,
  name VARCHAR(100) NOT NULL,
  creation_date TIMESTAMP);


CREATE TABLE IF NOT EXISTS post (
  id INTEGER IDENTITY PRIMARY KEY,
  post_id  UUID NOT NULL,
  author_id UUID NOT NULL,
  content VARCHAR(140) NOT NULL,
  creation_date TIMESTAMP);

  CREATE TABLE IF NOT EXISTS favourite_author (
  id INTEGER IDENTITY PRIMARY KEY,
  follower_id  UUID NOT NULL,
  followed_id UUID NOT NULL,
  );


CREATE SEQUENCE author_seq;
CREATE SEQUENCE post_seq;
CREATE SEQUENCE favourite_author_seq;


