module SoManyFeeds.Feeds exposing (Feed, defaultFeeds, selectFeeds, selectedSources, view)

import Html exposing (..)
import Html.Attributes exposing (..)
import Html.Events exposing (onClick)
import String
import List



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


selectedSources : List Feed -> List String
selectedSources feeds =
  feeds
    |> List.filter (\f -> f.selected)
    |> List.map (\f -> f.slug)


selectFeeds : List Feed -> String -> List Feed
selectFeeds feeds sources =
  let
    sourceList = String.split "," sources
  in
    List.map
        (\f -> {f | selected = List.member f.slug sourceList})
        feeds


view : List Feed -> Html nothing
view feeds =
  ul [] (List.map (listItem feeds) feeds)


listItem : List Feed -> Feed -> Html nothing
listItem feeds feed =
  li [ class (selectedClass feed) ] [
    a [ href (feedLink feeds feed) ] [
      text feed.name
    ]
  ]


feedLink : List Feed -> Feed -> String
feedLink feeds feed =
  "#" ++ (
    feeds
      |> List.filterMap (\f ->
          if f.slug == feed.slug then
            if f.selected then
              Nothing
            else
              Just f.slug

          else if f.selected then
            Just f.slug
          else
            Nothing
        )
      |> String.join ","
  )


selectedClass : Feed -> String
selectedClass feed =
  if feed.selected then
    "selected"
  else
    "not"
