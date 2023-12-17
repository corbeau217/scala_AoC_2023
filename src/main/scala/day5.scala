package day5

import scala.io.Source
import Main._
// import scala.language.postfixOps
import org.bitbucket.inkytonik.kiama.output.PrettyPrinter.{any, layout}
import org.bitbucket.inkytonik.kiama.parsing.{Success, Parsers}
import org.bitbucket.inkytonik.kiama.util.{FileSource, Positions}

class MapAnalysis(positions : Positions) extends Parsers (positions) {

  import MapLangTree._
  import scala.language.postfixOps

  lazy val parser : PackratParser[Input] =
      phrase (input)

  lazy val input : PackratParser[Input] =
      exp ^^ Input

  lazy val exp : PackratParser[Exp] =
    seedDef |
    failure ("exp expected")

  lazy val integer =
      regex ("[0-9]+".r)

  lazy val seedDef : PackratParser[SeedDefn] = 
    "seeds: " ~> (integer ~ (integer*)) ^^ { (a:String,b:Vector[String]) => SeedDefn(a+:b) }
}

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
          handlePart1(Main.grabLinesFromFile("data/day5testinput1.txt"),true)
          // handlePart2(Main.grabLinesFromFile("data/day5testinput1.txt"),true)
          // ...
          // ============================================================
        }
        // the part matching
        case 1 => {
          // ============================================================
          handlePart1(Main.grabLinesFromFile("data/day5input.txt"),false)
          // ============================================================
        }
        case 2 => {
          // ============================================================
          handlePart2(Main.grabLinesFromFile("data/day5input.txt"),false)
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

  def handlePart1(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    // TODO: DAY 5 PART 1
  }

  // ========================================
  // ========================================

  def handlePart2(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    // TODO: DAY 5 PART 2
  }

  // ========================================
  // ========================================
}
// ################################################################################
// ################################################################################

object MapLangTree {
  // common super of all the things
  sealed abstract class MapLangNode

  case class Input (exp : Exp) extends MapLangNode

  sealed abstract class Exp extends MapLangNode

  case class SeedDefn (seeds : Vector[String]) extends Exp

  // the particular mapping
  case class MapExp (destStart : Int,sourceStart : Int,range : Int) extends Exp
  // for the map block signatures
  case class MapDec (source : String, destination : String) extends Exp
  // for a map block
  case class MapDefnExp (mapSignature : MapDec, mappings : Vector[MapExp]) extends Exp

  case class MapDefList (maps : Vector[MapDefnExp]) extends Exp
  
  // case class SeedVal (val : String)
}

// ################################################################################
// ################################################################################
