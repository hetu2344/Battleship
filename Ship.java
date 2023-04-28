
public class Ship {
    private final int id;
    private String name;
    private int length;
    private int[] location;
    private Game g;
    private boolean horizontal;
    private static int count = 0;

    public Ship(String name, int length, Game g) {
        this.id = count++;
        this.name = name;
        this.length = length;
        this.g = g;
    }

    public void setHorizontal(boolean h) {
        horizontal = h;
    }

    public void setCoordinates(int[] co) {
        this.location = co;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public boolean isHorizontal() {
        return this.horizontal;
    }

    public boolean getSink() {
        int a = location[2];
        int b = location[3];
        for (int i = a; i <= b; i++) {
            if (horizontal && (this.g.battleGround[location[0]][i] == 'O')) {
                return false;
            } else if (this.g.battleGround[i][location[0]] == 'O') {
                return false;
            }
        }
        // int x1 = location[0];
        // int y1 = location[1];
        // int x2 = location[2];
        // int y2 = location[3];
        // if (x1 == x2) {
        // if (y1 > y2) {
        // for (int i = y2; i <= y1; i++) {
        // if (this.g.battleGround[x1][i] == 'O') {
        // return false;
        // }
        // }
        // } else {
        // for (int i = y1; i <= y2; i++) {
        // if (this.g.battleGround[x1][i] == 'O') {
        // return false;
        // }
        // }
        // }
        // } else {
        // if (x1 > x2) {
        // for (int i = x2; i <= x1; i++) {
        // if (this.g.battleGround[i][y1] == 'O') {
        // return false;
        // }
        // }
        // } else {
        // for (int i = x1; i <= x2; i++) {
        // if (this.g.battleGround[i][y1] == 'O') {
        // return false;
        // }
        // }
        // }
        // }
        return true;
    }
}
