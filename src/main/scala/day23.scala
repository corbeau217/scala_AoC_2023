package day23

import scala.io.Source
import Main._

object Day23 {
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
          handlePart1(Main.grabLinesFromFile("data/day23testinput1.txt"),true)
          // handlePart2(Main.grabLinesFromFile("data/day23testinput1.txt"),true)
          // ...
          // ============================================================
        }
        // the part matching
        case 1 => {
          // ============================================================
          handlePart1(Main.grabLinesFromFile("data/day23input.txt"),false)
          // ============================================================
        }
        case 2 => {
          // ============================================================
          handlePart2(Main.grabLinesFromFile("data/day23input.txt"),false)
          // ============================================================
        }
        case numberInput => {
          // ============================================================
          Main.failingMessage("DAY 23 INVALID PART NUMBER: "+numberInput)
          // ============================================================
        }
      }
    }
    catch {
      case e:Exception=> Main.failingMessage("DAY 23 HAD EXCEPTION: "+e.toString())
    }
  }

  // ========================================
  // ========================================

  def handlePart1(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    // TODO: DAY 23 PART 1
  }

  // ========================================
  // ========================================

  def handlePart2(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    // TODO: DAY 23 PART 2
  }

  // ========================================
  // ========================================
}