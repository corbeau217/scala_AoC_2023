# day 8: scala
scala attempt at day 8
## Quick links
### REPO NAV
* [`README`](./README.md)
* [`projectChangesAndNotes`](./projectChangesAndNotes.md)
* [`dayDocMaker`](./dayDocMaker.md)
* [`stuctureNotes`](./structureNotes.md)
* [`todoItems`](./todoItems.md)
### DAY FILES
* [day's AoC page](https://adventofcode.com/2023/day/8)
* [Input file](https://adventofcode.com/2023/day/8/input)
* [day's scala file](../../src/main/scala/day8.scala)

### COMIC PAGE
* [`"HAUNTED WASTELAND" - (Fish N Chips UK) #64`](https://www.webtoons.com/en/canvas/advent-of-code/haunted-wasteland/viewer?title_no=713188&episode_no=64)

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
6. welp, guess we used a treemap, that was kinda funky to use i like it
## part 2
### strategy
1. turn each node into a list
2. we grab all the ones ending in A
3. then we run through all of them and apply instruction
4. repeating until we have nothing ending in `Z`
5. idea
    1. have it that each iteration we assume we make it to the end
    2. while looping over all of them to apply instruction
        * check if it doesnt end in `Z`
        * say we need another iteration still
    3. when found a next that didnt end in `Z` we do an iteration
6. idea another
    1. before building our current node
    2. we loop over the elements until we find one not ending in `A`
        * could just `indexWhere` with a pattern asking for not ending in `A`
    3. counting how many there are
    4. then we make a states array the size of the number we found
7. bruteforce solution done
    1. currently unconfirmed
    2. chars in console reference:
        1. `m` - all nodes match their first node
        2. `.` - `1000000` instructions performed on all nodes
        3. `'` - every `10` of `.`
        4. `*` - every `10` of `'`
8. new idea:
    1. run on each to see if it returns to its first element
    2. also then when it starts looping and what the period is for that looping
    3. find each time it lands on a Z for a given node
    4. then find how many steps it takes to make them all land on the node with math
    5. then say it
