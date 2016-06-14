module SoManyFeeds.App exposing (..)

import Html exposing (..)
import Html.Attributes exposing (..)
import Html.App

import SoManyFeeds.Feeds as Feeds
import SoManyFeeds.Articles as Articles


type alias AppModel =
  {
    articles : List Articles.Article,
    feeds : List Feeds.Feed
  }

initialModel : AppModel
initialModel =
  {
    articles = [],
    feeds = Feeds.defaultFeeds
  }

init : (AppModel, Cmd Msg)
init =
  (initialModel, Cmd.map ArticleMsg Articles.fetchAll)

type Msg
  = ArticleMsg Articles.Msg
  | FeedMsg Feeds.Msg

view : AppModel -> Html Msg
view model =
  div [] [
    header [ id "app-header" ] [
      section [ class "content" ] [
        h1 [] [
          img [ src "logo.svg", alt "damo.io" ] []
        ],
        aside [ id "app-menu" ] [
          Html.App.map FeedMsg (Feeds.view model.feeds)
        ]
      ]
    ],
    Html.App.map ArticleMsg (Articles.view (articlesToDisplay model))
  ]


articlesToDisplay: AppModel -> List Articles.Article
articlesToDisplay model =
  selectedArticles model.articles (selectedSources model.feeds)

selectedArticles: List Articles.Article -> List String -> List Articles.Article
selectedArticles allArticles selectedSources =
 allArticles
  |> List.filter (isSelected selectedSources)

isSelected: List String -> Articles.Article -> Bool
isSelected sources article =
  List.any (\s -> s == article.source) sources

selectedSources: List Feeds.Feed -> List String
selectedSources feeds =
  feeds
    |> List.filter (\f -> f.selected)
    |> List.map (\f -> f.slug)


update : Msg -> AppModel -> (AppModel, Cmd Msg)
update message model =
  case message of
    ArticleMsg subMsg ->
      let (updatedArticles, articleCmd) =
        Articles.update subMsg model.articles
      in
        ({ model | articles = updatedArticles } , Cmd.map ArticleMsg articleCmd)
    FeedMsg subMsg ->
      let (updatedFeeds, feedCmd) =
        Feeds.update subMsg model.feeds
      in
        ({ model | feeds = updatedFeeds } , Cmd.map FeedMsg feedCmd)


subscriptions : AppModel -> Sub Msg
subscriptions model =
  Sub.none

main =
  Html.App.program
    { init = init
    , view = view
    , update = update
    , subscriptions = subscriptions
    }
