module SoManyFeeds.Feeds.List exposing (list)

import SoManyFeeds.Feeds.Model exposing (Feed)
import SoManyFeeds.Feeds.Messages exposing (..)
import SoManyFeeds.Feeds.Messages as Messages

import Html exposing (Html, text, ul, li, a)
import Html.Attributes exposing (href)
import Html.Events exposing (onClick)


list : List Feed -> Html Msg
list feeds =
  ul [] (List.map listItem feeds)

listItem : Feed -> Html Msg
listItem feed =
  li [] [
    a [ href "#", onClick (Toggle feed) ] [
      text feed.name
    ],
    text (toString feed.selected)
  ]
