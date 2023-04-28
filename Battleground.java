/**
 * This is Battleground class.
 * This class has methods to start and end the game and
 * to check every condition.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Battleground {
    final private String VERTICAL_COORDINATES = "ABCDEFGHIJ";
    final private String HORIZONTAL_COORDINATES = "  1 2 3 4 5 6 7 8 9 10";
    final private Scanner INPUT = new Scanner(System.in);
    private HashSet<Integer> invalid_coordinates = new HashSet<>();
    ArrayList<Ship> ships = new ArrayList<>();
    private int countOfSink = 0;
    //Battleground
    protected char[][] battleGround = {{'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}};
    //Partial battleground for enemy.
    private char[][] battleGroundFOG = {{'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}, {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~',}};

    /**
     * This method set the ships for each player
     *
     * @param s Arraylist containing un-destroyed ships
     */
    public void setShip(ArrayList<Ship> s) {
        this.ships = s;
    }

    /**
     * This method will clear terminal screen.
     */
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Method to print battleground
     */
    public void printBattleGround() {
        System.out.println(HORIZONTAL_COORDINATES);
        for(int i = 0; i < battleGround.length; i++) {
            System.out.print(VERTICAL_COORDINATES.charAt(i) + " ");
            for(int j = 0; j < battleGround[i].length; j++) {
                System.out.print(battleGround[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Method to print partial battleground for enemy.
     */
    public void printBattleGroundFOG() {
        System.out.println(HORIZONTAL_COORDINATES);
        for(int i = 0; i < battleGroundFOG.length; i++) {
            System.out.print(VERTICAL_COORDINATES.charAt(i) + " ");
            for(int j = 0; j < battleGroundFOG[i].length; j++) {
                System.out.print(battleGroundFOG[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * This method will find distance between valid end and start coordinates.
     *
     * @param co Array containing start and end coordinates of ship
     *
     * @return Distance between start and end coordinates
     */
    private int findLength(int[] co) {
        return co[3] - co[2] + 1;
    }

    /**
     * This method will check whether ship is near to any other ship or not
     *
     * @param co         Array containing start and end coordinates of ship
     * @param horizontal Boolean describing alignment of ship
     *
     * @return True if ship is not near to any other ship
     * False if ship is near to any other ship
     */
    private boolean fits(int[] co, boolean horizontal) {
        boolean valid = true;
        int a = co[2];
        int b = co[3];
        for(int i = a; i <= b; i++) {
            if(horizontal) {
                if(invalid_coordinates.contains(co[0] * 10 + i))
                    return false;
            } else {
                if(invalid_coordinates.contains(i * 10 + co[0]))
                    return false;
            }
        }
        return valid;
    }

    /**
     * This method will return numeric value of alphabet
     * from 0,1,2,...25.
     *
     * @param a Vertical coordinate provided by Player
     *
     * @return Number value of a.
     */
    private int convertToNumeric(char a) {
        return a - 65;
    }

    /**
     * This method will add all invalid coordinates to HashSet that will
     * become invalid after placing a ship
     *
     * @param x Numeric vertical coordinate
     * @param y Horizontal coordinate
     */
    private void addInvalidCoordinates(int x, int y) {
        invalid_coordinates.add(x * 10 + y);
        invalid_coordinates.add(x * 10 + y + 1);
        invalid_coordinates.add(x * 10 + y - 1);
        invalid_coordinates.add((x - 1) * 10 + y);
        invalid_coordinates.add((x + 1) * 10 + y);
    }

    /**
     * This method will place the ship.
     * This method will be called only after Player has
     * provided valid coordinates for ship with every
     * condition passed
     *
     * @param co         Coordinates of ship
     * @param x          Position of ship in Arraylist
     * @param horizontal Boolean describing alignment of ship
     */
    private void fillShip(int[] co, int x, boolean horizontal) {
        ships.get(x).setCoordinates(co);
        int a = co[2];
        int b = co[3];
        for(int i = a; i <= b; i++) {
            if(horizontal) {
                addInvalidCoordinates(co[0], i);
                battleGround[co[0]][i] = 'O';
            } else {
                addInvalidCoordinates(i, co[0]);
                battleGround[i][co[0]] = 'O';
            }
        }
    }

    /**
     * This method will take coordinates from Player and
     * will check range for each coordinate as well.
     * It will also check the alignment od ship as well, horizontal or vertical.
     *
     * @param ship The ship whose coordinate has to taken from Player
     *
     * @return int[] coordinate given by Player for ship.
     * If alignment of ship is neither horizontal nor vertical,
     * it will return array of invalid valid coordinates with length 0.
     *
     * @throws IncorrectVCoordinate Custom exception to check range for Horizontal coordinate.
     * @throws IncorrectHCoordinate Custom exception to check range for Vertical coordinate.
     */
    private int[] takeCoordinates(Ship ship) {
        int start_V = - 1, start_H = - 1, input_V = - 1, input_H = - 1;
        System.out.print("Enter the coordinate of " + ship.getName() + " (" + ship.getLength() + " cells): ");

        //First while loop will iterate until correct Start coordinates of ship is not provided

        do {
            String start = INPUT.next().toUpperCase();
            try {
                start_V = convertToNumeric(start.charAt(0));
                if(start_V > 9 || start_V < 0) {
                    throw new IncorrectVCoordinate(start.charAt(0));
                }
                start_H = Integer.parseInt(start.substring(1)) - 1;
                if(start_H > 9 || start_H < 0) {
                    throw new IncorrectHCoordinate(start_H);
                }
                break;
            } catch(IncorrectVCoordinate | IncorrectHCoordinate i) {
                System.out.println(i.getMessage());
            } catch(NumberFormatException e) {
                System.out.println(e.getMessage() + " incorrect HORIZONTAL " + "coordinate." + "\nEnter from: 1 - 10 !!");
            }
        } while(true);

        //Second while loop will iterate until correct End coordinates of ship is not provided

        do {
            String end = INPUT.next().toUpperCase();
            try {
                input_V = convertToNumeric(end.charAt(0));
                if(input_V > 9 || input_V < 0) {
                    throw new IncorrectVCoordinate(end.charAt(0));
                }
                input_H = Integer.parseInt(end.substring(1)) - 1;
                if(input_H > 9 || input_H < 0) {
                    throw new IncorrectHCoordinate(input_H);
                }
                break;
            } catch(IncorrectVCoordinate | IncorrectHCoordinate i) {
                System.out.println(i.getMessage());
            } catch(NumberFormatException e) {
                System.out.print(e.getMessage() + " incorrect HORIZONTAL " + "coordinate." + "\nEnter from: 1 - 10 !!");
            }
        } while(true);

        //Figuring alignment of ship in context of coordinates given by Player

        if(start_H == input_H) {
            ship.setHorizontal(false);
            return new int[]{start_H, input_H, Math.min(start_V, input_V), Math.max(start_V, input_V)};
        } else if(start_V == input_V) {
            ship.setHorizontal(true);
            return new int[]{start_V, input_V, Math.min(start_H, input_H), Math.max(start_H, input_H)};
        } else {
            return new int[]{0, 0, 0, - 1};
        }
    }


    /**
     * This method will ask Player for coordinate to shoot
     * missile and will also check for the correct range of it.
     *
     * @param input Coordinate provided by Player
     *
     * @return Integer array of coordinate
     *
     * @throws IncorrectVCoordinate Custom exception to check range for Horizontal coordinate.
     * @throws IncorrectHCoordinate Custom exception to check range for Vertical coordinate.
     */
    private int[] getMissileCoordinates(String input) {
        int input_V = - 1, input_H = - 1;

        //do-while loop to get coordinate from user until correct coordinate is not given

        do {
            String input1 = INPUT.next().toUpperCase();
            try {
                input_V = convertToNumeric(input1.charAt(0));
                if(input_V > 9 || input_V < 0) {
                    throw new IncorrectVCoordinate(input1.charAt(0));
                }
                input_H = Integer.parseInt(input1.substring(1)) - 1;
                if(input_H > 9 || input_H < 0) {
                    throw new IncorrectHCoordinate(input_H);
                }
                break;
            } catch(IncorrectVCoordinate | IncorrectHCoordinate i) {
                System.out.println(i.getMessage());
            } catch(NumberFormatException e) {
                System.out.println(e.getMessage() + " incorrect HORIZONTAL " + "coordinate." + "\nEnter from: 1 - 10 !!");
            }
        } while(true);
        return new int[]{input_V, input_H};
    }

    /**
     * This method will check if any ship has been destroyed or not,
     * it will also remove them the Arraylist of ships as well.
     *
     * @return True if ship has been destroyed
     * False if no ship has been destroyed
     */
    private boolean checkShip() {
        for(Ship s : ships) {
            if(s.getSink()) {
                countOfSink++;
                ships.remove(s);
                return true;
            }
        }
        return false;
    }

    /**
     * This method will fire missile to provided coordinate.
     * It will also update the battleground as well with result.
     * @param co Provided coordinate
     * @return True if missile hit a ship,
     *         False if missile does not hit ship.
     */
    private boolean fire(int[] co) {
        int a = co[0];
        int b = co[1];
        if(battleGround[a][b] == 'O' || battleGround[a][b] == 'X') {
            battleGround[a][b] = 'X';
            battleGroundFOG[a][b] = 'X';
            return true;
        } else {
            battleGround[a][b] = 'M';
            battleGroundFOG[a][b] = 'M';
            return false;
        }
    }

    /**
     * This method will be called first to start the shooting process for any player.
     * It will call getMissileCoordinates(in[] co) for getting coordinates,
     * fire(int[] co) for shooting missile and checkShip() to check for any destroyed ship.
     * After that it will process the output.
     */
    public void shootMissile() {
        String input = INPUT.next().toUpperCase();
        int[] coordinates = getMissileCoordinates(input);
        boolean hit = fire(coordinates);
        boolean sink = checkShip();
        if(sink && countOfSink != 5) {
            System.out.println("You sank a ship! Specify a new target:");
        } else {
            if(hit) {
                System.out.println("You hit a ship!");
            } else {
                System.out.println("You missed!");
            }
        }
    }

    /**
     * This method will assign each ship to battleground
     * It will call different method to check and place
     * ship to right position.
     */
    public void assignShips() {
        try {
            for(int i = 0; i < ships.size(); i++) {
                Ship s = ships.get(i);
                printBattleGround();
                int[] coordinates = takeCoordinates(s);
                int inputLength = findLength(coordinates);
                boolean length = s.getLength() == inputLength;
                boolean fits = fits(coordinates, s.isHorizontal());
                while(! (length) || ! (fits)) {
                    if(inputLength == - 1) {
                        System.out.println("Error! Wrong ship location! Try again: ");
                    } else if(! (length)) {
                        System.out.println("Error! Wrong length of " + s.getName() + " Try again: ");
                    } else {
                        System.out.println("Error! You placed it too close to another one. Try again: ");
                    }
                    coordinates = takeCoordinates(s);
                    inputLength = findLength(coordinates);
                    length = s.getLength() == inputLength;
                    fits = fits(coordinates, s.isHorizontal());
                }
                fillShip(coordinates, i, s.isHorizontal());
            }
        } catch(Exception e) {
            e.getMessage();
        }
        printBattleGround();
    }

    /**
     * This method will return number of destroyed ship in battleground
     * @return number of destroyed ship
     */
    public int getCountOfSink() {
        return this.countOfSink;
    }
}
