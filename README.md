# 7Seg

Implementation of a kata to display input in a 7-segment display style via ASCII output.

Prints the input string as an ASCII representation of a 7-segment display, for example:

```
         _       _   _
0123 -> | |   |  _|  _|
        |_|   | |_   _|

         ___
        |   | |               |
abcd -> |___| |___   ___   ___|  (size 3x2)
        |   | |   | |     |   |
        |   | |___| |___  |___|
```

---

The kata specification is here: http://codingdojo.org/kata/NumberToLCD/

## Testing

```sh
./gradlew test
```

## Running

```sh
./gradlew run --args=0123
./gradlew run --args='--size=3x2 0123'
./gradlew run --args='--size=3x2 -- --hi'
```
