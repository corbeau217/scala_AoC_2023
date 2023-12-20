# day 6: scala
scala attempt at day 6
## Quick links
### REPO NAV
* [`README`](./README.md)
* [`projectChangesAndNotes`](./projectChangesAndNotes.md)
* [`dayDocMaker`](./dayDocMaker.md)
### DAY FILES
* [day's AoC page](https://adventofcode.com/2023/day/6)
* [Input file](https://adventofcode.com/2023/day/6/input)
* [day's scala file](../../src/main/scala/day6.scala)
## part 1
### strategy
1. mathables
2. probably just hard code but maybe by now we have a parser we can just run it through?
3. boat racing thingy
4. yoink from day5 to process the input data??
5. nah just rip the numbers from it as a vector then deal
6. oh wow ez star
## part 2
### strategy
1. need to crop using strings for the length to get the digit count
2. then with the numbers we work from either side
3. try binary search for start and end??
4. idea-able
    1. we have a store for start and end
    2. then we move our start/end so we're only looking in each region needed for the thing
    3. then when start and end are the same, we have thing
5. oh my god it's mirrored
6. just /2 then you have neat data to work with
7. search first greater then deal
