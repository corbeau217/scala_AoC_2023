package day5

import org.bitbucket.inkytonik.kiama.util.ParseTests
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
/**
 * Tests that check that the syntax analyser works correctly.  I.e., it accepts
 * correct input and produces the appropriate trees, and it rejects illegal input.
 */
@RunWith(classOf[JUnitRunner])
class MapAnalysisTests extends ParseTests {

    import MapLangTree._

    val parsers = new MapAnalysis (positions)
    import parsers._


    test ("SEEDS PARSING: able to parse seeds") {
        seedDef ("seeds: 34 27 281 1") should parseTo[SeedDefn] (SeedDefn( Vector("34", "27", "281", "1") ))
    }
}