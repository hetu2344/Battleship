/**
 * This is custom Exception class to check the valid range
 * for Horizontal coordinate in battleground.
 * Range for horizontal coordinate: 1 - 10.
 */
public class IncorrectHCoordinate extends Exception {
    public IncorrectHCoordinate(int i) {
        super(i + " incorrect HORIZONTAL " +
        "coordinate." + "\nEnter from: 1 - 10 !!");
    }
}