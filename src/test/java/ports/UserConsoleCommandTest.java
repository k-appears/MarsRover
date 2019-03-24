package ports;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.HashSet;
import adapters.PlanetModifier;
import entities.Command;
import entities.Coordinate;
import entities.Direction;
import entities.Planet;
import entities.Rover;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserConsoleCommandTest {
    private static final int sizeX = 10;
    private static final int sizeY = 20;

    @Captor
    private ArgumentCaptor<Planet> planetCaptor;

    @Captor
    private ArgumentCaptor<Direction> directionCaptor;

    @Test(expected = IllegalArgumentException.class)
    public void createNewPlanetFromCommandsIllegalCommand() {
        Planet planet = Mockito.mock(Planet.class);
        PlanetModifier planetModifier = Mockito.mock(PlanetModifier.class);

        UserConsoleCommand userConsoleCommand = new UserConsoleCommand(planetModifier);

        userConsoleCommand.createNewPlanetFromCommand(planet, '@');
    }

    @Test
    public void createNewPlanetFromCommand_L_WhenRoverNorth() {
        Direction direction = Direction.N;
        Planet planet = initPlanet(direction);
        PlanetModifier planetModifier = Mockito.mock(PlanetModifier.class);

        UserConsoleCommand userConsoleCommand = new UserConsoleCommand(planetModifier);

        userConsoleCommand.createNewPlanetFromCommand(planet, Command.LEFT.value());

        Mockito.verify(planetModifier, Mockito.times(1))
               .roverChangesOrientation(planetCaptor.capture(), directionCaptor.capture());

        assertThat(directionCaptor.getValue()).isEqualTo(Direction.W);
        assertThat(planetCaptor.getValue()).isEqualTo(planet);
    }

    @Test
    public void createNewPlanetFromCommand_R_WhenRoverNorth() {
        Direction direction = Direction.N;
        Planet planet = initPlanet(direction);
        PlanetModifier planetModifier = Mockito.mock(PlanetModifier.class);

        UserConsoleCommand userConsoleCommand = new UserConsoleCommand(planetModifier);

        userConsoleCommand.createNewPlanetFromCommand(planet, Command.RIGHT.value());

        Mockito.verify(planetModifier, Mockito.times(1))
               .roverChangesOrientation(planetCaptor.capture(), directionCaptor.capture());

        assertThat(directionCaptor.getValue()).isEqualTo(Direction.E);
        assertThat(planetCaptor.getValue()).isEqualTo(planet);
    }

    @Test
    public void createNewPlanetFromCommand_F_WhenRoverNorthIn00() {
        Direction direction = Direction.N;
        Planet planet = initPlanet(direction);
        PlanetModifier planetModifier = Mockito.mock(PlanetModifier.class);

        UserConsoleCommand userConsoleCommand = new UserConsoleCommand(planetModifier);

        userConsoleCommand.createNewPlanetFromCommand(planet, Command.FORWARD.value());

        Mockito.verify(planetModifier, Mockito.times(1))
               .roverMoves(planetCaptor.capture(), directionCaptor.capture());

        assertThat(directionCaptor.getValue()).isEqualTo(Direction.N);
        assertThat(planetCaptor.getValue()).isEqualTo(planet);
    }

    @Test
    public void createNewPlanetFromCommand_B_WhenRoverNorthIn00() {
        Direction direction = Direction.N;
        Planet planet = initPlanet(direction);
        PlanetModifier planetModifier = Mockito.mock(PlanetModifier.class);

        UserConsoleCommand userConsoleCommand = new UserConsoleCommand(planetModifier);

        userConsoleCommand.createNewPlanetFromCommand(planet, Command.BACKWARD.value());

        Mockito.verify(planetModifier, Mockito.times(1))
               .roverMoves(planetCaptor.capture(), directionCaptor.capture());

        assertThat(directionCaptor.getValue()).isEqualTo(Direction.S);
        assertThat(planetCaptor.getValue()).isEqualTo(planet);
    }

    @Test
    public void nextMoveOptionalObstacle_F() {
        Direction direction = Direction.N;
        Planet planet = initPlanet(direction);
        PlanetModifier planetModifier = Mockito.mock(PlanetModifier.class);

        UserConsoleCommand userConsoleCommand = new UserConsoleCommand(planetModifier);

        userConsoleCommand.nextMoveOptionalObstacle(planet, Command.FORWARD.value());

        Mockito.verify(planetModifier, Mockito.times(1))
               .nextMoveOptionalObstacle(planetCaptor.capture(), directionCaptor.capture());

        assertThat(directionCaptor.getValue()).isEqualTo(Direction.N);
        assertThat(planetCaptor.getValue()).isEqualTo(planet);
    }

    @Test
    public void nextMoveOptionalObstacle_B() {
        Direction direction = Direction.N;
        Planet planet = initPlanet(direction);
        PlanetModifier planetModifier = Mockito.mock(PlanetModifier.class);

        UserConsoleCommand userConsoleCommand = new UserConsoleCommand(planetModifier);

        userConsoleCommand.nextMoveOptionalObstacle(planet, Command.BACKWARD.value());

        Mockito.verify(planetModifier, Mockito.times(1))
               .nextMoveOptionalObstacle(planetCaptor.capture(), directionCaptor.capture());

        assertThat(directionCaptor.getValue()).isEqualTo(Direction.S);
        assertThat(planetCaptor.getValue()).isEqualTo(planet);
    }

    @Test
    public void nextMoveOptionalObstacle_R() {
        Direction direction = Direction.N;
        Planet planet = initPlanet(direction);
        PlanetModifier planetModifier = Mockito.mock(PlanetModifier.class);

        UserConsoleCommand userConsoleCommand = new UserConsoleCommand(planetModifier);

        userConsoleCommand.nextMoveOptionalObstacle(planet, Command.RIGHT.value());

        Mockito.verify(planetModifier, Mockito.never())
               .nextMoveOptionalObstacle(Mockito.any(Planet.class), Mockito.any(Direction.class));
    }

    @Test
    public void nextMoveOptionalObstacle_L() {
        Direction direction = Direction.N;
        Planet planet = initPlanet(direction);
        PlanetModifier planetModifier = Mockito.mock(PlanetModifier.class);

        UserConsoleCommand userConsoleCommand = new UserConsoleCommand(planetModifier);

        userConsoleCommand.nextMoveOptionalObstacle(planet, Command.LEFT.value());

        Mockito.verify(planetModifier, Mockito.never())
               .nextMoveOptionalObstacle(Mockito.any(Planet.class), Mockito.any(Direction.class));
    }


    private Planet initPlanet(Direction direction) {
        Coordinate coordinateRover = new Coordinate(0, 0);
        Rover rover = new Rover(direction, coordinateRover);
        return new Planet(new HashSet<>(), rover, sizeX, sizeY);
    }

}