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

        // da patterns

        // var gameLineMatch = "Game ([0-9]+): (([0-9]+) (red|green|blue).? ?)+".r
        var numberMatcher = "[0-9]+".r
        var gameLineNumberMatcher = "Game ([0-9]+):".r
        // needs match all
        var gameIterMatcher = "(([0-9]+) (red|green|blue)(, )?)+;?".r
        var gameChoiceMatcher = "([0-9]+) (red|green|blue)".r
        var gameChoiceColorMatcher = "(red|green|blue)".r

        // for extraction of num
        def getFirstNumberFromString(inputStringToRipFrom:String):Int ={
          numberMatcher.findFirstIn(inputStringToRipFrom) match {
            case None => -65536
            case Some(thing) => {
              try{
                thing.toInt
              }catch {
                case e:Exception=> {
                  println("had a spooker on ripped number toInt transofmrmering: "+thing)
                  -65536 // spookable number
                }
              }
            }
          }
        }
        // the result val
        var runningGameNumberTotal = 0
        // loop all game liness
        for(gameLine <- inputLines){
          // ...
          // for debugging
          // println(gameLine)
          // ...
          // // ("Game \\d: ([a-zA-Z0-9]+)".r)(gameNumber,gameDetails)
          // game match {
          //   case "Game "+gameNumber+": "+gameDetails => printf("have the: %s\nwith: %s",gameNumber,gameDetails)
          // }


          try{
            // grab the game label
            val gameLineLabelString = gameLineNumberMatcher.findFirstIn(gameLine) match {
              case None => "???"
              case Some(thing) => thing
            }
            // grab the number from it
            val gameNumber = getFirstNumberFromString(gameLineLabelString)
            // grab the iterations
            val gameIterationsList = gameIterMatcher.findAllIn(gameLine)
            
            var gameNumberIsSafe = true // assume fine, thisisfine.gif

            for(gameIteration <- gameIterationsList){
              // println(gameIteration)
              // grab the selectables
              val gameIterationSelections = gameChoiceMatcher.findAllIn(gameIteration)
              for(selection <- gameIterationSelections){
                // color handle
                if(!(
                  (gameChoiceColorMatcher.findFirstIn(selection) match { case None => "???" case Some(thing) => thing}) match {
                    case "red" => getFirstNumberFromString(selection)>12
                    case "green" => getFirstNumberFromString(selection)>13
                    case "blue" => getFirstNumberFromString(selection)>14
                    case spookyInput => {
                      println("!!!! SPOOKY COLOUR !!!!: "+spookyInput)
                      true // // do nothing else?? pretend fine?
                    }
                  }
                )){
                  // make the spooky checker
                  gameNumberIsSafe = false
                }
              }
            }

            if(gameNumberIsSafe) {
              runningGameNumberTotal=runningGameNumberTotal+gameNumber
            }

          } catch {
            case e:Exception=> println("failed the handle lines: "+e.toString())
          }

          // done game line
        }

        // done all games

        printf("we got: %d\n",runningGameNumberTotal)
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