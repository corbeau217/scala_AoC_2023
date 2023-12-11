# Project changes and notes

## Quick links
* [`README`](./README.md)
* **`projectChangesAndNotes`**
* [`dayDocMaker`](./dayDocMaker.md)

## Structure notes
<details><summary><i>[show/hide]</i></summary>

* `${workspaceFolder}/.vscode/`
  - vscode files
  - *settings etc so we keep the workspace stuff*
* `${workspaceFolder}/data/`
  - input files
  - *this is where our scala files will look for their daily input files, so we can blacklist those files since AoC doesn't like having their datasets redistributed*
* `${workspaceFolder}/docs/`
  - documentation files
  - *for neatness and so we follow convention of where documentation is*
* `${workspaceFolder}/project/`
  - sbt/scala related files
  - *we include this so sbt/metals remembers that it's a scala project*
* `${workspaceFolder}/src/`
  - source files
  - *inside here is where our source files are, to follow convention of typical compilers i'm used to*
  - Subfolders:
    * `${workspaceFolder}/src/c/`
      - location for C source files
      - *simple scripts for file management mostly, but once we catch up we may attempt it in C later*
    * `${workspaceFolder}/src/main/scala/`
      - location for scala source files
      - *sbt wants us to use this specific folder and i forget how to change this. worrying about it too much meant i'd take away from the coding part so that's a later studiable*
      - Subfolders:
        * `${workspaceFolder}/src/main/scala/day*.scala`
          - each day's specific scala file
          - *using this format because then it's matching the markdown files meaning each is paired with its own scala file for compartmentalisation*
</details>

## major changes by date
<details><summary><i>[show/hide]</i></summary>

### `[05/12/23]`
* changed day structure to just provide lines, handle day does the grab file
  - *need to add change to days 1/2/3*
* restructured readme days reference to html table
* created this file to be where we put our changes
### `[10/12/23]`
* new day new fresh brain space, le-go
* oopsiewoopsie, wasnt meant to have the input files, fixing that
### `[11/12/23]`
* confirmed that repo likes the new file structure for input files
* completed moving the markdown files for each day, and restructured file placement
* confirmed removing the input files from repo 
* also changed `day1.scala` over to using the input files instead of statically defined content
### `[12/12/23]`
* updated project file structure notes
* restructured C files to be in `src/c/`
* cleaned up `.gitignore`
* fixed up the issues with ordered listss and their sublists across markdown files needing additional indentation
</details>

## todo thingables
* change each day to add its handle to a list for main to use before `main(Array[String]):Unit`?
* update the day markdowns
  - to have a link back to `readme.md`
  - to have a link to the advent of code page they're for
  - can do this using `src/dayDocMaker.c` for the ones that aren't yet made, maybe even for the made ones
* add link for `dayDocMaker.md` to the `readme.md`
  - maybe look at ways to structure this


## Ideas
* ...