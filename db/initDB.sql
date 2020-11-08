DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

GRANT ALL ON SCHEMA public TO epol_user;
GRANT ALL ON SCHEMA public TO public;

CREATE TABLE movie_genre
(
  id    serial primary key,
  title varchar(32) unique not null
);

CREATE TABLE address
(
  id           serial primary key,
  country      varchar(32) not null,
  city         varchar(32) not null,
  street       varchar(32) not null,
  house_number int         not null
);

CREATE TABLE actor
(
  id            serial primary key,
  first_name    varchar(32),
  last_name     varchar(32),
  date_of_birth date,
  gender        varchar(32),
  address_id    int references address (id) on delete cascade
);

CREATE TABLE movie
(
  id             serial primary key,
  title          varchar(32) unique not null,
  movie_genre_id int references movie_genre (id) on delete cascade
);

CREATE TABLE actor_movie
(
  actor_id int not null,
  movie_id int not null,
  primary key (actor_id, movie_id),
  FOREIGN KEY (actor_id) references actor (id) on update cascade,
  FOREIGN KEY (movie_id) references movie (id) on update cascade
);

CREATE TABLE counter (
  id             serial primary key,
  title          varchar(32) unique not null,
  count          int,
  counter_offset int
);

INSERT INTO counter (title, count, counter_offset)
VALUES ('WRITE_ACTOR_COUNTER', '5', '0'),('READ_ACTOR_COUNTER','3','0');
