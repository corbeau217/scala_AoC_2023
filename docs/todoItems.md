# Todo items and ideas file

## Quick links
* [`README`](./README.md)
* [`projectChangesAndNotes`](./projectChangesAndNotes.md)
* [`dayDocMaker`](./dayDocMaker.md)
* [`stuctureNotes`](./stuctureNotes.md)
* **`todoItems`**

## To-Do items by date

### `[21/12/23]`

#### SCALA changes
- [x] ~~*each day should now have a mapping in main that says what its handle day function is*~~
- [ ] each part should have the handle part functions mapped out so that a day knows which part to goto
- [x] ~~*main should know if a given day exists yet based on time*~~

#### C changes
- [ ] `src/c/*.c` becomes `src/main/c/*.c`
    - [ ] moving `src/c/dayDocMaker.c` to `src/main/c/dayDocMaker.c`
        - [ ] update `.gitignore`
    - [ ] update `structureNotes`
- [ ] `src/test/c/` for tests
    - [ ] update `structureNotes`
- [ ] `main.c` creation
    - [ ] uses args to branch to different C files
    - [ ] update `structureNotes`
- [ ] `dayDocMaker.c` file generation code decoupling and reorganisation 
- [ ] something for updating existing files created
    - [ ] code to update quicklinks of day files
        * `docs/structureNotes.md` and `docs/todoItems.md` existance
- [ ] test file generation script for generating default test files

#### MD changes
- [x] ~~*`docs/structureNotes.md` needs to be implemented in the documentation files*~~
    - [x] ~~*to start with we can change the links manually*~~
- [x] ~~*todo items probably need to be moved to their own file, this also needs to be updated in the documentation files*~~

#### MISC changes
- [ ] updating the file structure for each day to use a padding `0` when it's only 1 digit
    * will require the implementation of C file generation reformatting, otherwise it'll be manually updated
- [ ] change to use more than 1 arguement for say what day / part we want
    * have pattern match to extract int for the things
    * once implemented, we could look into running command lines through scala

## Ideas
1. section links and table of contents for our markdown files?
    * this could mean just merging the markdown files, but may be better to leave as seperate for less headache
2. looking into the idea of importing the content of one markdown file into another so that the viewer doesnt need to navigate between files as a snippet
3. day object ideas
    1. have the day objects as extending a day structure so they always have the same handle function
    2. having the day objects tell main about their handle function instead of hard coded

