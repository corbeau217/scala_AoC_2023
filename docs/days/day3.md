# day 3: scala
  scala attempt at day 3
## part 1
### strategy
1. setup the formatting, mild refactoring, confirmed no `[a-z]` in input file
2. musings:
  a. find the x/y of first all numbers paired with the digit count
  b. speed of results depend on how many numbers and how many symbols
  c. number perspective: (cpu intensive)
    i. check surrounding spots for symbols in orderly fashion
    ii. ignore `.`
    iii. dont look down if last row/left if first column etc
    iv. when none found, discard the number, otherwise accumulate it
  d. symbol perspective: (memory intensive)
    i. location of all symbols
    ii. check surrounding locations for numbers and flip `isPartNum = true`
    iii. iterate all found parts, accumulate the result
    iv. could use binary search tree/hash map/graph/tree?
  e. 2d array of usefulness states, default none
    i. `none|sym|adjacentToSym`
    ii. find syms mark as `sym`, mark adjacents as `adjacentToSym`
    iii. find numbers, iterate checking any digit `adjacentToSym`
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
  a. at first we thought to have it run through and just compare to above/below and left, then if it found above or below or self, it could tell left about it
    * could then have as booleans or bits that we do bit stuff with
  b. then there were problems with number of comparisons due to what if we got told about multiple adjacencies
    * also the number of sstates skyrockets so we need to account for all
  c. new idea that involves taking advantage of the data format
    i. we build a list of syms and a list of possible part numbers
    ii. since they're in order of location, we take a symbol off the symbol stack, compare to next possible part.
      * if it is more than 1 row away from the part number, we junk the symbols until we're within 1 row of the next part number
      * if next part number is more than 1 row away from sym, we junk the part numbers until we are within 1 row of sym
      * likewise with col, if within 1 row of part to sym, but col is more than 1, we junk until we find one
      * if it is within 1 col and/or 1 row of the symbol, it's a part number and we mark it or save it
        - mark it means we need to keep track of index to look at but is less operations
        - stash it elsewhere is simpler code for brain easy brain compartmentalisation
    iii. for now we work with:
      * junking as just pop from list
      * saving/marking as good is add to `goodPartNumberList`
      * `lastBadSymRow`/`lastBadSymCol`/`lastBadPartRow`/`lastBadPartCol`
        - when row is changed, col is set back to max
        - they decrease from max to 0
        - if col reaches 0, row might want to decrease