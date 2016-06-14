module SoManyFeeds.Articles exposing (ArticleList, Article, selectedArticles, Msg, view, update, fetchAll)

import Http
import Task exposing (Task)
import Json.Decode as Decode exposing ((:=))
import Html exposing (..)
import Html.Attributes exposing (..)
import Markdown

type alias ArticleList =
  { articles : (List Article)
  }

type alias Article =
  { title : String
  , link : String
  , content : String
  , date : String
  , source : String
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
  section [ id "app-content", class "content" ] (List.map listItem articles)

listItem : Article -> Html Msg
listItem model =
  article [] [
    header [] [
      h1 [] [
        a [ href model.link ] [ text model.title ]
      ],
      h2 [ class "date" ] [ text model.date ]
    ],
    section [] [ (Markdown.toHtml [] model.content) ]
  ]


update : Msg -> List Article -> (List Article, Cmd Msg)
update action articles =
  case action of
    FetchAllDone newArticleList ->
      (newArticleList.articles, Cmd.none)
    FetchAllFail error ->
      (articles, Cmd.none)


fetchAll : Cmd Msg
fetchAll = Http.get listDecoder "http://localhost:8080/articles/"
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
    ("title" := Decode.string)
    ("link" := Decode.string)
    ("content" := Decode.string)
    ("date" := Decode.string)
    ("source" := Decode.string)
