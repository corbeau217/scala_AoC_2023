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
    try{
      partNumber match {
        // testing addition
        case 99 => {
          // ============================================================
            // ...
          handlePart1(Main.grabLinesFromFile("data/day3testinput1.txt"),true)
          // ============================================================
        }
        // aaaaa the parts
        case 1 => { 
          handlePart1(Main.grabLinesFromFile("data/day3input.txt"),true)
        }
        case numberInput => {
          Main.failingMessage("DAY 3 INVALID PART NUMBER: "+numberInput)
        }
      }
    }
    catch {
      case e:Exception=> Main.failingMessage("DAY 3 HAD EXCEPTION: "+e.toString())
    }
  }
  // ========================================
  // ========================================

  def handlePart1(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    // character matrix
    var charMatrix = Array[Array[Char]]()
    for(currLine <- inputLines){
      // turn current line into symbol arrays
      charMatrix = charMatrix :+ currLine.toCharArray()
    }


    // gather information about the part numbers
    // (rowIdx, colIdx, digitCount)
    var possiblePartNumberLocationList = List[(Int,Int,Int)]()

    // TODO loop and acquire the part numbers and symbols
    var colIdx = 0
    var rowIdx = 0
    for(rowIdx <- 0 to charMatrix.length-1){
      for(colIdx <- 0 to charMatrix(rowIdx).length-1){
        // at an rowIdx colIdx
        if(includeDebuggingInfo) print(charMatrix(rowIdx)(colIdx))
        charMatrix(rowIdx)(colIdx) match {
          case ch if(ch.isDigit) => {
            // deal with digit
            // check last possible part and see if that idx would be 1 less than ours
            //  if it is, then increase the count for that
            // otherwise new possible part
            
          }
          case _ => {
            // otherwise snooze
          }
        }
      }
      if(includeDebuggingInfo) println("")
    }
    // ...
    // check all part numbers for being adjacent to the symbols
    //  what if 

    // TODO loop the part numbers or symbols with match case and mark adjacent as good
  }
  // ========================================
  // ========================================

  // ...
  
  // ========================================
  // ========================================
}