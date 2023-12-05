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
          handlePart1(Main.grabLinesFromFile("src/main/scala/day4/day4testinput1.txt"),true)
          handlePart2(Main.grabLinesFromFile("src/main/scala/day4/day4testinput1.txt"),true)
          // ============================================================
        }
        // aaaaa the parts
        case 1 => {
          // ============================================================
          handlePart1(Main.grabLinesFromFile("src/main/scala/day4/day4input.txt"),false)
          // ============================================================
        }
        case 2 => {
          // ============================================================
          // handlePart2(Main.grabLinesFromFile("src/main/scala/day4/day4input.txt"),true)
          handlePart2(Main.grabLinesFromFile("src/main/scala/day4/day4testinput1.txt"),true)
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
    
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##

    for(scratchCardLine <- inputLines){

      if (includeDebuggingInfo) printf("LINE: %s\n",scratchCardLine)
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

      // ...
      var winningNumbersIterator = numberMatcher.findAllIn(winningNumbersPortion).map((numberString)=>numberString.toInt)
      // ...
      // if (includeDebuggingInfo) println("got number's iterators")

      // ...
      var scratchCardMatches = 0
      var currScratchCardIdx = 0

      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      
      // given winner number
      for(winningNumberToTest <- winningNumbersIterator){
        if(includeDebuggingInfo) printf("%3s --> ",winningNumberToTest)

        // == == == == == == == == == == == == == == == == == == == == == == 
        // == == == == == == == == == == == == == == == == == == == == == == 

        var myNumbersIterator = numberMatcher.findAllIn(myNumbersPortion).map((numberString)=>numberString.toInt)

        // == == == == == == == == == == == == == == == == == == == == == == 
        // == == == == == == == == == == == == == == == == == == == == == == 
        // check all of the numbers on my card

        for(myNumberChoice <- myNumbersIterator){
          // given winning number to my car
          // if(includeDebuggingInfo) printf("%s == %s??",myNumberChoice,winningNumberToTest)

          if(includeDebuggingInfo) printf("%2s,%2s",winningNumberToTest,myNumberChoice)
          
          if(myNumberChoice == winningNumberToTest){
            if(includeDebuggingInfo) print("ye ")
            scratchCardMatches=scratchCardMatches+1
          }else {
            if(includeDebuggingInfo) print("   ")
          }
        } // done my numbers for this winning nnumber

        // == == == == == == == == == == == == == == == == == == == == == == 
        // == == == == == == == == == == == == == == == == == == == == == == 
        
        // if(includeDebuggingInfo) printf(" --------- isNext? %s \n",winningNumbersIterator.hasNext)
        if(includeDebuggingInfo) println("")
        // go next
        currScratchCardIdx = currScratchCardIdx+1
      }
      
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##

      // done all winning numbers

      // stash the game's winning total
      scratchCardMatchesArray(currScratchCardIdx) = scratchCardMatchesArray(currScratchCardIdx) + scratchCardMatches

      if (includeDebuggingInfo) printf("MATCHES: %d\n",scratchCardMatches)
      // if (includeDebuggingInfo) println("score update for card")
      
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
    }

    // now that we have them???

    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // == == WORK BACKWARDS THROUGH LIST AND GET WEIGHT OF A CARD

    var scratchCardMatchWeightArray:Array[Int] = new Array[Int](inputLines.length)

    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    
    for(weighingIndex <- inputLines.length-1 to 0){
      // ...
      val currCardMatches = scratchCardMatchesArray(weighingIndex)
      if(includeDebuggingInfo) printf("scratch match: %d\n",currCardMatches)

      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      
      // ...
      currCardMatches match {
        // == == == == == == == == == == == == == == == == == == == == == == 
        // == == == == == == == == == == == == == == == == == == == == == == 
        case 0 => {
          // weight is 0 bc no matchess, gg ez
          scratchCardMatchWeightArray(weighingIndex) = 0 // unecessary but brain brains it more
          // if (includeDebuggingInfo) println("nonmatcher")
        }
        // == == == == == == == == == == == == == == == == == == == == == == 
        // == == == == == == == == == == == == == == == == == == == == == == 
        case includedWeightCount => {
          // go back and find the other weights starting with next
          // assume at least this one
          scratchCardMatchWeightArray(weighingIndex) = currCardMatches

          // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
          // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
          // ignore current matches value
          
          for(accumulatingWeighIndex <- weighingIndex+1 to weighingIndex+currCardMatches-1 ){
            scratchCardMatchWeightArray(weighingIndex) = scratchCardMatchWeightArray(weighingIndex) + scratchCardMatchWeightArray(accumulatingWeighIndex)
          }

          // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
          // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
        }
        // == == == == == == == == == == == == == == == == == == == == == == 
        // == == == == == == == == == == == == == == == == == == == == == == 
      }
      
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
    }

    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // == == RUN THROUGH AGAIN AND ACCUMULATE

    var cummulativeTotalValue = 0
    for(item <- scratchCardMatchWeightArray){
      cummulativeTotalValue = cummulativeTotalValue + item
    }

    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##

    printf("total is %d captain 🫡\n",cummulativeTotalValue)
  }
  
  // ========================================================================================================================
  // ========================================================================================================================
  // ========================================================================================================================
  // ========================================================================================================================
}