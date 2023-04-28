public class Ship {
    private String name; //Name of ship
    private int length; //Length of ship
    private int[] location; //Location of ship in battleground
    private Battleground g; //Battleground in which ship is placed in
    private boolean horizontal; //Alignment of ship, horizontal or vertical

    /**
     * Constructor for Ship class
     *
     * @param name   Name of ship
     * @param length Length of ship
     * @param g      Battleground in which ship is placed in
     */
    public Ship(String name, int length, Battleground g) {
        this.name = name;
        this.length = length;
        this.g = g;
    }

    /**
     * Setter for horizontal variable in Ship class
     *
     * @param h Alignment of ship
     */
    public void setHorizontal(boolean h) {
        horizontal = h;
    }

    /**
     * Setter for location variable in Ship class
     *
     * @param co location of ship
     */
    public void setCoordinates(int[] co) {
        this.location = co;
    }

    /**
     * Getter for name in ship class
     *
     * @return name of ship
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for length in Ship class
     *
     * @return length of ship
     */
    public int getLength() {
        return length;
    }

    /**
     * Getter of horizontal variable in Ship class
     *
     * @return horizontal
     */
    public boolean isHorizontal() {
        return this.horizontal;
    }

    /**
     * Check is ship is destroyed or not.
     *
     * @return True if ship is destroyed,
     * False if ship is not destroyed.
     */
    public boolean getSink() {
        int a = location[2];
        int b = location[3];
        for(int i = a; i <= b; i++) {
            if(horizontal && (this.g.battleGround[location[0]][i] == 'O')) {
                return false;
            } else if(this.g.battleGround[i][location[0]] == 'O') {
                return false;
            }
        }
        return true;
    }
}
