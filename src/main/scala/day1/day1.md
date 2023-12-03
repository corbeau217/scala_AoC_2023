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
8. made table in this file
9. cleaned up `Main.scala`


### char acceptance state diagrams
* references:
  - https://github.com/JakeSteam/Mermaid/blob/main/state.md
  - https://mermaid.live/
  - https://blog.jakelee.co.uk/using-mermaid-for-diagrams-on-github/

<!-- ######################################################### -->
<ul>
  <!-- ======================================================= -->
  <!-- ======================================================= -->
  <li><h4>left-to-right alphabetical character acceptance</h4><details><summary><i>[show / hide]</i></summary>
<!-- BIGTODO: REVERSED ACCEPTANCE STATE DIAGRAM -->

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

  </details></li>
  <!-- ======================================================= -->
  <!-- ======================================================= -->
  <li><h4>right-to-left alphabetical character acceptance</h4><details><summary><i>[show / hide]</i></summary>

```mermaid
stateDiagram-v2
  direction RL
    %% ------------------
    %% transition 1
    [*] --> [*]: ε
    %% ...
```
  </details></li>
  <!-- ======================================================= -->
  <!-- ======================================================= -->
</ul>
<!-- ######################################################### -->

### char details table

<table>
  <!-- ------ ------ ------ ------ ------ -->
  <!-- ------ ------ ------ ------ ------ -->
  <tr>
    <!-- ------ ------ ------ ------ -->
    <td>thinger</td>
    <!-- ------ ------ ------ ------ -->
    <td>aaaa</td>
    <td>bbbb</td>
    <td>cccc</td>
    <td>dddd</td>
    <!-- ------ ------ ------ ------ -->
  </tr>
  <!-- ------ ------ ------ ------ ------ -->
  <!-- ------ ------ ------ ------ ------ -->
  <tr>
    <!-- ------ ------ ------ ------ -->
    <td>possible characters</td>
    <!-- ------ ------ ------ ------ -->
    <td>
      <ul>
        <!--  -->
        <!--  -->
        <li><code>e</code></li>
        <li><code>f</code></li>
        <li><code>g</code></li>
        <li><code>h</code></li>
        <li><code>i</code></li>
        <li><code>n</code></li>
        <li><code>o</code></li>
        <li><code>r</code></li>
        <li><code>s</code></li>
        <li><code>t</code></li>
        <li><code>u</code></li>
        <li><code>v</code></li>
        <li><code>w</code></li>
        <li><code>x</code></li>
      </ul>
    </td>
    <!-- ------ ------ ------ ------ -->
    <td>
      <code>List(e, f, g, h, i, n, o, r, s, t, u, v, w, x)</code>
    </td>
    <!-- ------ ------ ------ ------ -->
    <td>
      <code>/(e|f|g|h|i|n|o|r|s|t|u|v|w|x)/</code>
    </td>
    <!-- ------ ------ ------ ------ -->
  </tr>
  <!-- ------ ------ ------ ------ ------ -->
  <!-- ------ ------ ------ ------ ------ -->
  <tr>
    <!-- ------ ------ ------ ------ -->
    <td>crunchables</td>
    <!-- ------ ------ ------ ------ -->
    <td>
<details><summary>show/hide NFA1</summary>

| state | e | f | g | h | i | n | o | r | s | t | u | v | w | x |     ε     |   |
| ----- | - | - | - | - | - | - | - | - | - | - | - | - | - | - | --------- | - |
| start | s1| s2|   |   |   | s3| s4|   | s5| s6|   |   |   |   |   start   |   |
|    s1 |   |   |   |   | s7|   |   |   |   |   |   |   |   |   |      s1   |   |
|    s2 |   |   |   |   | s8|   | s9|   |   |   |   |   |   |   |      s2   |   |
|    s3 |   |   |   |   |s10|   |   |   |   |   |   |   |   |   |      s3   |   |
|    s4 |   |   |   |   |   |s11|   |   |   |   |   |   |   |   |      s4   |   |
|    s5 |s12|   |   |   |s13|   |   |   |   |   |   |   |   |   |      s5   |   |
|    s6 |   |   |   |s14|   |   |   |   |   |   |   |   |s15|   |      s6   |   |
|    s7 |   |   |s16|   |   |   |   |   |   |   |   |   |   |   |      s7   |   |
|    s8 |   |   |   |   |   |   |   |   |   |   |   |s17|   |   |      s8   |   |
|    s9 |   |   |   |   |   |   |   |   |   |   |s18|   |   |   |      s9   |   |
|   s10 |   |   |   |   |   |s19|   |   |   |   |   |   |   |   |     s10   |   |
|   s11 |s20|   |   |   |   |   |   |   |   |   |   |   |   |   |     s11   |   |
|   s12 |   |   |   |   |   |   |   |   |   |   |   |s21|   |   |     s12   |   |
|   s13 |   |   |   |   |   |   |   |   |   |   |   |   |   |s22|     s13   |   |
|   s14 |   |   |   |   |   |   |   |s23|   |   |   |   |   |   |     s14   |   |
|   s15 |   |   |   |   |   |   |s24|   |   |   |   |   |   |   |     s15   |   |
|   s16 |   |   |   |s25|   |   |   |   |   |   |   |   |   |   |     s16   |   |
|   s17 |s26|   |   |   |   |   |   |   |   |   |   |   |   |   |     s17   |   |
|   s18 |   |   |   |   |   |   |   |s27|   |   |   |   |   |   |     s18   |   |
|   s19 |s28|   |   |   |   |   |   |   |   |   |   |   |   |   |     s19   |   |
|   s20 |   |   |   |   |   |   |   |   |   |   |   |   |   |   | s20,finiš |   |
|   s21 |s29|   |   |   |   |   |   |   |   |   |   |   |   |   |     s21   |   |
|   s22 |   |   |   |   |   |   |   |   |   |   |   |   |   |   | s22,finiš |   |
|   s23 |s30|   |   |   |   |   |   |   |   |   |   |   |   |   |     s23   |   |
|   s24 |   |   |   |   |   |   |   |   |   |   |   |   |   |   | s24,finiš |   |
|   s25 |s31|   |   |   |   |   |   |   |   |   |   |   |   |   |     s25   |   |
|   s26 |   |   |   |   |   |   |   |   |   |   |   |   |   |   | s26,finiš |   |
|   s27 |   |   |   |   |   |   |   |   |   |   |   |   |   |   | s27,finiš |   |
|   s28 |   |   |   |   |   |   |   |   |   |   |   |   |   |   | s28,finiš |   |
|   s29 |s32|   |   |   |   |   |   |   |   |   |   |   |   |   |     s29   |   |
|   s30 |s33|   |   |   |   |   |   |   |   |   |   |   |   |   |     s30   |   |
|   s31 |   |   |   |   |   |   |   |   |   |   |   |   |   |   | s31,finiš |   |
|   s32 |   |   |   |   |   |   |   |   |   |   |   |   |   |   | s32,finiš |   |
|   s33 |   |   |   |   |   |   |   |   |   |   |   |   |   |   | s33,finiš |   |
| finiš |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   finiš   | * |

</details>
    </td>
    <!-- ------ ------ ------ ------ -->
  </tr>
  <!-- ------ ------ ------ ------ ------ -->
  <!-- ------ ------ ------ ------ ------ -->
</table>



