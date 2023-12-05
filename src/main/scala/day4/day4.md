# day 4: scala
  scala attempt at day 4
## part 1
### strategy
1. section out the portions
2. grab the numbers
3. for each winning number, check if we matched it in our scratch card numbers
4. increment the number of them
5. now get the value that gives and add to score accumulator

## part 2
### strategy
1. get list of matches for each game
2. run through to grab score from `matchCount` future games,
3. also branch on subsequent games
4. maybe build this from bottom up??
  a. has to be x number if we take last one, 
  b. second last one says x plus possible y if we had a match
  c. third last says grab the thingies based on ours
  d. this gives what all future branches are if we take "another copy" of a given thing
5. tbh y irl
  a. we got the ye
  b. it of the hello? words? yes we got in the amount of the matches dont work
  c. probably something in the list not referencing correctly or the identifier use, test the indexes
6. mild refactoring to the starting of a part, and added in comments to break up code