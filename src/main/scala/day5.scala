package day5

import scala.io.Source
import Main._
// import scala.language.postfixOps
import org.bitbucket.inkytonik.kiama.output.PrettyPrinter.{any, layout}
import org.bitbucket.inkytonik.kiama.parsing.{Success, Parsers}
import org.bitbucket.inkytonik.kiama.util.{FileSource, Positions}
import java.io.FileNotFoundException

// ################################################################################
// ################################################################################

object MapLangTree {
  // common super of all the things
  sealed abstract class MapLangNode

  sealed abstract class Exp extends MapLangNode

  case class Input (seeds : Vector[Long], mapList : Vector[MapDefnExp]) extends MapLangNode

  // the particular mapping
  case class MapExp (destStart : Long, sourceStart : Long, rangeSize : Long) extends Exp

  // for a map block
  case class MapDefnExp (source : String, destination : String, mappings : Vector[MapExp]) extends Exp

}

// ################################################################################
// ################################################################################

class MapAnalysis(positions : Positions) extends Parsers (positions) {

  import MapLangTree._
  import scala.language.postfixOps

  lazy val parser : PackratParser[Input] =
      phrase (input)

  lazy val input : PackratParser[Input] =
      seedList ~ mapDefList ^^ Input /*|
    failure ("Input expected") */

  lazy val longExp : PackratParser[Long] =
    regex ("[0-9]+".r) ^^ (s => s.toLong)

  lazy val idnExp : PackratParser[String] =
     regex ("[a-zA-Z]+".r)

  lazy val seedList : PackratParser[Vector[Long]] = 
    "seeds: " ~> longExp ~ ((longExp*)) ^^ { (a:Long,b:Vector[Long]) => a+:b }

  lazy val mapExp : PackratParser[MapExp] = 
    longExp ~ longExp ~ longExp ^^ MapExp

  lazy val mapDefnExp : PackratParser[MapDefnExp] =
    idnExp ~ ("-to-" ~> idnExp <~ "map:") ~ mapExp ~ ((mapExp)*) ^^ { (leftIdn:String, rightIdn:String, firstMap:MapExp, restMap:Vector[MapExp]) => MapDefnExp(leftIdn,rightIdn, firstMap+:restMap) }

  lazy val mapDefList : PackratParser[Vector[MapDefnExp]] = 
    mapDefnExp ~ ((mapDefnExp)*) ^^ { ( first:MapDefnExp, rest:Vector[MapDefnExp]) => first +: rest }

}

// ################################################################################
// ################################################################################

object MapRangeTree {

  // ========================================
  // ========================================
  
  /**
    * @brief common super class/type for the range nodes in the tree
    */
  sealed abstract class RangeNode

  /**
    * @brief this is a range node that is yet to be split
    * @param rangeStart where it begins??
    * @param rangeEnd where it ends honestly
    * @param rangeTransformCount 0 when it's still the seed range, and increases each time it is transformed
    */
  case class SpecifiedRange ( rangeStart : Long, rangeEnd : Long, rangeTransformCount : Int ) extends RangeNode

  /**
    * @brief binary node that has children to make a tree structure
    * @param leftRange either a divided range or a specified range child node to the left side to keep binary
    * @param rightRange either a divided range or a specified range child node
    */
  case class DividedRange ( leftRange : RangeNode, rightRange : RangeNode ) extends RangeNode

  // ========================================
  // ========================================

