# day 10: scala
scala attempt at day 10
## Quick links
### REPO NAV
* [`README`](./README.md)
* [`projectChangesAndNotes`](./projectChangesAndNotes.md)
* [`dayDocMaker`](./dayDocMaker.md)
### DAY FILES
* [AoC page](https://adventofcode.com/2023/day/10)
* [Input file](https://adventofcode.com/2023/day/10/input)
* [Scala file](../../src/main/scala/day10.scala)
## part 1
### strategy
1. probably needs to be the syntax analysis tbh
2. case classes help at this point tbh
3. or OR IdeA:
    1. just find the S in the grid,
    2. then march around with 2 position values until we find a place where `col1==col2&&row1==row2`
4. so new idea:
    1. break it into 2d array of char
    2. turn the char into their case class value for the "CellType"
    3. start the navigating of the thingy
    4. look up those maze racer things for their style of searching?? (doesnt work tho)
## part 2
### strategy
1. ...
