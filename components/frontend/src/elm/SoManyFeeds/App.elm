module SoManyFeeds.App exposing (..)

import Html exposing (Html, text, div, header, h1)
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
    header [] [
      h1 [] [ text "SoManyFeeds" ],
      Html.App.map FeedMsg (FeedList.list model.feeds)
    ],

    Html.App.map ArticleMsg (ArticleList.list model.articles)
  ]

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
