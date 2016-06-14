module SoManyFeeds.Feeds exposing (Feed, Msg, defaultFeeds, selectedSources, view, update)

import Html exposing (..)
import Html.Attributes exposing (..)
import Html.Events exposing (onClick)


type alias Feed =
  {
    name : String,
    slug : String,
    selected : Bool
  }

defaultFeeds : List Feed
defaultFeeds =
  [
    { name = "Google+" , slug = "gplus" , selected = True },
    { name = "Pivotal Blog" , slug = "pivotal" , selected = True },
    { name = "Github" , slug = "github" , selected = False }
  ]

selectedSources: List Feed -> List String
selectedSources feeds =
  feeds
    |> List.filter (\f -> f.selected)
    |> List.map (\f -> f.slug)


type Msg
  = Toggle Feed


view : List Feed -> Html Msg
view feeds =
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


update : Msg -> List Feed -> (List Feed, Cmd Msg)
update action feeds =
  case action of
    Toggle feed ->
      (toggleFeed feed feeds, Cmd.none)


toggleFeed : Feed -> List Feed -> List Feed
toggleFeed feed list =
  List.map
    (\f ->
      if f.slug == feed.slug then
        { f | selected = (not f.selected) }
      else
        f
    )
    list
