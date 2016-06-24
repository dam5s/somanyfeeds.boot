module SoManyFeeds.Logo exposing (view)

import Html exposing (Html)
import Svg exposing(..)
import Svg.Attributes exposing (..)

view : Html nothing
view =
  svg [ viewBox "0 0 1000 200" ] [
    circle [
      cx "595",
      cy "100",
      r "130",
      fill "#00BCD4"
    ] [],
    text' [
      x "0",
      y "160",
      textAnchor "130",
      fontFamily "Roboto Slab",
      fontSize "180",
      fontWeight "500",
      fill "#FFFFFF"
    ] [ text "damo.io" ]
  ]
