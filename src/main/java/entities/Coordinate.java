package entities;

import java.util.Objects;

public class Coordinate {

    public static final int OUT_OF_BOUNDS = -1;
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof Coordinate)) { return false; }
        Coordinate that = (Coordinate)o;
        return getX() == that.getX() &&
               getY() == that.getY();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "Coordinate{" +
               "x=" + x +
               ", y=" + y +
               '}';
    }
}
