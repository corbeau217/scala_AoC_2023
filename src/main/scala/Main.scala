
package Main
// bc file inputs
import scala.io.Source


object Main{

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------

  // ###############################################################

  /**
    * the main static of the void
    *
    * @param args
    */
  def main(args:Array[String]):Unit={
    // just match the arg thing of the choice
    args.toList match {
      // ........................................
      // ........................................
      case _  :: singleArg :: Nil => {
        // the arg we want
        singleArg match {
          // ........................................
          // ........................................
          case "day1-pt1" => day1.Day1.handleDay(1)
          case "day1-pt2" => day1.Day1.handleDay(2)
          // ........................................
          // ........................................
          case semivaluableSting => failingMessage(semivaluableSting)
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

}