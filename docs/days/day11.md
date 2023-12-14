# day 11: scala
scala attempt at day 11
## Quick links
### REPO NAV
* [`README`](./README.md)
* [`projectChangesAndNotes`](./projectChangesAndNotes.md)
* [`dayDocMaker`](./dayDocMaker.md)
### DAY FILES
* [AoC page](https://adventofcode.com/2023/day/11)
* [Input file](https://adventofcode.com/2023/day/11/input)
* [Scala file](../../src/main/scala/day11.scala)
## part 1
### strategy
1. kinda cells?
2. just search the empty row
3. then we can just lazy mode this right? col+row dif?
4. oh then they want a minimal distance ew?
5. binary search trees get the helpful for faster
    1. would want to check to make sure our difference we're looking isnt more than others so far?
    2. maybe rings expand out?
    3. hashmap the cells then put the keys into a graph for every location's relation to another location
    4. then fill in the hashtable so it reference copies into the structure
    5. navigate ignoring empties?
## part 2
### strategy
1. ...
