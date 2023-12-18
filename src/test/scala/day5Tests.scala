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
        intExp ("312323") should parseTo[MapLangNode] (IntExp(312323))
    }
    
    // ======================================================================

    test ("MAP IDN PARSING: able to parse map identifier"){
        idnExp ("soil") should parseTo[MapLangNode] (IdnUse("soil"))
    }
    
    // ======================================================================

    test ("MAP EXP PARSING: able to parse map exp") {
        mapExp ("34 27 281") should parseTo[MapLangNode] ( MapExp(IntExp(34), IntExp(27), IntExp(281)) )
    }
    
    // ======================================================================

    test ("SEEDS PARSING: able to parse seeds") {
        seedDef ("seeds: 34 27 281 1") should parseTo[MapLangNode] (SeedDefn( Vector(IntExp(34), IntExp(27), IntExp(281), IntExp(1)) ))
    }
    
    // ======================================================================

    test ("MAP DEFN PARSING: able to parse map def"){
        mapDefnExp ("""seed-to-soil map:
                50 98 2
                52 50 48""") should parseTo[MapLangNode] (
                    MapDefnExp(IdnUse("seed"),IdnUse("soil"),Vector(
                        MapExp(IntExp(50),IntExp(98),IntExp(2)),
                        MapExp(IntExp(52),IntExp(50),IntExp(48))
                    ))
                )
    }
    
    // ======================================================================

    test ("MAP DEF LIST PARSING: able to parse map def list"){
        mapDefExpList ("""seed-to-soil map:
                50 98 2
                52 50 48

                soil-to-fertilizer map:
                0 15 37
                31 52 2
                59 0 15
                """) should parseTo[MapLangNode] (MapDefList(Vector(
                    MapDefnExp(IdnUse("seed"),IdnUse("soil"),Vector(
                        MapExp(IntExp(50),IntExp(98),IntExp(2)),
                        MapExp(IntExp(52),IntExp(50),IntExp(48))
                    )),
                    MapDefnExp(IdnUse("soil"),IdnUse("fertilizer"),Vector(
                        MapExp(IntExp(0),IntExp(15),IntExp(37)),
                        MapExp(IntExp(31),IntExp(52),IntExp(2)),
                        MapExp(IntExp(59),IntExp(0),IntExp(15))
                    ))
                )))
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
                """) should parseTo[MapLangNode] (Input(
                    SeedDefn( Vector(IntExp(79), IntExp(14), IntExp(55), IntExp(13)) ),
                    MapDefList(Vector(
                        MapDefnExp(IdnUse("seed"),IdnUse("soil"),Vector(
                            MapExp(IntExp(50),IntExp(98),IntExp(2)),
                            MapExp(IntExp(52),IntExp(50),IntExp(48))
                        )),
                        MapDefnExp(IdnUse("soil"),IdnUse("fertilizer"),Vector(
                            MapExp(IntExp(0),IntExp(15),IntExp(37)),
                            MapExp(IntExp(37),IntExp(52),IntExp(2)),
                            MapExp(IntExp(39),IntExp(0),IntExp(15))
                        )),
                        MapDefnExp(IdnUse("fertilizer"),IdnUse("water"),Vector(
                            MapExp(IntExp(49),IntExp(53),IntExp(8)),
                            MapExp(IntExp(0),IntExp(11),IntExp(42)),
                            MapExp(IntExp(42),IntExp(0),IntExp(7)),
                            MapExp(IntExp(57),IntExp(7),IntExp(4))
                        )),
                        MapDefnExp(IdnUse("water"),IdnUse("light"),Vector(
                            MapExp(IntExp(88),IntExp(18),IntExp(7)),
                            MapExp(IntExp(18),IntExp(25),IntExp(70))
                        )),
                        MapDefnExp(IdnUse("light"),IdnUse("temperature"),Vector(
                            MapExp(IntExp(45),IntExp(77),IntExp(23)),
                            MapExp(IntExp(81),IntExp(45),IntExp(19)),
                            MapExp(IntExp(68),IntExp(64),IntExp(13))
                        )),
                        MapDefnExp(IdnUse("temperature"),IdnUse("humidity"),Vector(
                            MapExp(IntExp(0),IntExp(69),IntExp(1)),
                            MapExp(IntExp(1),IntExp(0),IntExp(69))
                        )),
                        MapDefnExp(IdnUse("humidity"),IdnUse("location"),Vector(
                            MapExp(IntExp(60),IntExp(56),IntExp(37)),
                            MapExp(IntExp(56),IntExp(93),IntExp(4))
                        ))
                    ))
                ))
    }
    
    // ======================================================================

    // ...
    
    // ======================================================================
}