package day27

import scala.io.Source
import Main._

object Day27 {
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
          handlePart1(Main.grabLinesFromFile("data/day27testinput1.txt"),true)
          // handlePart2(Main.grabLinesFromFile("data/day27testinput1.txt"),true)
          // ...
          // ============================================================
        }
        // the part matching
        case 1 => {
          // ============================================================
          handlePart1(Main.grabLinesFromFile("data/day27input.txt"),false)
          // ============================================================
        }
        case 2 => {
          // ============================================================
          handlePart2(Main.grabLinesFromFile("data/day27input.txt"),false)
          // ============================================================
        }
        case numberInput => {
          // ============================================================
          Main.failingMessage("DAY 27 INVALID PART NUMBER: "+numberInput)
          // ============================================================
        }
      }
    }
    catch {
      case e:Exception=> Main.failingMessage("DAY 27 HAD EXCEPTION: "+e.toString())
    }
  }

  // ========================================
  // ========================================

  def handlePart1(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    // TODO: DAY 27 PART 1
  }

  // ========================================
  // ========================================

  def handlePart2(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    // TODO: DAY 27 PART 2
  }

  // ========================================
  // ========================================
}