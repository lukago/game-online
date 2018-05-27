package engine;

import java.awt.*;
import java.util.Arrays;

/**
 * Keeps track of player's shots results and battlefield's state.
 */
public class PlayerBoard {

    /**
     * Instantiates new board if the validation of given constraints is passed.
     *
     * @param battleshipsPositions positions of fields that store battleships
     * @param boardConstraints     parameters defining board validation rules
     * @return instance of board generated from given settings
     * @throws InstantiationException if the validation fails
     */
    public static PlayerBoard setup(Point[] battleshipsPositions, BoardConstraints boardConstraints) throws InstantiationException {
        //validate setup
        boolean isValid = true;

        if (!isValid)
            throw new InstantiationException("Cannot initialize board with given setup.");

        return new PlayerBoard(battleshipsPositions);
    }

    private FieldState[][] stateBoard;
    private FieldState[][] aimBoard;

    /**
     * Creates new 10x10 board instance with battleships on given positions.
     *
     * @param battleshipsPositions positions of fields that store battleships
     */
    public PlayerBoard(Point[] battleshipsPositions) {
        this(battleshipsPositions, 10);
    }

    /**
     * Creates new board instance with battleships on given positions.
     *
     * @param battleshipsPositions positions of fields that store battleships
     * @param size                 board size
     */
    public PlayerBoard(Point[] battleshipsPositions, int size) {
        this(size);
        Arrays.stream(battleshipsPositions)
                .forEach(position ->
                        stateBoard[position.x][position.y] = FieldState.BattleshipPart);
    }

    /**
     * Creates new 10x10 board instance from archival state data.
     *
     * @param stateBoard archival state of player's board
     * @param aimBoard   archival state of player's shot results
     */
    public PlayerBoard(FieldState[][] stateBoard, FieldState[][] aimBoard) {
        this(stateBoard, aimBoard, 10);
    }

    /**
     * Creates new board instance from archival state data.
     *
     * @param stateBoard archival state of player's board
     * @param aimBoard   archival state of player's shot results
     * @param size       size of the board
     */
    public PlayerBoard(FieldState[][] stateBoard, FieldState[][] aimBoard, int size) {
        this(size);
        this.stateBoard = stateBoard;
        this.aimBoard = aimBoard;
    }

    private PlayerBoard(int size) {
        stateBoard = new FieldState[size][size];
        Arrays.stream(stateBoard).flatMap(s-> Arrays.stream(s)).forEach(s->s=FieldState.Clear);
        aimBoard = new FieldState[size][size];
        Arrays.stream(aimBoard).flatMap(s-> Arrays.stream(s)).forEach(s->s=FieldState.Clear);
    }

    /**
     * Counts number battleships still alive on tha board.
     *
     * @return number of not-sunk battleships.
     */
    public int getNumberOfBattleshipsLeft() {
        return (int) Arrays
                .stream(stateBoard)
                .flatMap(x -> Arrays.stream(x))
                .filter(f -> f == FieldState.BattleshipPart)
                .count();
    }

    /**
     * Simulates shot on the board.
     *
     * @param coords shot position
     * @return shot state whether it hit, miss or sank a battleship.
     */
    public ShotResult shot(Point coords) {
        return null;
    }

    /**
     * Marks friendly shot on the board.
     *
     * @param coords shot position
     * @param result type of result that is being marked
     */
    public void markFriendlyShot(Point coords, ShotResult result) {

    }
}
