package day2

import scala.io.Source
import Main._

// import 

object Day2 {
  // ========================================
  // ========================================


  def grabLinesFromFile(pathStr:String):List[String] = {
    var linesList = List[String]()
    try {

      val fileGrabbedBuffer = Source.fromFile(pathStr)
      for (line <- fileGrabbedBuffer.getLines()) {
          // println(line)
          linesList = linesList :+ line
      }
      fileGrabbedBuffer.close()
    }
    catch {
      case e: Exception => println("but it doesnae work: "+e.toString())
    }
    linesList
  } 
  
  /**
    * inlet for the code
    *
    * @param partNumber
    */
  def handleDay(partNumber:Int):Unit={
    partNumber match {
      // testing addition
      case 99 => {
        // ============================================================
        handlePart1("src/main/scala/day2/day2testinput1.txt",true)
        handlePart2("src/main/scala/day2/day2testinput1.txt",true) // same test file
        // ============================================================
      }
      // aaaaa the parts
      case 1 => { 
        handlePart1("src/main/scala/day2/day2input.txt",false)
      }
      case 2 => {
        handlePart2("src/main/scala/day2/day2input.txt",true)
      }
      case numberInput => {
        Main.failingMessage("DAY 2 INVALID PART NUMBER: "+numberInput)
      }
    }
  }
  // ========================================
  // ========================================


  // da patterns

  // var gameLineMatch = "Game ([0-9]+): (([0-9]+) (red|green|blue).? ?)+".r
  var numberMatcher = "[0-9]+".r
  var gameLineNumberMatcher = "Game ([0-9]+):".r
  // needs match all
  var gameIterMatcher = "(([0-9]+) (red|green|blue)(, )?)+;?".r
  var gameChoiceMatcher = "([0-9]+) (red|green|blue)".r
  var gameChoiceColorMatcher = "(red|green|blue)".r

  // for extraction of num
  def getFirstNumberFromString(inputStringToRipFrom:String):Int ={
    numberMatcher.findFirstIn(inputStringToRipFrom) match {
      case None => -65536
      case Some(thing) => {
        try{
          thing.toInt
        }catch {
          case e:Exception=> {
            println("had a spooker on ripped number toInt transofmrmering: "+thing)
            -65536 // spookable number
          }
        }
      }
    }
  }
  // ========================================
  // ========================================

  def handlePart1(inputFilePath:String,includeDebuggingInfo:Boolean):Unit={
    // ...
    var inputLines = grabLinesFromFile(inputFilePath)

    // println(inputLines.toString())

    // the result val
    var runningGameNumberTotal = 0
    // loop all game liness
    for(gameLine <- inputLines){
      // ...
      // for debugging
      if(includeDebuggingInfo) println(gameLine)
      // ...
      // // ("Game \\d: ([a-zA-Z0-9]+)".r)(gameNumber,gameDetails)
      // game match {
      //   case "Game "+gameNumber+": "+gameDetails => printf("have the: %s\nwith: %s",gameNumber,gameDetails)
      // }


      try{
        // grab the game label
        val gameLineLabelString = gameLineNumberMatcher.findFirstIn(gameLine) match {
          case None => "???"
          case Some(thing) => thing
        }
        // grab the number from it
        val gameNumber = getFirstNumberFromString(gameLineLabelString)
        
        // grab the iterations
        val gameIterationsList = gameIterMatcher.findAllIn(gameLine)
        
        var colorSafetyIsSafe = true // assume fine, thisisfine.gif

        for(gameIteration <- gameIterationsList){
          // println(gameIteration)
          // grab the selectables
          val gameIterationSelections = gameChoiceMatcher.findAllIn(gameIteration)
          for(selection <- gameIterationSelections){
            val selectionNumber = getFirstNumberFromString(selection)
            if(includeDebuggingInfo) printf("SELECTION: %s, NUMBER: %d",selection,selectionNumber)
            // color handle
            val colorExtractedSymbol = gameChoiceColorMatcher.findFirstIn(selection) match {
              case None => "???"
              case Some(thing) => thing
            }
            // P A I N YOU'RE JOKIN???? CRACKED RAW EGG BRAIN HONESTLY
            val colorWasUnsafe = colorExtractedSymbol match {
              case "red" => if(selectionNumber>12) true else false
              case "green" => if(selectionNumber>13) true else false
              case "blue" => if(selectionNumber>14) true else false
              case spookyInput => {
                if(includeDebuggingInfo) println("!!!! SPOOKY COLOUR !!!!: "+spookyInput)
                true // // do nothing else?? pretend fine?
              }
            }
            if(colorWasUnsafe){
              if(includeDebuggingInfo) print("!!! UNSAFE !!!")
              // make the spooky checker
              colorSafetyIsSafe = false
            }
            if(includeDebuggingInfo) printf(", EXTRACTED: %s\n",colorExtractedSymbol)
          }
        }

        if(colorSafetyIsSafe) {
          runningGameNumberTotal = runningGameNumberTotal+gameNumber
        }

        if(includeDebuggingInfo) printf(
          "GAME: %d, TOTAL: %d, SAFETY: %s\n",
          gameNumber,
          runningGameNumberTotal,
          colorSafetyIsSafe.toString()
        )
      } catch {
        case e:Exception=> println("failed the handle lines: "+e.toString())
      }

      // done game line
    }

    // done all games

    printf("END RESULT: %d\n",runningGameNumberTotal)
  }
  // ========================================
  // ========================================