  import MapLangTree._
  /**
    * @brief processes a given divided range with the map expression
    *
    * @param onNode node to apply the transform
    * @param mapTransformExp the transform to use
    * @param postTransformCount the count it would be afterwards
    * @param includeDebuggingInfo do we print debugging?
    * @return onNode when mapTransformExp doesn't apply to this range or
    *         when both left and right ranges have same rangeTransformCount as postTransformCount
    * otherwise, return the updated divided range node with the transsform applied
    */
  def applyTransform ( onNode : DividedRange, mapTransformExp : MapExp, postTransformCount : Int, includeDebuggingInfo : Boolean) : DividedRange = {
    // --> |  destStart |  srcStart  |  rangeSize |
    // --> |------------|------------|------------|
    // --> |            |            |            |

    def processChildNode(childNode:RangeNode):RangeNode={
      childNode match {
        // when divided range just reapply to the children nodes
          // POSSIBLEBUG we changed this afterwards bc we noticed it'd infinite loop with previously calling processChildNode on it
        case DividedRange(leftRange, rightRange) => applyTransform(DividedRange(leftRange,rightRange),mapTransformExp,postTransformCount,includeDebuggingInfo)

        case SpecifiedRange(rangeStart, rangeEnd, rangeTransformCount) if(
            // make sure we should even touch this specified range before touching it
            (postTransformCount>rangeTransformCount) && // have we already updated this thing?
            ( // that our range lies within the range that the mapExp applies to
              // our start is within range
              (rangeStart >= mapTransformExp.sourceStart && rangeStart < mapTransformExp.sourceStart+mapTransformExp.rangeSize) ||
              // our end is within range
              (rangeEnd >= mapTransformExp.sourceStart && rangeEnd < mapTransformExp.sourceStart+mapTransformExp.rangeSize)
            )
          ) => {
          // need to process on the current node making a new node
          // ----------------------------------------------------------------------
          
          // check for when our range lies fully in the range of the mapexp
          if( (rangeStart >= mapTransformExp.sourceStart && rangeStart < mapTransformExp.sourceStart+mapTransformExp.rangeSize) && // our start is within mapexp range AND
            (rangeEnd >= mapTransformExp.sourceStart && rangeEnd < mapTransformExp.sourceStart+mapTransformExp.rangeSize) ){ // our end is within mapexp range
            // direct translation of the thing

            // get our start offset
            var startOffset = rangeStart - mapTransformExp.sourceStart
            var endDif = rangeEnd-rangeStart
            // return updated range 
            SpecifiedRange(mapTransformExp.destStart+startOffset,mapTransformExp.destStart+startOffset+endDif,postTransformCount)

          }
          // otherwise
          // POSSIBLEBUG this wass kinda rushed
          else{
            // need to construct a divided range from our existing range as one child and the other as the new translated range
            //  split our range to accomodate for the new ranges
            // check if it's our start that needs updating
            if (rangeStart >= mapTransformExp.sourceStart && rangeStart < mapTransformExp.sourceStart+mapTransformExp.rangeSize){
              // when it's our start
              
              // get our start offset
              var startOffset = rangeStart - mapTransformExp.sourceStart
              // get where the end is in our range
              //  this is the offset of the mapexp end value, minus our start offset
              var newRangeEndDif = (mapTransformExp.rangeSize-1)-startOffset
              // now we need to make our new node
              var newNode = SpecifiedRange(rangeStart,rangeStart+newRangeEndDif,rangeTransformCount)
              var oldNode = SpecifiedRange(rangeEnd-newRangeEndDif,rangeEnd,rangeTransformCount)

              // return the processed form of the created node lol
              applyTransform(DividedRange(oldNode,newNode),mapTransformExp,postTransformCount,includeDebuggingInfo)
            }
            // otherwise it's the end
            else {
              // when it's end
              
              // get our start offset
              var endOffset = rangeEnd- mapTransformExp.sourceStart
              // get where the end is in our range
              //  this is the offset of the mapexp end value, minus our start offset
              var newRangeEndDif = (mapTransformExp.rangeSize-1)-endOffset
              // now we need to make our new node
              var newNode = SpecifiedRange(rangeStart,rangeStart+newRangeEndDif,rangeTransformCount)
              var oldNode = SpecifiedRange(rangeEnd-newRangeEndDif,rangeEnd,rangeTransformCount)

              // return the processed form of the created node lol
              applyTransform(DividedRange(oldNode,newNode),mapTransformExp,postTransformCount,includeDebuggingInfo)
            }


            //  have the modified one use the new transform count
            //  and the leftover continue to use the old transform count
          }
          
          // ----------------------------------------------------------------------
        }
        // otherwise just give it back as is with new transform count
        case SpecifiedRange(rangeStart, rangeEnd, rangeTransformCount) => SpecifiedRange(rangeStart, rangeEnd, postTransformCount)
      }
    }

    // recurse for left node
    var newLeftNode = processChildNode(onNode.leftRange)

    // recurse for right node
    var newRightNode = processChildNode(onNode.rightRange)

    // return a new divided range build from the new things
    // test if there was a change

    DividedRange(newLeftNode,newRightNode)
  }

