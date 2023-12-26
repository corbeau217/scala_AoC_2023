package day10

import scala.io.Source
import Main._
import java.io._
import scala.collection.mutable.ArraySeq
import day10.GridHandle.getNextTileLocation

// ################################################################################################################################
// ################################################################################################################################
// ################################################################################################################################
// ################################################################################################################################

object GridHandle {

  // ========================================================================================================================
  // ========================================================================================================================
  
  //                      ch, r1, c1, r2, c2
  type TileDef = Tuple5[Char,Int,Int,Int,Int]

  val tileCharList = Array[TileDef](
    //ch,  r1, c1,  r2, c2
    ('.',   0,  0,   0,  0 ), 
    ('S', 999,999, 999,999 ), 
    ('|',  -1,  0,   1,  0 ), 
    ('-',   0, -1,   0,  1 ), 
    ('L',  -1,  0,   0,  1 ), 
    ('J',  -1,  0,   0, -1 ), 
    ('F',   0,  1,   1,  0 ), 
    ('7',   0, -1,   1,  0 )
  )
  val tileUnicodeList = Array[Char](
    ' ', // . 
    '▓', // S 
    '║', // | 
    '═', // - 
    '╚', // L 
    '╝', // J 
    '╔', // F 
    '╗'  // 7
  )

  //              tileDefIdx, r,  c, legal
  type TileUse = Tuple4[Int,Int,Int,Boolean]

  // ========================================================================================================================
  // ========================================================================================================================

  // regex for grabbing any single character
  val singleCharPattern = ".".r

  // ========================================================================================================================
  // ========================================================================================================================

  type TileLoc = Tuple2[Int,Int]

  // doesnt like being given stuff adjacent to start tiles or dirt tiles
  def getNextTileLocation(currentTile:TileUse, prevTileLoc:TileLoc):TileLoc={
    val currentTileDef = tileCharList(currentTile._1)
    val adj1Loc:TileLoc = (currentTileDef._2+currentTile._2,currentTileDef._3+currentTile._3)
    val adj2Loc:TileLoc = (currentTileDef._4+currentTile._2,currentTileDef._5+currentTile._3)

    // when our prev location was the adj1Loc, give 2
    if(prevTileLoc==adj1Loc){
      adj2Loc
    }
    else{
      adj1Loc
    }
  }

  // ========================================================================================================================
  // ========================================================================================================================

  // ...

  // ========================================================================================================================
  // ========================================================================================================================

}

// ################################################################################################################################
// ################################################################################################################################
// ################################################################################################################################
// ################################################################################################################################

object Ofbug {
  // ...
  val logFile:java.io.File = new java.io.File("logs/day10log.txt")

  def out:java.io.PrintStream = {
    if(null==instance){
      // instance = new PrintWriter(logFile)
      instance = new java.io.PrintStream(logFile)
      instance
    }
    else{
      instance 
    }
  }

  var instance:java.io.PrintStream = null

  def print(message:Any):Unit = {
    out.print(message)
    System.out.print(message)
  }
  def println():Unit = {
    out.println()
    System.out.println()
  }
  def println(message:Any):Unit = {
    out.println(message)
    System.out.println(message)
  }

  def close():Unit={
    instance.close()
  }
}

// ################################################################################################################################
// ################################################################################################################################
// ################################################################################################################################
// ################################################################################################################################

object Day10 {

