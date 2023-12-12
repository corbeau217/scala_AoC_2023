# dayDocMaker notes and info
# ![C](https://img.shields.io/badge/c-%2300599C.svg?style=for-the-badge&logo=c&logoColor=white)

## Quick links
* [`README`](./README.md)
* [`projectChangesAndNotes`](./projectChangesAndNotes.md)
* **`dayDocMaker`**

## Todo thingables
* need to add this to each `day*.md`:
  ```md
  ## Quick links
  * [`README`](./README.md)
  * [`projectChangesAndNotes`](./projectChangesAndNotes.md)
  * [`dayDocMaker`](./dayDocMaker.md)
  * [day's AoC page](https://adventofcode.com/2023/day/1)
  * [day's scala file](../../src/main/scala/day1.scala)
  ```

## Ideas
1. need to figure out updating existing text
  a. lazy mode:
    * make a copy of the file in a char array buffer
    * modify a part of that char array
    * write whole buffer back to file
  b. abuse 