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
          handlePart2(Main.grabLinesFromFile("data/day8testinput3.txt"),true)
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
          handlePart2bruteforce(Main.grabLinesFromFile("data/day8input.txt"),false)
          // ============================================================
        }
        case 3 => {
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

    // creation of our data
    // ----------------------------------------------------------------------------------------

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

    // debugging notifications
    // ----------------------------------------------------------------------------------------

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


    // prepare for loopin'
    // ----------------------------------------------------------------------------------------

    // now we start with AAA
    var currNodeName = "AAA"
    var currNode = nodeListMap.head._2
    if(includeDebuggingInfo) printf("STARTING: %s -> (%s,%s)\n",currNodeName,currNode._1,currNode._2)
    
    val lastNodeKey = nodeListMap.last._1
    if(includeDebuggingInfo) printf("ENDING: %s\n",lastNodeKey)

    // need to modulo with the length of the thing
    var instructionCounter:Long = 0


    // loopin' time
    // ----------------------------------------------------------------------------------------

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

    // done, tell the people
    // ----------------------------------------------------------------------------------------

    printf(
      "ENDED.\nINSTRUCTION COUNTER:  %5d\nINSTRUCTION SET USES: %5d\n",
      instructionCounter,
      (instructionCounter/instructionList.length)
    )
  }

  // ========================================
  // ========================================

  def handlePart2bruteforce(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    val periodMatching = Array(
        (1000000000l,  "#"),
        (100000000l,   "*"),
        (10000000l,    "'"),
        (1000000l,    ".")
      )


    val nodeNameMatcher = "[0-9A-Za-z]{3}".r

    val nodeNameEndsA = "(.){2}A".r
    val nodeNameEndsZ = "(.){2}Z".r
    

    //get our chars
    val instructionList = inputLines(0).toCharArray()

    var nodeListMap = TreeMap[String, (String,String)]()

    // warn the user
    // ----------------------------------------------------------------------------------------
    println("this will take a while, maybe try 'pt3' instead to avoid trying to bruteforce this one.\nlike, a really long time.")
    println("m --> all nodes match first node they were (never happens)")
    periodMatching.map(
      (periodMatcher)=>{
        //...
        printf("%s --> %d instructions performed\n",periodMatcher._2,periodMatcher._1)
      }
    )

    // creation of our data
    // ----------------------------------------------------------------------------------------

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

    // debugging notifications
    // ----------------------------------------------------------------------------------------

    if(includeDebuggingInfo) nodeListMap.get("AAA") match {
      case Some(value) => {
        printf("AAA found: %s\n",value.toString())
      }
      case None => {
        println("AAA didnt at all, henk")
        // bout to have some rly sspookiess
      }
    }

    // tell the world about our mappings
    if(includeDebuggingInfo){
      println("ELEMS:\n-------------------")
      for(elem <- nodeListMap){
        printf("%s : (%s,%s)\n",elem._1,elem._2._1,elem._2._2)
      }
      println("-------------------")
    }


    // make the list of starting nodes
    // ----------------------------------------------------------------------------------------

    var workingNodesList = Array[Tuple2[String,(String,String)]]()

    // search everywhere for them
    for(currNode <- nodeListMap){
      // when matching
      if(currNode._1.matches(nodeNameEndsA.regex)){
        workingNodesList = workingNodesList :+ (currNode._1,currNode._2)
      }
    }

    // prepare for loopin'
    // ----------------------------------------------------------------------------------------

    var startingNodesList = workingNodesList.clone()

    // prepare for loopin'
    // ----------------------------------------------------------------------------------------

    // let em know
    if(includeDebuggingInfo){
      println("WORKING NODES:\n-------------------")
      for(currNode <- workingNodesList){
        if(includeDebuggingInfo) printf("%s ---> (%s,%s)\n",currNode._1,currNode._2._1,currNode._2._2)
      }
      println("-------------------")
    }

    // need to modulo with the length of the thing
    var instructionCounter:Long = 0

    var prevInstructionCounter:Long = 0

    var foundNotEndingZ = true

    var foundNotMatchingFirst = false

    // loopin' time
    // ----------------------------------------------------------------------------------------

    val doLooper = true

    // when we havent found a set of things ending in Z
    if(doLooper) while(foundNotEndingZ){
      // reset our Z finder var, to assume the best
      // --------------------------------------------------
      foundNotEndingZ = false

      // crop our instruction count to be an index
      // --------------------------------------------------
      val currInstructionIndex:Int = (instructionCounter % instructionList.length).toInt
      
      // get the instruction to use
      // --------------------------------------------------
      val currInstruction = instructionList(currInstructionIndex)

      // the node indexing
      // --------------------------------------------------
      var nodeIdx = 0

      // now handle the instruction
      // --------------------------------------------------
      workingNodesList = workingNodesList.map(
        (currNodeToUpdate)=>{
          // test our node for matches first
          // -------------------------------
          if(currNodeToUpdate._1 != startingNodesList(nodeIdx)._1){
            foundNotMatchingFirst = true
          }
          
          // update our index for the testable
          // -------------------------------
          nodeIdx = nodeIdx+1

          // get our new key
          // -------------------------------
          val newKey = currInstruction match {
            case 'L' => {
              // ... left branching
              currNodeToUpdate._2._1
            }
            case 'R' => {
              // ... right branching
              currNodeToUpdate._2._2
            }
            case ch => {
              // da heck
              if(includeDebuggingInfo) printf("!!! %3d: GOT A SPOOKY INSTRUCTION CAPITAINE: %c\n",currInstructionIndex,ch)
              "???"
            }
          }

          // tell about our key change
          // -------------------------------
          if(includeDebuggingInfo) printf("[%6d][%c]: %s --> %s\n",instructionCounter,currInstruction,currNodeToUpdate._1,newKey)
          
          // test our key for ends Z
          // -------------------------------
          if( !(newKey.matches(nodeNameEndsZ.regex)) ){
            foundNotEndingZ = true
          }

          // generate our new node
          // -------------------------------
          (
            newKey,
            (nodeListMap.get(newKey) match {
              case None => {
                // failure to find the new node
                if(includeDebuggingInfo) printf("!!! %3s: SPOOKY FOUNDABLE OF NONE\n",newKey)
                ("???","???")
              }
              case Some(value) => {
                // gooderer return
                value
              }
            })
          )
        }
      )
      prevInstructionCounter = instructionCounter
      // increment the instruction counter
      // --------------------------------------------------
      instructionCounter = instructionCounter + 1

      if(!includeDebuggingInfo){
        periodMatching.indexWhere((item)=>{ prevInstructionCounter/item._1 != instructionCounter/item._1}) match {
          case -1 => {
            // honk shoo
          }
          case idxToPrint if(0 <= idxToPrint && periodMatching.length > idxToPrint) =>{
            print(periodMatching(idxToPrint)._2)
          }
          case _: Int => {
            // honk shoo mimimimi
          }
        }

        if(!foundNotMatchingFirst) print("m")
      }
      foundNotMatchingFirst = false
    }


    // done, tell the people
    // ----------------------------------------------------------------------------------------


    // let em know
    if(includeDebuggingInfo){
      println("\nWORKING NODES:\n-------------------")
      for(currNode <- workingNodesList){
        if(includeDebuggingInfo) printf("%s ---> (%s,%s)\n",currNode._1,currNode._2._1,currNode._2._2)
      }
      println("-------------------")
    }

    printf(
      "ENDED.\nINSTRUCTION COUNTER:  %5d\nINSTRUCTION SET USES: %5d\n",
      instructionCounter,
      (instructionCounter/instructionList.length)
    )

  }

  // ========================================
  // ========================================

  def handlePart2(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    val beepSmallPeriod = 1000000
    val beepMediumPeriod = 10000000
    val beepLargePeriod = 100000000


    val nodeNameMatcher = "[0-9A-Za-z]{3}".r

    val nodeNameEndsA = "(.){2}A".r
    val nodeNameEndsZ = "(.){2}Z".r
    

    //get our chars
    val instructionList = inputLines(0).toCharArray()

    var nodeListMap = TreeMap[String, (String,String)]()

    // creation of our data
    // ----------------------------------------------------------------------------------------

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

    // debugging notifications
    // ----------------------------------------------------------------------------------------

    if(includeDebuggingInfo) nodeListMap.get("AAA") match {
      case Some(value) => {
        printf("AAA found: %s\n",value.toString())
      }
      case None => {
        println("AAA didnt at all, henk")
        // bout to have some rly sspookiess
      }
    }

    // tell the world about our mappings
    if(includeDebuggingInfo){
      println("ELEMS:\n-------------------")
      for(elem <- nodeListMap){
        printf("%s : (%s,%s)\n",elem._1,elem._2._1,elem._2._2)
      }
      println("-------------------")
    }


    // make the list of starting nodes
    // ----------------------------------------------------------------------------------------

    var workingNodesList = Array[Tuple2[String,(String,String)]]()

    // search everywhere for them
    for(currNode <- nodeListMap){
      // when matching
      if(currNode._1.matches(nodeNameEndsA.regex)){
        workingNodesList = workingNodesList :+ (currNode._1,currNode._2)
      }
    }

    // prepare for loopin'
    // ----------------------------------------------------------------------------------------

    var startingNodesList = workingNodesList.clone()

    // prepare for loopin'
    // ----------------------------------------------------------------------------------------

    // let em know
    if(includeDebuggingInfo){
      println("WORKING NODES:\n-------------------")
      for(currNode <- workingNodesList){
        if(includeDebuggingInfo) printf("%s ---> (%s,%s)\n",currNode._1,currNode._2._1,currNode._2._2)
      }
      println("-------------------")
    }

    // need to modulo with the length of the thing
    var instructionCounter:Long = 0

    var foundNotEndingZ = true

    var foundNotMatchingFirst = false

    // loopin' time
    // ----------------------------------------------------------------------------------------

    val doLooper = true

    // when we havent found a set of things ending in Z
    if(doLooper) while(foundNotEndingZ){
      // reset our Z finder var, to assume the best
      // --------------------------------------------------
      foundNotEndingZ = false

      // crop our instruction count to be an index
      // --------------------------------------------------
      val currInstructionIndex:Int = (instructionCounter % instructionList.length).toInt
      
      // get the instruction to use
      // --------------------------------------------------
      val currInstruction = instructionList(currInstructionIndex)

      // the node indexing
      // --------------------------------------------------
      var nodeIdx = 0

      // now handle the instruction
      // --------------------------------------------------
      workingNodesList = workingNodesList.map(
        (currNodeToUpdate)=>{
          // test our node for matches first
          // -------------------------------
          if(currNodeToUpdate._1 != startingNodesList(nodeIdx)._1){
            foundNotMatchingFirst = true
          }
          
          // update our index for the testable
          // -------------------------------
          nodeIdx = nodeIdx+1

          // get our new key
          // -------------------------------
          val newKey = currInstruction match {
            case 'L' => {
              // ... left branching
              currNodeToUpdate._2._1
            }
            case 'R' => {
              // ... right branching
              currNodeToUpdate._2._2
            }
            case ch => {
              // da heck
              if(includeDebuggingInfo) printf("!!! %3d: GOT A SPOOKY INSTRUCTION CAPITAINE: %c\n",currInstructionIndex,ch)
              "???"
            }
          }

          // tell about our key change
          // -------------------------------
          if(includeDebuggingInfo) printf("[%6d][%c]: %s --> %s\n",instructionCounter,currInstruction,currNodeToUpdate._1,newKey)
          
          // test our key for ends Z
          // -------------------------------
          if( !(newKey.matches(nodeNameEndsZ.regex)) ){
            foundNotEndingZ = true
          }

          // generate our new node
          // -------------------------------
          (
            newKey,
            (nodeListMap.get(newKey) match {
              case None => {
                // failure to find the new node
                if(includeDebuggingInfo) printf("!!! %3s: SPOOKY FOUNDABLE OF NONE\n",newKey)
                ("???","???")
              }
              case Some(value) => {
                // gooderer return
                value
              }
            })
          )
        }
      )
      val prevInstructionLoopCounts = instructionCounter/beepSmallPeriod
      val prevInstructionLoopCountm = instructionCounter/beepMediumPeriod
      val prevInstructionLoopCountl = instructionCounter/beepLargePeriod
      // increment the instruction counter
      // --------------------------------------------------
      instructionCounter = instructionCounter + 1

      if(!includeDebuggingInfo){
        if(prevInstructionLoopCountl != instructionCounter/beepLargePeriod) print("*") 
        else if(prevInstructionLoopCountm != instructionCounter/beepMediumPeriod) print("'") 
        else if(prevInstructionLoopCounts != instructionCounter/beepSmallPeriod) print(".") 
        if(!foundNotMatchingFirst) print("m")
      }
      foundNotMatchingFirst = false
    }


    // done, tell the people
    // ----------------------------------------------------------------------------------------


    // let em know
    if(includeDebuggingInfo){
      println("\nWORKING NODES:\n-------------------")
      for(currNode <- workingNodesList){
        if(includeDebuggingInfo) printf("%s ---> (%s,%s)\n",currNode._1,currNode._2._1,currNode._2._2)
      }
      println("-------------------")
    }

    printf(
      "ENDED.\nINSTRUCTION COUNTER:  %5d\nINSTRUCTION SET USES: %5d\n",
      instructionCounter,
      (instructionCounter/instructionList.length)
    )



    println("TODO")
  }

  // ========================================
  // ========================================
}