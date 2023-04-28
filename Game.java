
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Game {
    final private String VERTICAL_COORDINATES = "ABCDEFGHIJ";
    final private String HORRIZONTAL_COORDINATES = "  1 2 3 4 5 6 7 8 9 10";
    final private Scanner INPUT = new Scanner(System.in);
    private HashSet<Integer> invalid_coordinates = new HashSet<>();
    ArrayList<Ship> ships = new ArrayList<>();
    private int countOfSink = 0;
    protected char[][] battleGround = { { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', } };
    private char[][] battleGroundFOG = { { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', },
            { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', } };

    /**
     * @param s
     */
    public void setShip(ArrayList<Ship> s) {
        this.ships = s;
    }

    public void printBattleGround() {
        System.out.println(HORRIZONTAL_COORDINATES);
        for (int i = 0; i < battleGround.length; i++) {
            System.out.print(VERTICAL_COORDINATES.charAt(i) + " ");
            for (int j = 0; j < battleGround[i].length; j++) {
                System.out.print(battleGround[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printBattleGroundFOG() {
        System.out.println(HORRIZONTAL_COORDINATES);
        for (int i = 0; i < battleGroundFOG.length; i++) {
            System.out.print(VERTICAL_COORDINATES.charAt(i) + " ");
            for (int j = 0; j < battleGroundFOG[i].length; j++) {
                System.out.print(battleGroundFOG[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * @return StringBuilder
     */
    public StringBuilder printBattleGroundFOGS() {
        StringBuilder s1 = new StringBuilder(HORRIZONTAL_COORDINATES);
        s1.append(System.lineSeparator());
        for (int i = 0; i < battleGroundFOG.length; i++) {
            s1.append(VERTICAL_COORDINATES.charAt(i)).append(" ");
            for (int j = 0; j < battleGroundFOG[i].length; j++) {
                s1.append(battleGroundFOG[i][j]).append(" ");
            }
            s1.append(System.lineSeparator());
        }
        return s1;
    }

    private int findLength(int[] co) {
        int a = co[0];
        int b = co[1];
        int c = co[2];
        int d = co[3];
        // if (a == c) {
        // return b > d ? b - d + 1 : d - b + 1;
        // } else if (b == d) {
        // return a > c ? a - c + 1 : c - a + 1;
        // } else {
        // return -1;
        // }
        return co[3] - co[2] + 1;
    }

    private boolean fits(int[] co, boolean horizontal) {
        boolean valid = true;
        int a = co[2];
        int b = co[3];
        for (int i = a; i <= b; i++) {
            if (horizontal) {
                if (invalid_coordinates.contains(co[0] * 10 + i))
                    return false;
            } else {
                if (invalid_coordinates.contains(i * 10 + co[0]))
                    return false;
            }
        }
        return valid;
    }

    private int convertToNumeric(char a) {
        return a - 65;
    }

    private void addInvalidCoordinates(int x, int y) {
        invalid_coordinates.add(x * 10 + y);
        invalid_coordinates.add(x * 10 + y + 1);
        invalid_coordinates.add(x * 10 + y - 1);
        invalid_coordinates.add((x - 1) * 10 + y);
        invalid_coordinates.add((x + 1) * 10 + y);
    }

    private void fillShip(int[] co, int x, boolean horizontal) {
        ships.get(x).setCoordinates(co);
        int a = co[2];
        int b = co[3];
        for (int i = a; i <= b; i++) {
            if (horizontal) {
                addInvalidCoordinates(co[0], i);
                battleGround[co[0]][i] = 'O';
            } else {
                addInvalidCoordinates(i, co[0]);
                battleGround[i][co[0]] = 'O';
            }
        }
    }

    /**
     * @param ship
     * @return int[]
     * @throws IncorrectVCoordinate
     * @throws IncorrectHCoordinate
     */
    private int[] takeCoordinates(Ship ship) throws IncorrectVCoordinate, IncorrectHCoordinate {
        int start_V = -1, start_H = -1, end_V = -1, end_H = -1;
        System.out.print("Enter the coordinate of "
                + ship.getName() + " (" + ship.getLength() + " cells): ");
        do {
            String start = INPUT.next().toUpperCase();
            try {
                start_V = convertToNumeric(start.charAt(0));
                if (start_V > 9 || start_V < 0) {
                    throw new IncorrectVCoordinate(start.charAt(0));
                }
                start_H = Integer.parseInt(start.substring(1)) - 1;
                if (start_H > 9 || start_H < 0) {
                    throw new IncorrectHCoordinate(start_H);
                }
                break;
            } catch (IncorrectVCoordinate | IncorrectHCoordinate i) {
                System.out.println(i.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage() + " incorrect HORIZONTAL " +
                        "coordinate." + "\nEnter from: 1 - 10 !!");
            }
        } while (true);
        do {
            String end = INPUT.next().toUpperCase();
            try {
                end_V = convertToNumeric(end.charAt(0));
                if (end_V > 9 || end_V < 0) {
                    throw new IncorrectVCoordinate(end.charAt(0));
                }
                end_H = Integer.parseInt(end.substring(1)) - 1;
                if (end_H > 9 || end_H < 0) {
                    throw new IncorrectHCoordinate(end_H);
                }
                break;
            } catch (IncorrectVCoordinate | IncorrectHCoordinate i) {
                System.out.println(i.getMessage());
            } catch (NumberFormatException e) {
                System.out.print(e.getMessage() + " incorrect HORIZONTAL " +
                        "coordinate." + "\nEnter from: 1 - 10 !!");
            }
        } while (true);
        if (start_H == end_H) {
            ship.setHorizontal(false);
            int[] coordinates = { start_H, end_H, Math.min(start_V, end_V), Math.max(start_V, end_V) };
            return coordinates;
        } else {
            ship.setHorizontal(true);
            int[] coordinates = { start_V, end_V, Math.min(start_H, end_H), Math.max(start_H, end_H) };
            return coordinates;
        }
    }

    private int[] getMissileCoordinates(String input) {
        int[] coordinates = new int[2];
        coordinates[0] = convertToNumeric(input.charAt(0));
        coordinates[1] = Integer.parseInt(input.substring(1)) - 1;
        return coordinates;
    }

    private boolean checkMissileCoordinates(int[] array) {
        if (array[0] > -1 && array[0] < 10) {
            return array[1] > -1 && array[1] < 10;
        } else
            return false;
    }

    private boolean checkShip() {
        for (Ship s : ships) {
            if (s.getSink()) {
                countOfSink++;
                ships.remove(s);
                return true;
            }
        }
        return false;
    }

    private boolean fire(int[] co) {
        int a = co[0];
        int b = co[1];
        if (battleGround[a][b] == 'O' || battleGround[a][b] == 'X') {
            battleGround[a][b] = 'X';
            battleGroundFOG[a][b] = 'X';
            return true;
        } else {
            battleGround[a][b] = 'M';
            battleGroundFOG[a][b] = 'M';
            return false;
        }
    }

    public void shootMissile() {
        // System.out.println("Take a shot!");
        String input = INPUT.next().toUpperCase();
        int[] coordinates = getMissileCoordinates(input);
        boolean retake = checkMissileCoordinates(coordinates);
        while (!retake) {
            System.out.print("Error! You entered the wrong coordinates! Try again: ");
            input = INPUT.next().toUpperCase();
            coordinates = getMissileCoordinates(input);
            retake = checkMissileCoordinates(coordinates);
        }
        boolean hit = fire(coordinates);
        boolean sink = checkShip();
        if (sink && countOfSink != 5) {
            System.out.println("You sank a ship! Specify a new target:");
        } else {
            if (hit) {
                System.out.println("You hit a ship!");
            } else {
                System.out.println("You missed!");
            }
        }
    }

    public void assignShips() {
        try {
            for (int i = 0; i < ships.size(); i++) {
                Ship s = ships.get(i);
                printBattleGround();
                int[] coordinates = takeCoordinates(s);
                int inputLength = findLength(coordinates);
                boolean length = s.getLength() == inputLength;
                boolean fits = fits(coordinates, s.isHorizontal());
                while (!(length) || !(fits)) {
                    if (inputLength == -1) {
                        System.out.println("Error! Wrong ship location! Try again: ");
                    } else if (!(length)) {
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
        } catch (Exception e) {
            e.getMessage();
        }
        printBattleGround();
    }

    public int getCountOfSink() {
        return this.countOfSink;
    }

}
