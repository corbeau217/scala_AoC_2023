package day5

import org.bitbucket.inkytonik.kiama.util.ParseTests
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * Tests that check that the map analyser works correctly.  I.e., it accepts
 * correct input and produces the appropriate trees, and it rejects illegal input.
 */
@RunWith(classOf[JUnitRunner])
class MapAnalysisTests extends ParseTests {
    
    // ======================================================================

    import MapLangTree._

    val parsers = new MapAnalysis (positions)
    import parsers._
    
    // ======================================================================

    test ("INT PARSING: able to parse int") {
        longExp ("312323") should parseTo[Long] (312323)
    }
    
    // ======================================================================

    test ("MAP IDN PARSING: able to parse map identifier"){
        idnExp ("soil") should parseTo[String] ("soil")
    }
    
    // ======================================================================

    test ("MAP EXP PARSING: able to parse map exp") {
        mapExp ("34 27 281") should parseTo[MapLangNode] ( MapExp(34, 27, 281) )
    }
    
    // ======================================================================

    // test ("SEEDS PARSING: able to parse seeds") {
    //     seedDef ("seeds: 34 27 281 1") should parseTo[MapLangNode] (SeedDefn( Vector(34, 27, 281, 1) ))
    // }
    test ("SEEDS PARSING: able to parse seeds") {
        seedList ("seeds: 34 27 281 1") should parseTo[Vector[Long]] ( Vector(34, 27, 281, 1) )
    }
    
    // ======================================================================

    test ("MAP DEFN PARSING: able to parse map def"){
        mapDefnExp ("""seed-to-soil map:
                50 98 2
                52 50 48""") should parseTo[MapLangNode] (
                    MapDefnExp("seed","soil",Vector(
                        MapExp(50,98,2),
                        MapExp(52,50,48)
                    ))
                )
    }
    
    // ======================================================================

    test ("MAP DEF LIST PARSING: able to parse map def list"){
        mapDefList ("""seed-to-soil map:
                50 98 2
                52 50 48

                soil-to-fertilizer map:
                0 15 37
                31 52 2
                59 0 15
                """) should parseTo[Vector[MapDefnExp]] ( Vector(
                    MapDefnExp("seed","soil",Vector(
                        MapExp(50,98,2),
                        MapExp(52,50,48)
                    )),
                    MapDefnExp("soil","fertilizer",Vector(
                        MapExp(0,15,37),
                        MapExp(31,52,2),
                        MapExp(59,0,15)
                    ))
                ) )
    }
    
    // ======================================================================

    test ("WHOLE THING PARSING: able to parse whole thing"){
        parser ("""seeds: 79 14 55 13

                seed-to-soil map:
                50 98 2
                52 50 48
                
                soil-to-fertilizer map:
                0 15 37
                37 52 2
                39 0 15
                
                fertilizer-to-water map:
                49 53 8
                0 11 42
                42 0 7
                57 7 4
                
                water-to-light map:
                88 18 7
                18 25 70
                
                light-to-temperature map:
                45 77 23
                81 45 19
                68 64 13
                
                temperature-to-humidity map:
                0 69 1
                1 0 69
                
                humidity-to-location map:
                60 56 37
                56 93 4
                """) should parseTo[MapLangNode] ( Input(
                    Vector( 79, 14, 55, 13 ),
                    Vector(
                        MapDefnExp("seed","soil",Vector(
                            MapExp(50,98,2),
                            MapExp(52,50,48)
                        )),
                        MapDefnExp("soil","fertilizer",Vector(
                            MapExp(0,15,37),
                            MapExp(37,52,2),
                            MapExp(39,0,15)
                        )),
                        MapDefnExp("fertilizer","water",Vector(
                            MapExp(49,53,8),
                            MapExp(0,11,42),
                            MapExp(42,0,7),
                            MapExp(57,7,4)
                        )),
                        MapDefnExp("water","light",Vector(
                            MapExp(88,18,7),
                            MapExp(18,25,70)
                        )),
                        MapDefnExp("light","temperature",Vector(
                            MapExp(45,77,23),
                            MapExp(81,45,19),
                            MapExp(68,64,13)
                        )),
                        MapDefnExp("temperature","humidity",Vector(
                            MapExp(0,69,1),
                            MapExp(1,0,69)
                        )),
                        MapDefnExp("humidity","location",Vector(
                            MapExp(60,56,37),
                            MapExp(56,93,4)
                        ))
                    )
                ) )
    }
    
    // ======================================================================

    // ...
    
    // ======================================================================
}