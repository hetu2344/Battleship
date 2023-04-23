
public class Player {
    private String name;
    private Game g;

    public Player(String name, Game g) {
        this.name = name;
        this.g = g;
    }

    public String getName() {
        return name;
    }

    public Game getGame(){
        return this.g;
    }
}
