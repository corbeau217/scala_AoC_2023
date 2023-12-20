
package Main
// bc file inputs
import scala.io.Source

// import scala.



object Main{

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  
  // ###############################################################
  // ###############################################################

  def isDayUnlocked(dayNumber:Int):Boolean={
    // ...
    // because what we do is, we find the day we are at in december
    // then we figure out if we reached out relative timezone of the time
    // I'm in UTC+11, AEDT, so it happens at 4pm for me


    return false
  }
  
  // ###############################################################
  // ###############################################################

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------

  // ###############################################################
  // ###############################################################

  // TODO: stuff about handle day function list here

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

          // could have all days add to a list that's globally accessible then have it sorted and search for item
          
          val dayHandleFunctions = List(
            day1.Day1.handleDay(_),
            day2.Day2.handleDay(_),
            day3.Day3.handleDay(_),
            day4.Day4.handleDay(_),
            day5.Day5.handleDay(_),
            day6.Day6.handleDay(_),
            day7.Day7.handleDay(_),
            day8.Day8.handleDay(_),
            day9.Day9.handleDay(_),
            day10.Day10.handleDay(_),
            day11.Day11.handleDay(_),
            day12.Day12.handleDay(_),
            day13.Day13.handleDay(_),
            day14.Day14.handleDay(_),
            day15.Day15.handleDay(_),
            day16.Day16.handleDay(_),
            day17.Day17.handleDay(_),
            day18.Day18.handleDay(_),
            day19.Day19.handleDay(_),
            day20.Day20.handleDay(_),
            day21.Day21.handleDay(_),
            day22.Day22.handleDay(_),
            day23.Day23.handleDay(_),
            day24.Day24.handleDay(_),
            day25.Day25.handleDay(_),
          )

          // the arg we want
          var usingDayHandleFunction = dayHandleFunctions(dayNumVal.toInt-1)
          // use it now
          usingDayHandleFunction(
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

  def doingSandbox(){
    
    
    
    
    
    
    
  }
  
  // ###############################################################
  // ###############################################################

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  
}
  