  // ========================================
  // ========================================

  def buildTree( rangeTuples : Array[Tuple2[Long,Long]]) : RangeNode = {
    var rangeNodesVector:Vector[RangeNode] = rangeTuples.toVector.map((item:Tuple2[Long,Long])=>SpecifiedRange(item._1,item._1+item._2-1,0))

    // while we have them to merge
    while(rangeNodesVector.length > 1){
      // the place we put the good ones
      var newRangeNodesVector = Vector[RangeNode]()
      // pull 2 out, and try merge them
      while(rangeNodesVector.length > 1){
        // assign the left over to it, but extract the stuff we need
        rangeNodesVector = rangeNodesVector match {
          case nodeLeft +: nodeRight +: restList => {
            newRangeNodesVector = newRangeNodesVector :+ DividedRange(nodeLeft,nodeRight)
            restList
          }
        }
      }
      // should be 1 or 0 left, but we just merge the two
      rangeNodesVector = newRangeNodesVector ++ rangeNodesVector
    }
    // now we sshould have the tree
    rangeNodesVector.head
  }

  // ========================================
  // ========================================

  /**
    * @brief grabs the earliest start
    * @param rootNode the node that's considered root for the current place in the tree
    * @return earliest value in the tree at current node or in its sub trees
    */
  def getEarliestStartInTree( rootNode : RangeNode ) : SpecifiedRange = {
    rootNode match {
      case DividedRange(leftRange, rightRange) => {
        var leftMin = getEarliestStartInTree(leftRange)
        var rightMin = getEarliestStartInTree(rightRange)
        if(leftMin.rangeStart > rightMin.rangeStart) rightMin else leftMin
      }
      case SpecifiedRange(rangeStart, rangeEnd, rangeTransformCount) => SpecifiedRange(rangeStart, rangeEnd, rangeTransformCount)
    }
  }

  // ========================================
  // ========================================
}

// ################################################################################
// ################################################################################

object Day5 {
  // ========================================
  // ========================================
  
