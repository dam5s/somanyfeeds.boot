module SoManyFeeds.App exposing (..)

import Html exposing (..)
import Html.Attributes exposing (..)
import Html.App
import Navigation

import SoManyFeeds.Routing as Routing
import SoManyFeeds.Logo as Logo
import SoManyFeeds.Feeds as Feeds
import SoManyFeeds.Articles as Articles


apiServer : String
apiServer = "http://damo.io"


type alias AppModel =
  {
    articles : List Articles.Article,
    feeds : List Feeds.Feed,
    route : Routing.Route
  }


initialModel : Routing.Route -> AppModel
initialModel route =
  {
    articles = [],
    feeds = feedsForRoute route,
    route = route
  }


feedsForRoute : Routing.Route -> List Feeds.Feed
feedsForRoute route =
  case route of
    Routing.NoFeedsSelectedRoute -> Feeds.defaultFeeds
    Routing.SelectedFeedsRoute sources -> Feeds.selectFeeds sources
    Routing.NotFoundRoute -> Feeds.defaultFeeds


init : Result String Routing.Route -> (AppModel, Cmd Msg)
init result =
  let
    currentRoute = Routing.routeFromResult result
  in
    (initialModel currentRoute, Cmd.map ArticleMsg (Articles.fetchAll apiServer))


urlUpdate : Result String Routing.Route -> AppModel -> ( AppModel, Cmd Msg )
urlUpdate result model =
  let
    currentRoute = Routing.routeFromResult result
  in
    ({ model | route = currentRoute, feeds = feedsForRoute currentRoute }, Cmd.none)


type Msg
  = ArticleMsg Articles.Msg


view : AppModel -> Html Msg
view model =
  div [] [
    header [ id "app-header" ] [
      section [ class "content" ] [
        h1 [] [ Logo.view ],
        aside [ id "app-menu" ] [ Feeds.view model.feeds ]
      ]
    ],
    Html.App.map ArticleMsg (Articles.view (articlesToDisplay model))
  ]


articlesToDisplay: AppModel -> List Articles.Article
articlesToDisplay model =
  let
    selectedSources = Feeds.selectedSources model.feeds
  in
    if List.length selectedSources > 0 then
      Articles.selectedArticles model.articles selectedSources
    else
      [Articles.defaultArticle]



update : Msg -> AppModel -> (AppModel, Cmd Msg)
update message model =
  case message of
    ArticleMsg subMsg ->
      let (updatedArticles, articleCmd) =
        Articles.update subMsg model.articles
      in
        ({ model | articles = updatedArticles } , Cmd.map ArticleMsg articleCmd)


subscriptions : AppModel -> Sub Msg
subscriptions model =
  Sub.none


main =
  Navigation.program Routing.parser
    {
      init = init,
      view = view,
      update = update,
      urlUpdate = urlUpdate,
      subscriptions = subscriptions
    }
