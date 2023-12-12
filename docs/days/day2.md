# day 2: scala
  scala attempt at day 2
## Quick links
* [`README`](./README.md)
* [`projectChangesAndNotes`](./projectChangesAndNotes.md)
* [`dayDocMaker`](./dayDocMaker.md)
* [day's AoC page](https://adventofcode.com/2023/day/2)
* [day's scala file](../../src/main/scala/day2.scala)
## part 1
### strategy
1. ...thoughts:
    * this is dangerously close to monte carlo/las vegas algo
    * seems random chance build out O(n^2) for all possible then lookup
    * that's lazy, we should parser
    * syntax analysis ez with packrat parser
    * or we match case but that kinda weird
2. packrat parser solution
```
var number = "[0-9]+"
// bluerdgn 
var colourName = "blue|red|green"

gameNumber:  ("Game " ~> number <~ ": ")
numOfColour: number ~ colourName

drawingIter: numOfColour ~ (", "~> numOfColour)*

gameData:    gameNumber ~ drawingIter ~ ("; " ~> drawingIter)*

```
3. figured out file handling
4. went for regex usage
    1. grab the game number (debugging reasons)
    2. grab the game iterations list
    3. grabe the selection list out of a given iteration
    4. check the number based on colour
    5. encounter errors bc i didnt name things well
    6. realise i shadowed the safety checking variable every time bc i used the same name for two variabless oopsie
5. then just bug fixing and reformatting

## part 2
### stategy
1. copypasta part 1:
    1. add in `List[(String,Int)]` for `largestColorSelectionSoFar` (initialise int to `0`)
    2. then get `gamePower = ...`
    3. accumulate `gamePower`
2. prepared the file for conversion, taking short break before continuing for the brain refresh cache