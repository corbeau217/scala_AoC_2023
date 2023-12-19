# day 5: scala
  scala attempt at day 5
## Quick links
### REPO NAV
* [`README`](./README.md)
* [`projectChangesAndNotes`](./projectChangesAndNotes.md)
* [`dayDocMaker`](./dayDocMaker.md)
### DAY FILES
* [day's AoC page](https://adventofcode.com/2023/day/5)
* [Input file](https://adventofcode.com/2023/day/5/input)
* [day's scala file](../../src/main/scala/day5.scala)
## part 1
### strategy
1. kinda just yoinks from our day4 solution,
2. just grab the info for a given source then BST them?
3. may benefit from a parse tree for understanding the things
4. they forcing us to use longs :blobsob:
5. what if we just make a parser lmao?
    1. parser means we get things to use later on to speed things up
    2. means more time spent on earlier days though
6. SO WHADAWE GOT
    1. need some defineables
    2. just keep them as they were then redefine later we can tree it
        * Input
          - `seedExp ~ MapExpList`
        * seedExp
          - `seedDeclaration`
        * seedDeclaraction
          - `"Seed:" ~> (Integer)+`
        * MapExpList
          - `(Mapping)+`
        * Mapping
          - `MapSignature ~ (mappingDeclaration)+`
        * MapSignature
          - `source ~ ("-to-" ~> destination <~ "map:")`
        * mappingDeclaration
          - `Integer ~ Integer ~ Integer`

<details><summary><i>show/hide <code>FunLangTree</code></i></summary>

```mermaid
classDiagram
    %% ===================================
    %% ===================================
    %% === supers

    class FunLangNode

    class Type
    
    class Identifier{
        String
    }

    %% ===================================
    %% ===================================
    %% === super children

    class Exp
    FunLangNode <|-- Exp

    class Program {
        Program(Exp)
    }
    FunLangNode <|-- Program

    class Idn{
        +Identifier idn
    }
    FunLangNode <|-- Idn
    Identifier <.. Idn

    class Defn {
        Defn(Vector[IdnDef], Exp)
    }
    FunLangNode <|-- Defn

    %% ===================================
    %% ===================================
    %% === identifier branch branch

    class IdnDef
    Exp <|-- IdnDef
    Idn <.. IdnDef

    class IdnUse
    Exp <|-- IdnUse
    Idn <.. IdnUse

    %% ===================================
    %% ===================================
    %% === Type branch

    class BoolType
    Type <|-- BoolType

    class FunType{
        FunType(Type,Type)
    }
    Type <|-- FunType

    class IntType
    Type <|-- IntType

    class ListType{
        ListType(Type)
    }
    Type <|-- ListType

    class TupleType{
        TupleType(Vector[Type])
    }
    Type <|-- TupleType

    class UnknownType
    Type <|-- UnknownType

    %% ===================================
    %% ===================================
    %% === Exp branch

    class WithDoExp{
        WithDoExp(Vector[Defn],Exp)
    }
    Exp <|-- WithDoExp
    Defn <.. WithDoExp

    
    class AppExp{
        AppExp(Exp,Exp)
    }
    Exp <|-- AppExp

     
    class LamExp{
        LamExp(Vector[IdnDef],Exp)
    }
    Exp <|-- LamExp
    IdnDef <.. LamExp

    
    class TupleExp{
        TupleExp(Vector[Exp])
    }
    Exp <|-- TupleExp

    
    class ListExp{
        ListExp(Vector[Exp])
    }
    Exp <|-- ListExp

    
    class BoolExp{
        BoolExp(Boolean)
    }
    Exp <|-- BoolExp

    
    class EqualExp{
        EqualExp(Exp,Exp)
    }
    Exp <|-- EqualExp

    
    class IfExp{
        IfExp(Exp,Exp,Exp)
    }
    Exp <|-- IfExp

    
    class IntExp{
        IntExp(Int)
    }
    Exp <|-- IntExp

    
    class LessExp{
        LessExp(Exp,Exp)
    }
    Exp <|-- LessExp

    class MinusExp{
        MinusExp(Exp,Exp)
    }
    Exp <|-- MinusExp

    
    class PlusExp{
        PlusExp(Exp,Exp)
    }
    Exp <|-- PlusExp

    
    class SlashExp{
        SlashExp(Exp,Exp)
    }
    Exp <|-- SlashExp

    
    class StarExp{
        StarExp(Exp,Exp)
    }
    Exp <|-- StarExp

    
    class ConsExp{
        ConsExp(Exp,Exp)
    }
    Exp <|-- ConsExp

    %% ===================================
    %% ===================================
```
</details>

7. need to build the tests documents for this 
    1. build out what the expectations are so we know what to aim for
8. we built the tree now deal with it
9. removed the unecessary translations
10. each mapping needs a min and max for the source and destination
    1. we then need to match the min of when it's source or destination for the non seed/location
11. bigggg brain:
    1. just get seed count
        * dont even need to check minimum/maximum possible number
        * but if we wanted to we just make a table of all the numbers and all their end range numbers, then loop all and check smallest/biggest
    2. transform the seeds by the mapping
        1. use to check if we're within a particular mapping, then our offset in, is what we get of destination
        2. stash what idn state we're in of the current transform
    3. then at the end we check which we are


## part 2
### strategy
1. ...
