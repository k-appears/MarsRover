package entities;

public class Rover {

    private final Direction direction;
    private final Coordinate position;

    public Rover(Direction direction, Coordinate position) {
        this.direction = direction;
        this.position = position;
    }

    public Coordinate getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Rover{" +
               "direction=" + direction +
               ", position=" + position +
               '}';
    }
}
