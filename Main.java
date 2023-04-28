
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner INPUT = new Scanner(System.in);
    final private static int[] SHIPSLENGTH = { 5, 4, 3, 2, 2 };
    final private static String[] SHIPSNAMES = { "Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer" };

    private static void changePlayer() {
        System.out.print("Press ENTER and pass the move to another player");
        INPUT.nextLine();
    }

    private static void initiallizeShip(Player p) {
        System.out.println(p.getName() + ", place your ships on the game field");
        p.getGame().assignShips();
    }

    private static Player initiallizePlayer(String name) {
        ArrayList<Ship> ships = new ArrayList<>();
        Player p = new Player(name, new Game());
        for (int i = 0; i < SHIPSLENGTH.length; i++) {
            ships.add(new Ship(SHIPSNAMES[i], SHIPSLENGTH[i], p.getGame()));
        }
        p.getGame().setShip(ships);
        return p;
    }

    public static void startGame(Player p1, Player p2) {
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
        Player p1 = initiallizePlayer("Player 1");
        Player p2 = initiallizePlayer("Player 1");
        initiallizeShip(p1);
        changePlayer();
        initiallizeShip(p2);
        System.out.print("Press ENTER to start the GAME!!");
        INPUT.nextLine();
        startGame(p1, p2);
    }
}
