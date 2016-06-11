module SoManyFeeds.Articles.Commands exposing (fetchAll)

import Http
import Task exposing (Task)
import Json.Decode as Decode exposing ((:=))
import SoManyFeeds.Articles.Model exposing (Article, ArticleList)
import SoManyFeeds.Articles.Messages exposing (..)

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
  Decode.object3
    Article
    ("title" := Decode.string)
    ("link" := Decode.string)
    ("content" := Decode.string)
