# day 8: scala
scala attempt at day 8
## Quick links
### REPO NAV
* [`README`](./README.md)
* [`projectChangesAndNotes`](./projectChangesAndNotes.md)
* [`dayDocMaker`](./dayDocMaker.md)
### DAY FILES
* [day's AoC page](https://adventofcode.com/2023/day/8)
* [Input file](https://adventofcode.com/2023/day/8/input)
* [day's scala file](../../src/main/scala/day8.scala)
## part 1
### strategy
1. it's the `AAA = (BBB, DDD)` thingy
2. Binary tree/state machine??
    * this idea but we tunnel vision the state machine idea
    * then we minimise the NFA using the algorithm thingy
3. idea:
    1. just di-graph the connections
    2. then get minimal tree from ZZZ?
        * dijkstra?
4. idea2:
    1. binary search tree the whole thing
    2. then find the depth of ZZZ?
    3. gg ez
5. idea:
    * extract the tree to get where it traverses
    * maybe construct a graph that has `nodeLeft` and `nodeRight` to navigate with
    * there's cycles in the graph
    * use the instructions to say where we're going in the graph
    * loop until we find it
## part 2
### strategy
1. ...
