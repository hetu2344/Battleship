
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner s = new Scanner(System.in);
    
    private static void changePlayer() {
        System.out.println("Press Enter and pass the move to another player");
        s.nextLine();
    }
    
    public static void startGame() {
        ArrayList<Ship> ships1 = new ArrayList<>();
        ArrayList<Ship> ships2 = new ArrayList<>();
        final int[] shipsLength = { 5, 4, 3, 3, 2 };
        final String[] shipsNames = { "Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer" };
        Player p1 = new Player("Player 1", new Game());
        Player p2 = new Player("Player 2", new Game());
        for (int i = 0; i < shipsLength.length; i++) {
            ships1.add(new Ship(shipsNames[i], shipsLength[i], p1.getGame()));
        }
        for (int i = 0; i < shipsLength.length; i++) {
            ships2.add(new Ship(shipsNames[i], shipsLength[i], p2.getGame()));
        }
        p1.getGame().setShip(ships1);
        p2.getGame().setShip(ships2);
        System.out.println(p1.getName() + ", place your ships on the game field");
        p1.getGame().startGame();
        changePlayer();
        System.out.println(p2.getName() + ", place your ships on the game field");
        p2.getGame().startGame();
        changePlayer();
        do  {
            p2.getGame().printBattleGroundFOG();
            System.out.println("---------------------");
            p1.getGame().printBattleGround();
            System.out.println(p1.getName() + ", it's your turn: ");
            p2.getGame().shootMissile();
            if (p2.getGame().getCountOfSink() == 5) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }
            changePlayer();
            p1.getGame().printBattleGroundFOG();
            System.out.println("---------------------");
            p2.getGame().printBattleGround();
            System.out.println(p2.getName() + ", it's your turn: ");
            p1.getGame().shootMissile();
            if (p1.getGame().getCountOfSink() == 5) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }
            changePlayer();
        } while(true);
    }
    
    public void main(String[] args){
        startGame();
}
