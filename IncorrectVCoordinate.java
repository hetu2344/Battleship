/**
 * This is custom Exception class to check the valid range
 * for Vertical coordinate in battleground.
 * Range for vertical coordinate: A - J.
 */
public class IncorrectVCoordinate extends Exception {
    public IncorrectVCoordinate(char c) {
        super(c + ": incorrect VERTICAL coordinate." +
                 "\nEnter from: A - J !!");
    }
}