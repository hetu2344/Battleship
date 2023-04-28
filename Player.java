public class Player {
    private String name; //Name of player
    private Battleground g; //Battleground of player

    /**
     * Constructor for Player class
     * @param name Name of Player
     * @param g Battleground of Player
     */
    public Player(String name, Battleground g) {
        this.name = name;
        this.g = g;
    }

    /**
     * Getter for name variable in Player class
     * @return name of Player
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for game variable in Player class
     * @return The battleground of Player
     */
    public Battleground getGame(){
        return this.g;
    }
}
