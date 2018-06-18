package pl.zzpwj.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpwj.game.engine.FieldState;
import pl.zzpwj.game.engine.PlayerBoard;
import pl.zzpwj.game.engine.Point;
import pl.zzpwj.game.engine.ShotResult;
import pl.zzpwj.game.model.Game;

@Service
public class GameService {

    @Autowired
    public GameService() {
    }

    public void createGame(String username) {
        Game.createGame(username);
    }

    public void joinGame(String username, String hostUsername) {
        Game.joinGame(username, hostUsername);
    }

    public PlayerBoard setupBoard(String username, Point[][] battleshipPositions) throws InstantiationException, NullPointerException {
        return Game.setupBoard(username, battleshipPositions);
    }

    public FieldState[][] getBoardState(String username) throws NullPointerException {
        return Game.getPlayerBoard(username).getBoardState();
    }

    public ShotResult[][] getShotResults(String username) throws NullPointerException {
        return Game.getPlayerBoard(username).getShotResults();
    }

    public ShotResult shoot(String shooterUsername, Point target) throws NullPointerException {
        return Game.shoot(shooterUsername, target);
    }

}
