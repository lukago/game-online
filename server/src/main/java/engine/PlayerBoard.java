package engine;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

/**
 * Keeps track of player's shots results and battlefield's state.
 */
public class PlayerBoard {
    enum FieldState{
        Clear,
        Shot,
        BattleshipPart,
        SunkBattleshipPart
    }

    /**
     * Instantiates new board if the validation of given constraints is passed.
     * @param battleshipsPositions positions of fields that store battleships
     * @param boardConstraints parameters defining board validation rules
     * @return instance of board generated from given settings
     * @throws InstantiationException if the validation fails
     */
    public static PlayerBoard GetSetup(Point[] battleshipsPositions, BoardConstraints boardConstraints) throws InstantiationException {
        //validate setup
        boolean isValid = true;

        if(!isValid)
            throw new InstantiationException("Cannot initialize board with given setup.");

        return new PlayerBoard(battleshipsPositions);
    }

    /**
     * Creates new board instance with battleships on given positions.
     * @param battleshipsPositions positions of fields that store battleships
     */
    public  PlayerBoard(Point[] battleshipsPositions) {

        //init stateBoard
    }

    /**
     * Creates new board instance from archival state data.
     * @param stateBoard archival state of player's board
     * @param aimBoard archival state of player's shot results
     */
    public PlayerBoard(FieldState[][] stateBoard, FieldState[][] aimBoard){

    }

    private FieldState[][] stateBoard;
    private FieldState[][] aimBoard;

    /**
     * Counts number battleships still alive on tha board.
     * @return number of not-sunk battleships.
     */
    public int GetNumberOfBattleshipsLeft(){
        throw new NotImplementedException();
    }

    /**
     * Simulates shot on the board.
     * @param coords shot position
     * @return shot state whether it hit, miss or sank a battleship.
     */
    public ShotResult Shot(Point coords){
        throw new NotImplementedException();
    }

    /**
     * Marks friendly shot on the board.
     * @param coords shot position
     * @param result type of result that is being marked
     */
    public void MarkFriendlyShot(Point coords, ShotResult result){
        throw new NotImplementedException();
    }
}
