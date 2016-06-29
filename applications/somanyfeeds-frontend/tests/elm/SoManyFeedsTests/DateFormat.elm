module SoManyFeedsTests.DateFormat exposing (tests)

import ElmTest exposing (..)
import String
import SoManyFeeds.DateFormat exposing (parseAndFormat)

tests = suite "DateFormat"
  [
    test "parseAndFormat" (
      assert
        (String.contains "Dec 31 2015 @ 3:30" (parseAndFormat "2015-12-31T10:30:59Z"))
    )
  ]
