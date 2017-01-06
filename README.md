# So Many Feeds

So Many Feeds is a feed aggregator.

Easily manage your feeds from different social websites,
and aggregate in one simple home page.

So Many Feeds will automatically generate for you
any combination of your feeds you like,
both as a web page and as a RSS feed.


## Setup

Install the following

 * Java 8
 * Elm 0.18
 * Elm Test for Elm 0.18 (`npm install -g elm-test`)
 * Postgres
 * Graphviz (for the Dependencies Graph)

Setup your databases

```
 $ psql
 > CREATE DATABASE somanyfeeds_dev WITH ENCODING='utf-8'; 
 > CREATE DATABASE somanyfeeds_feed_test WITH ENCODING='utf-8'; 
 > CREATE DATABASE somanyfeeds_article_test WITH ENCODING='utf-8';
 > ^D
 $ psql somanyfeeds_dev < db/schema.ddl
 $ psql somanyfeeds_feed_test < db/schema.ddl
 $ psql somanyfeeds_article_test < db/schema.ddl
```

Build the entire app from the top level folder:

```
$ ./gradlew
```

Check the dependencies graph:

```
$ open build/dependenciesGraph/graph.dot.png
```
