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
- [x] ~~*updating the file structure for each day to use a padding `0` when it's only 1 digit*~~
    * did manually, may break later on package name not changed
        - only changed file names and markdown links to the day .md files
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
4. future AoC pre-planning ideas?
    1. check if input is square
        * might be char grid
    2. check for only numbers or numbers + symbols or only alpha or hex or only symbols
    3. line similarity comparison
    4. time based fetching of content
        * stub files
        * generate modules
        * save the input files (and test data fetched from the webpage)
            - language tree for analysing the test data relation to the input files
            - for certainty about which bit is test data
            - allows for generate tests for day based on fetched content
            - would want to have a web client engine to fetch the webpage
                * then have a language parsing thing to translate the content into useful stuff
            - translation of the html file into markdown
                * so it can be put into a github document
                * also for use in obsidian
        * code to check how many stars we have and automatically update our stars table
            - need to rest/oauth/whatever the thing is they use
            - but then we could have it as a periodic update that then pushes to the github repo, updating only the file responsible for tracking that
            - or it's fetched from my own api thing? (maybe look into svg things)
        * what if make own language lel
5. idea of web fetching the content
    1. [`ADVENTOFCODE.COM/ABOUT`](https://adventofcode.com/about) mentions that there's [`a github thing about the oauth called octocat`](https://api.github.com/users/octocat)
        * `ADVENTOFCODE.COM/ABOUT` also mentions that there's [`a reddit thing about the oauth`](https://api.reddit.com/user/reddit/about)
6. timed release of content as mentioned in about section of page: "*puzzles unlock at midnight EST/UTC-5*"
7. need private leaderboard stuffs?