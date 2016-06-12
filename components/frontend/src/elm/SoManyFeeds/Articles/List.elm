module SoManyFeeds.Articles.List exposing (list)

import Html exposing (..)
import Html.Attributes exposing (..)
import Markdown
import SoManyFeeds.Articles.Messages exposing (..)
import SoManyFeeds.Articles.Model exposing (Article)


list : List Article -> Html Msg
list articles =
  section [ id "app-content", class "content" ] (List.map listItem articles)

listItem : Article -> Html Msg
listItem model =
  article [] [
    header [] [
      h1 [] [
        a [ href model.link ] [ text model.title ]
      ],
      h2 [ class "date" ] [ text model.date ]
    ],
    section [] [ (Markdown.toHtml [] model.content) ]
  ]
