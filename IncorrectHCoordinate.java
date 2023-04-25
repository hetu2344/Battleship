public class IncorrectHCoordinate extends Exception {
    public IncorrectHCoordinate(int i) {
        super(i + " incorrect HORIZONTAL " +
        "coordinate." + "\nEnter from: 1 - 10 !!");
    }
}