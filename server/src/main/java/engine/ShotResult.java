package engine;

/**
 * Describes the outcome of shot on the board.
 */
public enum ShotResult{
    /**
     * Shot hit water and did not damage ay battleship.
     */
    Miss,
    /**
     * Shot hit battleship but did not sunk it.
     */
    Hit,
    /**
     * Shot hit battleship and sunk it (all parts of that ship were hit before)
     */
    HitAndSunk
}
