# Update 1

#### Date: 1 April, 2023

By mistake, I uploaded the GUI version of `Main.java`, which is currently in progress and does not include the
main(Strings[] args) class to run the console version of game. So I changed that to correct version of it.

# Update 2

#### Date: 14 April, 2023

Their way a major error in `fits(int[] co)` method, which check whether the ship can be placed or not at the
given coordinates, in `Battleground.java` file. I have changes to correct and now there is no error in the code, and it is perfectly
fine to run.

# Update 3

#### Date: 23 April, 2023

1. Changed `if-else` loops in `fits(int[] co)` in `Battleground.java` to better and efficient
   `switch` statements.
2. Moved all the source code out from `BattleShip Battleground src` folder
   and deleted the folder.

# Update 4

#### Date: 25 April, 2023

Modified the source code in `Battleground.java` to handle the necessary exceptions as follows:

1. **IncorrectHCoordinate**: This will check whether the given `Horizontal` coordinate is
   out of _range_ or not. _Range_ for `Horizontal` coordinates: `1 - 10`.
2. **NumberFormat Exception**: This is specifically used to handle the situation when
   `Horizontal` coordinate is entered as **_not_** an integer.
3. **IncorrectVCoordinate**: To check the _range_ and _format_ of `Vertical` coordinate this exception is used. _Range_ for `Vertical` coordinates: `A - J`.
