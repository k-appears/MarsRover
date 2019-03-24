package adapters;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import entities.Coordinate;
import entities.Direction;
import entities.Obstacle;
import entities.Planet;
import entities.Rover;

import static entities.Coordinate.OUT_OF_BOUNDS;

public class PlanetModifier {

    public Planet roverMoves(Planet planet, Direction direction) {

        Direction oldDirection = planet.getRover().getDirection();

        int directionIndex = Direction.LIST.indexOf(planet.getRover().getDirection());
        int index = (directionIndex + 2) % Direction.LIST.size();

        List<Direction> allowedDirections = Arrays.asList(oldDirection, Direction.LIST.get(index));
        if (!allowedDirections.contains(direction)) {
            throw new IllegalArgumentException("New direction not in allowed 'Directions available': " +
                                               allowedDirections.stream().map(Direction::toString).collect(Collectors.joining(",")));
        }

        Coordinate oldRoverPosition = planet.getRover().getPosition();

        int oldX = oldRoverPosition.getX();
        int oldY = oldRoverPosition.getY();

        Coordinate newCoordinate = getNewCoordinate(planet, direction, oldX, oldY);

        if (planet.getObstacles().contains(new Obstacle(newCoordinate))) {
            return planet;
        }
        // If rover moves backwards or forward, its direction does not change
        Rover newRover = new Rover(oldDirection, newCoordinate);

        return new Planet(planet.getObstacles(), newRover, planet.getLimitX(), planet.getLimitY());
    }

    public Planet roverChangesOrientation(Planet planet, Direction direction) {
        Coordinate oldRoverPosition = planet.getRover().getPosition();
        Rover newRover = new Rover(direction, oldRoverPosition);

        return new Planet(planet.getObstacles(), newRover, planet.getLimitX(), planet.getLimitY());
    }

    public Optional<Obstacle> nextMoveOptionalObstacle(Planet planet, Direction direction) {
        Coordinate newCoordinate = getNewCoordinate(planet, direction, planet.getRover().getPosition().getX(), planet.getRover().getPosition().getY());
        Obstacle obstacle = new Obstacle(newCoordinate);
        if (planet.getObstacles().contains(obstacle)) {
            return Optional.of(obstacle);
        }
        return Optional.empty();
    }

    private Coordinate getNewCoordinate(Planet planet, Direction direction, int oldX, int oldY) {
        int newX = OUT_OF_BOUNDS;
        int newY = OUT_OF_BOUNDS;
        if (Direction.N == direction) {
            newX = oldX;
            newY = (oldY + 1) % planet.getLimitY();
        } else if (Direction.S == direction) {
            newX = oldX;
            newY = oldY - 1 < 0 ? planet.getLimitY() - 1 : oldY - 1;
        } else if (Direction.E == direction) {
            newX = (oldX + 1) % planet.getLimitX();
            newY = oldY;
        } else if (Direction.W == direction) {
            newX = oldX - 1 < 0 ? planet.getLimitX() - 1 : oldX - 1;
            newY = oldY;
        }
        return new Coordinate(newX, newY);
    }
}
