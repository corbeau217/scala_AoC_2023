package day7

import scala.io.Source
import Main._
import javax.smartcardio.Card

// ################################################################################
// ################################################################################

// ...
object CardHandle {
  val AllCardTypesDesc = List('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
  val AllCardTypesDescPt2 = List('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J')
  // doesnt fill all places in the string for some, so use it as "exists in"
  val AllCardPatternMatchingDesc = List(
    // five of a kind
    "(A{5}|K{5}|Q{5}|J{5}|T{5}|9{5}|8{5}|7{5}|6{5}|5{5}|4{5}|3{5}|2{5})".r,
    // four of a kind
    "(A{4}|K{4}|Q{4}|J{4}|T{4}|9{4}|8{4}|7{4}|6{4}|5{4}|4{4}|3{4}|2{4})".r,
    // full house
    "((AA|KK|QQ|JJ|TT|99|88|77|66|55|44|33|22)(AAA|KKK|QQQ|JJJ|TTT|999|888|777|666|555|444|333|222))|((AAA|KKK|QQQ|JJJ|TTT|999|888|777|666|555|444|333|222)(AA|KK|QQ|JJ|TT|99|88|77|66|55|44|33|22))".r,
    // three of a kind
    "(AAA|KKK|QQQ|JJJ|TTT|999|888|777|666|555|444|333|222)".r,
    // two pair
    "((AA|KK|QQ|JJ|TT|99|88|77|66|55|44|33|22){2})|((AA|KK|QQ|JJ|TT|99|88|77|66|55|44|33|22).(AA|KK|QQ|JJ|TT|99|88|77|66|55|44|33|22))".r,
    // one pair
    "(AA|KK|QQ|JJ|TT|99|88|77|66|55|44|33|22)".r,
    // high card
    "[AKQJT2-9]{5}".r
  )
  val AllCardPatternMatchingDescPt2 = List(
    // five of a kind
    // ---------------------------------------------------------------------------

    // WOULD WORK WITH PT2
    "(A{5}|K{5}|Q{5}|J{5}|T{5}|9{5}|8{5}|7{5}|6{5}|5{5}|4{5}|3{5}|2{5})|((A{4}|K{4}|Q{4}|J{4}|T{4}|9{4}|8{4}|7{4}|6{4}|5{4}|4{4}|3{4}|2{4})(.*)J)|((A{3}|K{3}|Q{3}|J{3}|T{3}|9{3}|8{3}|7{3}|6{3}|5{3}|4{3}|3{3}|2{3})(.*)J{2})|((A{2}|K{2}|Q{2}|J{2}|T{2}|9{2}|8{2}|7{2}|6{2}|5{2}|4{2}|3{2}|2{2})(.*)J{3})|((.*)J{4})".r,

    // four of a kind
    // ---------------------------------------------------------------------------

    // WOULD WORK WITH PT2
    "(A{4}|K{4}|Q{4}|J{4}|T{4}|9{4}|8{4}|7{4}|6{4}|5{4}|4{4}|3{4}|2{4})|((A{3}|K{3}|Q{3}|J{3}|T{3}|9{3}|8{3}|7{3}|6{3}|5{3}|4{3}|3{3}|2{3})(.*)J)|((A{2}|K{2}|Q{2}|J{2}|T{2}|9{2}|8{2}|7{2}|6{2}|5{2}|4{2}|3{2}|2{2})(.*)J{2})|((.*)J{3})".r,
    
    // full house
    // ---------------------------------------------------------------------------

    // WOULD WORK WITH PT2
    "(A{3}|K{3}|Q{3}|J{3}|T{3}|9{3}|8{3}|7{3}|6{3}|5{3}|4{3}|3{3}|2{3})(A{2}|K{2}|Q{2}|J{2}|T{2}|9{2}|8{2}|7{2}|6{2}|5{2}|4{2}|3{2}|2{2})|(A{2}|K{2}|Q{2}|J{2}|T{2}|9{2}|8{2}|7{2}|6{2}|5{2}|4{2}|3{2}|2{2})(A{3}|K{3}|Q{3}|J{3}|T{3}|9{3}|8{3}|7{3}|6{3}|5{3}|4{3}|3{3}|2{3})|(A{2}|K{2}|Q{2}|J{2}|T{2}|9{2}|8{2}|7{2}|6{2}|5{2}|4{2}|3{2}|2{2})((A{2}|K{2}|Q{2}|J{2}|T{2}|9{2}|8{2}|7{2}|6{2}|5{2}|4{2}|3{2}|2{2})(.*)J)|((A{2}|K{2}|Q{2}|J{2}|T{2}|9{2}|8{2}|7{2}|6{2}|5{2}|4{2}|3{2}|2{2})(.*)J{2})".r,
    
    // three of a kind
    // ---------------------------------------------------------------------------
    
    // WOULD WORK WITH PT2
    "(A{3}|K{3}|Q{3}|J{3}|T{3}|9{3}|8{3}|7{3}|6{3}|5{3}|4{3}|3{3}|2{3})|((A{2}|K{2}|Q{2}|J{2}|T{2}|9{2}|8{2}|7{2}|6{2}|5{2}|4{2}|3{2}|2{2})(.*)J)|((.*)J{2})".r,
    
    // two pair
    // ---------------------------------------------------------------------------

    // WOULD WORK WITH PT2
    "((A{2}|K{2}|Q{2}|J{2}|T{2}|9{2}|8{2}|7{2}|6{2}|5{2}|4{2}|3{2}|2{2})(.*)(A{2}|K{2}|Q{2}|J{2}|T{2}|9{2}|8{2}|7{2}|6{2}|5{2}|4{2}|3{2}|2{2}))|(J{3})".r,
    
    // one pair
    // ---------------------------------------------------------------------------

    // WOULD WORK WITH PT2
    "((A{2}|K{2}|Q{2}|J{2}|T{2}|9{2}|8{2}|7{2}|6{2}|5{2}|4{2}|3{2}|2{2}))|((.*)J)".r,

    // high card
    // ---------------------------------------------------------------------------

    // WOULD WORK WITH PT2
    "[AKQT2-9J]{5}".r

    // ---------------------------------------------------------------------------
  )
  val CardMatcher = "[AKQJT2-9]".r
  val HandMatcher = "[AKQJT2-9][AKQJT2-9][AKQJT2-9][AKQJT2-9][AKQJT2-9]".r
}

// ################################################################################
// ################################################################################

object Day7 {
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
        case 98 => {
          // ============================================================
          // ...
          handlePart1(Main.grabLinesFromFile("data/day7testinput1.txt"),true)
          // ...
          // ============================================================
        }
        case 99 => {
          // ============================================================
          // ...
          handlePart2(Main.grabLinesFromFile("data/day7testinput1.txt"),true)
          // ...
          // ============================================================
        }
        // the part matching
        case 1 => {
          // ============================================================
          handlePart1(Main.grabLinesFromFile("data/day7input.txt"),true)
          // ============================================================
        }
        case 2 => {
          // ============================================================
          handlePart2(Main.grabLinesFromFile("data/day7input.txt"),false)
          // ============================================================
        }
        case numberInput => {
          // ============================================================
          Main.failingMessage("DAY 7 INVALID PART NUMBER: "+numberInput)
          // ============================================================
        }
      }
    }
    catch {
      case e:Exception=> Main.failingMessage("DAY 7 HAD EXCEPTION: "+e.toString())
    }
  }

  // ========================================
  // ========================================

  def handlePart1(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    val intMatcher = "[1-9][0-9]*".r
    
    var cardHands = new Array[Tuple3[String,String,Int]](inputLines.length)
    // tokenise the input into tuple2
    for(handIndex <- 0 to inputLines.length-1){
      cardHands(handIndex) = (
        CardHandle.CardMatcher.findAllIn(inputLines(handIndex).substring(0,5)).toList.map(item=>item.charAt(0)).sortBy(x=>CardHandle.AllCardTypesDesc.indexOf(x)).mkString,
        inputLines(handIndex).substring(0,5),
        intMatcher.findAllIn(inputLines(handIndex)).toList.last.toInt // and the number
      )
      if(includeDebuggingInfo) printf("ordered hand: | %5s | %4d |\n",cardHands(handIndex)._1,cardHands(handIndex)._3)
    }
    // now
    // we sort all of them by which pattern matches them
    var orderedCardHands = cardHands.map(item=>{
      (
        CardHandle.AllCardPatternMatchingDesc.indexWhere(regexPattern=>{
          regexPattern.findAllIn(item._1).toArray.length > 0
        }),

        item._2.toCharArray().map(ch=>{
          ('A'.toInt + CardHandle.AllCardTypesDesc.indexOf(ch).toInt).toChar
        }).mkString,
        
        item._3
      )
    }).sortBy(item=>(item._1,item._2))

    // print them now
    if(includeDebuggingInfo) println("as characters instead of card types:")
    if(includeDebuggingInfo) for(handIndex <- 0 to cardHands.length-1){
      //...
      printf("| %5s | %-4d | -- %-4d\n",orderedCardHands(handIndex)._2,orderedCardHands(handIndex)._3,handIndex+1)
    }


    var orderedCardsReturnedToNormalHand = orderedCardHands.map(item=>{
      (item._2.toCharArray().map(ch=>{
        CardHandle.AllCardTypesDesc(ch.toInt - 'A'.toInt)
      }).mkString, item._3)
    }).reverse
    // print them now
    if(includeDebuggingInfo) println("as ordered:")
    if(includeDebuggingInfo) for(handIndex <- 0 to cardHands.length-1){
      //...
      printf("| %5s | %-4d | -- %-4d\n",orderedCardsReturnedToNormalHand(handIndex)._1,orderedCardsReturnedToNormalHand(handIndex)._2,handIndex+1)
    }

    var totalAccumulator = 0

    for(handIndex <- 0 to orderedCardsReturnedToNormalHand.length-1){
      totalAccumulator = totalAccumulator + ((handIndex+1)*orderedCardsReturnedToNormalHand(handIndex)._2)
    }

    printf("RESULT SHOULD BE: %d\n",totalAccumulator)
  }

  // ========================================
  // ========================================

  def handlePart2(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={
    // TODO: DAY 7 PART 2


    val intMatcher = "[1-9][0-9]*".r
    
    var cardHands = new Array[Tuple3[String,String,Int]](inputLines.length)
    // tokenise the input into tuple2
    for(handIndex <- 0 to inputLines.length-1){
      cardHands(handIndex) = (
        CardHandle.CardMatcher.findAllIn(inputLines(handIndex).substring(0,5)).toList.map(item=>item.charAt(0)).sortBy(x=>CardHandle.AllCardTypesDescPt2.indexOf(x)).mkString,
        inputLines(handIndex).substring(0,5),
        intMatcher.findAllIn(inputLines(handIndex)).toList.last.toInt // and the number
      )
      if(includeDebuggingInfo) printf("ordered hand: | %5s | %4d |\n",cardHands(handIndex)._1,cardHands(handIndex)._3)
    }
    // now
    // we sort all of them by which pattern matches them
    var orderedCardHands = cardHands.map(item=>{
      (
        CardHandle.AllCardPatternMatchingDescPt2.indexWhere(regexPattern=>{
          regexPattern.findAllIn(item._1).toArray.length > 0
        }),

        item._2.toCharArray().map(ch=>{
          ('A'.toInt + CardHandle.AllCardTypesDescPt2.indexOf(ch).toInt).toChar
        }).mkString,
        
        item._3
      )
    }).sortBy(item=>(item._1,item._2))

    // print them now
    if(includeDebuggingInfo) println("as characters instead of card types:")
    if(includeDebuggingInfo) for(handIndex <- 0 to cardHands.length-1){
      //...
      printf("| %5s | %-4d | -- %-4d\n",orderedCardHands(handIndex)._2,orderedCardHands(handIndex)._3,handIndex+1)
    }


    var orderedCardsReturnedToNormalHand = orderedCardHands.map(item=>{
      (item._2.toCharArray().map(ch=>{
        CardHandle.AllCardTypesDescPt2(ch.toInt - 'A'.toInt)
      }).mkString, item._3)
    }).reverse
    // print them now
    if(includeDebuggingInfo) println("as ordered:")
    if(includeDebuggingInfo) for(handIndex <- 0 to cardHands.length-1){
      //...
      printf("| %5s | %-4d | -- %-4d\n",orderedCardsReturnedToNormalHand(handIndex)._1,orderedCardsReturnedToNormalHand(handIndex)._2,handIndex+1)
    }

    var totalAccumulator = 0

    for(handIndex <- 0 to orderedCardsReturnedToNormalHand.length-1){
      totalAccumulator = totalAccumulator + ((handIndex+1)*orderedCardsReturnedToNormalHand(handIndex)._2)
    }

    printf("RESULT SHOULD BE: %d\n",totalAccumulator)


    println("TODO")
  }

  // ========================================
  // ========================================
}

// ################################################################################
// ################################################################################

// ...

// ################################################################################
// ################################################################################
