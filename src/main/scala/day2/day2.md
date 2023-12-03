# day 2: scala
  scala attempt at day 2
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