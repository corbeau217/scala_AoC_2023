package day9

import scala.io.Source
import Main._

object Day9 {
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
        case 98 => {
          // ============================================================
          // ...
          handlePart1(Main.grabLinesFromFile("data/day9testinput1.txt"),true)
          // ...
          // ============================================================
        }
        case 99 => {
          // ============================================================
          // ...
          handlePart2(Main.grabLinesFromFile("data/day9testinput1.txt"),true)
          // ...
          // ============================================================
        }
        // the part matching
        case 1 => {
          // ============================================================
          handlePart1(Main.grabLinesFromFile("data/day9input.txt"),false)
          // ============================================================
        }
        case 2 => {
          // ============================================================
          handlePart2(Main.grabLinesFromFile("data/day9input.txt"),false)
          // ============================================================
        }
        case numberInput => {
          // ============================================================
          Main.failingMessage("DAY 9 INVALID PART NUMBER: "+numberInput)
          // ============================================================
        }
      }
    }
    catch {
      case e:Exception=> Main.failingMessage("DAY 9 HAD EXCEPTION: "+e.toString())
    }
  }

  // ========================================
  // ========================================

  def handlePart1(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={

    val intMatcher = "-?[0-9]+".r

    def vecPrinter(preMessage:String,longVec:Vector[Long]):Unit={
      print(preMessage)
      longVec.map(item=>printf("[%d]",item))
      println()
    }


    var lineValueArrays = Vector[Array[Long]]()

    for(line <- inputLines){
      lineValueArrays = lineValueArrays :+ intMatcher.findAllIn(line).toArray.map(item=>item.toLong)
      if(includeDebuggingInfo) vecPrinter("got items: ", lineValueArrays.last.toVector)
    }

    def checkIfHasNonZero(arrToCheck:Vector[Long]):Boolean={
      arrToCheck match {
        case _ if(arrToCheck.length==0) => false // no items
        case head+:tail=> if(0!=head) true else checkIfHasNonZero(tail)
      }
    }

    // the extrapolated total
    var accumulatedTotal:Long = 0

    // each line
    for(lineValueArrayIdx <- 0 to lineValueArrays.length-1){
      var currentValueArray = lineValueArrays(lineValueArrayIdx)
      // ----------------------------------------------------------------------------------------

      // assume zeros already
      var hasNonZero = true
  
      // ----------------------------------------------------------------------------------------
      
      // with the current value array already in it
      var currLineTree = Vector[Array[Long]]()
      
      currLineTree = currLineTree:+currentValueArray
      
      if(includeDebuggingInfo) vecPrinter("creating tree for: ",currentValueArray.toVector)
      while (hasNonZero && currLineTree.last.length > 1){
        var prevDiffArr = currLineTree.last
        var newListToAdd = new Array[Long](prevDiffArr.length-1)
        // reset the hasNonZero, this gets made true during the current line to allow another iteration
        //  we're assuming we wont have another until proven otherwise
        hasNonZero = false
        // now loop to make the new one
        for(idx <- 0 to newListToAdd.length-1){
          // make the location
          newListToAdd(idx) = prevDiffArr(idx+1)-prevDiffArr(idx)
          if(newListToAdd(idx)!=0) hasNonZero = true
        }
        if(includeDebuggingInfo) vecPrinter("-----: ", newListToAdd.toVector)
        currLineTree = currLineTree:+newListToAdd
      }

      // now what??

      // make a copy vector that each is now a vector
      var newCurrLineTree = currLineTree.map((subArr)=>{subArr.toVector})

      def suggestNext(resultingTree:Vector[Vector[Long]],tempWorkingTree:Vector[Vector[Long]]):Vector[Vector[Long]]={
        tempWorkingTree match {
          case _ if( 1==tempWorkingTree.length ) => tempWorkingTree++resultingTree
          case restWorkingTree :+ newWorkingTreeLast :+ lastVector => {
            suggestNext(lastVector+:restWorkingTree, (restWorkingTree :+ (newWorkingTreeLast :+ (newWorkingTreeLast.last + lastVector.last))))
          }
        }
      }

      // then loop up through for suggesting
      var suggestedNextTree = suggestNext(Vector[Vector[Long]](), newCurrLineTree)

      // then we got through and add up the end values?

      

      printf("adding [%d]: %d\n",lineValueArrayIdx, suggestedNextTree.head.last)
      accumulatedTotal = accumulatedTotal + suggestedNextTree.head.last

      // ----------------------------------------------------------------------------------------
    }

    printf("SHOULD BE %d\n",accumulatedTotal)

    println("TODO")
  }

  // ========================================
  // ========================================

  def handlePart2(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    // TODO: DAY 9 PART 2
    println("TODO")
  }

  // ========================================
  // ========================================
}