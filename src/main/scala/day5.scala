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

  case class Input (seeds : Vector[Int], mapList : Vector[MapDefnExp]) extends MapLangNode

  // the particular mapping
  case class MapExp (destStart : Int, sourceStart : Int, rangeSize : Int) extends Exp

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

  lazy val intExp : PackratParser[Int] =
    regex ("[0-9]+".r) ^^ (s => s.toInt)

  lazy val idnExp : PackratParser[String] =
     regex ("[a-zA-Z]+".r)

  lazy val seedList : PackratParser[Vector[Int]] = 
    "seeds: " ~> intExp ~ ((intExp*)) ^^ { (a:Int,b:Vector[Int]) => a+:b }

  lazy val mapExp : PackratParser[MapExp] = 
    intExp ~ intExp ~ intExp ^^ MapExp

  lazy val mapDefnExp : PackratParser[MapDefnExp] =
    idnExp ~ ("-to-" ~> idnExp <~ "map:") ~ mapExp ~ ((mapExp)*) ^^ { (leftIdn:String, rightIdn:String, firstMap:MapExp, restMap:Vector[MapExp]) => MapDefnExp(leftIdn,rightIdn, firstMap+:restMap) }

  lazy val mapDefList : PackratParser[Vector[MapDefnExp]] = 
    mapDefnExp ~ ((mapDefnExp)*) ^^ { ( first:MapDefnExp, rest:Vector[MapDefnExp]) => first +: rest }

}

// ################################################################################
// ################################################################################

object Day5 {
  // ========================================
  // ========================================
  
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
          handlePart1("data/day5testinput1.txt",true)
          // handlePart2("data/day5testinput1.txt",true)
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
          handlePart2("data/day5input.txt",false)
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



          // now handle the tree we get
          // --------------------------------------------------------

          // grab seed count
          var seedCount = sourcetree.seeds.length
          // 



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
    var inputFileLines = Main.grabLinesFromFile(inputPath)
    // TODO: DAY 5 PART 2
  }

  // ========================================
  // ========================================
}

// ################################################################################
// ################################################################################
