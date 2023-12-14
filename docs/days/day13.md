# day 13: scala
scala attempt at day 13
## Quick links
### REPO NAV
* [`README`](./README.md)
* [`projectChangesAndNotes`](./projectChangesAndNotes.md)
* [`dayDocMaker`](./dayDocMaker.md)
### DAY FILES
* [AoC page](https://adventofcode.com/2023/day/13)
* [Input file](https://adventofcode.com/2023/day/13/input)
* [Scala file](../../src/main/scala/day13.scala)
## part 1
### strategy
1. sounds syntax analysis
2. this is more than one chunkable
3. need to break into seperate grids
4. then find the line were it's a common sub string from that point outwards
5. idea for efficiency
    1. sort the columns/rows paired with their colIdx/rowIdx
    2. pair them up if they're there's multiple of the same column/row
    3. mark when the pairs have colIdx/rowIdx within one of each other and count out from those pairings?
    4. find the longest period where the pairings are mirrored in distance from each other 
## part 2
### strategy
1. ...
