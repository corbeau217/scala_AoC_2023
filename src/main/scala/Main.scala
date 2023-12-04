
package Main
// bc file inputs
import scala.io.Source


object Main{

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------


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

  /**
    * the main static of the void
    *
    * @param args
    */
  def main(args:Array[String]):Unit={
    val dayMatcher = "day([1-9][0-9]*)\\-pt([1-9][0-9]*)".r
    
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

          // the arg we want
          dayNumVal match {
            // ........................................
            // ........................................

            // FUNTODO: REFACTOR TO JUST BE THE FUNCTION SIGNATURE THAT THE SWITCH STATEMENT CHOOSES
            // ........................................
            // ........................................
            case "1" => {
              day1.Day1.handleDay(
                // trying to parse the numbered
                try{
                  dayPartVal.toInt
                }
                catch {
                  case e : Exception => 666
                }
              )
            }
            // ........................................
            // ........................................
            case "2" => {
              day2.Day2.handleDay(
                // trying to parse the numbered
                try{
                  dayPartVal.toInt
                }
                catch {
                  case e : Exception => 666
                }
              )
            }
            // ........................................
            // ........................................
            case "3" => {
              day3.Day3.handleDay(
                // trying to parse the numbered
                try{
                  dayPartVal.toInt
                }
                catch {
                  case e : Exception => 666
                }
              )
            }
            // ........................................
            // ........................................
            case semivaluableSting => failingMessage("none day: " +semivaluableSting)
          }
        }
        catch {
          case e:Exception =>{
            // try see if playgrounding
            singleArg match {
              case "sandbox" => {doingSandbox()}
              case _ => {
                println("!!! FAILURE IN THE GRABBING THE DAY/PART\n"+
                "args like: day<number>-pt<number>")
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


  // // ###############################################################

  // // -----------------------------------------------------------------
  // // -----------------------------------------------------------------
  // // -----------------------------------------------------------------

  // // recursively grab the things as list till none left
  // def iteratorToList(iteratorToMakeAsList:Iterator[String],listAsWeGo:List[String]):List[String]={
  //   if(!iteratorToMakeAsList.hasNext) return listAsWeGo

  //   iteratorToList(iteratorToMakeAsList,listAsWeGo:+iteratorToMakeAsList.next())
  // }
  // // ###############################################################

  // def readLinesFromFile(filePather:String):Unit={
    

  //   // over thinking the stick


  //   var linesFromBufferableSource = Iterator("")
  //   try {
  //     var bufferableSource = Source.fromFile(filePather)
  //     for(line <- bufferableSource.getLines()){
  //       println(line)
  //     }
  //     bufferableSource.close()
  //   }
  //   catch {
  //     case errory : Throwable => failingMessage("input error of a kind\n\n"+errory.toString())
  //   }

    

  //   // deal with them to list
  //   // iteratorToList(linesFromBufferableSource,Nil)

  // }

  // // ###############################################################

  // // -----------------------------------------------------------------
  // // -----------------------------------------------------------------
  // // -----------------------------------------------------------------

  // // ###############################################################

  // def handleLines(linesInput:List[String], linesAsWeGo:List[String]): Unit={
  //   // TODO: aaa
  //   println("")
  // }

  // // ###############################################################
  def failingMessage() : Unit = {
    println("!!! FAILED SOMEHOW, KTHNXBYE")
  }
  def failingMessage(semivaluableSting:String) : Unit = {
    println("!!! FAILURE WITH SOME INFORMATION:\n\n"+semivaluableSting)
  }



  // ###############################################################

  def doingSandbox(){

    val dayDigitList = List(
      '1',
      '2',
      '3',
      '4',
      '5',
      '6',
      '7',
      '8',
      '9'
    )
    val dayCharList = List(
      'o','n','e',
      't','w','o',
      't','h','r','e','e',
      'f','o','u','r',
      'f','i','v','e',
      's','i','x',
      's','e','v','e','n',
      'e','i','g','h','t',
      'n','i','n','e'
      )  
      
      var uniqueCharacterList = List(' ')
      for( ch <- dayCharList){
        if(!uniqueCharacterList.contains(ch)) {
          uniqueCharacterList = uniqueCharacterList:+ch
        }
      }
      
      println(uniqueCharacterList.tail.sorted.toString())

    }
    
    
    
  }
  