  def handlePart2(inputFilePath:String,includeDebuggingInfo:Boolean):Unit={
    // ...
    var inputLines = grabLinesFromFile(inputFilePath)
    // ...

    // println(inputLines.toString())

    // the result val
    var runningGameNumberTotal = 0

    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##

    // FUNTODO: THIS COULD BE ANONYMOUS FUNCTION ON COLLECTION
    // loop all game liness
    for(gameLine <- inputLines){
      // ...
      // for debugging
      if(includeDebuggingInfo) println(gameLine)
      // ...

      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##

      try{
        // grab the game label
        val gameLineLabelString = gameLineNumberMatcher.findFirstIn(gameLine) match {
          case None => "???"
          case Some(thing) => thing
        }
        // grab the number from it
        val gameNumber = getFirstNumberFromString(gameLineLabelString)
        
        // grab the iterations
        val gameIterationsList = gameIterMatcher.findAllIn(gameLine)
        
        // (redMax,greenMax,blueMax)
        // var maximumSoFar = Map.Map2("bep",5) //????
        // idk hashmaps in scala??
        // the using the of the maximum so far

        var redMaxSoFar = 0
        var greenMaxSoFar = 0
        var blueMaxSoFar = 0

        // == == == == == == == == == == == == == == == == == == == == == == 
        // == == == == == == == == == == == == == == == == == == == == == == 
        
        // FUNTODO: THIS COULD BE ANONYMOUS FUNCTION ON COLLECTION
        for(gameIteration <- gameIterationsList){
          // println(gameIteration)
          // grab the selectables
          val gameIterationSelections = gameChoiceMatcher.findAllIn(gameIteration)

          // debugging thingy
          var changedMaxSoFar = false

          // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
          // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
          // FUNTODO: THIS COULD BE ANONYMOUS FUNCTION ON COLLECTION
          for(selection <- gameIterationSelections){
            
            val selectionNumber = getFirstNumberFromString(selection)

            if(includeDebuggingInfo) printf("SELECTION: %s, NUMBER: %d",selection,selectionNumber)

            // color handle
            val colorExtractedSymbol = gameChoiceColorMatcher.findFirstIn(selection) match {
              case None => "???"
              case Some(thing) => thing
            }

            // FUNTODO: SHOULD CHANGE TO A LIST OR SOMETHING TO HANDLE FOR NEW COLORS
            colorExtractedSymbol match {
              case "red" if(selectionNumber>redMaxSoFar) => redMaxSoFar = {
                changedMaxSoFar = true
                selectionNumber
              }
              case "green" if(selectionNumber>greenMaxSoFar) => greenMaxSoFar = {
                changedMaxSoFar = true
                selectionNumber
              }
              case "blue" if (selectionNumber>blueMaxSoFar) => blueMaxSoFar =  {
                changedMaxSoFar = true
                selectionNumber
              }
              case spookyInput => {
                false // // do nothing else?? pretend fine?
              }
            } // done color matched

            if(changedMaxSoFar){
              if(includeDebuggingInfo) print(" > ")
            }
            else{
              if(includeDebuggingInfo) print("<= ")
            }

            if(includeDebuggingInfo) printf(", EXTRACTED: %s\n",colorExtractedSymbol)
          }
          // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
          // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
        }

        // == == == == == == == == == == == == == == == == == == == == == == 
        // == == == == == == == == == == == == == == == == == == == == == == 
        // == game's power level
        var currGamePower = redMaxSoFar * greenMaxSoFar * blueMaxSoFar
        if(includeDebuggingInfo) printf("")
        runningGameNumberTotal = runningGameNumberTotal+gameNumber

        if(includeDebuggingInfo) printf(
          "GAME: %d, POWER: %d, TOTAL: %d\n",
          gameNumber,
          currGamePower,
          runningGameNumberTotal
        )

        // == == == == == == == == == == == == == == == == == == == == == == 
        // == == == == == == == == == == == == == == == == == == == == == == 
      } catch {
        case e:Exception=> println("failed the handle lines: "+e.toString())
      }

      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##
      // ## ## == == == == == == == == == == == == == == == == == == == == == == ## ##

      // done game line
    }

    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##
    // ## ## == == == == ## == == == == ## ## ## ## ## == == == == ## == == == == ## ##

    // done all games

    printf("END RESULT: %d\n",runningGameNumberTotal)
  }
  // ========================================
  // ========================================

  // ...
  
  // ========================================
  // ========================================
}