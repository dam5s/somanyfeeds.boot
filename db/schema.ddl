-- create database somanyfeeds_dev with encoding='utf-8'

drop table if exists article;
drop table if exists feed;
drop type if exists feed_type;


create type feed_type as enum ('RSS', 'ATOM');

create table feed (
  id   bigserial primary key,
  name text,
  slug text,
  url  text,
  type feed_type
);

insert into feed (name, slug, url, type) values
  ('Github', 'github', 'https://github.com/dam5s.atom', 'ATOM'),
  ('Google+', 'gplus', 'http://gplusrss.com/rss/feed/aa49b266059d0628a1c112dabaec23a152aa2bad054d8', 'RSS'),
  ('Pivotal Blog', 'pivotal', 'http://pivotallabs.com/author/dleberrigaud/feed/', 'RSS');

create table article (
  id      bigserial primary key,
  feed_id int references feed (id),
  title   text,
  link    text,
  content text,
  date    timestamp
);
