package ports;

import java.util.Optional;
import adapters.PlanetModifier;
import entities.Command;
import entities.Direction;
import entities.Obstacle;
import entities.Planet;

public class UserConsoleCommand {

    private final PlanetModifier planetModifier;

    public UserConsoleCommand(PlanetModifier planetModifier) {
        this.planetModifier = planetModifier;
    }

    public Planet createNewPlanetFromCommand(Planet planet, char commandChar) {
        Command command = Command.fromName(commandChar);
        if (command == Command.LEFT || command == Command.RIGHT) {
            int directionIndex = Direction.LIST.indexOf(planet.getRover().getDirection());
            if (command == Command.RIGHT) {
                int index = (directionIndex + 1) % Direction.LIST.size();
                return planetModifier.roverChangesOrientation(planet, Direction.LIST.get(index));
            } else {
                int index = (directionIndex - 1) < 0 ? Direction.LIST.size() - 1 : directionIndex - 1;
                return planetModifier.roverChangesOrientation(planet, Direction.LIST.get(index));
            }
        } if (command == Command.FORWARD) {
            return planetModifier.roverMoves(planet, planet.getRover().getDirection());
        } else {
            return planetModifier.roverMoves(planet, getReverserDirection(planet));
        }
    }

    public Optional<Obstacle> nextMoveOptionalObstacle(Planet planet, char commandChar) {
        Command command = Command.fromName(commandChar);
        if (command == Command.FORWARD) {
            return planetModifier.nextMoveOptionalObstacle(planet, planet.getRover().getDirection());
        } else if (command == Command.BACKWARD) {
            return planetModifier.nextMoveOptionalObstacle(planet, getReverserDirection(planet));
        }
        return Optional.empty();
    }

    private Direction getReverserDirection(Planet planet) {
        int directionIndex = Direction.LIST.indexOf(planet.getRover().getDirection());
        return Direction.LIST.get((directionIndex + 2) % Direction.LIST.size());
    }
}
