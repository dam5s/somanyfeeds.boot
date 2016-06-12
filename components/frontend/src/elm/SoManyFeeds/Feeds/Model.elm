module SoManyFeeds.Feeds.Model exposing (..)


type alias Feed =
  { name : String
  , slug : String
  , selected : Bool
  }

defaultFeeds : List Feed
defaultFeeds = [
  { name = "Google+" , slug = "gplus" , selected = True },
  { name = "Pivotal Blog" , slug = "pivotal" , selected = True },
  { name = "Github" , slug = "github" , selected = False }
  ]
