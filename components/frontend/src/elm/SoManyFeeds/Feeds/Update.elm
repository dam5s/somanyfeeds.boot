module SoManyFeeds.Feeds.Update exposing (update)

import SoManyFeeds.Feeds.Messages exposing (..)
import SoManyFeeds.Feeds.Model exposing (Feed)

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
