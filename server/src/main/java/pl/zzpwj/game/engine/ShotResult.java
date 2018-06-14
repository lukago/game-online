package pl.zzpwj.game.engine;

/**
 * Describes the outcome of shot on the board.
 */
public enum ShotResult{
    /**
     * Empty result - shot has not been fired yet.
     */
    None,
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
