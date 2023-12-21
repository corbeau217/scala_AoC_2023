
package Main
// bc file inputs
import scala.io.Source
import java.time._
// import java.time.temporal.TemporalField
// import java.time.temporal.ChronoField

// import scala.



object Main{

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  
  // ###############################################################
  // ###############################################################
  val epochSecondOfFirsDec = 1701406800

  def epochSecondsSinceFirstChallengeReleased : Long = (Instant.now().getEpochSecond()-epochSecondOfFirsDec)

  def isDayUnlocked(dayNumber:Int):Boolean={

    var minutesSince = epochSecondsSinceFirstChallengeReleased / 60
    var hoursSince = minutesSince / 60

    val challengeDaysReleased = hoursSince / 24 + 1 // add 1 because 0 is when the first challenge released

    // say if it was more or equal the number released
    challengeDaysReleased >= dayNumber
  }
  
  // ###############################################################
  // ###############################################################

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  
  // ###############################################################
  // ###############################################################

  def grabLinesFromFile(pathStr:String):List[String] = {
    var linesList = List[String]()
    try {

      val fileGrabbedBuffer = Source.fromFile(pathStr)
      for (line <- fileGrabbedBuffer.getLines()) {
          // println(line)
          linesList = linesList :+ line
      }
      fileGrabbedBuffer.close()
    }
    catch {
      case e: Exception => println("but it doesnae work: "+e.toString())
    }
    linesList
  } 
  
  // ###############################################################
  // ###############################################################

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  
  // ###############################################################
  // ###############################################################



  // could have all days add to a list that's globally accessible then have it sorted and search for item
  
  var dayHandleFunctions:List[Tuple2[Int,Int=>Unit]] = List(
    (1,day1.Day1.handleDay(_)),
    (2,day2.Day2.handleDay(_)),
    (3,day3.Day3.handleDay(_)),
    (4,day4.Day4.handleDay(_)),
    (5,day5.Day5.handleDay(_)),
    (6,day6.Day6.handleDay(_)),
    (7,day7.Day7.handleDay(_)),
    (8,day8.Day8.handleDay(_)),
    (9,day9.Day9.handleDay(_)),
    (10,day10.Day10.handleDay(_)),
    (11,day11.Day11.handleDay(_)),
    (12,day12.Day12.handleDay(_)),
    (13,day13.Day13.handleDay(_)),
    (14,day14.Day14.handleDay(_)),
    (15,day15.Day15.handleDay(_)),
    (16,day16.Day16.handleDay(_)),
    (17,day17.Day17.handleDay(_)),
    (18,day18.Day18.handleDay(_)),
    (19,day19.Day19.handleDay(_)),
    (20,day20.Day20.handleDay(_)),
    (21,day21.Day21.handleDay(_)),
    (22,day22.Day22.handleDay(_)),
    (23,day23.Day23.handleDay(_)),
    (24,day24.Day24.handleDay(_)),
    (25,day25.Day25.handleDay(_)),
  )

  // ###############################################################
  // ###############################################################

  /**
    * the main static of the void
    *
    * @param args
    */
  def main(args:Array[String]):Unit={
    val dayMatcher = "day([0-9]*)\\-pt([0-9]*)".r
    
    // just match the arg thing of the choice
    args.toList match {
      // ........................................
      // ........................................
      case _  :: singleArg :: Nil => {
        var dayNumVal = "spookableSTARTINGstring"
        var dayPartVal = "spookableSTARTINGstring"

        // try the catchable for the thinger bc idk
        try {
          val dayMatcher(dayNumVal,dayPartVal) = singleArg

          val dayNumUsing = dayNumVal.toInt

          // the arg we want
          var usingDayHandleFunction = dayHandleFunctions(dayHandleFunctions.indexWhere(elem=>dayNumUsing==elem._1))._2
          
          // use it now
          if(isDayUnlocked(dayNumUsing)) usingDayHandleFunction(
            // trying to parse the numbered
            try{
              dayPartVal.toInt
            }
            catch {
              case e : Exception => {
                println("failedable with the " + e.toString())
                666
              }
            }
          )
          else println("day not unlocked yet!")

          // ...
        }
        catch {
          case e:Exception =>{
            // try see if playgrounding
            singleArg match {
              case "sandbox" => {doingSandbox()}
              case _ => {
                println("!!! FAILURE IN THE GRABBING THE DAY/PART\n"+
                "args like: day<number>-pt<number>")
                printf("had: %s\nday: %s\npart: %s\n",singleArg,dayNumVal,dayPartVal)
              }
            }
          }
        }
      }
      // ........................................
      // ........................................
      case _ => failingMessage(args.mkString(","))
    }
  }
  
  // ###############################################################
  // ###############################################################

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  
  // ###############################################################
  // ###############################################################

  def failingMessage(semivaluableSting:String) : Unit = {
    println("!!! FAILURE WITH SOME INFORMATION:\n\n"+semivaluableSting)
  }
  
  // ###############################################################
  // ###############################################################

  def failingMessageNumberInputPart(semivaluableSting:Int) : Unit = {
    println("!!! FAILURE WITH SOME INFORMATION:\n\n"+semivaluableSting)
  }
  
  // ###############################################################
  // ###############################################################

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  
  // ###############################################################
  // ###############################################################

  def doingSandbox():Unit={
    
    
    
    // println(isDayUnlocked(20).toString())
    
    
    
  }
  
  // ###############################################################
  // ###############################################################

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  
}
  