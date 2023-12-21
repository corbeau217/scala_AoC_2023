# day 3: scala
  scala attempt at day 3
## Quick links
### REPO NAV
* [`README`](./README.md)
* [`projectChangesAndNotes`](./projectChangesAndNotes.md)
* [`dayDocMaker`](./dayDocMaker.md)
* [`stuctureNotes`](./structureNotes.md)
* [`todoItems`](./todoItems.md)
### DAY FILES
* [day's AoC page](https://adventofcode.com/2023/day/3)
* [Input file](https://adventofcode.com/2023/day/3/input)
* [day's scala file](../../src/main/scala/day3.scala)

### COMIC PAGE
* [`"GEAR RATIOS" - (Fish N Chips UK) #59`](https://www.webtoons.com/en/canvas/advent-of-code/gear-ratios/viewer?title_no=713188&episode_no=59)

> [!WARNING]  
> Day 3 was completed before file handling was implemented

## part 1
### strategy
1. setup the formatting, mild refactoring, confirmed no `[a-z]` in input file
2. musings:
    1. find the x/y of first all numbers paired with the digit count
    2. speed of results depend on how many numbers and how many symbols
    3. number perspective: (cpu intensive)
        1. check surrounding spots for symbols in orderly fashion
        2. ignore `.`
        3. dont look down if last row/left if first column etc
        4. when none found, discard the number, otherwise accumulate it
    4. symbol perspective: (memory intensive)
        1. location of all symbols
        2. check surrounding locations for numbers and flip `isPartNum = true`
        3. iterate all found parts, accumulate the result
        4. could use binary search tree/hash map/graph/tree?
    5. 2d array of usefulness states, default none
        1. `none|sym|adjacentToSym`
        2. find syms mark as `sym`, mark adjacents as `adjacentToSym`
        3. find numbers, iterate checking any digit `adjacentToSym`
3. ...onnhold workinng on day 4
4. ideas for retry:
    * day3: loop all and mark if symbol or digit or none
        - stash digit locations and length
        - loop all digit locations, check adjacency
        - what if each cell had a memory of who wants to know
        - loop all symbols then tell the knowers
            * what if we compare number of symbols to number of parts before doing this to see which is faster?
    * day3b: lists
        - list of symbols
        - list of part numbers
            * isDigit
            * if is, then check last digit was connected to this digit and increase its count if is
        - need symbol to know about candidates
5. forgor to trycatch our handle day so we didnt skip back to main
6. newer ideas
    1. at first we thought to have it run through and just compare to above/below and left, then if it found above or below or self, it could tell left about it
        * could then have as booleans or bits that we do bit stuff with
    2. then there were problems with number of comparisons due to what if we got told about multiple adjacencies
        * also the number of sstates skyrockets so we need to account for all
    3. new idea that involves taking advantage of the data format
        1. we build a list of syms and a list of possible part numbers
        2. since they're in order of location, we take a symbol off the symbol stack, compare to next possible part.
            * if it is more than 1 row away from the part number, we junk the symbols until we're within 1 row of the next part number
            * if next part number is more than 1 row away from sym, we junk the part numbers until we are within 1 row of sym
            * likewise with col, if within 1 row of part to sym, but col is more than 1, we junk until we find one
            * if it is within 1 col and/or 1 row of the symbol, it's a part number and we mark it or save it
                - mark it means we need to keep track of index to look at but is less operations
                - stash it elsewhere is simpler code for brain easy brain compartmentalisation
        3. for now we work with:
            * junking as just pop from list
            * saving/marking as good is add to `goodPartNumberList`
            * `lastBadSymRow`/`lastBadSymCol`/`lastBadPartRow`/`lastBadPartCol`
                - when row is changed, col is set back to max
                - they decrease from max to 0
                - if col reaches 0, row might want to decrease
7. started getting painful with the whole idea of backlog and everything so we just settled for n^2
    * kinda ended up n^3 really, bc the contains thing
8. omg u done it
## part 2
### strategy
1. honesstly just copy the idea from part 1, dont overcomplicate it
2. change the match case to search for `'*'` instead of symbols, then find if the numbers are adjacent
3. what if we loop all symbols in order and break from loop when we find one that's too far?
    * spooky sandwich with tuna, we're overcomplicating again
4. ladies and gentlemen, we gottem