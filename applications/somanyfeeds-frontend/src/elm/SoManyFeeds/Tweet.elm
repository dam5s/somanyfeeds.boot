module SoManyFeeds.Tweet exposing (display)

import Html exposing (Attribute, Html, section)
import Html.Attributes exposing (class)
import Json.Encode as Encode
import VirtualDom


display : String -> Html msg
display content =
    if isRetweet content then
        section [ class "retweet", innerHtml (retweetContent content) ] []
    else
        section [ innerHtml content ] []


isRetweet : String -> Bool
isRetweet =
    String.startsWith "RT"


innerHtml : String -> Attribute msg
innerHtml =
    VirtualDom.property "innerHTML" << Encode.string


retweetContent : String -> String
retweetContent =
    String.dropLeft 3
