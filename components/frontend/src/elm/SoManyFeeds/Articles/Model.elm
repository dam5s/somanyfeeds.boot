module SoManyFeeds.Articles.Model exposing (..)

type alias ArticleList =
  { articles : (List Article)
  }

type alias Article =
  { title : String
  , link : String
  , content : String
  , date : String
  , source : String
  }
