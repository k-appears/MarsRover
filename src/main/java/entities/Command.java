package entities;

import java.util.Arrays;

public enum Command {
    LEFT('L'),
    RIGHT('R'),
    FORWARD('F'),
    BACKWARD('B');

    private final char value;

    Command(char value) {
        this.value = value;
    }

    public final char value() { return this.value; }

    public static Command fromName(char value) {
        return Arrays.stream(Command.values())
                     .filter(f -> f.value == value)
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException("Command " + " not in: " + Arrays.toString(Command.values())));
    }
}