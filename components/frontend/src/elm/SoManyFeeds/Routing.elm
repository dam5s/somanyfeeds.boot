module SoManyFeeds.Routing exposing (Route(..), routeFromResult, parser)

import UrlParser exposing (..)
import Navigation
import String

type Route
    = DefaultFeedsRoute
    | SelectedFeedsRoute String
    | NotFoundRoute


matchers : Parser (Route -> a) a
matchers =
    oneOf [
        format DefaultFeedsRoute (s ""),
        format SelectedFeedsRoute (string)
    ]


hashParser : Navigation.Location -> Result String Route
hashParser location =
    location.hash
        |> String.dropLeft 1
        |> parse identity matchers


parser : Navigation.Parser (Result String Route)
parser =
    Navigation.makeParser hashParser


routeFromResult : Result String Route -> Route
routeFromResult result =
    case result of
        Ok route ->
            route
        Err string ->
            NotFoundRoute
