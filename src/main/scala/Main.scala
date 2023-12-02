
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
    args match {
      // ........................................
      // ........................................
      case Array(singleArg) => {
        // the arg we want
        singleArg match {
          // ........................................
          // ........................................
          case "wk1-pt1" => {
            week1part1()
          }
          // ........................................
          // ........................................
          case _ => failingMessage()
        }
      }
      // ........................................
      // ........................................
      case _ => failingMessage()
    }
  }

  // ###############################################################

  def failingMessage() : Unit = {
    println("non argumentative input, leaving you bye")
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

  def getLinesFromFile(filePather:String):List[String]={
    
    val bufferableSource = Source.fromFile(filePather)

    val linesFromBufferableSource = bufferableSource.getLines()

    // cleanup with it
    bufferableSource.close()

    // deal with them to list
    iteratorToList(linesFromBufferableSource,Nil)

  }

  // ###############################################################

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------

  // ###############################################################

  def handleLines(linesInput:List[String], linesAsWeGo:List[String])

  // ###############################################################

  def week1part1():Unit={
    // grabem
    var linesToWorkWith = getLinesFromFile("inputs/wk1_pt1.txt")

    // TODO : deal with them

  }

  // ###############################################################
}