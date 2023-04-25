public class IncorrectVCoordinate extends Exception {
    public IncorrectVCoordinate(char c) {
        super(c + ": incorrect VERTICAL coordinate." +
                 "\nEnter from: A - J !!");
    }
}