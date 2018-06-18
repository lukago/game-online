package pl.zzpwj.game.model;

import lombok.Setter;
import pl.zzpwj.game.engine.PlayerBoard;
import pl.zzpwj.game.engine.Point;
import pl.zzpwj.game.engine.ShotResult;

import java.util.HashMap;
import java.util.Map;

public class Game {

    private Map<String, PlayerBoard> boards = new HashMap<>();

    private String firstPlayerUsername;

    @Setter
    private String secondPlayerUsername;

    public Game(String hostUsername) {
        this.firstPlayerUsername = hostUsername;
    }

    public PlayerBoard setupBoard(String username, Point[][] battleshipPositions) throws InstantiationException {
        PlayerBoard board = PlayerBoard.setup(battleshipPositions, null);
        this.boards.put(username, board);
        return board;
    }

    public PlayerBoard getPlayerBoard(String username) throws NullPointerException {
        return this.boards.get(username);
    }

    public ShotResult shoot(String shooterUsername, Point point) throws NullPointerException {
        String targetUsername;
        if (shooterUsername.equals(this.firstPlayerUsername)) targetUsername = this.secondPlayerUsername;
        else targetUsername = this.firstPlayerUsername;
        ShotResult result = this.boards.get(targetUsername).shoot(point);
        this.boards.get(shooterUsername).markFriendlyShot(point, result);
        return result;
    }
}
