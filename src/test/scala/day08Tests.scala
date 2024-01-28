package day8

import org.bitbucket.inkytonik.kiama.util._
import org.junit.runner.RunWith
// import org.junit.runner._
// import org.scalatestplus.junit.JUnitRunner
import org.scalatestplus.junit.JUnitRunner
// import org.scalatest._
// import org.scalatest.
// import org.scalatest.junit.JUnitRunner
import org.scalatest.concurrent.TimeLimitedTests
import org.scalatest.time._
import org.scalatest.time.SpanSugar._
import scala.concurrent.duration.span
import org.scalatest.fixture.NoArgTestWrapper
import org.junit.runner.Result
// import org.scalatest.FunSpec

/**
 * Tests that check that the map analyser works correctly.  I.e., it accepts
 * correct input and produces the appropriate trees, and it rejects illegal input.
 */
@RunWith(classOf[JUnitRunner])
class IterationTesting extends ParseTests with TimeLimitedTests {

    import Day8._  

    val timeLimit = Span(1000,Millis)

    // ======================================================================

    test ("PART1: test 1 results 2") {
        handlePart1 (Main.Main.grabLinesFromFile("data/day8testinput1.txt"),false) == 2l
    }

    // ======================================================================

    test ("PART1: test 2 results 6") {
        handlePart1 (Main.Main.grabLinesFromFile("data/day8testinput2.txt"),false) == 6l
    }

    // ======================================================================

    test ("PART1: input results 21883") {
        handlePart1 (Main.Main.grabLinesFromFile("data/day8input.txt"),false) == 21883l
    }

    // ======================================================================
    // ======================================================================

    
    test ("PART2BRUTEFORCE: test 3 results 6") {
        handlePart2bruteforce (Main.Main.grabLinesFromFile("data/day8testinput3.txt"),false) should be (6l)
    }

    // ======================================================================
    // ======================================================================

    // this.withFixture( NoArgTestWrapper (() => {
    //         (handlePart2 (Main.Main.grabLinesFromFile("data/day8testinput3.txt"),false) should be (6l)).asInstanceOf[Result]
    //     } )
    // )

    // ======================================================================

}