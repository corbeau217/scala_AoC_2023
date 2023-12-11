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

    // printf("we have the first as: %c\n",charMatrix(0)(0))
    // printf("we have another as: %c\n",charMatrix(5)(4))
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
  }
  // ========================================
  // ========================================

  // ...
  
  // ========================================
  // ========================================
}