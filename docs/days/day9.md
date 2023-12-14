# day 9: scala
scala attempt at day 9
## Quick links
### REPO NAV
* [`README`](./README.md)
* [`projectChangesAndNotes`](./projectChangesAndNotes.md)
* [`dayDocMaker`](./dayDocMaker.md)
### DAY FILES
* [AoC page](https://adventofcode.com/2023/day/9)
* [Input file](https://adventofcode.com/2023/day/9/input)
* [Scala file](../../src/main/scala/day9.scala)
## part 1
### strategy
1. tbh recursive data structures honestly
2. length of each array is previousLength-1
3. keep going till the difference is all 0's then go back one
4. idea for implementation
    1. list for recursed diff array
    2. each list is 1 less length than the last
    3. just get the diff between each element pairing of the last one
    4. mark off if we found a non 0 at all 
    5. when we finished getting them all without getting a non 0, we go back through grabbing the last of each
## part 2
### strategy
1. ...
