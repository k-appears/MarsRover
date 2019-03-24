package entities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Direction {
    N,
    E,
    S,
    W;

    public static final List<Direction> LIST = Collections.unmodifiableList(Arrays.asList(Direction.values())); // In order of initialization

}