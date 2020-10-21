DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

GRANT ALL ON SCHEMA public TO epol_user;
GRANT ALL ON SCHEMA public TO public;

CREATE TABLE movie_genre
(
  id    serial primary key,
  title varchar(32)
);

CREATE TABLE address
(
  id           serial primary key,
  country      varchar(32),
  city         varchar(32),
  street       varchar(32),
  house_number int
);

CREATE TABLE actors
(
  id            serial primary key,
  first_name    varchar(32),
  last_name     varchar(32),
  date_of_birth date,
  gender        varchar(32),
  address_id    int references address (id) on delete cascade
);

CREATE TABLE movies
(
  id             serial constraint movies_pk primary key,
  title          varchar(32),
  movie_genre_id int references movie_genre (id) on delete cascade
);

CREATE TABLE actor_movies
(
  actor_id int not null,
  movie_id int not null,
  primary key (actor_id, movie_id),
  FOREIGN KEY (actor_id) references actors,
  FOREIGN KEY (movie_id) references movies
);


INSERT INTO movie_genre (title)
VALUES ('Adventure'), ('Comedy'), ('Western'), ('Thriller'), ('Horror'), ('Family'), ('Action');