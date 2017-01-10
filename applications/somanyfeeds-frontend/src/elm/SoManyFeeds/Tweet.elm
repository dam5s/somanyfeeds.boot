module SoManyFeeds.Tweet exposing (display)

import Html exposing (Attribute, Html, section)
import Html.Attributes exposing (class)
import Json.Encode as Encode
import VirtualDom
import Regex


display : String -> Html msg
display content =
    section [ innerHtml <| createLinks content ] []


innerHtml : String -> Attribute msg
innerHtml =
    VirtualDom.property "innerHTML" << Encode.string


createLinks : String -> String
createLinks tweet =
    let
        mentionRegex =
            Regex.regex "(^|\\s)@([A-Za-z_]+[A-Za-z0-9_]+)"
    in
        Regex.replace Regex.All mentionRegex mentionLink tweet


mentionLink : Regex.Match -> String
mentionLink match =
    case match.submatches of
        maybeLeading :: maybeHandle :: _ ->
            case maybeHandle of
                Nothing ->
                    match.match

                Just submatch ->
                    (Maybe.withDefault "" maybeLeading)
                        ++ "<a href=\"https://twitter.com/"
                        ++ submatch
                        ++ "\">@"
                        ++ submatch
                        ++ "</a>"

        _ ->
            match.match
