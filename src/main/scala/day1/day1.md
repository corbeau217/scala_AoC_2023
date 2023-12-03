# day 1: scala
  scala attempt at day 1
## part 1
### strategy
1. attempted to use file inputting
2. hurdled too hard so just copy past and multi cursor'd into a `List(line*:String)`
3. in each line, grab all digits
  - first is cast to `Int` and `*10`
    * forgor for a bit that we needed to `*10`
  - second is cast to `Int`
  - add two values
  - update running total
4. answer failed so fixed `*10`
5. testing with supplied test
6. done

## part 2
### strategy
1. added pattern match for words
2. errors so debug with supplied test
3. works so reread, found input is same
4. had solution spoilt for me and realised i was only matching from left to right even while treating it like i was changing direction
5. attempting to find a pattern in the words
  * couldnt find any that held properly for all
  * created left to right state diagram
5. started reformatting file structure so i could include mermaid charts for each day
6. reformatted into new structure but still have some to do
7. need table for the data info

### char acceptance state diagrams
* references:
  - https://github.com/JakeSteam/Mermaid/blob/main/state.md
  - https://mermaid.live/
  - https://blog.jakelee.co.uk/using-mermaid-for-diagrams-on-github/

```mermaid
stateDiagram-v2
  direction LR
    %% ------------------
    %% transition 2
    [*] --> s1: e
    [*] --> s2: f
    %% ...

    [*] --> s3: n
    [*] --> s4: o
    [*] --> s5: s

    %% ...
    [*] --> s6: t
    %% ...
    %% ------------------
    %% transition 2
    s1 --> s7: i
    s2 --> s8: i
    s2 --> s9: o

    s3 --> s10: i
    s4 --> s11: n
    s5 --> s12: e

    s5 --> s13: i
    s6 --> s14: h
    s6 --> s15: w
    %% ------------------
    %% transition 3
    s7 --> s16: g
    s8 --> s17: v
    s9 --> s18: u

    s10 --> s19: n
    s11 --> s20: e
    s12 --> s21: v

    s13 --> s22: x
    s14 --> s23: r
    s15 --> s24: o
    %% ------------------
    %% transition 4
    s16 --> s25: h
    s17 --> s26: e
    s18 --> s27: r

    s19 --> s28: e
    s20 --> [*]: ε
    s21 --> s29: e

    s22 --> [*]: ε
    s23 --> s30: e
    s24 --> [*]: ε
    %% ------------------
    %% transition 5
    s25 --> s31: t
    s26 --> [*]: ε
    s27 --> [*]: ε

    s28 --> [*]: ε
    %% ...
    s29 --> s32: n

    %% ...
    s30 --> s33: e
    %% ...
    %% ------------------
    %% transition 6
    s31 --> [*]: ε
    %% ...
    %% ...

    %% ...
    %% ...
    s32 --> [*]: ε

    %% ...
    s33 --> [*]: ε
    %% ...
```
### char details table

<!-- TODO -->