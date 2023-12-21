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
          // handlePart1(Main.grabLinesFromFile("data/day3testinput1.txt"),true)
          handlePart2(Main.grabLinesFromFile("data/day3testinput1.txt"),true)
          // ...
          // ============================================================
        }
        // aaaaa the parts
        case 1 => { 
          // ============================================================
          handlePart1(Main.grabLinesFromFile("data/day3input.txt"),false)
          // ============================================================
        }
        case 2 => { 
          // ============================================================
          handlePart2(Main.grabLinesFromFile("data/day3input.txt"),false)
          // ============================================================
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
    // gather information about the symbols
    // (rowIdx, colIdx)
    var symbolLocationList = List[(Int,Int)]()

    var colIdx = 0
    var rowIdx = 0
    for(rowIdx <- 0 to charMatrix.length-1){
      for(colIdx <- 0 to charMatrix(rowIdx).length-1){
        // at an rowIdx colIdx
        if(includeDebuggingInfo) print(charMatrix(rowIdx)(colIdx))
        charMatrix(rowIdx)(colIdx) match {
          case ch if(ch.isDigit) => {
            // ------------------------------------------------------------------
            // ------------------------------------------------------------------
            // deal with digit
            possiblePartNumberLocationList match {
              // extracting the last one
              case rest :+ last => {
                last match {
                  // check last possible part and see if that idx would be 1 less than ours
                  case (lastRowIdx,lastColIdx,lastDigitCount) if  (lastRowIdx == rowIdx && lastColIdx + lastDigitCount == colIdx) => {
                    // when it'ss the one we gotta update, do it
                    possiblePartNumberLocationList = rest :+ Tuple3(lastRowIdx,lastColIdx, lastDigitCount+1)
                  }
                  // otherwise
                  case _ => {
                    possiblePartNumberLocationList = possiblePartNumberLocationList :+ Tuple3(rowIdx,colIdx,1)
                  }
                }
              }
              case _ => {
                // empty list or not one we gotta update, just start a new one
                possiblePartNumberLocationList = possiblePartNumberLocationList :+ Tuple3(rowIdx,colIdx,1)
              }
            }
            // ------------------------------------------------------------------
            // ------------------------------------------------------------------
          }
          case '.' => {
            // when it's an empty spot
            // honk shoo
          }
          case _ => {
            // otherwise it's a symbol, add it regardless of last
            symbolLocationList = symbolLocationList :+ Tuple2(rowIdx,colIdx)
          }
        } // end of current character match case
      }
      if(includeDebuggingInfo) println("")
    }
    // ...
    // finished nested loop traversing all characters
    
    // now we should debug as print our part numbers
    if(includeDebuggingInfo) for(posiblePartNumber <- possiblePartNumberLocationList){
      posiblePartNumber match {
        case (rowIdx,colIdx,digitCount) => {
          printf("possible number: r[%d]c[%d]d[%d]\n",rowIdx,colIdx,digitCount)
        }
        case otherwise => {
          println("spooker!!")
        }
      }
    }
    // and symbols
    if(includeDebuggingInfo) for(symbolLocation <- symbolLocationList){
      symbolLocation match {
        case (rowIdx,colIdx) => {
          printf("symbol: r[%d]c[%d]\n",rowIdx,colIdx)
        }
        case otherwise => {
          println("spooker!!")
        }
      }
    }

    // time to get all the real part numbers 
    var realPartNumberLocationList = List[(Int,Int,Int)]()
    // honestly just n^2 it, the other way was painful with backlog and juggling
    for(currSymLocation <- symbolLocationList){
      for(currPartNumberLocation <- possiblePartNumberLocationList){
        // debugger tell us about the thing
        if (includeDebuggingInfo) printf( "sym: r[%d]c[%d] to part: r[%d]c[%d]d[%d]\n",
          currSymLocation._1,currSymLocation._2,
          currPartNumberLocation._1,currPartNumberLocation._2,currPartNumberLocation._3
        )
        // now try for matchable
        if( (
          // test row
          Math.abs(currSymLocation._1 - currPartNumberLocation._1) < 2
        ) && (
          // test col
          // check from start
          (currSymLocation._2 >= currPartNumberLocation._2 - 1) &&
          // from end (include part digit count)
          (currSymLocation._2 <= currPartNumberLocation._2 + currPartNumberLocation._3)
        )){
          // should be match
          if(includeDebuggingInfo) printf("MATCHED: r[%d]c[%d] to part: r[%d]c[%d]d[%d]\n",
            currSymLocation._1,currSymLocation._2,
            currPartNumberLocation._1,currPartNumberLocation._2,currPartNumberLocation._3
          )
          // add the number to the location list
          if(!realPartNumberLocationList.contains(currPartNumberLocation)) realPartNumberLocationList = realPartNumberLocationList :+ currPartNumberLocation
          // we should now remove the part number from the part number list but that is spooky with this style of things
          //  maybe we should mark it as done
        }
      }
    }
    // cummulative total val
    var cummulativeTotal = 0
    // have good numbers, now process them for end
    for(realPartNum <- realPartNumberLocationList){
      // for stash digits
      var partNumString = ""
      // get the digits
      for(colIdx <- realPartNum._2 to (realPartNum._2+realPartNum._3-1)){
        partNumString = partNumString + charMatrix(realPartNum._1)(colIdx)
      }
      // now we want that as an Int
      var partNumInt = partNumString.toInt
      // then add to our total
      cummulativeTotal = cummulativeTotal+partNumInt
      if (includeDebuggingInfo) printf( "part: r[%d]c[%d]d[%d] -- '%d' -- '%d'\n",
        realPartNum._1,realPartNum._2,realPartNum._3,partNumInt,cummulativeTotal
      )
    }
    // done, print our final num
    printf("final part number: %d\n",cummulativeTotal)
  }

  // ========================================
  // ========================================

  def handlePart2(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    // make character matrix

    var charMatrix = Array[Array[Char]]()
    for(currLine <- inputLines){
      // turn current line into symbol arrays
      charMatrix = charMatrix :+ currLine.toCharArray()
    }

    // now get part/symbol info

    // gather information about the part numbers
    // (rowIdx, colIdx, digitCount)
    var possiblePartNumberLocationList = List[(Int,Int,Int)]()
    // gather information about the symbols
    // (rowIdx, colIdx)
    var symbolLocationList = List[(Int,Int)]()

    var colIdx = 0
    var rowIdx = 0
    for(rowIdx <- 0 to charMatrix.length-1){
      for(colIdx <- 0 to charMatrix(rowIdx).length-1){
        // at an rowIdx colIdx
        if(includeDebuggingInfo) print(charMatrix(rowIdx)(colIdx))

        charMatrix(rowIdx)(colIdx) match {
          case ch if(ch.isDigit) => {
            // ------------------------------------------------------------------
            // ------------------------------------------------------------------
            // deal with digit
            possiblePartNumberLocationList match {
              // extracting the last one
              case rest :+ last => {
                last match {
                  // check last possible part and see if that idx would be 1 less than ours
                  case (lastRowIdx,lastColIdx,lastDigitCount) if  (lastRowIdx == rowIdx && lastColIdx + lastDigitCount == colIdx) => {
                    // when it'ss the one we gotta update, do it
                    possiblePartNumberLocationList = rest :+ Tuple3(lastRowIdx,lastColIdx, lastDigitCount+1)
                  }
                  // otherwise
                  case _ => {
                    possiblePartNumberLocationList = possiblePartNumberLocationList :+ Tuple3(rowIdx,colIdx,1)
                  }
                }
              }
              case _ => {
                // empty list or not one we gotta update, just start a new one
                possiblePartNumberLocationList = possiblePartNumberLocationList :+ Tuple3(rowIdx,colIdx,1)
              }
            }
            // ------------------------------------------------------------------
            // ------------------------------------------------------------------
          }
          case '*' => {
            // otherwise it's a symbol, add it regardless of last
            symbolLocationList = symbolLocationList :+ Tuple2(rowIdx,colIdx)
          }
          case _ => {
            // when it's an empty spot or other symbol
            // honk shoo
          }
        } // end of current character match case
      }
      if(includeDebuggingInfo) println("")
    }
    // ...
    // finished nested loop traversing all characters
    
    // now we should debug as print our part numbers
    if(includeDebuggingInfo) for(posiblePartNumber <- possiblePartNumberLocationList){
      posiblePartNumber match {
        case (rowIdx,colIdx,digitCount) => {
          printf("possible number: r[%d]c[%d]d[%d]\n",rowIdx,colIdx,digitCount)
        }
        case otherwise => {
          println("spooker!!")
        }
      }
    }
    // and symbols
    if(includeDebuggingInfo) for(symbolLocation <- symbolLocationList){
      symbolLocation match {
        case (rowIdx,colIdx) => {
          printf("symbol: r[%d]c[%d]\n",rowIdx,colIdx)
        }
        case otherwise => {
          println("spooker!!")
        }
      }
    }

    // cummulative total val
    var cummulativeTotal = 0
    
    // now loop our syms to part numbers
    for(currSymLocation <- symbolLocationList){
      // time to get all the real part numbers surrounding a gear
      //  refreshing this to 0 each time bc we try again
      var realPartNumberLocationList = List[(Int,Int,Int)]()
      // the parts to a sym
      for(currPartNumberLocation <- possiblePartNumberLocationList){
        // debugger tell us about the thing
        if (includeDebuggingInfo) printf( "sym: r[%d]c[%d] to part: r[%d]c[%d]d[%d]\n",
          currSymLocation._1,currSymLocation._2,
          currPartNumberLocation._1,currPartNumberLocation._2,currPartNumberLocation._3
        )
        // now try for matchable
        if( (
          // test row
          Math.abs(currSymLocation._1 - currPartNumberLocation._1) < 2
        ) && (
          // test col
          // check from start
          (currSymLocation._2 >= currPartNumberLocation._2 - 1) &&
          // from end (include part digit count)
          (currSymLocation._2 <= currPartNumberLocation._2 + currPartNumberLocation._3)
        )){
          // should be match
          if(includeDebuggingInfo) printf("MATCHED: r[%d]c[%d] to part: r[%d]c[%d]d[%d]\n",
            currSymLocation._1,currSymLocation._2,
            currPartNumberLocation._1,currPartNumberLocation._2,currPartNumberLocation._3
          )
          // add the number to the location list
          if(!realPartNumberLocationList.contains(currPartNumberLocation)) realPartNumberLocationList = realPartNumberLocationList :+ currPartNumberLocation
          // we should now remove the part number from the part number list but that is spooky with this style of things
          //  maybe we should mark it as done
        }
      }
      // now check we have exactly 2 part numbers
      if(realPartNumberLocationList.length == 2){
        // having both
        // =======================================================
        // ... convert to regular numbers and process
        var gearRatioNums = List[Int]()
        // have good numbers, now process them for end
        for(realPartNum <- realPartNumberLocationList){
          // for stash digits
          var partNumString = ""
          // get the digits
          for(colIdx <- realPartNum._2 to (realPartNum._2+realPartNum._3-1)){
            partNumString = partNumString + charMatrix(realPartNum._1)(colIdx)
          }
          // now we want that as an Int
          var partNumInt = partNumString.toInt
          // then add to our list of ratios
          gearRatioNums = gearRatioNums:+partNumInt
          if (includeDebuggingInfo) printf( "part: r[%d]c[%d]d[%d] -- '%d'\n",
            realPartNum._1,realPartNum._2,realPartNum._3,partNumInt
          )
        }
        // now just add the conversion to cummulative total
        if(includeDebuggingInfo) printf("%d * %d gets %d\n", gearRatioNums(0),gearRatioNums(1),( gearRatioNums(0) * gearRatioNums(1) ))
        cummulativeTotal = cummulativeTotal + (gearRatioNums(0) * gearRatioNums(1))
        // =======================================================
      }
    }

    printf("end up with total: %d\n",cummulativeTotal)
  }
  
  // ========================================
  // ========================================
}