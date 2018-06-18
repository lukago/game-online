package pl.zzpwj.game.model;

import pl.zzpwj.game.engine.PlayerBoard;
import pl.zzpwj.game.engine.Point;
import pl.zzpwj.game.engine.ShotResult;

import java.util.HashMap;
import java.util.Map;

public class Game {

    private static Map<String,Game> games = new HashMap<>();

    private Map<String,PlayerBoard> boards = new HashMap<>();

    private String firstPlayerUsername;

    private String secondPlayerUsername;

    public static void createGame(String hostUsername) {
        Game game = new Game();
        game.firstPlayerUsername = hostUsername;
        games.put(hostUsername, game);
    }

    public static void joinGame(String username, String hostUsername) throws NullPointerException {
        Game game = games.get(hostUsername);
        game.secondPlayerUsername = username;
        games.put(username, game);
    }

    public static PlayerBoard setupBoard(String username, Point[][] battleshipPositions) throws InstantiationException, NullPointerException {
        PlayerBoard board = PlayerBoard.setup(battleshipPositions, null);
        games.get(username).boards.put(username, board);
        return board;
    }

    public static PlayerBoard getPlayerBoard(String username) throws NullPointerException {
        return games.get(username).boards.get(username);
    }

    public static ShotResult shoot(String shooterUsername, Point point) throws NullPointerException {
        String targetUsername;
        Game game = games.get(shooterUsername);
        if(shooterUsername.equals(game.firstPlayerUsername)) targetUsername = game.secondPlayerUsername;
        else targetUsername = game.firstPlayerUsername;
        return game.boards.get(targetUsername).shoot(point);
    }
}
