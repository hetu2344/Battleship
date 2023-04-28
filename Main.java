/**
 * This is driver class for the Battleship Game.
 * It will initialize players, ships and battleground.
 * And will start the Battleship game between 2 Players.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner INPUT = new Scanner(System.in); //Scanner to change Player
    final private static int[] SHIPSLENGTH = { 5, 4, 3, 2, 2 }; //Array containing length of ships
    //Array containing names of ships
    final private static String[] SHIPSNAMES = { "Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer" };

    /**
     * To change the turn of player in game
     */
    private static void changePlayer() {
        System.out.print("Press ENTER and pass the move to another player");
        INPUT.nextLine();
    }

    /**
     * This will initialize ships to battleground of player.
     * @param p Player to which ship are going to get initialized
     */
    private static void initializeShip(Player p) {
        System.out.println(p.getName() + ", place your ships on the game field");
        p.getGame().assignShips();
    }

    /**
     * This method will initialize Player who will play the Game
     * @param name Name of player
     * @return Created Player
     */
    private static Player initializePlayer(String name) {
        ArrayList<Ship> ships = new ArrayList<>();
        Player p = new Player(name, new Battleground());
        for (int i = 0; i < SHIPSLENGTH.length; i++) {
            ships.add(new Ship(SHIPSNAMES[i], SHIPSLENGTH[i], p.getGame()));
        }
        p.getGame().setShip(ships);
        return p;
    }

    /**
     * This method will start the game between 2 players.
     * @param p1 Player 1
     * @param p2 Player 2
     */
    private static void startGame(Player p1, Player p2) {
        do {
            p2.getGame().printBattleGroundFOG();
            System.out.println("---------------------");
            p1.getGame().printBattleGround();
            System.out.print(p1.getName() + ", it's your turn: ");
            p2.getGame().shootMissile();
            if (p2.getGame().getCountOfSink() == 5) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }
            changePlayer();
            p1.getGame().printBattleGroundFOG();
            System.out.println("---------------------");
            p2.getGame().printBattleGround();
            System.out.print(p2.getName() + ", it's your turn: ");
            p1.getGame().shootMissile();
            if (p1.getGame().getCountOfSink() == 5) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }
            changePlayer();
        } while (true);
    }

    public static void main(String[] args) {
        Player p1 = initializePlayer("Player 1");
        Player p2 = initializePlayer("Player 1");
        initializeShip(p1);
        changePlayer();
        initializeShip(p2);
        System.out.print("Press ENTER to start the GAME!!");
        INPUT.nextLine();
        startGame(p1, p2);
    }
}
