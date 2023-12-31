package Main
object Numbers{

  // ========================================
  // ========================================
  
  // val digitMatcher = "[1-9]"
  val digitMatcher = "[0-9]"
  // val digitNameMatcher = "(one)|(two)|(three)|(four)|(five)|(six)|(seven)|(eight)|(nine)"
  val digitNameMatcher = "(zero)|(one)|(two)|(three)|(four)|(five)|(six)|(seven)|(eight)|(nine)"
  // val lazyDigitAndNameMatcher = "[1-9]|(one)|(two)|(three)|(four)|(five)|(six)|(seven)|(eight)|(nine)".r
  // val lazyDigitAndNameMatcherReversed = "[1-9]|(one)|(two)|(three)|(four)|(five)|(six)|(seven)|(eight)|(nine)".r
  
  // val refinedDigitAndNameMatcher = "t(wo|hree)|(ni|o)ne|s(even|ix)|f(ive|our)|eight|[1-9]".r
  val refinedDigitAndNameMatcher = "zero|t(wo|hree)|(ni|o)ne|s(even|ix)|f(ive|our)|eight|[0-9]".r
  // val refinedDigitAndNameMatcherReversed = "owt|e(erht|n(in|o)|vif)|(neve|xi)s|thgie|[1-9]".r
  val refinedDigitAndNameMatcherReversed = "ruof|o(wt|rez)|e(erht|n(in|o)|vif)|(neve|xi)s|thgie|[0-9]".r
  // ========================================
  // ========================================
  
  /**
    * lazy digit to word conversion
    *
    * @param digitName string of a digit
    * @return the word 
    */
  def lazyDigitNameAsInt(digitName:String):Int={
    // faster than array search, icky worst case
    digitName match {
      case "zero" => 0
      case "one" => 1
      case "two" => 2
      case "three" => 3
      case "four" => 4
      case "five" => 5
      case "six" => 6
      case "seven" => 7
      case "eight" => 8
      case "nine" => 9
      case _ => {
        // BIGTODO: JAM A THROWABLE IN HERE
        -1 // to keep IDE happy?
      }
    }
  }

  // ========================================
  // ========================================
  
}