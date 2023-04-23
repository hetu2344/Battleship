
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    final private String row = "ABCDEFGHIJ";
    final private String column = "  1 2 3 4 5 6 7 8 9 10";
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
    private final Scanner s = new Scanner(System.in);
    ArrayList<Ship> ships = new ArrayList<>();
    private int countOfSink = 0;

    public void setShip(ArrayList<Ship> s) {
        this.ships = s;
    }

    public void printBattleGround() {
        System.out.println(column);
        for (int i = 0; i < battleGround.length; i++) {
            System.out.print(row.charAt(i) + " ");
            for (int j = 0; j < battleGround[i].length; j++) {
                System.out.print(battleGround[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printBattleGroundFOG() {
        System.out.println(column);
        for (int i = 0; i < battleGroundFOG.length; i++) {
            System.out.print(row.charAt(i) + " ");
            for (int j = 0; j < battleGroundFOG[i].length; j++) {
                System.out.print(battleGroundFOG[i][j] + " ");
            }
            System.out.println();
        }
    }

    public StringBuilder printBattleGroundFOGS() {
        StringBuilder s1 = new StringBuilder(column);
        s1.append(System.lineSeparator());
        for (int i = 0; i < battleGroundFOG.length; i++) {
            s1.append(row.charAt(i)).append(" ");
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
        if (a == c) {
            return b > d ? b - d + 1 : d - b + 1;
        } else if (b == d) {
            return a > c ? a - c + 1 : c - a + 1;
        } else if (a == -1 || c == -1) {
            return -1;
        } else {
            return -1;
        }
    }

    private boolean empty(int a, int b) {
        return battleGround[a][b] == '~';
    }

    public boolean fits(int[] co) {
        int a = co[0];
        int b = co[1];
        int c = co[2];
        int d = co[3];
        boolean Svalid;
        boolean Evalid;

        switch (a) {
            case 0:
                switch (b) {
                    case 0:
                        Svalid = empty(a + 1, b) && empty(a, b + 1);
                        break;
                    case 9:
                        Svalid = empty(a, b - 1) && empty(a + 1, b);
                        break;
                    default:
                        Svalid = empty(a + 1, b) && empty(a, b - 1) && empty(a, b + 1);
                        break;
                }
                break;
            case 9:
                switch (b) {
                    case 0:
                        Svalid = empty(a - 1, b) && empty(a, b + 1);
                        break;
                    case 9:
                        Svalid = empty(a - 1, b) && empty(a, b - 1);
                        break;
                    default:
                        Svalid = empty(a - 1, b) && empty(a, b - 1) && empty(a, b + 1);
                        break;
                }
                break;
            default:
                switch (b) {
                    case 0:
                        Svalid = empty(a - 1, b) && empty(a, b + 1) && empty(a + 1, b);
                        break;
                    case 9:
                        Svalid = empty(a - 1, b) && empty(a, b - 1) && empty(a + 1, b);
                        break;
                    default:
                        Svalid = empty(a - 1, b) && empty(a + 1, b) && empty(a, b + 1) && empty(a, b - 1);
                        break;
                }
                break;
        }

        switch (c) {
            case 0:
                switch (d) {
                    case 0:
                        Evalid = empty(c + 1, d) && empty(c, d + 1);
                        break;
                    case 9:
                        Evalid = empty(c, d - 1) && empty(c + 1, d);
                        break;
                    default:
                        Evalid = empty(c + 1, d) && empty(c, d - 1) && empty(c, d + 1);
                        break;
                }
                break;
            case 9:
                switch (d) {
                    case 0:
                        Evalid = empty(c - 1, d) && empty(c, d + 1);
                        break;
                    case 9:
                        Evalid = empty(c - 1, d) && empty(c, d - 1);
                        break;
                    default:
                        Evalid = empty(c - 1, d) && empty(c, d - 1) && empty(c, d + 1);
                        break;
                }
                break;
            default:
                switch (d) {
                    case 0:
                        Evalid = empty(c + 1, d) && empty(c, d + 1) && empty(c - 1, d);
                        break;
                    case 9:
                        Evalid = empty(c - 1, d) && empty(c, d - 1) && empty(c + 1, d);
                        break;
                    default:
                        Evalid = empty(c - 1, d) && empty(c + 1, d) && empty(c, d - 1) && empty(c, d + 1);
                        break;

                }
                break;
        }
        // int a = co[0];
        // int b = co[1];
        // int c = co[2];
        // int d = co[3];
        // boolean Svalid;
        // boolean Evalid;
        // if (a == 0 && b == 0) {
        // Svalid = empty(a + 1, b) && empty(a, b + 1);
        // } else if (a == 9 && b == 9) {
        // Svalid = empty(a - 1, b) && empty(a, b - 1);
        // } else if (a == 0 && b == 9) {
        // Svalid = empty(a, b - 1) && empty(a + 1, b);
        // } else if (a == 9 && b == 0) {
        // Svalid = empty(a - 1, b) && empty(a, b + 1);
        // } else if (a == 0) {
        // Svalid = empty(a + 1, b) && empty(a, b - 1) && empty(a, b + 1);
        // } else if (b == 0) {
        // Svalid = empty(a + 1, b) && empty(a - 1, b) && empty(a, b + 1);
        // } else if (a == 9) {
        // Svalid = empty(a - 1, b) && empty(a, b - 1) && empty(a, b + 1);
        // } else if (b == 9) {
        // Svalid = empty(a - 1, b) && empty(a + 1, b) && empty(a, b - 1);
        // } else {
        // Svalid = empty(a - 1, b) && empty(a + 1, b) && empty(a, b + 1) && empty(a, b
        // - 1);
        // }
        // if (c == 0 && d == 0) {
        // Evalid = empty(c + 1, d) && empty(c, d + 1);
        // } else if (c == 9 && d == 9) {
        // Evalid = empty(c - 1, d) && empty(c, d - 1);
        // } else if (c == 0 && d == 9) {
        // Evalid = empty(c, d - 1) && empty(c + 1, d);
        // } else if (c == 9 && d == 0) {
        // Evalid = empty(c - 1, d) && empty(c, d + 1);
        // } else if (c == 0) {
        // Evalid = empty(c + 1, d) && empty(c, d - 1) && empty(c, d + 1);
        // } else if (d == 0) {
        // Evalid = empty(c + 1, d) && empty(c - 1, d) && empty(c, d + 1);
        // } else if (c == 9) {
        // Evalid = empty(c - 1, d) && empty(c, d - 1) && empty(c, d + 1);
        // } else if (d == 9) {
        // Evalid = empty(c - 1, d) && empty(c + 1, d) && empty(c, d - 1);
        // } else {
        // Evalid = empty(c - 1, d) && empty(c + 1, d) && empty(c, d + 1) && empty(c, d
        // - 1);
        // }
        return Svalid && Evalid;
    }

    private int convertToNumeric(char a) {
        return a - 65;
    }

    private void fillShip(int[] co, int x) {
        ships.get(x).setCoordinates(co);
        int a = co[0];
        int b = co[1];
        int c = co[2];
        int d = co[3];
        if (a == c) {
            if (b < d) {
                for (int i = b; i <= d; i++) {
                    battleGround[a][i] = 'O';
                }
            } else {
                for (int i = d; i <= b; i++) {
                    battleGround[a][i] = 'O';
                }
            }
        } else {
            if (a < c) {
                for (int i = a; i <= c; i++) {
                    battleGround[i][b] = 'O';
                }
            } else {
                for (int i = c; i <= a; i++) {
                    battleGround[i][b] = 'O';
                }
            }
        }
    }

    private int[] getCoordinates(String shipName, int shipLength) {
        System.out.println("Enter the coordinate of " + shipName + " (" + shipLength + " cells): ");
        String start = s.next().toUpperCase();
        String end = s.next().toUpperCase();
        int start2 = Integer.parseInt(start.substring(1)) - 1;
        int end2 = Integer.parseInt(end.substring(1)) - 1;
        int[] coordinates = { 0, start2, 0, end2 };
        int start1 = convertToNumeric(start.charAt(0));
        int end1 = convertToNumeric(end.charAt(0));
        coordinates[0] = start1;
        coordinates[2] = end1;
        return coordinates;
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
        String input = s.next().toUpperCase();
        int[] coordinates = getMissileCoordinates(input);
        boolean retake = checkMissileCoordinates(coordinates);
        while (!retake) {
            System.out.println("Error! You entered the wrong coordinates! Try again: ");
            input = s.next().toUpperCase();
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

    public void startGame() {
        for (int i = 0; i < ships.size(); i++) {
            int shipLength = ships.get(i).getLength();
            String shipName = ships.get(i).getName();
            printBattleGround();
            int[] coordinates = getCoordinates(shipName, shipLength);
            int inputLength = findLength(coordinates);
            boolean length = shipLength == inputLength;
            boolean fits = fits(coordinates);
            while (!(length) || !(fits)) {
                if (inputLength == -1) {
                    System.out.println("Error! Wrong ship location! Try again: ");
                } else if (!(length)) {
                    System.out.println("Error! Wrong length of " + shipName + " Try again: ");
                } else {
                    System.out.println("Error! You placed it to close to another one. Try again: ");
                }
                coordinates = getCoordinates(shipName, shipLength);
                inputLength = findLength(coordinates);
                length = shipLength == inputLength;
                fits = fits(coordinates);
            }
            fillShip(coordinates, i);
        }
        printBattleGround();
        // System.out.println("The game starts!");
        // printBattleGroundFOG();
        // shootMissile();
        // System.out.println("You sank the last ship. You won. Congratulations!");
    }

    public int getCountOfSink() {
        return this.countOfSink;
    }

}
