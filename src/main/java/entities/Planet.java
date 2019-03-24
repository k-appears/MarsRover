package entities;

import java.util.Collections;
import java.util.Set;

public class Planet {

    private final Set<Obstacle> obstacles;
    private final Coordinate limitX;
    private final Coordinate limitY;
    private final Rover rover;

    public Planet(Set<Obstacle> obstacles, Rover rover, int sizeX, int sizeY) {
        if (rover.getPosition().getX() >= sizeX || rover.getPosition().getY() >= sizeY) {
            throw new IllegalArgumentException(String.format("%s must be lower than maps' limits: X '%d', Y '%d'", rover.toString(), sizeX, sizeY));
        }
        obstacles.forEach(obstacle -> {
            if (obstacle.getCoordinate().getY() >= sizeY || obstacle.getCoordinate().getX() >= sizeX) {
                throw new IllegalArgumentException(
                    String.format("%s must be lower than maps' limits: X '%d', Y '%d'", obstacle.toString(), sizeX, sizeY));
            }
            if (rover.getPosition().getX() == obstacle.getCoordinate().getX() && rover.getPosition().getY() == obstacle.getCoordinate().getY()) {
                throw new IllegalArgumentException(
                    String.format("%s can not be in obstacle %s", rover.toString(), obstacle.toString()));
            }
        });
        this.obstacles = obstacles;

        this.limitX = new Coordinate(sizeX, 0);
        this.limitY = new Coordinate(0, sizeY);
        this.rover = rover;
    }

    public Rover getRover() {
        return new Rover(rover.getDirection(), rover.getPosition());
    }

    public Set<Obstacle> getObstacles() {
        return Collections.unmodifiableSet(obstacles);
    }

    public int getLimitX() {
        return limitX.getX();
    }

    public int getLimitY() {
        return limitY.getY();
    }
}