  // ========================================================================================================================
  // ========================================================================================================================

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
          handlePart1(Main.grabLinesFromFile("data/day10testinput2.txt"),true)
          // ...
          // ============================================================
        }
        case 99 => {
          // ============================================================
          // ...
          handlePart2(Main.grabLinesFromFile("data/day10testinput1.txt"),true)
          // ...
          // ============================================================
        }
        // the part matching
        case 1 => {
          // ============================================================
          handlePart1(Main.grabLinesFromFile("data/day10input.txt"),true)
          // ============================================================
        }
        case 2 => {
          // ============================================================
          handlePart2(Main.grabLinesFromFile("data/day10input.txt"),false)
          // ============================================================
        }
        case numberInput => {
          // ============================================================
          Main.failingMessage("DAY 10 INVALID PART NUMBER: "+numberInput)
          // ============================================================
        }
      }

      Ofbug.close()
    }
    catch {
      case e:Exception=> {
        Main.failingMessage("DAY 10 HAD EXCEPTION: "+e.toString())
        e.printStackTrace()
      }
    }
  }

  // ========================================================================================================================
  // ========================================================================================================================

  def tileAcceptsTileAsAdjacency(masterGrid:Array[Array[GridHandle.TileUse]], consumerTileUse:GridHandle.TileUse, subjectTileCoords:Tuple2[Int,Int], includeDebuggingInfo:Boolean):Boolean={

    // tile definition for our tile
    // val consumerTileDef = GridHandle.tileCharList(consumerTileUse._1)

    var printBuffer = String.format("ADJ-TEST [%s][r:%4s][c:%4s][e:%1s] >>> ",GridHandle.tileCharList(consumerTileUse._1)._1.toString(),consumerTileUse._2.toString(),consumerTileUse._3.toString(),(if(consumerTileUse._4) "T" else "F"))

    if(
      ((0 <= subjectTileCoords._1) && (0 <= subjectTileCoords._2)) &&
      ((masterGrid.length > subjectTileCoords._1) && (masterGrid(0).length > subjectTileCoords._2))
    ){
      // real tile
      val subjectTileUse = masterGrid(subjectTileCoords._1)(subjectTileCoords._2)
      val subjectTileDef = GridHandle.tileCharList(subjectTileUse._1)
      
      printBuffer = printBuffer + 
        String.format("[%s][r:%4s][c:%4s][e:%1s] >>> ",GridHandle.tileCharList(subjectTileUse._1)._1.toString(),subjectTileUse._2.toString(),subjectTileUse._3.toString(),(if(subjectTileUse._4) "T" else "F"))

      if(!subjectTileUse._4){
        Ofbug.print(
          printBuffer +
          "FAILURE --- SUBJECT INACTIVE\n"
        )
        false
      }
      else subjectTileDef._1 match {
        case '.' => {
          // dirty consumer, so no
          Ofbug.print(
            printBuffer +
            "FAILURE --- DIRTY\n"
          )
          false
        }
        case 'S' => {
          // start consumer, so yes
          // Ofbug.print(
          //   printBuffer +
          //   "SUCCESS --- START\n"
          // )
          true
        }
        case _ => {
          // otherwise we might?

          val subjectAdjacency1Coords = Tuple2[Int,Int]((subjectTileUse._2+subjectTileDef._2),(subjectTileUse._3+subjectTileDef._3))
          val subjectAdjacency2Coords = Tuple2[Int,Int]((subjectTileUse._2+subjectTileDef._4),(subjectTileUse._3+subjectTileDef._5))

          // test that they're real coords
          if(
            (0<=subjectAdjacency1Coords._1)&&(0<=subjectAdjacency1Coords._2) &&
            (0<=subjectAdjacency2Coords._1)&&(0<=subjectAdjacency2Coords._2) &&
            (masterGrid.length > subjectAdjacency1Coords._1)&&(masterGrid(0).length > subjectAdjacency1Coords._2) &&
            (masterGrid.length > subjectAdjacency2Coords._1)&&(masterGrid(0).length > subjectAdjacency2Coords._2)
          ){ 
            // check that one of them matches the consumer
            if(((consumerTileUse._2==subjectAdjacency1Coords._1)&&(consumerTileUse._3==subjectAdjacency1Coords._2))||
            ((consumerTileUse._2==subjectAdjacency2Coords._1)&&(consumerTileUse._3==subjectAdjacency2Coords._2))){
              // Ofbug.print(
              //   printBuffer +
              //   "SUCCESS --- MATCHED ADJACENCY\n"
              // )
              true
            }
            else{
              Ofbug.print(
                printBuffer +
                "FAILURE --- NOT MATCHING\n"
              )
              false
            }
          }
          else {
            Ofbug.print(
              printBuffer +
              "FAILURE --- OUT OF BOUNDS SUBJECT ADJ\n"
            )
            false
          }
          
        }
      }
    }
    else {
      // spooky tile
      // subjectTileUse:GridHandle.TileUse
      Ofbug.print(
        printBuffer +
        String.format("[?][r:%4s][c:%4s][e:?] >>> SPOOKY SUBJECT COORDS\n",subjectTileCoords._1.toString(),subjectTileCoords._2.toString())
      )
      false
    }

  }

  // ========================================================================================================================
  // ========================================================================================================================

  // for handling the checking of legality
  // assume spooky if called on a ground tile
  def getNumberOfLegalAdjacencies(masterGrid:Array[Array[GridHandle.TileUse]], specificTile:GridHandle.TileUse, includeDebuggingInfo:Boolean):Int={
    
    // tile definition for our tile
    val cellTileDef = GridHandle.tileCharList(specificTile._1)

    // indexes
    val cellRowIdx = specificTile._2
    val cellColIdx = specificTile._3
    
    // ...
    val adjacency1:Tuple2[Int,Int] = Tuple2[Int,Int]((cellRowIdx+cellTileDef._2),(cellColIdx+cellTileDef._3))
    val adjacency2:Tuple2[Int,Int] = Tuple2[Int,Int]((cellRowIdx+cellTileDef._4),(cellColIdx+cellTileDef._5))
    
    var goodAdjacencyCounter = 0

    if(tileAcceptsTileAsAdjacency(masterGrid,specificTile,adjacency1,includeDebuggingInfo)){
      goodAdjacencyCounter = goodAdjacencyCounter + 1
    }
    if(tileAcceptsTileAsAdjacency(masterGrid,specificTile,adjacency2,includeDebuggingInfo)){
      goodAdjacencyCounter = goodAdjacencyCounter + 1
    }

    // then tell the asker
    goodAdjacencyCounter
  }

  // ========================================================================================================================
  // ========================================================================================================================



  // ========================================================================================================================
  // ========================================================================================================================
  
  // sub function for handling the assassination of a tile
  def getNearbyTiles(masterGrid:Array[Array[GridHandle.TileUse]], specificTile:GridHandle.TileUse):Vector[GridHandle.TileUse]={

    // indexes
    val cellRowIdx = specificTile._2
    val cellColIdx = specificTile._3

    var optionResultingVector:Vector[Option[GridHandle.TileUse]] = Vector[Tuple2[Int,Int]](
      ( cellRowIdx+1,  cellColIdx  ),
      ( cellRowIdx-1,  cellColIdx  ),
      (  cellRowIdx , cellColIdx+1 ),
      (  cellRowIdx , cellColIdx-1 )
    ).map(
      (coordTuple)=>{
        // real coord
        (if(
          ((0 <= coordTuple._1) && (0 <= coordTuple._2))&&
          ((masterGrid.length > coordTuple._1) && (masterGrid(0).length > coordTuple._2))
        ){
          // grab the tile
          val wouldBeTileUse = masterGrid(coordTuple._1)(coordTuple._2)
          // check enabled
          if(wouldBeTileUse._4){
            Option(wouldBeTileUse)
          }
          else{
            None
          }
        }
        else{
          None
        })
      }
    )

    // now make a vector of those
    var resultingVector =  Vector[GridHandle.TileUse]()

    optionResultingVector.map(
      (optionalTileUse)=>{
        optionalTileUse match {
          case None => {/* zzzz */}
          case Some(value) => {
            resultingVector = resultingVector :+ value
          }
        }
      }
    )

    // done
    resultingVector
  }
  
  // ========================================================================================================================
  // ========================================================================================================================

  def printMasterGrid(masterGrid:Array[Array[GridHandle.TileUse]],deadTileCharReplacer:Char,includeColoring:Boolean):Unit={
    for(rowIdx <- 0 to masterGrid.length-1){
      for(colIdx <- 0 to masterGrid(0).length-1){
        //...
        Ofbug.print(String.format("%s",(
          if(masterGrid(rowIdx)(colIdx)._4) GridHandle.tileCharList(masterGrid(rowIdx)(colIdx)._1)._1.toString() else deadTileCharReplacer.toString()
        )))
      }
      Ofbug.println()
    }
  }
  
  // ========================================================================================================================
  // ========================================================================================================================

  def printGridAsUnicode(masterGrid:Array[Array[GridHandle.TileUse]],includeColoring:Boolean):Unit={
    for(rowIdx <- 0 to masterGrid.length-1){
      for(colIdx <- 0 to masterGrid(0).length-1){
        Ofbug.print(String.format("%s",(
          if(masterGrid(rowIdx)(colIdx)._4) GridHandle.tileUnicodeList(masterGrid(rowIdx)(colIdx)._1).toString() else GridHandle.tileUnicodeList(0).toString()
        )))
      }
      Ofbug.println()
    }
  }

  // ========================================================================================================================
  // ========================================================================================================================

  def handlePart1(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={

    // break into 2d tile use array
    // --------------------------------------------------------------------------------------------


    val rowCount = inputLines.length
    var masterGrid = new Array[Array[GridHandle.TileUse]](rowCount)
    var updateQueue = Vector[GridHandle.TileUse]()
    // all lines
    for(charGridRowIdx <- 0 to rowCount-1){
      // specific line
      // convert to chars
      val currLineChars = inputLines(charGridRowIdx).toCharArray()
      // save the number
      val colCount = currLineChars.length
      // prepare the row array
      masterGrid(charGridRowIdx) = new Array[GridHandle.TileUse](colCount)
      // all cols in a row
      for(charGridColIdx <- 0 to colCount-1 ){
        // specific row col combination
        val currChar = currLineChars(charGridColIdx)
        val tileDefIdx = GridHandle.tileCharList.indexWhere((tileDef=>{
          tileDef._1==currChar
        }))
        val isLegal = ('.'!=currChar)// make sure it's not ground tile
        masterGrid(charGridRowIdx)(charGridColIdx) = (
          tileDefIdx,
          charGridRowIdx,
          charGridColIdx,
          isLegal
        )
        if(isLegal && 'S'!=GridHandle.tileCharList(tileDefIdx)._1){ // check if ground tile and not start tile
          // add to update queue
          updateQueue = masterGrid(charGridRowIdx)(charGridColIdx)+:updateQueue
        }
      }
    }


    // print out the legal tiles?
    // --------------------------------------------------------------------------------------------
    

    if(includeDebuggingInfo) printMasterGrid(masterGrid,'.',true)

    // has tile use array, now loop all update required till we have none more to update
    // --------------------------------------------------------------------------------------------
    
    while(updateQueue.length > 0){
      if(includeDebuggingInfo) Ofbug.print(String.format("[queueLength: %6s] --- ",updateQueue.length.toString()))
      // grab last leaving the rest in our update queue
      val currUpdatingTile = updateQueue match {
        case rest :+ last => {
          updateQueue = rest
          last
        } 
        case _ => { null }
      }

      if(includeDebuggingInfo) Ofbug.print(String.format(
        "TILE: [%s][r:%4s][c:%4s][g?:%s]\n",
        (GridHandle.tileCharList(currUpdatingTile._1)._1).toString(),
        currUpdatingTile._2.toString(),
        currUpdatingTile._3.toString(),
        (if(0==currUpdatingTile._1){"Y"} else {"N"}))
      )

      val numLegalAdjacencies = getNumberOfLegalAdjacencies(masterGrid,currUpdatingTile,includeDebuggingInfo)
      
      // if(includeDebuggingInfo) if(2==numLegalAdjacencies) Ofbug.print(String.format("[adjCount:%s]\n", numLegalAdjacencies.toString()))

      // when there's not enough legal adjacencies
      if(2 > numLegalAdjacencies){
        updateQueue = getNearbyTiles(masterGrid,currUpdatingTile) ++ updateQueue
        // remake it disabled tho
        masterGrid(currUpdatingTile._2)(currUpdatingTile._3) = (currUpdatingTile._1,currUpdatingTile._2,currUpdatingTile._3,false)
      }

      // otherwise, already popped, so just move along

      // unless empty
      // when empty list, run through everything again just incase
      if(0==updateQueue.length) {
        for(currRowIdx <- 0 to masterGrid.length-1){
          for(currColIdx <- 0 to masterGrid(0).length-1){
            val currTile = masterGrid(currRowIdx)(currColIdx)
            if ((currTile._4) && ('S'!=GridHandle.tileCharList(currTile._1)._1) && (2 > getNumberOfLegalAdjacencies(masterGrid,currTile,includeDebuggingInfo))){
              if(includeDebuggingInfo) Ofbug.print(String.format("[%d][%d] >>> sneaky, recheck needed\n",currRowIdx,currColIdx))
              updateQueue = updateQueue:+currTile
            }
          }
        }
      }
    }

    if(includeDebuggingInfo) Ofbug.println("--------------------------------------------------------------------------------------------")

    // print out the legal tiles?
    // --------------------------------------------------------------------------------------------

    if(includeDebuggingInfo) printMasterGrid(masterGrid,' ',true)
    if(includeDebuggingInfo) printGridAsUnicode(masterGrid,true)

    if(includeDebuggingInfo) Ofbug.println("--------------------------------------------------------------------------------------------")

    // got our legal maze, now we need to setup traversing information
    // --------------------------------------------------------------------------------------------

    // prepare starting coord
    var startLocation:Tuple2[Int,Int] = (-1,-1)

    // prepare traversable grid
    var traverseGrid:Array[Array[GridHandle.TileUse]] = new Array[Array[GridHandle.TileUse]](masterGrid.length)

    // find the start location, also build our traverse grid
    for(currRowIdx <- 0 to masterGrid.length-1){
      // make the that row
      traverseGrid(currRowIdx) = new Array[GridHandle.TileUse](masterGrid(currRowIdx).length)
      // now loopable and make each
      for(currColIdx <- 0 to masterGrid(0).length-1){
        // copy reference
        val stealingTile = masterGrid(currRowIdx)(currColIdx)
        // make the instance at the place
        traverseGrid(currRowIdx)(currColIdx) = (
          (if(stealingTile._4) stealingTile._1 else 0), // turn it into a ground tile if not active
          currRowIdx, // row copy
          currColIdx, // col copy
          false       // inactive till we traverse it
        )
        // check for start
        if('S'==GridHandle.tileCharList(stealingTile._1)._1){
          startLocation = (currRowIdx,currColIdx)
        }
      }
    }
    
    // setup navigating objects
    var possibleFirstMoveList = getNearbyTiles(masterGrid,masterGrid(startLocation._1)(startLocation._2)).filter(
      (tileUse)=>{
        tileAcceptsTileAsAdjacency(masterGrid,masterGrid(startLocation._1)(startLocation._2),(tileUse._2,tileUse._3),includeDebuggingInfo)
      }
    )

    if(includeDebuggingInfo) possibleFirstMoveList.map(
      (tileUse)=>{
        Ofbug.println(String.format("possible location: [%s][r:%s][c:%s]",GridHandle.tileUnicodeList(tileUse._1),tileUse._2,tileUse._3))
      }
    )

    // enable it
    traverseGrid(startLocation._1)(startLocation._2) = (
      traverseGrid(startLocation._1)(startLocation._2)._1,
      startLocation._1,
      startLocation._2,
      true
    )

    // prepare
    var moveCounter = 1

    var searcher1:Tuple4[Int,Int,Int,Int] = (
      possibleFirstMoveList(0)._2,
      possibleFirstMoveList(0)._3,
      startLocation._1,
      startLocation._2
    )
    var searcher2:Tuple4[Int,Int,Int,Int] = (
      possibleFirstMoveList(1)._2,
      possibleFirstMoveList(1)._3,
      startLocation._1,
      startLocation._2
    )


    // enable the locations we are
    traverseGrid(searcher1._1)(searcher1._2) = (
      traverseGrid(searcher1._1)(searcher1._2)._1,
      searcher1._1,
      searcher1._2,
      true
    )
    traverseGrid(searcher2._1)(searcher2._2) = (
      traverseGrid(searcher2._1)(searcher2._2)._1,
      searcher2._1,
      searcher2._2,
      true
    )


    if(includeDebuggingInfo){
      Ofbug.println(String.format("searcher1: [r:%s][c:%s]",searcher1._1,searcher1._2))
      Ofbug.println(String.format("searcher2: [r:%s][c:%s]",searcher2._1,searcher2._2))
    }



    if(includeDebuggingInfo) Ofbug.println("--------------------------------------------------------------------------------------------")

    // do the traversing
    // --------------------------------------------------------------------------------------------

    while( !((searcher1._1 == searcher2._1) && (searcher1._2 == searcher2._2)) ){
      // going new movement
      moveCounter = moveCounter + 1

      val searcher1Next = GridHandle.getNextTileLocation(traverseGrid(searcher1._1)(searcher1._2),(searcher1._3,searcher1._4))
      val searcher2Next = GridHandle.getNextTileLocation(traverseGrid(searcher2._1)(searcher2._2),(searcher2._3,searcher2._4))

      searcher1 = (searcher1Next._1,searcher1Next._2,searcher1._1,searcher1._2)
      searcher2 = (searcher2Next._1,searcher2Next._2,searcher2._1,searcher2._2)


      // enable the locations we are
      traverseGrid(searcher1._1)(searcher1._2) = (
        traverseGrid(searcher1._1)(searcher1._2)._1,
        searcher1._1,
        searcher1._2,
        true
      )
      traverseGrid(searcher2._1)(searcher2._2) = (
        traverseGrid(searcher2._1)(searcher2._2)._1,
        searcher2._1,
        searcher2._2,
        true
      )
    }

    if(includeDebuggingInfo) printGridAsUnicode(traverseGrid,true)
    
    if(includeDebuggingInfo) Ofbug.println("--------------------------------------------------------------------------------------------")

    // print where we get up to
    // --------------------------------------------------------------------------------------------
    
    if(includeDebuggingInfo) {
      Ofbug.println(String.format("moves: %s",moveCounter))
      Ofbug.println(String.format("searcher1: [r:%s][c:%s]",searcher1._1,searcher1._2))
      Ofbug.println(String.format("searcher2: [r:%s][c:%s]",searcher2._1,searcher2._2))
    }

    
    // --------------------------------------------------------------------------------------------
    println(String.format("FURTHEST IS: %s",moveCounter))
  }

  // ========================================================================================================================
  // ========================================================================================================================

  def handlePart2(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    // TODO: DAY 10 PART 2
    println("THIS PART IS UNFINISHED")
  }

  // ========================================================================================================================
  // ========================================================================================================================
  
}

// ################################################################################################################################
// ################################################################################################################################
// ################################################################################################################################
// ################################################################################################################################
