module SoManyFeeds.Articles exposing (ArticleList, Article, selectedArticles, defaultArticle, Msg, view, update, fetchAll, titleText)

import Http
import Date
import Json.Decode as Decode exposing (field)
import Html exposing (..)
import Html.Attributes exposing (..)
import VirtualDom
import Json.Encode as Encode
import SoManyFeeds.DateFormat as DateFormat exposing (parseAndFormat)
import SoManyFeeds.Tweet as Tweet
import Regex


type alias ArticleList =
    { articles : List Article
    }


type alias Article =
    { title : Maybe String
    , link : Maybe String
    , content : String
    , date : Maybe String
    , source : String
    }


selectedArticles : List Article -> List String -> List Article
selectedArticles allArticles selectedSources =
    allArticles
        |> List.filter (isSelected selectedSources)


isSelected : List String -> Article -> Bool
isSelected sources article =
    List.member article.source sources


type Msg
    = FetchAll (Result Http.Error ArticleList)


view : List Article -> Html Msg
view articles =
    section
        [ id "app-content", class "content" ]
        (List.map listItem articles)


innerHtml : String -> Attribute Msg
innerHtml =
    VirtualDom.property "innerHTML" << Encode.string


compact : List (Maybe a) -> List a
compact list =
    List.filterMap identity list


listItem : Article -> Html Msg
listItem model =
    case model.title of
        Just t ->
            article []
                [ articleHeader model
                , section [ innerHtml model.content ] []
                ]

        Nothing ->
            article [ class "tweet" ]
                [ articleHeader model
                , Tweet.display model.content
                ]


articleHeader : Article -> Html Msg
articleHeader model =
    header []
        (compact
            [ Maybe.map (articleTitle model) model.title
            , Maybe.map articleDate model.date
            ]
        )


articleDate : String -> Html Msg
articleDate date =
    h2 [ class "date" ] [ text (DateFormat.parseAndFormat date) ]


articleTitle : Article -> String -> Html Msg
articleTitle model title =
    case model.link of
        Just link ->
            h1 []
                [ a [ href link ] [ text (titleText title) ]
                ]

        Nothing ->
            h1 []
                [ text (titleText title)
                ]


titleText : String -> String
titleText title =
    let
        regex =
            Regex.regex "\\shttp(s?)://[^\\s]*"
    in
        Regex.replace Regex.All regex (\_ -> "") title


update : Msg -> List Article -> ( List Article, Cmd Msg )
update action articles =
    case action of
        FetchAll result ->
            case result of
                Ok newArticleList ->
                    ( List.append [ aboutArticle ] newArticleList.articles, Cmd.none )

                Err error ->
                    ( articles, Cmd.none )


fetchAll : String -> Cmd Msg
fetchAll apiServer =
    Http.get (apiServer ++ "/articles") listDecoder
        |> Http.send FetchAll


listDecoder : Decode.Decoder ArticleList
listDecoder =
    Decode.map
        ArticleList
        (field "articles" (Decode.list memberDecoder))


memberDecoder : Decode.Decoder Article
memberDecoder =
    Decode.map5
        Article
        (field "title" (Decode.maybe Decode.string))
        (field "link" (Decode.maybe Decode.string))
        (field "content" (Decode.string))
        (field "date" (Decode.maybe Decode.string))
        (field "source" (Decode.string))


defaultArticle : Article
defaultArticle =
    { title = Just "Nothing to see here."
    , link = Just "http://damo.io"
    , content = defaultContent
    , date = Nothing
    , source = "none"
    }


defaultContent : String
defaultContent =
    """
  <p>
    You have deselected all the feeds in the menu. There is nothing to show.
    Feel free to select one or more feeds to display more entries.
  </p>
"""


aboutArticle : Article
aboutArticle =
    { title = Just "About"
    , link = Nothing
    , content = aboutContent
    , date = Nothing
    , source = "about"
    }


aboutContent : String
aboutContent =
    """
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
