# day 10: scala
scala attempt at day 10
## Quick links
### REPO NAV
* [`README`](./README.md)
* [`projectChangesAndNotes`](./projectChangesAndNotes.md)
* [`dayDocMaker`](./dayDocMaker.md)
* [`stuctureNotes`](./structureNotes.md)
* [`todoItems`](./todoItems.md)
### DAY FILES
* [AoC page](https://adventofcode.com/2023/day/10)
* [Input file](https://adventofcode.com/2023/day/10/input)
* [Scala file](../../src/main/scala/day10.scala)

### COMIC PAGE
* [`"PIPE MAZE" - (Fish N Chips UK) #66`](https://www.webtoons.com/en/canvas/advent-of-code/pipe-maze/viewer?title_no=713188&episode_no=66)

## part 1
### strategy
1. probably needs to be the syntax analysis tbh
2. case classes help at this point tbh
3. or OR IdeA:
    1. just find the S in the grid,
    2. then march around with 2 position values until we find a place where `col1==col2&&row1==row2`
4. so new idea:
    1. break it into 2d array of char
    2. turn the char into their case class value for the "CellType"
    3. start the navigating of the thingy
    4. look up those maze racer things for their style of searching?? (doesnt work tho)
5. details of the pieces
    - The pipes are arranged in a two-dimensional grid of tiles:
        * `|` is a vertical pipe connecting north and south.
        * `-` is a horizontal pipe connecting east and west.
        * `L` is a 90-degree bend connecting north and east.
        * `J` is a 90-degree bend connecting north and west.
        * `7` is a 90-degree bend connecting south and west.
        * `F` is a 90-degree bend connecting south and east.
        * `.` is ground; there is no pipe in this tile.
        * `S` is the starting position of the animal; there is a pipe on this tile, but your sketch doesn't show what shape the pipe has.
6. newer idea
    1. break out to cells
    2. traverse for the start location
    3. basically `march` function that advances the location of the two things
        1. moves to their next location
        2. prepares their next move
    4. `hasNext` just checks if 
        1. both the points are at the same location OR
        2. will swap places on prepared next move (bc they connect to each other in their next movement)
    5. have a thing that just prunes the dead branches of it (elbows into nothingness etc)
    6. each cell has a step number, start is 0
        1. we keep the step numberr in our iterator for our grid navigation
    7. find any cell that leads to nothing
        1. test for legality of cells 
            * each cell has 2 connections
            * add in the cell coords of either end
            * if only one connection, we prune
                - prune is we mark a cell in the main grid as junk
                - then after marking all bad cells junk we can check our grid that all the connections are legal still
7. cell types
    1. has col/row
    2. is the type it is
    3. allows matching to get what the connecting spots are
8. have a checker for a given location take a grid + location
    1. function for get the connection spots returns tuple2
        1. we use the tuple2 of locations to then
            1. check is legal location ( > -1 && < size )
            2. then check those exist in the collapsed legal lists
                * use binary search since they're ordered
            3. instead we can just check in our full grid
    2. if they're references attached to a cell / locations
        1. we could just access and check if it's not ground AND
        2. accepts our location as a connection
        3. this could be a function on the tile to provide the location
            * pass by need
            * kinda the previous thing anyway
    3. `collapsedLegalGrid` is used for which cells we choose to update
        1. need to make 1d copy for sorting by legal count
            1. made as 1d vector means we can just create an ordered copy
            2. or we could just use map function to make the ordered copy in 1d
        2. then we loop over legal count `< 2` and remove them from our `collapsedLegalGrid`
            1. tell `fullGrid` it's now a ground tile
            2. find in our collapsed grid where it is and make that not part of the thing anymore
    4. `fullGrid` is the overall grid of cells
        1. when cells are made void, we access this and change them to be dirt instead at the location
        2. fixed size
    5. `(if(cell.adj1.acceptsMovingFrom(cell)) 1 else 0) + (if(cell.adj2.acceptsMovingFrom(cell)) 1 else 0)` for number of legal connections
9. maybe just fixed width/height the grids till later instead of collapsed legal grid needing binary searching?
    1. gives back the `O(1)` accessing a location and easier testing for legality
    2. instead of testing index within bounds, try/catch, with catch as debug print we went out of range on edge tile connection, then say it doesnt accept
10. i wrote notes about this problem ages ago but i think i threw it out rip
    * idea was that each tile would be a rotation clockwise
    * this then gives us the reference points of view from one connection to the other
    * but then we need to do so much more math
11. added in a thing that prints the grid, our code wasnt working bc we forgor test file
12. [MAZING ALGORITHM VISUALISER THINGY??](https://github.com/hisham-maged10/path-finder)
13. newest idea
    1. give all cells the two locations they connect
    2. iterate all and check that for the two locations that the cell of that location has it's location in its locations
        1. when it is, we mark it as good
        2. otherwise add it to the list of cells to be handled for being illegal
    3. then iterate over illegal cells while we have illegal cells,
        1. when a cell is being popped from queue to handle, we add the adjacent cells to update queue
        2. then iterate the update queue to update cells and add to our illegal queue any that need to be made illegal
14. need to either
    1. copy maze for the enable traversed tiles
    2. or just keep last spot we were and be lazy?
15. finding furtherest?
    1. we just traverse full loop back to start then /2
    2. we keep two thingers and hope for no bug
16. need a function for get other adjacency
17. we gottem
## part 2
### strategy
1. would need part1 as hinted at method consisting of the stack of locations
2. then also just complete the loop instead of stop when both reach, like we thought of for /2 solution
3. then some sort of [even odd rule](https://en.wikipedia.org/wiki/Even%E2%80%93odd_rule) or rather to deal with the internal/external
4. rotationing of the points to say if we went 360 degrees or 0 or less than 360?
5. turning into points at the corners and lines, then keeping when there's space between lines?
6. is the focus on cutting out the exterior or the interior
7. [ray cast](https://www.youtube.com/watch?v=RSXM9bgqxJM)
    * treemap the locations
    * find the ones that are more or less than
    * maybe we list of x values/y values to binary search them
    
