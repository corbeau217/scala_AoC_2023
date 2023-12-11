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
  * day3b: lists
    - list of symbols
    - list of part numbers
      * isDigit
      * if is, then check last digit was connected to this digit and increase its count if is
    - need symbol to know about candidates
5. forgor to trycatch our handle day so we didnt skip back to main