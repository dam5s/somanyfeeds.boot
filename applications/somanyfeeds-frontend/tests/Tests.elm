module Tests exposing (..)

import Test exposing (..)
import Expect
import String
import SoManyFeedsTests.DateFormat
import SoManyFeedsTests.Feeds
import SoManyFeedsTests.Articles


all : Test
all =
    describe "So Many Feeds"
        [ SoManyFeedsTests.DateFormat.tests
        , SoManyFeedsTests.Feeds.tests
        , SoManyFeedsTests.Articles.tests
        ]
