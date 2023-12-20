# day 7: scala
scala attempt at day 7
## Quick links
### REPO NAV
* [`README`](./README.md)
* [`projectChangesAndNotes`](./projectChangesAndNotes.md)
* [`dayDocMaker`](./dayDocMaker.md)
### DAY FILES
* [day's AoC page](https://adventofcode.com/2023/day/7)
* [Input file](https://adventofcode.com/2023/day/7/input)
* [day's scala file](../../src/main/scala/day7.scala)
## part 1
### strategy
1. card hands
2. loop all and sort, each
3. then parse for what hand type it is
4. then ssort the hand types
5. sounds knapsacky
6. maybe turn the card sym into a card rank number
7. then we can sort by value of a digit (use hex)
8. after sorting all hand's cards we can try to sort hand values
    * try hash the hand type + card values to make the hand value
    * then we can sort by hand hashed value
9. finally just grab the things as needed
10. AAAAAAAAAAAAAAAAAAAAAAAAAND THAT'S A WRAP PEEEEEOPLLLLLLE
## part 2
### strategy
1. yeah we just yoinked the previous format and changed the regex
2. did ssome scribblings on paper to figure out the regex
