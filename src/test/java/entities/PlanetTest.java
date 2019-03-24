package entities;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class PlanetTest {

    private int sizeX = 10;
    private int sizeY = 20;

    @Test(expected = IllegalArgumentException.class)
    public void createPlanetWithInvalidRoverPositionX() {
        Rover rover = initRover(sizeX, 0);

        Set<Obstacle> obstacles = new HashSet<>();
        new Planet(obstacles, rover, sizeX, sizeY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPlanetWithInvalidRoverPositionY() {
        Rover rover = initRover(0, sizeY);

        Set<Obstacle> obstacles = new HashSet<>();
        new Planet(obstacles, rover, sizeX, sizeY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPlanetWithInvalidRoverPositionXAndY() {
        Rover rover = initRover(sizeX, sizeY);

        Set<Obstacle> obstacles = new HashSet<>();
        new Planet(obstacles, rover, sizeX, sizeY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPlanetWithInvalidObstacleX() {
        Rover rover = initRover(0, 0);

        Set<Obstacle> obstacles = new HashSet<>();
        obstacles.add(new Obstacle(new Coordinate(sizeX, 0)));
        new Planet(obstacles, rover, sizeX, sizeY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPlanetWithInvalidObstacleY() {
        Rover rover = initRover(0, 0);

        Set<Obstacle> obstacles = new HashSet<>();
        obstacles.add(new Obstacle(new Coordinate(0, sizeY)));
        new Planet(obstacles, rover, sizeX, sizeY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPlanetWithInvalidObstacleXAndY() {
        Rover rover = initRover(0, 0);

        Set<Obstacle> obstacles = new HashSet<>();
        obstacles.add(new Obstacle(new Coordinate(sizeX, sizeY)));
        new Planet(obstacles, rover, sizeX, sizeY);
    }

    @Test
    public void createPlanetWithRoverIn00AndObstacle01() {
        Rover rover = initRover(0, 0);

        Set<Obstacle> obstacles = new HashSet<>();
        obstacles.add(new Obstacle(new Coordinate(0, 1)));
        new Planet(obstacles, rover, sizeX, sizeY);
    }

    @Test
    public void createPlanetWithRoverIn00AndObstacle10() {
        Rover rover = initRover(0, 0);

        Set<Obstacle> obstacles = new HashSet<>();
        obstacles.add(new Obstacle(new Coordinate(0, 1)));
        new Planet(obstacles, rover, sizeX, sizeY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPlanetWithRoverInObstacle() {
        Rover rover = initRover(0, 0);

        Set<Obstacle> obstacles = new HashSet<>();
        obstacles.add(new Obstacle(new Coordinate(0, 0)));
        new Planet(obstacles, rover, sizeX, sizeY);
    }

    private Rover initRover(int x, int y) {
        Direction direction = Direction.E;
        Coordinate roverInitialCoordinate = new Coordinate(x, y);
        return new Rover(direction, roverInitialCoordinate);
    }
}