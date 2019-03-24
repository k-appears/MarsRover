import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import adapters.PlanetModifier;
import entities.Command;
import entities.Coordinate;
import entities.Direction;
import entities.Obstacle;
import entities.Planet;
import entities.Rover;
import ports.UserConsoleCommand;

public class MarsRover {

    private static final int MAX = 2147395600; // 2147395600 < Integer.MAX_VALUE

    public static void main(String[] args) {

        PlanetModifier planetModifier = new PlanetModifier();
        UserConsoleCommand userConsoleCommand = new UserConsoleCommand(planetModifier);

        try (Scanner reader = new Scanner(System.in)) {
            System.out.println("Initializing map, num. max tiles is " + MAX + " ie: 46340 * 46340");
            System.out.println(" Insert horizontal map size, min 1, max " + MAX);
            int sizeX = getIntFromInput(reader, 1, MAX);
            System.out.println(" Insert vertical map size, min 1, max " + (MAX / sizeX));
            int sizeY = getIntFromInput(reader, 1, MAX / sizeX);

            boolean validInit = false;
            Planet planet = null; // Initialization required
            while (!validInit) {
                System.out.println(" Insert number of different obstacles, min 0, max " + sizeX * sizeY);
                int obstaclesNumber = getIntFromInput(reader, 0, sizeX * sizeY);

                int x;
                int y;
                Set<Obstacle> obstacles = new HashSet<>(obstaclesNumber);
                for (int i = 0; i < obstaclesNumber; i++) {
                    System.out.println("  Obstacle " + i);
                    System.out.println("  Insert obstacle coordinate x (horizontal), min 0, max " + (sizeX - 1));
                    x = getIntFromInput(reader, 0, sizeX - 1);
                    System.out.println("  Insert obstacle coordinate y (vertical), min 0, max " + (sizeY - 1));
                    y = getIntFromInput(reader, 0, sizeY - 1);
                    Obstacle obstacle = new Obstacle(new Coordinate(x, y));
                    obstacles.add(obstacle);
                }

                System.out.println("Initializing Rover");
                System.out.println(" Insert horizontal initial rover position, min 0, max " + (sizeX - 1));
                int roverX = getIntFromInput(reader, 0, sizeX - 1);
                System.out.println(" Insert vertical initial rover position, min 0, max " + (sizeY - 1));
                int roverY = getIntFromInput(reader, 0, sizeY - 1);
                System.out.println(" Insert initial rover directions: " + Direction.LIST.stream()
                                                                                        .map(Object::toString)
                                                                                        .collect(Collectors.joining(",")));
                Direction roverDirection = getDirectionFromInput(reader); //n = north, e = east, w = west, s = south
                try {
                    planet = new Planet(obstacles, new Rover(roverDirection, new Coordinate(roverX, roverY)), sizeX, sizeY);
                    validInit = true;
                } catch (IllegalArgumentException iae) {
                    System.err.println("Invalid input values: " + iae.getMessage());
                }
            }
            // planet is not null
            while (true) {
                System.out.println("Press one of the following keys (l = turn left, r = turn right, f = forward, b = backward):");
                char command = getCommandFromInput(reader);
                Optional<Obstacle> obstacleOptional = userConsoleCommand.nextMoveOptionalObstacle(planet, command);
                obstacleOptional.ifPresent(obstacle -> System.out.println(String.format("Moving Rover to %s encounters %s", command, obstacle)));
                planet = userConsoleCommand.createNewPlanetFromCommand(planet, command);
                Rover afterRover = planet.getRover();
                System.out.println(String.format("* Rover is at x:%d y:%d facing:%s", afterRover.getPosition().getX(),
                                                 afterRover.getPosition().getY(), afterRover.getDirection().name()));
            }
        } catch (NoSuchElementException ex) {
            System.out.println("Closing program: No able to collect more elements");
        }
    }

    private static int getIntFromInput(Scanner reader, int min, int max) {
        int result = -1;
        boolean found = false;
        while (!found) {
            try {
                result = reader.nextInt();
                if (result < min) {
                    System.err.println("Input value should be grater or equal than " + min);
                } else {
                    found = true;
                }
            } catch (InputMismatchException e) {
                System.err.println("Input should be a positive integer, maximum value: " + max);
                reader.next(); // this consumes the invalid token
            }
        }
        return result;
    }

    private static Direction getDirectionFromInput(Scanner reader) {
        Function<Character, Boolean> function = (character) -> Direction.LIST.stream().anyMatch(direction1 -> direction1.name().charAt(0) == character);
        return Direction.valueOf(getInputKey(reader, function) + "");
    }

    private static char getCommandFromInput(Scanner reader) {
        List<Command> commands = Arrays.asList(Command.values());
        Function<Character, Boolean> function = (character) -> commands.stream().anyMatch(command1 -> command1.value() == character);
        return getInputKey(reader, function);
    }

    private static char getInputKey(Scanner reader, Function<Character, Boolean> function) {
        char key = 'a'; // Required initialization
        boolean found = false;

        while (!found) {
            String input = reader.nextLine().toUpperCase();
            if (input.length() > 1) {
                System.err.println("Please, press only one key");
            } else if (input.length() != 0) {
                char ch = input.charAt(0);
                if (function.apply(ch)) {
                    key = ch;
                    found = true;
                } else {
                    System.err.println("Invalid key pressed '" + ch + "'");
                }
            }
        }
        return key;
    }
}
