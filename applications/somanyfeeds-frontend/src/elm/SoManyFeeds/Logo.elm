module SoManyFeeds.Logo exposing (view)

import Html exposing (Html)
import Svg exposing (..)
import Svg.Attributes exposing (..)


view : Html nothing
view =
    svg [ viewBox "0 0 730 200" ]
        [ circle
            [ cx "595"
            , cy "100"
            , r "130"
            , fill "#00BCD4"
            ]
            []
        , text_
            [ x "0"
            , y "160"
            , textAnchor "130"
            , fontFamily "Vollkorn"
            , fontSize "200"
            , fontWeight "500"
            , fill "#FFFFFF"
            ]
            [ text "damo.io" ]
        ]
