module SoManyFeeds.App exposing (..)

import Html exposing (..)
import Html.Attributes exposing (..)
import Navigation
import SoManyFeeds.Routing as Routing
import SoManyFeeds.Logo as Logo
import SoManyFeeds.Feeds as Feeds
import SoManyFeeds.Articles as Articles


apiServer : String
apiServer =
    "http://localhost:8080"


type alias AppModel =
    { articles : List Articles.Article
    , feeds : List Feeds.Feed
    }


initialModel : Routing.Route -> AppModel
initialModel route =
    { articles = []
    , feeds = feedsForRoute route
    }


feedsForRoute : Routing.Route -> List Feeds.Feed
feedsForRoute route =
    case route of
        Routing.NoFeedsSelectedRoute ->
            Feeds.defaultFeeds

        Routing.SelectedFeedsRoute sources ->
            Feeds.selectFeeds sources

        Routing.NotFoundRoute ->
            Feeds.defaultFeeds


init : Navigation.Location -> ( AppModel, Cmd Msg )
init location =
    let
        currentRoute =
            Routing.parseRoute location
    in
        ( initialModel currentRoute, Cmd.map ArticleMsg (Articles.fetchAll apiServer) )


type Msg
    = ArticleMsg Articles.Msg
    | UrlChange Navigation.Location


view : AppModel -> Html Msg
view model =
    div []
        [ header [ id "app-header" ]
            [ section [ class "content" ]
                [ Logo.view
                , aside [ id "app-menu" ] [ Feeds.view model.feeds ]
                ]
            ]
        , Html.map ArticleMsg (Articles.view (articlesToDisplay model))
        ]


articlesToDisplay : AppModel -> List Articles.Article
articlesToDisplay model =
    let
        selectedSources =
            Feeds.selectedSources model.feeds
    in
        if List.length selectedSources > 0 then
            Articles.selectedArticles model.articles selectedSources
        else
            [ Articles.defaultArticle ]


update : Msg -> AppModel -> ( AppModel, Cmd Msg )
update message model =
    case message of
        ArticleMsg subMsg ->
            let
                ( updatedArticles, articleCmd ) =
                    Articles.update subMsg model.articles
            in
                ( { model | articles = updatedArticles }, Cmd.map ArticleMsg articleCmd )

        UrlChange location ->
            let
                currentRoute =
                    Routing.parseRoute location
            in
                ( { model | feeds = feedsForRoute currentRoute }, Cmd.none )


main =
    Navigation.program UrlChange
        { init = init
        , view = view
        , update = update
        , subscriptions = (\_ -> Sub.none)
        }
