package day4

import scala.io.Source
import Main._

// import 

object Day4 {
  
  // ========================================================================================================================
  // ========================================================================================================================
  // ========================================================================================================================
  // ========================================================================================================================

  /**
    * inlet for the code
    *
    * @param partNumber
    */
  def handleDay(partNumber:Int):Unit={
    try{
      partNumber match {
        // testing addition
        case 99 => {
          // ============================================================
          handlePart1(Main.grabLinesFromFile("data/day4testinput1.txt"),true)
          handlePart2(Main.grabLinesFromFile("data/day4testinput1.txt"),true)
          // ============================================================
        }
        // aaaaa the parts
        case 1 => {
          // ============================================================
          handlePart1(Main.grabLinesFromFile("data/day4input.txt"),false)
          // ============================================================
        }
        case 2 => {
          // ============================================================
          // handlePart2(Main.grabLinesFromFile("data/day4input.txt"),true)
          handlePart2(Main.grabLinesFromFile("data/day4testinput1.txt"),true)
          // ============================================================
        }
        case numberInput => {
          // ============================================================
          Main.failingMessage("DAY 4 INVALID PART NUMBER: "+numberInput)
          // ============================================================
        }
      }
    } catch {
      case e:Exception => {
        println("spooked in the day4: "+e.toString())
      }
    }
  }

  // ========================================================================================================================
  // ========================================================================================================================
  // ========================================================================================================================
  // ========================================================================================================================
  // ==== helpers

  val winningNumbersMatcher = ":[^|]*".r
  val scratchCardNumbersMatcher = "\\|.*".r
  val numberMatcher = "[0-9]+".r

  def getScratchCardScore(matchesCount:Int,scoreAccumulator:Int):Int={
    matchesCount match {
      case 0 => scoreAccumulator
      case _ => getScratchCardScore(matchesCount-1,scoreAccumulator*2)
    }
  }

  // ========================================================================================================================
  // ========================================================================================================================
  // ========================================================================================================================
  // ========================================================================================================================

  def handlePart1(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    var scoresRunningTotal = 0

    for(scratchCardLine <- inputLines){
      if (includeDebuggingInfo) printf("LINE: %s\n",scratchCardLine)
      // ...
      var winningNumbersPortion = winningNumbersMatcher.findFirstIn(scratchCardLine) match {
        case None => "???"
        case Some(thing) => thing
      }
      if(includeDebuggingInfo) printf("WIN NUMS PORTION: [%s]\n",winningNumbersPortion)
      var myNumbersPortion = scratchCardNumbersMatcher.findFirstIn(scratchCardLine) match {
        case None => "???"
        case Some(thing) => thing
      }
      if(includeDebuggingInfo) printf("MY NUMS PORTION: [%s]\n",myNumbersPortion)
      // if (includeDebuggingInfo) println("did numbers sectioning")
      // ...
      // FUNTODO: intuative approach then improve with sorting/binary search
      
      // ...
      var winningNumbersIterator = numberMatcher.findAllIn(winningNumbersPortion).map((numberString)=>numberString.toInt)
      // ...
      // if (includeDebuggingInfo) println("got number's iterators")
      // ...
      var scratchCardMatches = 0
      
      // given winner number
      for(winningNumberToTest <- winningNumbersIterator){
        if(includeDebuggingInfo) printf("%s --> ",winningNumberToTest)

        var myNumbersIterator = numberMatcher.findAllIn(myNumbersPortion).map((numberString)=>numberString.toInt)
        // check all of the numbers on my card
        for(myNumberChoice <- myNumbersIterator){
          // given winning number to my car
          // if(includeDebuggingInfo) printf("%s == %s??",myNumberChoice,winningNumberToTest)

          if(includeDebuggingInfo) printf("%s,%s",winningNumberToTest,myNumberChoice)
          
          if(myNumberChoice == winningNumberToTest){
            if(includeDebuggingInfo) print("ye ")
            scratchCardMatches=scratchCardMatches+1
          }else {
            if(includeDebuggingInfo) print("   ")
          }
        } // done my numbers for this winning nnumber
        // if(includeDebuggingInfo) printf(" --------- isNext? %s \n",winningNumbersIterator.hasNext)
        if(includeDebuggingInfo) println("")
        // go next
      }
      // done all winning numbers

      // if (includeDebuggingInfo) println("compareable and scoring")
      
      
      // ... 
      if(scratchCardMatches>0){
        scoresRunningTotal = scoresRunningTotal + getScratchCardScore(scratchCardMatches-1,1)
      }

      if (includeDebuggingInfo) printf("MATCHES: %d\nSCORE: %d\n",scratchCardMatches,getScratchCardScore(scratchCardMatches-1,1))
      // if (includeDebuggingInfo) println("score update for card")
    }
    // ...
    println("aaaa we got: "+scoresRunningTotal)
  }

