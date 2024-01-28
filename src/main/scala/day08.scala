package day8

import scala.io.Source
import Main._

import scala.collection.immutable.TreeMap
import org.bitbucket.inkytonik.kiama.relation.Tree
import scala.collection.mutable.SortedSet
import java.util.IllegalFormatException

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
          handlePart2(Main.grabLinesFromFile("data/day8input.txt"),true)
          // ============================================================
        }
        case 3 => {
          // ============================================================
          handlePart2bruteforce(Main.grabLinesFromFile("data/day8input.txt"),true)
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

  def handlePart1(inputLines:List[String],includeDebuggingInfo:Boolean):Long={
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

    instructionCounter
  }

  // ========================================
  // ========================================

  def handlePart2bruteforce(inputLines:List[String],includeDebuggingInfo:Boolean):Long= {
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

    var foundNotEndingZ = false

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
    instructionCounter
  }

  // ========================================
  // ========================================

  def handlePart2(inputLines:List[String],includeDebuggingInfo:Boolean):Long={
    
    // acrue our patterns
    // ----------------------------------------------------------------------------------------

    // matches with the first line
    val instructionLinePattern = "([LR]+)".r
    // how big our names are
    val nodeNameNumChars = 3
    // a specific name 
    val nodeNamePattern = String.format("(\\w{%d})",nodeNameNumChars).r
    // the whole mapping line
    val mappingLinePattern = String.format("%s(?: = \\()%s(?:, )%s\\)",nodeNamePattern,nodeNamePattern,nodeNamePattern).r

    // define our structures
    // ----------------------------------------------------------------------------------------
    
    // will be our first line of instructions
    var rawInstructionArray:Array[Char] = Array()
    // the node mappings as their strings for now
    var rawNodeStringMappingTreeMap:TreeMap[String,(String,String)] = TreeMap()
    
    // fill out the structures
    // ----------------------------------------------------------------------------------------

    inputLines.map(
      specificLine=>{
        specificLine match {
          case instructionLinePattern(instructionLineStr) => {
            // make the instruction array
            rawInstructionArray = instructionLineStr.toCharArray()
            if(includeDebuggingInfo) printf("--> acquired %d instructions\n",rawInstructionArray.length)
          }
          case mappingLinePattern(nodeName,leftElem,rightElem) => {
            // add the mapping
            rawNodeStringMappingTreeMap = rawNodeStringMappingTreeMap ++ TreeMap(nodeName -> (leftElem,rightElem))
          }
          case _ => {
            // honk shoo mimimimi
            if(includeDebuggingInfo) printf(">>> spooky line: %s\n",specificLine)
          }
        }
      }
    )

    // reversed names sorted alphabetically
    val nodeMappingKeysRLsort = rawNodeStringMappingTreeMap.keys.toArray.map( // flip them
      specificElem => specificElem.reverse
    ).sorted.map( // sort, then flip them back
      specificElem => specificElem.reverse
    )
    // var sortedSettable:scala.collection.mutable.SortedSet[String] = scala.collection.mutable.SortedSet()
    // rawNodeStringMappingTreeMap.keys.toArray.map(
    //   item=>sortedSettable.addOne(item.reverse)
    // )

    val nodeNameEndsA = "\\w*A".r
    val nodeNameEndsZ = "\\w*Z".r

    val startingNodeNameList = nodeMappingKeysRLsort.filter(
      possibleKey=>{
        nodeNameEndsA.matches(possibleKey)
      }
    )

    // debuggin notification of our values so far
    // ----------------------------------------------------------------------------------------

    if(includeDebuggingInfo&&false){
      // for spacer line print tracking
      var lastEndChar = '@'
      // print them
      nodeMappingKeysRLsort.map(
        mappingElem=>{
          // the name but not reversed
          val mappingElemProperName = mappingElem
          // the actual mapping of that node
          val mappingElemProper = rawNodeStringMappingTreeMap(mappingElemProperName)
          // space out the char ends with groups
          val currEndChar = mappingElemProperName.charAt(mappingElemProperName.length-1)
          if(currEndChar!=lastEndChar){
            lastEndChar = currEndChar // make it this one for next
            //      "AAA -> (AAA,AAA)"
            println(". . . . . . . .")
          }
          printf("%s -> (%s,%s)\n",mappingElemProperName,mappingElemProper._1,mappingElemProper._2)
        }
      )
      println(". . . . . . . .")

      println(">> USING ELEMS:\n. . . . . . . .")
      startingNodeNameList.map(
        startElem=>{
          // the actual mapping of that node
          val realNodeData = rawNodeStringMappingTreeMap(startElem)
          printf("%s -> (%s,%s)\n",startElem,realNodeData._1,realNodeData._2)
        }
      )
    }

    // trace path for each node
    // ----------------------------------------------------------------------------------------
    // Array[(instructionIdx,nodeIdx)] per node
    // then just keep going till we find one that exists already in our list
    // then keep a count of how many times that is for that node

    var things:Vector[Tuple2[Int,String]] = Vector()
    

    val nodePathList = startingNodeNameList.map(
      specificStartingNode => {
        var instructionCounter:Long = -1
        var instructionCurrIdx:Int = -1

        var loopStartInstruction:Int = -1
        
        var nodeInstructionPairing:TreeMap[Tuple2[Int,String],Long] = TreeMap()
        var lastInstructionPair:Tuple2[Int,String] = ( instructionCurrIdx, specificStartingNode )


        // iterate over the instructions till we start repeating what we're adding
        while(!(nodeInstructionPairing.keySet.contains(lastInstructionPair)/* && lastInstructionPair._2.matches(nodeNameEndsZ.regex)*/)){
          // add the last one
          nodeInstructionPairing = nodeInstructionPairing ++ TreeMap(lastInstructionPair -> instructionCounter)
          // update the counter and index
          instructionCounter = instructionCounter + 1
          instructionCurrIdx = (instructionCounter % rawInstructionArray.length).toInt
          // get mapping to work with
          val currMapping = rawNodeStringMappingTreeMap(lastInstructionPair._2)
          val prevInstructionPair = lastInstructionPair
          // now handle the instructiong
          lastInstructionPair = (
            instructionCurrIdx,
            (rawInstructionArray(instructionCurrIdx) match {
              case 'L' => {
                // left branch
                currMapping._1
              }
              case 'R' => {
                // right branch
                currMapping._2
              }
              case ch => {
                // que pasa senor?
                // nan desu ka
                // qu'est que ce?
                throw new IllegalArgumentException(String.format("HAD: '%s', ONLY 'L' OR 'R' ALLOWED FOR INSTRUCTIONS",ch))
              }
            })
          )
          // debuggables
          if(includeDebuggingInfo) printf("%s: [%6d][%3d] --> %s\n",specificStartingNode,instructionCounter,instructionCurrIdx,lastInstructionPair)
        }
        // done getting the path info
        if(includeDebuggingInfo) printf("===> %s HAD %d instructions until looping\n",specificStartingNode,instructionCounter)
        things = things :+ lastInstructionPair
        // returnable
        (
          specificStartingNode,
          nodeInstructionPairing,
          nodeInstructionPairing(lastInstructionPair),
          instructionCounter
        )
      }
    )

    things.map(println(_))

    // deal with the found information
    // ----------------------------------------------------------------------------------------
    
    // say the useful debug stuff
    if(includeDebuggingInfo){
      println("============================================================")
      nodePathList.map(
        specificNodePath =>{
          printf("%s ==> [loopStartIdx: %3d] -- [instructions: %6d]\n",specificNodePath._1,specificNodePath._3,specificNodePath._2.size)
        }
      )
      println("============================================================")
    }
    
    // now we have the loop starts
    //  we need the ends Z indexes
    
    var nodePathListWithEndsZ = nodePathList.map(
      nodePathData => {
        (
          nodePathData._1,
          nodePathData._2,
          nodePathData._3,
          // filter to only end Z
          nodePathData._2.filter(
            specificInstructionData=>{
              // end in Z
              specificInstructionData._1._2.matches(nodeNameEndsZ.regex)
            }
          ),
          nodePathData._4
        )
      }
    )

    // debugging value, igonre when includeDebuggingInfo == false
    var variableChar = 'a'
    
    // acquire the loop data
    var loopData = nodePathListWithEndsZ.map(
      item=>{
        val returnableItem = (
          // start name
          item._1,
          // instructions before loop starts
          item._3,
          // loop instruction count
          item._5-item._3,
          // end Z counter
          item._4.head._2
        )

        // for when we want to know the equation information
        if(includeDebuggingInfo){
          printf(
            "%s --> [loopIdx: %d][endzCount: %5d][endzInstr: %3s][period: %5d]\n",
            // start name
            item._1,
            // start idx
            item._3,
            // end counter
            item._4.head._2,
            // end name
            item._4.head._1._2,
            // 
            item._4.head._2-item._3
          )
          var nodeEquation = String.format("%s: %d + (%c * %d) = x",returnableItem._1,returnableItem._2,variableChar,returnableItem._3)
          println(nodeEquation)
          variableChar = (variableChar.toInt + 1).toChar
        }

        returnableItem
      }
    )
    
    // startPath -> loopStart -> endZ -> loopIterEnd
    // ...
    // endZ
    //  endZ + (a * loopSize) = ...
    
    // need to figure out if instruction counter is on z
    var settable:scala.collection.mutable.SortedSet[Tuple5[Long,Long,String,Long,Long]] = SortedSet()

    loopData.map(
      item => settable.add(
        (
          item._4,
          0l,
          item._1,
          item._2,
          item._3,
        )
      )
    )

    var loopCheckCounter1 = 0
    var loopCheckCounter2 = 0

    val notifyPeriod1 = 10000000
    val notifyPeriod2 = 10
    val notifyPeriod3 = 100

    var changeInLoops = 0

    var lowestDif:Long = settable.last._1-settable.head._1
    var currDif:Long = 0

    var logFile = new java.io.File("logs/day08log.txt")
    var printStreamable = new java.io.PrintStream(logFile)

    val logFilePattern = "%s [head:%s][last:%s] ,--> [%d] ,-- [lowest:%d]\n"

    while(lowestDif > 0){
      var updatingElem = settable.head
      var restSet = settable.tail
      

      // printf("%s --> ",updatingElem)
      
      updatingElem = (
        updatingElem._1 + updatingElem._5, // add a loop to it
        updatingElem._2 + 1, // increase loop count
        updatingElem._3,
        updatingElem._4,
        updatingElem._5
      )

      // remake the first one with another loop
      settable = restSet.addOne( updatingElem )

      // println(updatingElem)

      currDif = Math.abs(settable.last._1-settable.head._1)

      if(currDif<lowestDif){
        // ...
        lowestDif = currDif
        printStreamable.printf(logFilePattern,"NEW >>",settable.head,settable.last,currDif,lowestDif)
        print("#")
        if(includeDebuggingInfo){
          if(((loopCheckCounter1+1)/notifyPeriod1) != (loopCheckCounter1/notifyPeriod1)){
            loopCheckCounter2 = loopCheckCounter2 + 1
          }
          loopCheckCounter1 = loopCheckCounter1 + 1
        }
      } else
      if(includeDebuggingInfo){
        if(((loopCheckCounter1+1)/notifyPeriod1) != (loopCheckCounter1/notifyPeriod1)){
          if(((loopCheckCounter2+1)/notifyPeriod2) != (loopCheckCounter2/notifyPeriod2)){
            //...
            if(((loopCheckCounter2+1)/notifyPeriod3) != (loopCheckCounter2/notifyPeriod3)){
              printStreamable.printf(logFilePattern,"*",settable.head,settable.last,currDif,lowestDif)
              print("*")
            }
            else {
              //...
              print("'")
            }
          }
          else {
            //...
            print(".")
          }
          loopCheckCounter2 = loopCheckCounter2 + 1
        }
        loopCheckCounter1 = loopCheckCounter1 + 1
      }
    }
    if(includeDebuggingInfo) println()

    printStreamable.close()
    // print them

    settable.map(println(_))

    // things.map(println(_))
    
    // ----------------------------------------------------------------------------------------
    settable.head._1
  }

  // ========================================
  // ========================================
}