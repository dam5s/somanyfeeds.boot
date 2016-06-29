module SoManyFeedsTests.Tests exposing (..)

import SoManyFeedsTests.Feeds as FeedsTests
import SoManyFeedsTests.DateFormat as DateFormatTests
import ElmTest exposing (..)


fullSuite = suite "SoManyFeeds"
  [
    FeedsTests.tests,
    DateFormatTests.tests
  ]

main : Program Never
main =
  runSuite fullSuite
