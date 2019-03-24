package adapters;

import org.junit.Test;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import entities.Coordinate;
import entities.Direction;
import entities.Obstacle;
import entities.Planet;
import entities.Rover;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlanetModifierTest {
    private static final int sizeX = 10;
    private static final int sizeY = 20;

    @Test
    public void roverMovesFromNorthDirectionToNorth_withPosition00() {
        Planet planet = createPlanetRoverIn00NoObstacles(Direction.N);
        PlanetModifier planetModifier = new PlanetModifier();

        Planet planet1 = planetModifier.roverMoves(planet, Direction.N);

        assertThat(planet1.getRover().getDirection()).isEqualTo(Direction.N);

        assertThat(planet1.getRover().getPosition()).isEqualTo(
            new Coordinate(0, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void roverMovesFromNorthDirectionToEast_withPosition00_exception() {
        Planet planet = createPlanetRoverIn00NoObstacles(Direction.N);
        PlanetModifier planetModifier = new PlanetModifier();

        planetModifier.roverMoves(planet, Direction.E);
    }

    @Test
    public void roverMovesWestFromLeftLimit() {
        Planet planet = createPlanetRoverIn00NoObstacles(Direction.W);
        PlanetModifier planetModifier = new PlanetModifier();

        Planet planet1 = planetModifier.roverMoves(planet, Direction.W);

        assertThat(planet1.getRover().getDirection()).isEqualTo(Direction.W);

        assertThat(planet1.getRover().getPosition()).isEqualTo(
            new Coordinate(sizeX - 1, 0));
    }

    @Test
    public void roverMovesBackwards_FromNorthDirection_throughLowerLimit() {
        Planet planet = createPlanetRoverIn00NoObstacles(Direction.N);
        PlanetModifier planetModifier = new PlanetModifier();

        Planet planet1 = planetModifier.roverMoves(planet, Direction.S);

        assertThat(planet1.getRover().getDirection()).isEqualTo(Direction.N);

        assertThat(planet1.getRover().getPosition()).isEqualTo(
            new Coordinate(0, sizeY - 1));
    }

    @Test
    public void roverMovesNorthFromUpperLimit() {
        Planet planet = createPlanetNoObstaclesRoverIn(0, sizeY - 1, Direction.N);
        PlanetModifier planetModifier = new PlanetModifier();

        Planet planet1 = planetModifier.roverMoves(planet, Direction.N);

        assertThat(planet1.getRover().getDirection()).isEqualTo(Direction.N);

        assertThat(planet1.getRover().getPosition()).isEqualTo(
            new Coordinate(0, 0));
    }

    @Test
    public void roverMovesEastFromRightLimit() {
        Planet planet = createPlanetNoObstaclesRoverIn(sizeX - 1, 0, Direction.E);
        PlanetModifier planetModifier = new PlanetModifier();
        Planet planet1 = planetModifier.roverMoves(planet, Direction.E);

        assertThat(planet1.getRover().getDirection()).isEqualTo(Direction.E);

        assertThat(planet1.getRover().getPosition()).isEqualTo(
            new Coordinate(0, 0));
    }

    @Test
    public void roverMovesToNorthWithObstacle() {
        Direction direction = Direction.N;
        Coordinate roverInitialCoordinate = new Coordinate(0, 0);
        Rover rover = new Rover(direction, roverInitialCoordinate);

        Set<Obstacle> obstacles = new HashSet<>();
        obstacles.add(new Obstacle(new Coordinate(0, 1)));

        Planet planet = new Planet(obstacles, rover, sizeX, sizeY);
        PlanetModifier planetModifier = new PlanetModifier();

        Planet planet1 = planetModifier.roverMoves(planet, Direction.N);

        assertThat(planet1.getRover().getDirection()).isEqualTo(Direction.N);

        assertThat(planet1.getRover().getPosition()).isEqualTo(
            new Coordinate(0, 0));
    }

    @Test
    public void roverChangesOrientationFromNorthToEast() {
        Planet planet = createPlanetRoverIn00NoObstacles(Direction.N);
        PlanetModifier planetModifier = new PlanetModifier();

        Planet planet1 = planetModifier.roverChangesOrientation(planet, Direction.E);

        assertThat(planet1.getRover().getDirection()).isEqualTo(Direction.E);

        assertThat(planet1.getRover().getPosition()).isEqualTo(
            new Coordinate(0, 0));
    }

    @Test
    public void roverChangesOrientationFromEastToSouth() {
        Planet planet = createPlanetRoverIn00NoObstacles(Direction.E);
        PlanetModifier planetModifier = new PlanetModifier();

        Planet planet1 = planetModifier.roverChangesOrientation(planet, Direction.S);

        assertThat(planet1.getRover().getDirection()).isEqualTo(Direction.S);

        assertThat(planet1.getRover().getPosition()).isEqualTo(
            new Coordinate(0, 0));
    }

    @Test
    public void roverChangesOrientationFromSouthToWest() {
        Planet planet = createPlanetRoverIn00NoObstacles(Direction.S);
        PlanetModifier planetModifier = new PlanetModifier();

        Planet planet1 = planetModifier.roverChangesOrientation(planet, Direction.W);

        assertThat(planet1.getRover().getDirection()).isEqualTo(Direction.W);

        assertThat(planet1.getRover().getPosition()).isEqualTo(
            new Coordinate(0, 0));
    }

    @Test
    public void roverChangesOrientationFromWestToNorth() {
        Planet planet = createPlanetRoverIn00NoObstacles(Direction.W);
        PlanetModifier planetModifier = new PlanetModifier();

        Planet planet1 = planetModifier.roverChangesOrientation(planet, Direction.N);

        assertThat(planet1.getRover().getDirection()).isEqualTo(Direction.N);

        assertThat(planet1.getRover().getPosition()).isEqualTo(
            new Coordinate(0, 0));
    }

    @Test
    public void roverChangesOrientationFromNorthToWest() {
        Planet planet = createPlanetRoverIn00NoObstacles(Direction.N);
        PlanetModifier planetModifier = new PlanetModifier();

        Planet planet1 = planetModifier.roverChangesOrientation(planet, Direction.W);

        assertThat(planet1.getRover().getDirection()).isEqualTo(Direction.W);

        assertThat(planet1.getRover().getPosition()).isEqualTo(
            new Coordinate(0, 0));
    }

    @Test
    public void roverChangesOrientationFromWestToSouth() {
        Planet planet = createPlanetRoverIn00NoObstacles(Direction.W);
        PlanetModifier planetModifier = new PlanetModifier();

        Planet planet1 = planetModifier.roverChangesOrientation(planet, Direction.S);

        assertThat(planet1.getRover().getDirection()).isEqualTo(Direction.S);

        assertThat(planet1.getRover().getPosition()).isEqualTo(
            new Coordinate(0, 0));
    }

    @Test
    public void roverChangesOrientationFromSouthToEast() {
        Planet planet = createPlanetRoverIn00NoObstacles(Direction.S);
        PlanetModifier planetModifier = new PlanetModifier();

        Planet planet1 = planetModifier.roverChangesOrientation(planet, Direction.E);

        assertThat(planet1.getRover().getDirection()).isEqualTo(Direction.E);

        assertThat(planet1.getRover().getPosition()).isEqualTo(
            new Coordinate(0, 0));
    }

    @Test
    public void roverChangesOrientationFromEastToNorth() {
        Planet planet = createPlanetRoverIn00NoObstacles(Direction.E);
        PlanetModifier planetModifier = new PlanetModifier();

        Planet planet1 = planetModifier.roverChangesOrientation(planet, Direction.N);

        assertThat(planet1.getRover().getDirection()).isEqualTo(Direction.N);

        assertThat(planet1.getRover().getPosition()).isEqualTo(
            new Coordinate(0, 0));
    }

    private Planet createPlanetRoverIn00NoObstacles(Direction direction) {
        Coordinate roverInitialCoordinate = new Coordinate(0, 0);
        Rover rover = new Rover(direction, roverInitialCoordinate);

        Set<Obstacle> obstacles = new HashSet<>();

        return new Planet(obstacles, rover, sizeX, sizeY);
    }

    private Planet createPlanetNoObstaclesRoverIn(int x, int y, Direction direction) {
        Coordinate roverInitialCoordinate = new Coordinate(x, y);
        Rover rover = new Rover(direction, roverInitialCoordinate);

        Set<Obstacle> obstacles = new HashSet<>();

        return new Planet(obstacles, rover, sizeX, sizeY);
    }

    @Test
    public void nextMoveOptionalObstacle() {
        Direction direction = Direction.N;
        Coordinate roverInitialCoordinate = new Coordinate(0, 0);
        Rover rover = new Rover(direction, roverInitialCoordinate);

        Set<Obstacle> obstacles = new HashSet<>();
        Obstacle obstacle = new Obstacle(new Coordinate(0, 1));
        obstacles.add(obstacle);

        Planet planet = new Planet(obstacles, rover, sizeX, sizeY);
        PlanetModifier planetModifier = new PlanetModifier();

        Optional<Obstacle> obstacleOptional = planetModifier.nextMoveOptionalObstacle(planet, Direction.N);

        assertThat(obstacleOptional).isPresent();

        assertThat(obstacleOptional).get().isEqualToComparingFieldByField(obstacle);
    }

    @Test
    public void nextMoveOptionalNoObstacle() {
        Direction direction = Direction.E;
        Coordinate roverInitialCoordinate = new Coordinate(0, 0);
        Rover rover = new Rover(direction, roverInitialCoordinate);

        Set<Obstacle> obstacles = new HashSet<>();
        Obstacle obstacle = new Obstacle(new Coordinate(0, 1));
        obstacles.add(obstacle);

        Planet planet = new Planet(obstacles, rover, sizeX, sizeY);
        PlanetModifier planetModifier = new PlanetModifier();

        Optional<Obstacle> obstacleOptional = planetModifier.nextMoveOptionalObstacle(planet, Direction.E);

        assertThat(obstacleOptional).isNotPresent();
    }
}