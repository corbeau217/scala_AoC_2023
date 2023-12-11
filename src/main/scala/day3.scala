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
    // (x,y,digitCount)
    var possiblePartNumberLocationList = List[(Int,Int,Int)]()
    // gather infomration about the symbols
    var symbolsLocationList = List[(Int,Int)]()

    // TODO loop and acquire the part numbers and symbols
    var x = 0
    var y = 0
    for(y <- 0 to charMatrix.length-1){
      for(x <- 0 to charMatrix(y).length-1){
        // at an x y
        print(charMatrix(y)(x))
      }
      println("")
    }
    // ...

    // TODO loop the part numbers or symbols with match case and mark adjacent as good
  }
  // ========================================
  // ========================================

  // ...
  
  // ========================================
  // ========================================
}