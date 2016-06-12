module SoManyFeeds.Feeds.List exposing (list)

import SoManyFeeds.Feeds.Model exposing (Feed)
import SoManyFeeds.Feeds.Messages exposing (..)
import SoManyFeeds.Feeds.Messages as Messages

import Html exposing (..)
import Html.Attributes exposing (..)
import Html.Events exposing (onClick)


list : List Feed -> Html Msg
list feeds =
  ul [] (List.map listItem feeds)

listItem : Feed -> Html Msg
listItem feed =
  li [ class (selectedClass feed) ] [
    a [ href "#", onClick (Toggle feed) ] [
      text feed.name
    ]
  ]

selectedClass : Feed -> String
selectedClass feed =
  if feed.selected then
    "selected"
  else
    "not"
