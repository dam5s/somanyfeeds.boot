module SoManyFeeds.Articles.Messages exposing (..)

import Http
import SoManyFeeds.Articles.Model exposing (ArticleList)

type Msg
  = FetchAllDone ArticleList
  | FetchAllFail Http.Error