  // ========================================================================================================================
  // ========================================================================================================================
  // ========================================================================================================================
  // ========================================================================================================================

  def handlePart2(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    // ...
    
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // == == GET MATCHES PER CARD

    var scratchCardMatchesArray:Array[Int] = new Array[Int](inputLines.length)
    var currScratchCardIdx = 0
    
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##

    // for a given sscratch card (line)
    for(scratchCardLine <- inputLines){
      // figuring out the stuff

      if (includeDebuggingInfo) printf("SCRATCH CARD: %s\n",scratchCardLine)
      // ...

      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##

      var winningNumbersPortion = winningNumbersMatcher.findFirstIn(scratchCardLine) match {
        case None => "???"
        case Some(thing) => thing
      }
      var myNumbersPortion = scratchCardNumbersMatcher.findFirstIn(scratchCardLine) match {
        case None => "???"
        case Some(thing) => thing
      }

      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##

      if(includeDebuggingInfo) printf("WIN NUMS PORTION: [%s]\n",winningNumbersPortion)
      if(includeDebuggingInfo) printf("MY NUMS PORTION: [%s]\n",myNumbersPortion)
      // if (includeDebuggingInfo) println("did numbers sectioning")
      // ...
      // FUNTODO: intuative approach then improve with sorting/binary search
      
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##

      // POSSIBLEBUG: ".map" maybe sandbox try this then split up not on one line, map keyword seems to be shared with hashmaps in scala
      var winningNumbersIterator = numberMatcher.findAllIn(winningNumbersPortion).map((numberString)=>numberString.toInt)
      // ...
      // if (includeDebuggingInfo) println("got number's iterators")

      // ...
      // var scratchCardMatches = 0

      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      
      // given winner number
      for(winningNumberToTest <- winningNumbersIterator){
        if(includeDebuggingInfo) printf("%3s --> ",winningNumberToTest)

        // == == == == == == == == == == == == == == == == == == == == == == 
        // == == == == == == == == == == == == == == == == == == == == == == 

        // POSSIBLEBUG: ".map" maybe sandbox try this then split up not on one line, map keyword seems to be shared with hashmaps in scala
        // scratch card hass numbers iterator, used for us to check a given winning number
        //  we reinitialise every iteration of the winning numbers loop so it starts frmom the beginning again
        // POSSIBLEBUG: not exactly bug, but it's super ridiculously horrible inefficiency, maybe just find a way to restart or `toArray` or `toList` our `Iterator`
        var myNumbersIterator = numberMatcher.findAllIn(myNumbersPortion).map((numberString)=>numberString.toInt)

        // == == == == == == == == == == == == == == == == == == == == == == 
        // == == == == == == == == == == == == == == == == == == == == == == 
        // check all of the numbers on my card

        for(myNumberChoice <- myNumbersIterator){
          // given winning number to my car
          // if(includeDebuggingInfo) printf("%s == %s??",myNumberChoice,winningNumberToTest)

          // SPAMMER litererally every test of winningNumber to myNumber
          if(includeDebuggingInfo) printf("%2s,%2s",winningNumberToTest,myNumberChoice)
          
          if(myNumberChoice == winningNumberToTest){
            if(includeDebuggingInfo) print("ye ")
            // we just keep the matches ammount now
            scratchCardMatchesArray(currScratchCardIdx) = scratchCardMatchesArray(currScratchCardIdx)+1
            // scratchCardMatches=scratchCardMatches+1
          }else {
            if(includeDebuggingInfo) print("   ")
          }
        } // done my numbers for this winning nnumber

        // == == == == == == == == == == == == == == == == == == == == == == 
        // == == == == == == == == == == == == == == == == == == == == == == 
        
        // if(includeDebuggingInfo) printf(" --------- isNext? %s \n",winningNumbersIterator.hasNext)
        if(includeDebuggingInfo) println("")
      }
      
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##

      // done all winning numbers

      // // stash the game's winning total
      // scratchCardMatchesArray(currScratchCardIdx) = scratchCardMatchesArray(currScratchCardIdx) + scratchCardMatches
      

      if (includeDebuggingInfo) printf("MATCHES: %d\n",scratchCardMatchesArray(currScratchCardIdx))
      // if (includeDebuggingInfo) println("score update for card")
      
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##

      // POSSIBLEBUG: maybe change inverse of foreach/index usage
      // go next
      currScratchCardIdx = currScratchCardIdx+1
      
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
    }

    // now that we have them???

    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // == == WORK THROUGH LIST AND GET NUMBER OF A CARD

    var scratchCardCopyCountArray:Array[Int] = new Array[Int](inputLines.length)

    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    
    for(copyingCardIdxDif <- 0 to inputLines.length-1){
      var copyingCardIdx = (inputLines.length)-copyingCardIdxDif
      // ...
      // POSSIBLEBUG: maybe change to using just the indexing each time rather than iteration local copy of the position?
      val currCardMatches = scratchCardMatchesArray(copyingCardIdx)
      if(includeDebuggingInfo) printf("card %d scratch match: %d\n",copyingCardIdx+1,currCardMatches)

      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      
      // current card number 
      // scratchCardCopyCountArray(copyingCardIdx) = scratchCardCopyCountArray(copyingCardIdx) + 1

      // find future ones
      // ...
      // == == == == == == == == == == == == == == == == == == == == == == 
      // == == == == == == == == == == == == == == == == == == == == == == 
        
      // POSSIBLEBUG: maybe there's an isssue with us doing +1 and -1?? seems a bit wonky, but we'd have more than 0 ending if that were bug
      val nextIndex = copyingCardIdx+1
      for(increasingCardCountIndex <- copyingCardIdx to copyingCardIdx+scratchCardMatchesArray(copyingCardIdx) ){
        // if(includeDebuggingInfo) printf("[%d]: %d   +1\n",increasingCardCountIndex,scratchCardCopyCountArray(increasingCardCountIndex))
        scratchCardCopyCountArray(increasingCardCountIndex) = scratchCardCopyCountArray(increasingCardCountIndex) + 1
        // if(includeDebuggingInfo) printf("%d\n",scratchCardCopyCountArray(increasingCardCountIndex))
      }
      // == == == == == == == == == == == == == == == == == == == == == == 
      // == == == == == == == == == == == == == == == == == == == == == == 
      
      
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
    }

    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // == == RUN THROUGH AGAIN AND ACCUMULATE

    if(includeDebuggingInfo) print("ACCUMULATING TOTAL...\n")
    
    var cummulativeTotalValue = 0
    for(itemIdx <- 0 to inputLines.length-1){
      // SPAMMER: for knowing that it does the accumulating
      if(includeDebuggingInfo) printf("card %d --> %d * %d = %d\n",itemIdx+1,scratchCardMatchesArray(itemIdx),(scratchCardCopyCountArray(itemIdx)),(scratchCardMatchesArray(itemIdx) * (scratchCardCopyCountArray(itemIdx))))
      cummulativeTotalValue = cummulativeTotalValue + scratchCardMatchesArray(itemIdx) * (scratchCardCopyCountArray(itemIdx))
    }

    if(includeDebuggingInfo) println("")

    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##

    // POSSIBLEBUG: maybe this is always 0??
    printf("total is %d captain 🫡\n",cummulativeTotalValue)
  }
  
  // ========================================================================================================================
  // ========================================================================================================================
  // ========================================================================================================================
  // ========================================================================================================================
}