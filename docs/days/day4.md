# day 4: scala
  scala attempt at day 4
## Quick links
### REPO NAV
* [`README`](./README.md)
* [`projectChangesAndNotes`](./projectChangesAndNotes.md)
* [`dayDocMaker`](./dayDocMaker.md)
### DAY FILES
* [day's AoC page](https://adventofcode.com/2023/day/4)
* [Input file](https://adventofcode.com/2023/day/4/input)
* [day's scala file](../../src/main/scala/day4.scala)
## part 1
### strategy
1. section out the portions
2. grab the numbers
3. for each winning number, check if we matched it in our scratch card numbers
4. increment the number of them
5. now get the value that gives and add to score accumulator

## part 2
### strategy
1. get list of matches for each game
2. run through to grab score from `matchCount` future games,
3. also branch on subsequent games
4. maybe build this from bottom up??
    1. has to be x number if we take last one, 
    2. second last one says x plus possible y if we had a match
    3. third last says grab the thingies based on ours
    4. this gives what all future branches are if we take "another copy" of a given thing
5. tbh y irl
    1. we got the ye
    2. it of the hello? words? yes we got in the amount of the matches dont work
    3. probably something in the list not referencing correctly or the identifier use, test the indexes
6. mild refactoring to the starting of a part, and added in comments to break up code
7. went through and labeled potential bugs
    * noticing an issue with the updating of the index of `currScratchCardIdx` so moved to after the inner loop and post inner loop code
8. current bug info (zero result):
    1. seems before accumulating the total weights?
    2. NEVER ACCUMULATES WEIGHT, ALWAYS 0 CASE MATCH `scratchCardMatchesArray(weighingIndex)`
    3. never even scratch match??
        * fixed scratch match, but still 0, another bug earlier
        * target eliminated, new target in sights
9. new bug info (still earlier):
    1. likely an earlier same bug
    2. wass indexing
    3. maybe we refactor and simplify?
10. new bug, wrong matches?
    1. oh we count the number not the weight
      * it's like bruv can u read innit?
    2. change structure so that instead of this weight rubbish, we have an array of the number of times a scratch card is used, then timess the two
    3. at this point, break, scrub, try again
11. unfixable of unfixable, it spaghetti now
