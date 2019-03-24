package entities;

import java.util.Objects;

public class Obstacle {
    private final Coordinate coordinate;

    public Obstacle(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "Obstacle{" +
               coordinate +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof Obstacle)) { return false; }
        Obstacle obstacle = (Obstacle)o;
        return Objects.equals(getCoordinate(), obstacle.getCoordinate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCoordinate());
    }
}
