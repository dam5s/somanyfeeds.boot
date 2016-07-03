module SoManyFeeds.Articles exposing (ArticleList, Article, selectedArticles, defaultArticle, Msg, view, update, fetchAll)

import Http
import Date
import Task exposing (Task)
import Json.Decode as Decode exposing ((:=))
import Html exposing (..)
import Html.Attributes exposing (..)
import Markdown
import SoManyFeeds.DateFormat as DateFormat exposing (parseAndFormat)

type alias ArticleList =
  {
    articles : (List Article)
  }

type alias Article =
  {
    title : Maybe String,
    link : Maybe String,
    content : String,
    date : Maybe String,
    source : String
  }


selectedArticles: List Article -> List String -> List Article
selectedArticles allArticles selectedSources =
 allArticles
  |> List.filter (isSelected selectedSources)


isSelected: List String -> Article -> Bool
isSelected sources article =
  List.any (\s -> s == article.source) sources


type Msg
  = FetchAllDone ArticleList
  | FetchAllFail Http.Error


view : List Article -> Html Msg
view articles =
  section
    [ id "app-content", class "content" ]
    (List.map listItem articles)


listItem : Article -> Html Msg
listItem model =
  article [] [
    articleHeader model,
    section [] [ (Markdown.toHtml [] model.content) ]
  ]


articleHeader : Article -> Html Msg
articleHeader model =
  case model.title of
    Just title ->
      articleHeaderWithTitle model title
    Nothing ->
      articleHeaderWithoutTitle model

articleHeaderWithoutTitle : Article -> Html Msg
articleHeaderWithoutTitle model =
  case model.date of
    Just date ->
      header [] [
        h2 [ class "date" ] [ text (DateFormat.parseAndFormat date) ]
      ]
    Nothing ->
      header [] []


articleHeaderWithTitle : Article -> String -> Html Msg
articleHeaderWithTitle model title =
  case model.date of
    Just date ->
      header [] [
        articleTitle model title,
        h2 [ class "date" ] [ text (DateFormat.parseAndFormat date) ]
      ]
    Nothing ->
      header [] [
        articleTitle model title
      ]


articleTitle : Article -> String -> Html Msg
articleTitle model title =
  case model.link of
    Just link ->
      h1 [] [
        a [ href link ] [ text title ]
      ]
    Nothing ->
      h1 [] [
        text title
      ]


update : Msg -> List Article -> (List Article, Cmd Msg)
update action articles =
  case action of
    FetchAllDone newArticleList ->
      (List.append [ aboutArticle ] newArticleList.articles, Cmd.none)
    FetchAllFail error ->
      (articles, Cmd.none)


fetchAll : String -> Cmd Msg
fetchAll apiServer = Http.get listDecoder (apiServer ++ "/articles")
  |> Task.perform FetchAllFail FetchAllDone


listDecoder : Decode.Decoder ArticleList
listDecoder =
  Decode.object1
    ArticleList
    ("articles" := Decode.list memberDecoder)


memberDecoder : Decode.Decoder Article
memberDecoder =
  Decode.object5
    Article
    ("title" := Decode.maybe Decode.string)
    ("link" := Decode.maybe Decode.string)
    ("content" := Decode.string)
    ("date" := Decode.maybe Decode.string)
    ("source" := Decode.string)


defaultArticle : Article
defaultArticle =
  {
    title = Just "Nothing to see here.",
    link = Just "http://damo.io",
    content = defaultContent,
    date = Nothing,
    source = "none"
  }


defaultContent : String
defaultContent = """
  <p>
    You have deselected all the feeds in the menu. There is nothing to show.
    Feel free to select one or more feeds to display more entries.
  </p>
"""


aboutArticle : Article
aboutArticle =
  {
    title = Just "About",
    link = Nothing,
    content = aboutContent,
    date = Nothing,
    source = "about"
  }


aboutContent : String
aboutContent = """
  <p>
    I'm <strong>Damien Le Berrigaud</strong>, Software Engineer for
    <a href="https://pivotal.io/labs">Pivotal Labs</a>.

    I like working with
    <a href="https://kotlinlang.org">Kotlin</a>,
    <a href="https://www.oracle.com/technetwork/java/index.html">Java</a>,
    <a href="https://spring.io">Spring</a>,
    <a href="https://developer.android.com">Android</a>,
    <a href="https://developer.apple.com/ios">iOS (Swift or Objective-C)</a>,
    <a href="https://golang.org">Go</a>...

    I've had previous experiences in
    <a href="https://www.ruby-lang.org">Ruby</a> and
    <a href="https://www.microsoft.com/net">.NET</a>.
  </p>

  <p>
    That's a lot of technologies that I got to work with while working at <em>Pivotal Labs</em> and in my previous
    jobs.
    In order to keep up-to-date, I keep rebuilding this website.
  </p>

  <p>
    This version is  a <a href="http://www.appcontinuum.io/">components based</a> <em>Kotlin</em> application, using <em>Spring Boot</em>: including <em>Spring MVC</em> and <em>Spring
    JDBC</em>.
    The new front-end has been re-written with <a href="http://elm-lang.org/">Elm</a>. The source code is entirely
    available
    <a href="https://github.com/dam5s/somanyfeeds.boot">on my github</a>.
  </p>

  <p>
    You can choose to follow multiple of my feeds, on a single page, by using the menu at the top of the page.
  </p>
"""
