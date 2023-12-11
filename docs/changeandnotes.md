# Changes and Notes

## changes
* `[05/12/23]` ---> changed day structure to just provide lines, handle day does the grab file
  - *need to add change to days 1/2/3*
* `[05/12/23]` ---> restructured readme days reference to html table
* `[05/12/23]` ---> created this file to be where we put our changes
* `[05/12/23]` ---> ...

## Ideas
* day4: loop all and mark if symbol or digit or none
  - stash digit locations and length
  - loop all digit locations, check adjacency
  - what if each cell had a memory of who wants to know
  - loop all symbols then tell the knowers
* day4b: lists
  - list of symbols
  - list of part numbers
    * isDigit
    * if is, then check last digit was connected to this digit and increase its count if is
  - need symbol to know about candidates