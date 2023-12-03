package day2

import scala.io.Source
import Main._

// import 

object Day2 {
  // ========================================
  // ========================================


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
  
  /**
    * inlet for the code
    *
    * @param partNumber
    */
  def handleDay(partNumber:Int):Unit={
    partNumber match {
      // testing addition
      case 99 => {
        // ============================================================
          
        // ============================================================
      }
      // aaaaa the parts
      case 1 => { 
        // ...
        var inputLines = grabLinesFromFile("src/main/scala/day2/day2input.txt")

        // println(inputLines.toString())

        
      }
      case numberInput => {
        Main.failingMessage("DAY 2 INVALID PART NUMBER: "+numberInput)
      }
    }
  }
  // ========================================
  // ========================================

  // ...
  
  // ========================================
  // ========================================
}