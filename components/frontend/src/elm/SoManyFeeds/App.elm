module SoManyFeeds.App exposing (..)

import Html exposing (..)
import Html.Attributes exposing (..)
import Html.App

import SoManyFeeds.Articles.Model exposing (Article)
import SoManyFeeds.Articles.Model as ArticleModel
import SoManyFeeds.Articles.List as ArticleList
import SoManyFeeds.Articles.Messages as ArticleMessages
import SoManyFeeds.Articles.Update as ArticleUpdate
import SoManyFeeds.Articles.Commands as ArticleCmd

import SoManyFeeds.Feeds.Model exposing (Feed, defaultFeeds)
import SoManyFeeds.Feeds.List as FeedList
import SoManyFeeds.Feeds.Messages as FeedMessages
import SoManyFeeds.Feeds.Update as FeedUpdate


type alias AppModel =
  {
    articles : List Article,
    feeds : List Feed
  }

initialModel : AppModel
initialModel =
  {
    articles = [],
    feeds = defaultFeeds
  }

init : (AppModel, Cmd Msg)
init =
  (initialModel, Cmd.map ArticleMsg ArticleCmd.fetchAll)

type Msg
  = ArticleMsg ArticleMessages.Msg
  | FeedMsg FeedMessages.Msg

view : AppModel -> Html Msg
view model =
  div [] [
    header [ id "app-header" ] [
      section [ class "content" ] [
        h1 [] [
          img [ src "logo.svg", alt "damo.io" ] []
        ],
        aside [ id "app-menu" ] [
          Html.App.map FeedMsg (FeedList.list model.feeds)
        ]
      ]
    ],
    Html.App.map ArticleMsg (ArticleList.list (articlesToDisplay model))
  ]


articlesToDisplay: AppModel -> List Article
articlesToDisplay model =
  selectedArticles model.articles (selectedSources model.feeds)

selectedArticles: List Article -> List String -> List Article
selectedArticles allArticles selectedSources =
 allArticles
  |> List.filter (isSelected selectedSources)

isSelected: List String -> Article -> Bool
isSelected sources article =
  List.any (\s -> s == article.source) sources

selectedSources: List Feed -> List String
selectedSources feeds =
  feeds
    |> List.filter (\f -> f.selected)
    |> List.map (\f -> f.slug)


update : Msg -> AppModel -> (AppModel, Cmd Msg)
update message model =
  case message of
    ArticleMsg subMsg ->
      let (updatedArticles, articleCmd) =
        ArticleUpdate.update subMsg model.articles
      in
        ({ model | articles = updatedArticles } , Cmd.map ArticleMsg articleCmd)
    FeedMsg subMsg ->
      let (updatedFeeds, feedCmd) =
        FeedUpdate.update subMsg model.feeds
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