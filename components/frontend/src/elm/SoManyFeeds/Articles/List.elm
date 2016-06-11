module SoManyFeeds.Articles.List exposing (list)

import Html exposing (Html, text, section, article, h1, a)
import Html.Attributes exposing (href)
import Markdown
import SoManyFeeds.Articles.Messages exposing (..)
import SoManyFeeds.Articles.Model exposing (Article)


list : List Article -> Html Msg
list articles =
  section [] (List.map listItem articles)

listItem : Article -> Html Msg
listItem model =
  article [] [
    h1 [] [
      a [ href model.link ] [ text model.title ]
    ],
    section [] [ (Markdown.toHtml [] model.content) ]
  ]
