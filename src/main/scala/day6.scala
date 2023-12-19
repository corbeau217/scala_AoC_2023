package day6

import scala.io.Source
import org.bitbucket.inkytonik.kiama.output.PrettyPrinter.{any, layout}
import Main._

object Day6 {
  // ========================================
  // ========================================

  /**
    * inlet for the code
    *
    * @param partNumber given as Int from caller
    */
  def handleDay(partNumber:Int):Unit={
    try{
      partNumber match {
        // testing addition
        case 99 => {
          // ============================================================
          // ...
          handlePart1(Main.grabLinesFromFile("data/day6testinput1.txt"),true)
          // handlePart2(Main.grabLinesFromFile("data/day6testinput1.txt"),true)
          // ...
          // ============================================================
        }
        // the part matching
        case 1 => {
          // ============================================================
          handlePart1(Main.grabLinesFromFile("data/day6input.txt"),false)
          // ============================================================
        }
        case 2 => {
          // ============================================================
          handlePart2(Main.grabLinesFromFile("data/day6input.txt"),false)
          // ============================================================
        }
        case numberInput => {
          // ============================================================
          Main.failingMessage("DAY 6 INVALID PART NUMBER: "+numberInput)
          // ============================================================
        }
      }
    }
    catch {
      case e:Exception=> Main.failingMessage("DAY 6 HAD EXCEPTION: "+e.toString())
    }
  }

  // ========================================
  // ========================================

  def handlePart1(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    // TODO: DAY 6 PART 1
    var intMatcher = "[1-9][0-9]*".r
    // prepare vectors of numbers
    var raceTimes = Vector[Int]()
    var raceDistances = Vector[Int]()
    // strip the numbers
    for(item <- intMatcher.findAllIn(inputLines(0))){
      raceTimes=raceTimes:+item.toInt
    }
    for(item <- intMatcher.findAllIn(inputLines(1))){
      raceDistances=raceDistances:+item.toInt
    }

    if(includeDebuggingInfo) {
      print("race Times: ")
      raceTimes.map(item=>printf("[%3d]",item))
      println()
    }
    if(includeDebuggingInfo) {
      print("race dists: ")
      raceDistances.map(item=>printf("[%3d]",item))
      println()
    }

    // both should be same length
    val raceCount = raceTimes.length

    // access all races finding the min and maximum hold time
    // =============================================================================

    var minHeldArray = new Array[Int](raceCount)
    var maxHeldArray = new Array[Int](raceCount)
    var rangeSizeArray = new Array[Int](raceCount)

    // every race index
    for(currRaceIndex <- 0 to raceCount-1){
      // set the starting min and max to be worst case
      minHeldArray(currRaceIndex) = Int.MaxValue
      maxHeldArray(currRaceIndex) = Int.MinValue

      // assume 1
      var millisHeld = 1

      // while we still at least 1 second for the movement
      while( millisHeld < raceTimes(currRaceIndex)){
        var raceTimeRemaining = raceTimes(currRaceIndex)-millisHeld

        // when we can use the time remaining to cross the finish line at our current speed
        if(raceTimeRemaining*millisHeld > raceDistances(currRaceIndex)){
          // try for updates

          // check if we havent found min yet
          if(minHeldArray(currRaceIndex) > millisHeld) minHeldArray(currRaceIndex) = millisHeld
          // otherwise if max needss updating
          if(maxHeldArray(currRaceIndex) < millisHeld) maxHeldArray(currRaceIndex) = millisHeld
        }

        // prepare for next
        millisHeld = millisHeld+1
      }
      // stash the combinationss
      rangeSizeArray(currRaceIndex) = maxHeldArray(currRaceIndex) - minHeldArray(currRaceIndex) + 1

      if(includeDebuggingInfo) printf("race[%d] -- min: %3d, max: %3d, num: %3d\n",currRaceIndex,minHeldArray(currRaceIndex),maxHeldArray(currRaceIndex), rangeSizeArray(currRaceIndex))
    }
    // get the product of all our race combinations and print
    // =============================================================================
    printf("RESULT SHOULD BE: %d\n",rangeSizeArray.product)
    // =============================================================================
    
  }

  // ========================================
  // ========================================

  def handlePart2(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    // TODO: DAY 6 PART 2
  }

  // ========================================
  // ========================================
}