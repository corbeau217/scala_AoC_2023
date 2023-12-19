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

  def buildTree( rangeTuples : Vector[Tuple2[Long,Long]]) : RangeNode = {
    // TODO implement this to build our tree as per doc part 2 list item 9

    // dummy data while we havent finished this
    SpecifiedRange(0,99,0)
  }

  // ========================================
  // ========================================
  
  import day5.MapLangTree._

  /**
    * @brief processes a given divided range with the map expression
    *
    * @param onNode node to apply the transform
    * @param mapTransformExp the transform to use
    * @param postTransformCount the count it would be afterwards
    * @return onNode when mapTransformExp doesn't apply to this range or
    *         when both left and right ranges have same rangeTransformCount as postTransformCount
    * otherwise, return the updated divided range node with the transsform applied
    */
  def applyTransform ( onNode : DividedRange, mapTransformExp : MapExp, postTransformCount : Int) : DividedRange={
    // TODO: body of this method as per doc part 2 list item 13

    // FIXME returns the node given no matter what bc we just say it's fine while making this stuff
    onNode
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
