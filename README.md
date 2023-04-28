# Battleship
Console version of Battleship game coded in Java.  
Battleship is played between two players, each player have their own battleground in which they arrange their 5 ships. Player who destroy all the ships of opponent first is winner.  
More on this can be found here: [Battleship Wikipedia](https://en.wikipedia.org/wiki/Battleship_(game)). 
## Rules
### Ships
There are 5 ships in this game:
1. Aircraft Carrier, length - 5
2. Battleship, length - 4
3. Submarine, length - 3
4. Cruiser, length - 2
5. Destroyer, length - 2
### Conditions for ships
Each ship must be placed either `Horizontal` or `Vertical`.  
Ship can only be placed to certain coordinates if the `distance` between coordinates is equal to
length of ship and the ship is not within __1__ coordinate of __already__ placed ship. 
### Playing the Game
The game will start after both player has placed their ships in `Battleground`.  
Each Player will take their turn one-by-one.  
In each turn a Player will shoot missile to battleground of another Player to destroy ship of them.  
The Player who's all ships are destroyed will lose and other will win the game.
# Compile and Running the Code
Compile all the files and run the `Main.java` file to start the demo of game.
All the source code is currently under working stage as well,
so comment and more efficient methods are subject to change and updates will be made every time changes is/are done.
## Current Status
Currently, I am working to make a better graphical version this game by implementing GUI components available in java.
I will uplode the source code for GUI version of game as well once it starts talking some shape.
#### Updates
Any changes regarding source code will be updated as they are made and can be seen in `updates.md` file.
# Cloning repository
To clone this copy the following into terminal:
```
git clone https://github.com/hetu2344/Battleship.git
```
## Cloning in VS code
Open command `Command Palette` by shortcut: `cmd + shift + P` and type `Git clone`
select the first option and paste the following: 
```
https://github.com/hetu2344/Battleship.git
```
# Notes
Any changes and recommendations regarding this project will be most welcomed.  
Thankyou,  
Het Patel  
