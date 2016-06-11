module SoManyFeeds.Articles.Update exposing (..)

import SoManyFeeds.Articles.Messages exposing (..)
import SoManyFeeds.Articles.Model exposing(Article)

update : Msg -> List Article -> (List Article, Cmd Msg)
update action articles =
  case action of
    FetchAllDone newArticleList ->
      (newArticleList.articles, Cmd.none)
    FetchAllFail error ->
      (articles, Cmd.none)
