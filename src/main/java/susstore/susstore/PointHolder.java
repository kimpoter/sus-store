package susstore.susstore;

public interface PointHolder {
    public static final String POINT_CURRENCY = "IDR";
    public static final double POINT_DEFAULT_RATE = 0.01;
    public int nominalToPoint(Nominal nominal);
    public void addPoint(int point);
    public void usePoint(int point);
    public void setPoint(int point);
    public int getPoint();

}

class InvalidPointValueException extends RuntimeException {
    public InvalidPointValueException() {
        super("Point must not be of negative value!");
    }
}

class InsufficientPointException extends RuntimeException {
    public InsufficientPointException() {
        super("Not enough points to use!");
    }
}
