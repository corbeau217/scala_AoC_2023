package day1

import Main._

// import 

object Day1 {
  
  // ###############################################################
  // ###############################################################

  /**
    * inlet for the code
    *
    * @param partNumber
    */
  def handleDay(partNumber:Int):Unit={
    try{
      partNumber match {
        // testing addition
        case 98 => {
          // ============================================================
          // ...
          handlePart1(Main.grabLinesFromFile("data/day1testinput1.txt"),true)
          // ...
          // ============================================================

        }
        case 99 => {
          // ============================================================
          // ...
          handlePart2(Main.grabLinesFromFile("data/day1testinput1.txt"),true)
          // ...
          // ============================================================
          
        }
        // hand off sandbox
        case 100 => { 
          // ============================================================
          sandbox()
          // ============================================================
        }
        // aaaaa the parts
        case num if(1==num|2==num) => { //if(partNumber.==(1).||(partNumber.==(2)))
          // ============================================================
          // hand off to function
          var inputLines = Main.grabLinesFromFile("data/day1input.txt")
          printf(
            "line count: %d\nresult: %d\n",
            inputLines.length,
            day1AccumulateList(
              inputLines,
              0,
              ( // function to process with, could simplify to Int
                (s:String)=>{handleInputSingleLine(num,s)}
              )
            )
          )
          // ============================================================
        }
        case numberInput => {
          Main.failingMessage("DAY 1 INVALID PART NUMBER: "+numberInput)
        }
      }
    }
    catch {
      case e:Exception=> Main.failingMessage("DAY 1 HAD EXCEPTION: "+e.toString())
    }
  }
  
  // ###############################################################
  // ###############################################################
  
  def handlePart1(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    printf(
      "line count: %d\nresult: %d\n",
      inputLines.length,
      day1AccumulateList(
        inputLines,
        0,
        ( // function to process with, could simplify to Int
          (s:String)=>{handleInputSingleLine(1,s)}
        )
      )
    )
  }
  
  // ###############################################################
  // ###############################################################
  
  def handlePart2(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={

    inputLines.map((singleLine)=>{
      var lineLengthable = singleLine.length()
      // tbh just assume that it's always got the thing in it to find
      var digitsListForward = (
        Numbers.refinedDigitAndNameMatcher
      ).findAllIn(singleLine).toList

      var digitsListReverse = (
        Numbers.refinedDigitAndNameMatcherReversed
      ).findAllIn(singleLine.reverse).toList

      // println("=====================================================")
      printf("LINE[%s]: %s",lineLengthable.toString(),singleLine)
      // print("LR--> ")
      // digitsListForward.map( (str) => printf( "[ %5s ]",str ))
      printf("\nLR[%3s]--> ",digitsListForward.length.toString())
      digitsListForward.map( (str) => printf( "[ %s ]",(
        if( str.matches(Numbers.digitMatcher) ) str
        else Numbers.lazyDigitNameAsInt(str).toString()
      )) )
      // print("\nRL--> ")
      // digitsListReverse.reverse.map( (str) => printf( "[ %5s ]",str.reverse ))
      printf("\nRL[%3s]--> ",digitsListReverse.length.toString())
      digitsListReverse.map( (str) => printf( "[ %s ]",(
        if( str.matches(Numbers.digitMatcher) ) str
        else Numbers.lazyDigitNameAsInt(str.reverse).toString()
      )) )
      println("")
    })

    // ============================================================
    printf(
      "line count: %d\nresult: %d\n",
      inputLines.length,
      day1AccumulateList(
        inputLines,
        0,
        ( // function to process with, could simplify to Int
          (s:String)=>{handleInputSingleLine(2,s)}
        )
      )
    )

    
    
  }

  // ###############################################################
  // ###############################################################

  def handleInputSingleLine(partNumber:Int,lineToHandle:String):Int={

    // tbh just assume that it's always got the thing in it to find
    var digitsList = (
      if(partNumber==1) Numbers.digitMatcher.r
      else Numbers.refinedDigitAndNameMatcher
    ).findAllIn(lineToHandle).toList

    var digitsListReversed = (
      if(partNumber==1) Numbers.digitMatcher.r
      else Numbers.refinedDigitAndNameMatcherReversed
    ).findAllIn(
      lineToHandle.reverse // need to look in the reversed order
    ).toList

    var headDigit = digitsList.head
    var lastDigit = digitsListReversed.head.reverse // reverse the reversed bc then it's a real word
    // var lastDigit = digitsList.last

    // handle converting based on part
    var convertedHead = {
      // fail early into part one parseables
      if( ( partNumber.==(1) ).||( headDigit.matches(Numbers.digitMatcher) ) ) {
        headDigit.toInt
      }
      else {
        Numbers.lazyDigitNameAsInt(headDigit)
      }
    }
    var convertedLast = {
      // fail early into part one parseables
      if( ( partNumber.==(1) ).||( lastDigit.matches(Numbers.digitMatcher) ) ) {
        lastDigit.toInt
      }
      else {
        Numbers.lazyDigitNameAsInt(lastDigit)
      }
    }

    var convertedTotal = (convertedHead*10)+convertedLast

    // // .. debugging out the output
    // printf("[h: %5s ][l: %5s ][t: %d ]\n",headDigit,lastDigit,convertedTotal)

    // returning:
    convertedTotal
  }
  
  // ###############################################################
  // ###############################################################

  def day1AccumulateList(listToProcess:List[String],valueSoFar:Int, lineHandleFunction:String => Int):Int={
    listToProcess match {
      case Nil => valueSoFar
      case head +: rest => {
        day1AccumulateList( rest, ( valueSoFar + lineHandleFunction(head) ), lineHandleFunction )
      }
    }
  }

  // ###############################################################
  // ###############################################################

  def sandbox():Unit={

    // val dayDigitList = List(
    //   '1',
    //   '2',
    //   '3',
    //   '4',
    //   '5',
    //   '6',
    //   '7',
    //   '8',
    //   '9'
    // )
    // val dayCharList = List(
    //   'o','n','e',
    //   't','w','o',
    //   't','h','r','e','e',
    //   'f','o','u','r',
    //   'f','i','v','e',
    //   's','i','x',
    //   's','e','v','e','n',
    //   'e','i','g','h','t',
    //   'n','i','n','e'
    //   )  
      
    //   var uniqueCharacterList = List(' ')
    //   for( ch <- dayCharList){
    //     if(!uniqueCharacterList.contains(ch)) {
    //       uniqueCharacterList = uniqueCharacterList:+ch
    //     }
    //   }
      
    //   println(uniqueCharacterList.tail.sorted.toString())


  }
  
  // ###############################################################
  // ###############################################################

}