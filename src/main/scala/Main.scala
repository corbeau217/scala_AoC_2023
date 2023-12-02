
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
          case "wk1-pt1" => {
            week1part1()
          }
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

  def failingMessage(semivaluableSting:String) : Unit = {
    println("non argumentative input, leaving you bye\n\n"+semivaluableSting)
  }

  // ###############################################################

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------

  // recursively grab the things as list till none left
  def iteratorToList(iteratorToMakeAsList:Iterator[String],listAsWeGo:List[String]):List[String]={
    if(!iteratorToMakeAsList.hasNext) return listAsWeGo

    iteratorToList(iteratorToMakeAsList,listAsWeGo:+iteratorToMakeAsList.next())
  }
  // ###############################################################

  def readLinesFromFile(filePather:String):Unit={
    

    // over thinking the stick


    var linesFromBufferableSource = Iterator("")
    try {
      var bufferableSource = Source.fromFile(filePather)
      for(line <- bufferableSource.getLines()){
        println(line)
      }
      bufferableSource.close()
    }
    catch {
      case errory : Throwable => failingMessage("input error of a kind\n\n"+errory.toString())
    }

    

    // deal with them to list
    // iteratorToList(linesFromBufferableSource,Nil)

  }

  // ###############################################################

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------

  // ###############################################################

  def handleLines(linesInput:List[String], linesAsWeGo:List[String]): Unit={
    // TODO: aaa
    println("")
  }

  // ###############################################################

  def week1part1():Unit={
    readLinesFromFile("src/main/scala/inputs/wk1_pt1.txt")

  }

  // ###############################################################
}