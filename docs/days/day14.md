# day 14: scala
scala attempt at day 14
## Quick links
### REPO NAV
* [`README`](./README.md)
* [`projectChangesAndNotes`](./projectChangesAndNotes.md)
* [`dayDocMaker`](./dayDocMaker.md)
* [`stuctureNotes`](./structureNotes.md)
* [`todoItems`](./todoItems.md)
### DAY FILES
* [AoC page](https://adventofcode.com/2023/day/14)
* [Input file](https://adventofcode.com/2023/day/14/input)
* [Scala file](../../src/main/scala/day14.scala)

### COMIC PAGE
* [`"TBD" - (Fish N Chips UK) #70?`](https://www.webtoons.com/en/creator/69q8f)

## part 1
### strategy
1. feels like ye we cells
2. then we check the cell relation to each other
3. try for array (length of col/row depends direction) "max can move" starting from end and counting towards side they're coming from
4. then reset that col/row if we encounter a cube rock
5. reduce the max can move by 1 if we encounter a round rock
6. also keep a thingy for where the first round rock is in each row/col
    1. if we encounter a cube rock then this changes
    2. but then we want 3 varss for each row/col for first pos, moved number, furthest can move
    3. yeah keep it messy for now i guess
## part 2
### strategy
1. ...
