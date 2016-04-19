-- create database somanyfeeds_dev with encoding='utf-8'

DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS feed;
DROP TYPE IF EXISTS feed_type;


CREATE TYPE feed_type AS ENUM ('RSS', 'ATOM');

CREATE TABLE feed (
  id   BIGSERIAL PRIMARY KEY,
  name TEXT,
  slug TEXT,
  url  TEXT,
  type feed_type
);

INSERT INTO feed (name, slug, url, type) VALUES
  ('Github', 'github', 'https://github.com/dam5s.atom', 'ATOM'),
  ('Google+', 'gplus', 'http://gplusrss.com/rss/feed/aa49b266059d0628a1c112dabaec23a152aa2bad054d8', 'RSS'),
  ('Pivotal Blog', 'pivotal', 'http://blog.pivotal.io/author/dleberrigaud/feed', 'RSS');

CREATE TABLE article (
  id      BIGSERIAL PRIMARY KEY,
  feed_id INT REFERENCES feed (id) ON DELETE CASCADE,
  title   TEXT,
  link    TEXT,
  content TEXT,
  date    TIMESTAMP
);
