# So Many Feeds

So Many Feeds is a feed aggregator.

Easily manage your feeds from different social websites,
and aggregate in one simple home page.

So Many Feeds will automatically generate for you
any combination of your feeds you like,
both as a web page and as a RSS feed.


## Setup

You will need

 * Java 8
 * Node

The JAVA_HOME needs to be setup, `java` needs to be in your PATH and `npm` needs to be in your PATH.

Install node dependencies:

```
$ ./gradlew installNodeDependencies
```

This should install Elm locally.
Build the entire app from the top level folder with:

```
$ ./gradlew
```

### Database

TODO there is some local DB setup needed.


## Working on the Elm frontend

I created a convenience gradle task to launch the elm reactor using the local version of elm:

```
$ ./gradlew elmReactor
```

When working on the frontend with the reactor you may want to have the backend running too:

```
$ ./gradlew bootRun
```

Also CSS styling does not get applied to the UI in elm reactor,
until it becomes configurable I recommend configuring your browser.
For example, the [Chrome styler](https://chrome.google.com/webstore/detail/styler/bogdgcfoocbajfkjjolkmcdcnnellpkb)
extension can help.
