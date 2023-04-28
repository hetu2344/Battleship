# Update 1

#### Date: 1 April, 2023

By mistake, I uploaded the GUI version of `Main.java`, which is currently in progress and does not include the main(
Strings[] args) class to run the console version of game. So I changed that to correct version of it.

# Update 2

#### Date: 14 April, 2023

Their way a major error in `fits(int[] co)` method, which check whether the ship can be placed or not at the given
coordinates, in `Battleground.java` file. I have changes to correct and now there is no error in the code, and it is
perfectly fine to run.

# Update 3

#### Date: 23 April, 2023

1. Changed `if-else` loops in `fits(int[] co)` in `Battleground.java` to better and efficient
   `switch` statements.
2. Moved all the source code out from `BattleShip Battleground src` folder and deleted the folder.

# Update 4

#### Date: 25 April, 2023

Modified the source code in `Battleground.java` to handle the necessary exceptions as follows:

1. **IncorrectHCoordinate**: This will check whether the given `Horizontal` coordinate is out of _range_ or not. _Range_
   for `Horizontal` coordinates: `1 - 10`.
2. **NumberFormat Exception**: This is specifically used to handle the situation when
   `Horizontal` coordinate is entered as **_not_** an integer.
3. **IncorrectVCoordinate**: To check the _range_ and _format_ of `Vertical` coordinate this exception is used. _Range_
   for `Vertical` coordinates: `A - J`.

# Update 5

#### Date: 28 April

##### Rename

Renamed the `Game.java` class to `Battleground.java`. Reason for this is to not get confuse between Game and Battleship,
because in previous version Game in my code means battleground not the Game played between 2 Players

##### Changes in Battleship.java class

Changed the way of coordinates were handled after taking input from Player.  
Introduced new variable `invalid_coordinates`, a HashSet, which stores coordinates that are invalid for a ship to get
placed, in `Battleground.java`.  
Here are details of what changed by this implementation:

1. `fits(int[] co)`: Instead of long switch code, now we have to check is the coordinates provided by user is
   in `invalid_coordinates` or not.
2. `addInvalidCoordinates(int x, int y)` is new method added which will add all the invalid coordinates according to the
   rules in `invalid_coordinates`.
3. The storing of start and end coordinates of ship provided by user also got changer. Before it was store
   as `{start_V, start_H, end_V, end_H}`, now it is stored
   as `{start_H, input_H, Math.min(start_V, input_V), Math.max(start_V, input_V)}`
   if __Vertical__ or `{start_V, input_V, Math.min(start_H, input_H), Math.max(start_H, input_H)}` if __Horizontal__.
4. By new of storing coordinates, the code in `fillShip(int[] co, int x, boolean horizontal)` got reduced. Same is the
   case with `getSink()` method in `Ship.java` class.
5. Modified `getMissileCoordinates(String input)` method to handle necessary exceptions.

##### Documentation 
Every class in this project is commented such that it is better to understand the code.