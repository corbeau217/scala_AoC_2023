package day8

import scala.io.Source
import Main._

import scala.collection.immutable.TreeMap

object Day8 {
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
          handlePart1(Main.grabLinesFromFile("data/day8testinput1.txt"),true)
          // ...
          // ============================================================
        }
        case 99 => {
          // ============================================================
          // ...
          handlePart2(Main.grabLinesFromFile("data/day8testinput1.txt"),true)
          // ...
          // ============================================================
        }
        // the part matching
        case 1 => {
          // ============================================================
          handlePart1(Main.grabLinesFromFile("data/day8input.txt"),true)
          // ============================================================
        }
        case 2 => {
          // ============================================================
          handlePart2(Main.grabLinesFromFile("data/day8input.txt"),false)
          // ============================================================
        }
        case numberInput => {
          // ============================================================
          Main.failingMessage("DAY 8 INVALID PART NUMBER: "+numberInput)
          // ============================================================
        }
      }
    }
    catch {
      case e:Exception=> Main.failingMessage("DAY 8 HAD EXCEPTION: "+e.toString())
    }
  }

  // ========================================
  // ========================================

  def handlePart1(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    val nodeNameMatcher = "[A-Z]{3}".r
    

    //get our chars
    val instructionList = inputLines(0).toCharArray()

    var nodeListMap = TreeMap[String, (String,String)]()
    // assuming we start instructions from line index 2
    // add all the node data to a vector
    for(lineIdx <- 2 to inputLines.length-1){
      // nodeListMap = nodeListMap :+
      nodeNameMatcher.findAllIn(inputLines(lineIdx)).toArray match {
        // when found good, add it
        case Array(keyStr,leftKeyStr,rightKeyStr) => {
          nodeListMap = nodeListMap ++ TreeMap(keyStr -> (leftKeyStr,rightKeyStr))
        }

        // elseing
        case otherwiseElem =>{
          if(includeDebuggingInfo) printf("spooky array line: %s\n",otherwiseElem)
        }
      }
    }

    if(includeDebuggingInfo) nodeListMap.get("AAA") match {
      case Some(value) => {
        printf("AAA found: %s\n",value.toString())
      }
      case None => {
        println("AAA didnt at all, henk")
      }
    }

    // tell the world about our mappings
    if(includeDebuggingInfo) for(elem <- nodeListMap){
      printf("elem.get(%s) : Some((%s,%s))\n",elem._1,elem._2._1,elem._2._2)
    }


    // now we start with AAA
    var currNodeName = "AAA"
    var currNode = nodeListMap.head._2
    if(includeDebuggingInfo) printf("STARTING: %s -> (%s,%s)\n",currNodeName,currNode._1,currNode._2)
    
    val lastNodeKey = nodeListMap.last._1
    if(includeDebuggingInfo) printf("ENDING: %s\n",lastNodeKey)

    // need to modulo with the length of the thing
    var instructionCounter:Long = 0

    // WHILE WE'RE NOT AT OUR DESIRED LOCATION WE GRAB AS PER THE INSTRUCTIONS
    while(lastNodeKey!=currNodeName){
      // crop our instruction count to be an index
      val currInstructionIndex:Int = (instructionCounter % instructionList.length).toInt
      // get the instruction to use
      val currInstruction = instructionList(currInstructionIndex)
      // now handle the instruction
      val newKey = currInstruction match {
        case 'L' => {
          // ... left branching
          currNode._1
        }
        case 'R' => {
          // ... right branching
          currNode._2
        }
        case ch => {
          // da heck
          if(includeDebuggingInfo) printf("!!! %3d: GOT A SPOOKY INSTRUCTION CAPITAINE: %c\n",currInstructionIndex,ch)
          "???"
        }
      }
      nodeListMap.get(newKey) match {
        case None => {
          // spooky retrieval
          if(includeDebuggingInfo) printf("!!! %s: couldn't find an elem !!!\n",newKey)
        }
        case Some(retrievedValue) => {
          // got the new elem
          currNodeName = newKey
          currNode = retrievedValue
          if(includeDebuggingInfo) printf("[%3d][%5d][i:%1s] %s -> (%s,%s)\n",
            (instructionCounter/instructionList.length),
            currInstructionIndex,
            currInstruction,
            newKey,
            retrievedValue._1,
            retrievedValue._2
          )
        }
      }
      // increment the instruction counter
      instructionCounter = instructionCounter + 1
    }

    printf(
      "ENDED.\nINSTRUCTION COUNTER:  %5d\nINSTRUCTION SET USES: %5d\n",
      instructionCounter,
      (instructionCounter/instructionList.length)
    )

    // println("TODO")
  }

  // ========================================
  // ========================================

  def handlePart2(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    // TODO: DAY 8 PART 2
    println("TODO")
  }

  // ========================================
  // ========================================
}