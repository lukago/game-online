package pl.zzpwj.game.engine;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;


/**
 * Defines set of rules of new battleships board validation.
 */

public class BoardConstraints {

    public static BoardConstraints getDefault() {
        BoardConstraints constraints = new BoardConstraints();
        constraints.minSpaceBetweenShips = 1;
        constraints.shipSizeCounts =
                new Pair[]{
                        new Pair(4, 1),
                        new Pair(3, 2),
                        new Pair(2, 3),
                        new Pair(1, 4),
                };
        return constraints;
    }

    @Getter
    @Setter
    public int minSpaceBetweenShips;

    @Getter
    @Setter
    public Pair<Integer, Integer>[] shipSizeCounts;

    public int GetShipsCountLimit() {
        return Arrays.stream(shipSizeCounts).mapToInt(s -> s.getValue()).sum();
    }
}
