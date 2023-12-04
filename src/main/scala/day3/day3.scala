package day3

import scala.io.Source
import Main._

// import 

object Day3 {
  // ========================================
  // ========================================


  
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
        // ...
        // ============================================================
      }
      // aaaaa the parts
      case 1 => { 
        handlePart1("src/main/scala/day3/day3input.txt",true)
      }
      case numberInput => {
        Main.failingMessage("DAY 3 INVALID PART NUMBER: "+numberInput)
      }
    }
  }
  // ========================================
  // ========================================

  def handlePart1(inputFilePath:String,includeDebuggingInfo:Boolean):Unit={
    // ...
    
    var inputLines = Main.grabLinesFromFile(inputFilePath)

    // ...
  }
  // ========================================
  // ========================================

  // ...
  
  // ========================================
  // ========================================
}