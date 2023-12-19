
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

          // the arg we want
          var dayHandle = dayNumVal match {
            // ........................................
            // ........................................
            case "1" => {
              day1.Day1.handleDay(_)
            }
            // ........................................
            // ........................................
            case "2" => {
              day2.Day2.handleDay(_)
            }
            // ........................................
            // ........................................
            case "3" => {
              day3.Day3.handleDay(_)
            }
            // ........................................
            // ........................................
            case "4" => {
              // printf("landed case 4, %s %s \n",dayNumVal,dayPartVal)
              day4.Day4.handleDay(_)
            }
            // ........................................
            // ........................................
            case "5" => {
              day5.Day5.handleDay(_)
            }
            // ........................................
            // ........................................
            case "6" => {
              day6.Day6.handleDay(_)
            }
            // ........................................
            // ........................................
            case semivaluableSting => {
              // println("landed semivaluableString")
              // println(semivaluableSting)
              failingMessageNumberInputPart(_)
            }
          }
          // use it now
          dayHandle(
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
  // def failingMessage() : Unit = {
  //   println("!!! FAILED SOMEHOW, KTHNXBYE")
  // }
  def failingMessage(semivaluableSting:String) : Unit = {
    println("!!! FAILURE WITH SOME INFORMATION:\n\n"+semivaluableSting)
  }
  def failingMessageNumberInputPart(semivaluableSting:Int) : Unit = {
    println("!!! FAILURE WITH SOME INFORMATION:\n\n"+semivaluableSting)
  }



  // ###############################################################

  def doingSandbox(){

    // val dayDigitList = List(
    //   '1',
    //   '2',
    //   '3',
    //   '4',
    //   '5',
    //   '6',
    //   '7',
    //   '8',
    //   '9'
    // )
    // val dayCharList = List(
    //   'o','n','e',
    //   't','w','o',
    //   't','h','r','e','e',
    //   'f','o','u','r',
    //   'f','i','v','e',
    //   's','i','x',
    //   's','e','v','e','n',
    //   'e','i','g','h','t',
    //   'n','i','n','e'
    //   )  
      
    //   var uniqueCharacterList = List(' ')
    //   for( ch <- dayCharList){
    //     if(!uniqueCharacterList.contains(ch)) {
    //       uniqueCharacterList = uniqueCharacterList:+ch
    //     }
    //   }
      
    //   println(uniqueCharacterList.tail.sorted.toString())


      var arrayableThing = new Array[Int](7)
      println("the thing is it looks like: "+arrayableThing.toString())
      for(elem <- arrayableThing){
        printf("elem: %d\n",elem)
      }
      arrayableThing(0)=77
      arrayableThing(1)=88
      println("now?")
      
      for(elem <- arrayableThing){
        printf("elem: %d\n",elem)
      }
      println("how about?")
      for(elemIdx <- 0 to arrayableThing.length-1){
        printf("%d --> %d\n",elemIdx,arrayableThing(elemIdx))
      }
      printf("items are: %d\n",arrayableThing.length)

      println("now we go for mapping cartographically saying the doghouse what?")


      val scratchCardLine = "Card   3: 87 17 54 56  4 53 14 76 63 11 | 96 53 52 90 50 91 17 24 99  5 83 44 97 63 66 54 35 37 84 11 73 34  7 79 12"

      var winningNumbersPortion = day4.Day4.winningNumbersMatcher.findFirstIn(scratchCardLine) match {
        case None => "???"
        case Some(thing) => thing
      }
      var myNumbersPortion = day4.Day4.scratchCardNumbersMatcher.findFirstIn(scratchCardLine) match {
        case None => "???"
        case Some(thing) => thing
      }

      var winningNumbersIterator = day4.Day4.numberMatcher.findAllIn(winningNumbersPortion).map((numberString)=>numberString.toInt)
      for(winner <- winningNumbersIterator){
        printf("winner: %2s--> ",winner.toString)
        var myNumbersIterator = day4.Day4.numberMatcher.findAllIn(myNumbersPortion).map((numberString)=>numberString.toInt)
        for(mine <- myNumbersIterator){
          printf("[%2s=%2s]",winner.toString,mine.toString)
        }
        println("")
      }

  }
    
    
    
}
  