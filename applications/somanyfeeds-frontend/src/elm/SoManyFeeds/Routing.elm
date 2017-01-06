module SoManyFeeds.Routing exposing (Route(..), parseRoute)

import UrlParser exposing (..)
import Navigation
import String


type Route
    = NoFeedsSelectedRoute
    | SelectedFeedsRoute String
    | NotFoundRoute


matchers : Parser (Route -> a) a
matchers =
    oneOf
        [ map NoFeedsSelectedRoute (s "")
        , map SelectedFeedsRoute (string)
        ]


parseRoute : Navigation.Location -> Route
parseRoute location =
    Maybe.withDefault NotFoundRoute (parseHash matchers location)
