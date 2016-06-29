module SoManyFeedsTests.DateFormat exposing (tests)

import ElmTest exposing (..)
import String
import SoManyFeeds.DateFormat exposing (parseAndFormat)

tests = suite "DateFormat"
  [
    test "parseAndFormat formats date" (
        assert
          (String.contains "Dec 31 2015 @ " (parseAndFormat "2015-12-31T10:30:59Z"))
    ),
    test "parseAndFormat formats time" (
        assert
          (String.contains ":30" (parseAndFormat "2015-12-31T10:30:59Z"))
    )
  ]