  import day5.MapLangTree._

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
        case 99 => {
          // ============================================================
          // ...
          // handlePart1("data/day5testinput1.txt",true)
          handlePart2("data/day5testinput1.txt",true)
          // ...
          // ============================================================
        }
        // the part matching
        case 1 => {
          // ============================================================
          handlePart1("data/day5input.txt",false)
          // ============================================================
        }
        case 2 => {
          // ============================================================
          handlePart2("data/day5input.txt",true)
          // ============================================================
        }
        case numberInput => {
          // ============================================================
          Main.failingMessage("DAY 5 INVALID PART NUMBER: "+numberInput)
          // ============================================================
        }
      }
    }
    catch {
      case e:Exception=> Main.failingMessage("DAY 5 HAD EXCEPTION: "+e.toString())
    }
  }

  // ========================================
  // ========================================

  // ========================================
  // ========================================

  def handlePart1(inputPath:String,includeDebuggingInfo:Boolean):Unit={
    try{
      // ...
      val source = new FileSource (inputPath)
      val positions = new Positions
      val parsers = new MapAnalysis (positions)
      
      // attempt parse the file
      parsers.parse (parsers.parser, source) match {
        // If it worked, we get a source tree
        case Success (sourcetree, _) => {
          // got a tree
          // --------------------------------------------------------

          // Pretty print the source tree
          if(includeDebuggingInfo) println (layout (any (sourcetree)))



          // get some data from the tree and setup our vars
          // --------------------------------------------------------

          // grab seed count
          var seedCount = sourcetree.seeds.length
          // and steps
          var mapStepsCount = sourcetree.mapList.length
          // debuggable
          if(includeDebuggingInfo) printf("seed count: %d, map steps count: %d\n",seedCount,mapStepsCount)
          
          // handle spooker
          if(seedCount<1 || mapStepsCount<1) println("!!! spooky situation, didnt recognise the input properly !!!")

          // prepare seed transforms array
          var currSeedTransforms = new Array[Long](seedCount)
          // copy our seeds
          sourcetree.seeds.copyToArray(currSeedTransforms)
          // not used since we're given it in order, but it'd make sure that we're using the correct mapping? 
          //  would need to change for each mapping to a while loop that checks this isnt location
          //  then find the next mapping that has source as our state
          // *** should be inside the forloop too so we can update it to the current one!!!
          // // and state
          // var currentSource = sourcetree.mapList.head.source

          // now process the tree
          // --------------------------------------------------------

          // ...
          // each transform map we do a thing
          for(currMappingDefExp <- sourcetree.mapList){
            if(includeDebuggingInfo) printf("%s -> %s\n",currMappingDefExp.source,currMappingDefExp.destination)
            // to each seed
            for(i <- 0 to seedCount-1){
              var processedCurrSeed = false
              // ..
              // which is that we need to check if it's a direct conversion or not
              // not direct conversion we need to find the corresponding map exp
              for(possibleMapRange <- currMappingDefExp.mappings){
                // when we havent already processed it
                if(!processedCurrSeed){
                  // when we're in the range of a thing
                  if(possibleMapRange.sourceStart <= currSeedTransforms(i) && possibleMapRange.sourceStart+possibleMapRange.rangeSize > currSeedTransforms(i)){
                    if(includeDebuggingInfo) printf("%s[%d]: %d",currMappingDefExp.source,i,currSeedTransforms(i))
                    // ------------------------------------------------

                    // update to be the diff from start into destination
                    currSeedTransforms(i) = possibleMapRange.destStart + (currSeedTransforms(i) - possibleMapRange.sourceStart)
                    // now say we updated it
                    processedCurrSeed = true

                    // ------------------------------------------------
                    if(includeDebuggingInfo) printf(" became %d\n",currSeedTransforms(i))
                  }
                }
              }
              // otherwise would be direct conversion we just move on
              if(includeDebuggingInfo) if(!processedCurrSeed) printf("%s[%d] was status quo\n",currMappingDefExp.source,i)
            }
            // done all seeds for current mapping
            // say we're moving to next?
          }
          // should be at the end

          
          // process our resulting locations
          // --------------------------------------------------------

          // printem if debugging
          if(includeDebuggingInfo) {
            print("have the locations: ")
            currSeedTransforms.map((d:Long)=>printf("%d ",d))
            println()
          }

          // start as none
          var earliestLocation = Long.MaxValue

          // tell user about the best location
          // --------------------------------------------------------
          
          currSeedTransforms.map((d:Long)=>if(d<earliestLocation) earliestLocation=d)

          printf("earliest location should be: %d\n",earliestLocation)
          if(includeDebuggingInfo) printf("if this is the same as:      %d, then we had a spooky!\n",Long.MaxValue)

          // --------------------------------------------------------
        }
        // Parsing failed, so report it
        case f =>
          println (f)
      }
    }
    catch {
      case e : FileNotFoundException =>
        printf( "failed to get file with error: %s\n", e.getMessage())
    }
    // ... 
  }

  // ========================================
  // ========================================

  def handlePart2(inputPath:String,includeDebuggingInfo:Boolean):Unit={
    try{
      // ...
      val source = new FileSource (inputPath)
      val positions = new Positions
      val parsers = new MapAnalysis (positions)
      
      // attempt parse the file
      parsers.parse (parsers.parser, source) match {
        // If it worked, we get a source tree
        case Success (sourcetree, _) => {
          // got a tree
          // --------------------------------------------------------

          // Pretty print the source tree
          if(includeDebuggingInfo) println (layout (any (sourcetree)))


          // get some data from the tree and setup our vars
          // --------------------------------------------------------

          // grab seed range count
          var seedRangeCount = sourcetree.seeds.length / 2
          // and steps
          var mapStepsCount = sourcetree.mapList.length
          // debuggable
          if(includeDebuggingInfo) printf("seed count: %d, map steps count: %d\n",seedRangeCount,mapStepsCount)
          
          // handle spooker
          if(seedRangeCount<1 || mapStepsCount<1) println("!!! spooky situation, didnt recognise the input properly !!!")

          // prepare seed transforms array
          var currSeedRangeTransforms = new Array[Tuple2[Long,Long]](seedRangeCount)
          // copy our seeds
          for( i <- 0 to seedRangeCount-1){
            currSeedRangeTransforms(i) = (sourcetree.seeds(i*2),sourcetree.seeds(i*2) + sourcetree.seeds(i*2 + 1) - 1)
            if(includeDebuggingInfo) printf("seedRange[%d]: %10d to %10d\n",i,currSeedRangeTransforms(i)._1,currSeedRangeTransforms(i)._2)
          }

          // generate the range tree
          // --------------------------------------------------------

          // this will cause an exception if we only had one seed range given, but it's easier than dealing with data hygeine
          var rangeTransformTree = MapRangeTree.buildTree(currSeedRangeTransforms).asInstanceOf[MapRangeTree.DividedRange]
          // assert we have a tree to the debugging
          if(includeDebuggingInfo) println (layout (any (rangeTransformTree)))
          
          var earliestSeedInTree = MapRangeTree.getEarliestStartInTree(rangeTransformTree)

          // test a thing
          if(includeDebuggingInfo) printf("EARLIEST SEED: %d\n",earliestSeedInTree.rangeStart)
          
          // now hand off each mapping to the tree
          // --------------------------------------------------------
          // for each mapdef in our map list
          for(mapDefIdx <- 0 to sourcetree.mapList.length-1){
            if(includeDebuggingInfo) printf("\n     ______________________________________ \n    /      %11s -> %-11s      \\\n    |____________.____________.____________|\n",sourcetree.mapList(mapDefIdx).source,sourcetree.mapList(mapDefIdx).destination)
            // for each mapping in the mapdef
            if(includeDebuggingInfo) println("    |  destStart |  srcStart  |  rangeSize |\n    |############|############|############|")
            for(currMapExp <- sourcetree.mapList(mapDefIdx).mappings){
              if(includeDebuggingInfo) printf("--> | %10d | %10d | %10d |\n",currMapExp.destStart,currMapExp.sourceStart,currMapExp.rangeSize)

              // apply the mapping to the tree
              rangeTransformTree = MapRangeTree.applyTransform(rangeTransformTree,currMapExp,mapDefIdx+1,includeDebuggingInfo)
              
              // if(includeDebuggingInfo) println("    |------------|------------|------------|")
            }
            if(includeDebuggingInfo) println("    |######################################|")
          }

          // find our best location in the resulting tree
          // --------------------------------------------------------
          
          // Pretty print the transform tree
          if(includeDebuggingInfo) println ()
          if(includeDebuggingInfo) println (layout (any (rangeTransformTree)))
          if(includeDebuggingInfo) println ()

          var earliestRangeFound = MapRangeTree.getEarliestStartInTree(rangeTransformTree)

          if(earliestRangeFound!=earliestSeedInTree) printf("\n\n\nEARLIEST HYPOTHETICAL LOCATION: %d\n",earliestRangeFound.rangeStart)
          else printf("\n\n\nEARLIEST LOCATION WAS EARLIEST SEED CAPTAIN !!!!: %d\n",earliestRangeFound.rangeStart)
        }
        // Parsing failed, so report it
        case f =>
          println (f)
      }
    }
    catch {
      case e : FileNotFoundException =>
        printf( "failed to get file with error: %s\n", e.getMessage())
    }
    // ... 
  }

  // ========================================
  // ========================================

  // JUNK
  def handlePart2old(inputPath:String,includeDebuggingInfo:Boolean):Unit={
    try{
      // ...
      val source = new FileSource (inputPath)
      val positions = new Positions
      val parsers = new MapAnalysis (positions)
      
      // attempt parse the file
      parsers.parse (parsers.parser, source) match {
        // If it worked, we get a source tree
        case Success (sourcetree, _) => {
          // got a tree
          // --------------------------------------------------------

          // Pretty print the source tree
          if(includeDebuggingInfo) println (layout (any (sourcetree)))



          // get some data from the tree and setup our vars
          // --------------------------------------------------------

          // grab seed range count
          var seedRangeCount = sourcetree.seeds.length / 2
          // and steps
          var mapStepsCount = sourcetree.mapList.length
          // debuggable
          if(includeDebuggingInfo) printf("seed count: %d, map steps count: %d\n",seedRangeCount,mapStepsCount)
          
          // handle spooker
          if(seedRangeCount<1 || mapStepsCount<1) println("!!! spooky situation, didnt recognise the input properly !!!")

          // prepare seed transforms array
          var currSeedRangeTransforms = new Array[Tuple3[Long,Long,Long]](seedRangeCount)
          // copy our seeds
          for( i <- 0 to seedRangeCount-1){
            currSeedRangeTransforms(i) = (sourcetree.seeds(i*2),sourcetree.seeds(i*2) + sourcetree.seeds(i*2 + 1) - 1,sourcetree.seeds(i*2 + 1))
            if(includeDebuggingInfo) printf("seedRange[%d]: %10d to %10d -- %10d vals\n",i,currSeedRangeTransforms(i)._1,currSeedRangeTransforms(i)._2,currSeedRangeTransforms(i)._3)
          }

          // now compare in the tree
          // --------------------------------------------------------

          // for each mapping
          for (currMapDef <- sourcetree.mapList){
            if(includeDebuggingInfo) printf("%s -> %s\n",currMapDef.source,currMapDef.destination)

            //  we then access each seed range
            for(i <- 0 to seedRangeCount-1){
              //...
              var seedFullyInRange = false
              var seedPartiallyInRange = false
              //  find a range it's in
              for(possibleMapRange <- currMapDef.mappings){
                if(!seedFullyInRange){
                  // not yet found it in a range
                  if(currSeedRangeTransforms(i)._1>=possibleMapRange.sourceStart && currSeedRangeTransforms(i)._1 < possibleMapRange.sourceStart+possibleMapRange.rangeSize){
                    // starrt is in range
                    if(currSeedRangeTransforms(i)._2>=possibleMapRange.sourceStart && currSeedRangeTransforms(i)._2 < possibleMapRange.sourceStart+possibleMapRange.rangeSize){
                      // end also in range
                      seedFullyInRange = true

                      if(includeDebuggingInfo) printf("%s[%d]: %10d to %10d -- ",possibleMapRange.sourceStart,i,currSeedRangeTransforms(i)._1,currSeedRangeTransforms(i)._2)
                      currSeedRangeTransforms(i) = (
                        possibleMapRange.destStart + (currSeedRangeTransforms(i)._1 - possibleMapRange.sourceStart),
                        possibleMapRange.destStart + (currSeedRangeTransforms(i)._2 - possibleMapRange.sourceStart),
                        currSeedRangeTransforms(i)._3
                      )
                      if(includeDebuggingInfo) printf("%10d to %10d\n",currSeedRangeTransforms(i)._1,currSeedRangeTransforms(i)._2)
                    }
                    //  scream if only partial in range
                    else {
                      seedPartiallyInRange = true
                      if(includeDebuggingInfo) printf("%s[%d]: %10d to %10d \n\n!!!!!!!!!!!!!!!!!!!!\n!!!!!!!!!!!!!!!!!!!!\nWAS ONLY PARTIALLY IN RANGE\n!!!!!!!!!!!!!!!!!!!!\n!!!!!!!!!!!!!!!!!!!!\n\n",possibleMapRange.sourceStart,i,currSeedRangeTransforms(i)._1,currSeedRangeTransforms(i)._2)
                      // let it brick
                    }
                  }
                }
              }
              if(includeDebuggingInfo) if(!seedPartiallyInRange) printf("%s[%d] was status quo\n",currMapDef.source,i)
            } // end loop curr seed range
            // done current mapping
          }

          // processs our resulting locations
          // --------------------------------------------------------


          // printem if debugging
          if(includeDebuggingInfo) {
            print("have the locations:\n")
            currSeedRangeTransforms.map((seedTransform:Tuple3[Long,Long,Long])=>printf("%10d to %10d\n",seedTransform._1,seedTransform._2))
            println()
          }

        }
        // Parsing failed, so report it
        case f =>
          println (f)
      }
    }
    catch {
      case e : FileNotFoundException =>
        printf( "failed to get file with error: %s\n", e.getMessage())
    }
    // ... 
  }

  // ========================================
  // ========================================
}

// ################################################################################
// ################################################################################
