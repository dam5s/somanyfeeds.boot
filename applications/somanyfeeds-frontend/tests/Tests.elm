module Tests exposing (..)

import Test exposing (..)
import Expect
import String
import SoManyFeedsTests.DateFormat
import SoManyFeedsTests.Feeds


all : Test
all =
    describe "So Many Feeds"
        [ SoManyFeedsTests.DateFormat.tests
        , SoManyFeedsTests.Feeds.tests
        ]
