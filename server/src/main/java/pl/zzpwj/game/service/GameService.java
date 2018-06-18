package pl.zzpwj.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpwj.game.engine.FieldState;
import pl.zzpwj.game.engine.PlayerBoard;
import pl.zzpwj.game.engine.Point;
import pl.zzpwj.game.engine.ShotResult;
import pl.zzpwj.game.model.Game;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {

    private Map<String, Game> games = new HashMap<>();

    @Autowired
    public GameService() {
    }

    public void createGame(String username) {
        games.put(username, new Game(username));
    }

    public void joinGame(String username, String hostUsername) throws NullPointerException {
        Game game = games.get(hostUsername);
        game.setSecondPlayerUsername(username);
        games.put(username, game);
    }

    public PlayerBoard setupBoard(String username, Point[][] battleshipPositions) throws InstantiationException, NullPointerException {
        return games.get(username).setupBoard(username, battleshipPositions);
    }

    public FieldState[][] getBoardState(String username) throws NullPointerException {
        return games.get(username).getPlayerBoard(username).getBoardState();
    }

    public ShotResult[][] getShotResults(String username) throws NullPointerException {
        return games.get(username).getPlayerBoard(username).getShotResults();
    }

    public ShotResult shoot(String shooterUsername, Point target) throws NullPointerException {
        return games.get(shooterUsername).shoot(shooterUsername, target);
    }

}
