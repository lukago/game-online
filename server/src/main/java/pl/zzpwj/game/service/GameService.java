package pl.zzpwj.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpwj.game.engine.FieldState;
import pl.zzpwj.game.engine.PlayerBoard;
import pl.zzpwj.game.engine.Point;
import pl.zzpwj.game.engine.ShotResult;

@Service
public class GameService {
    private PlayerBoard playerBoard;

    @Autowired
    public GameService() {
    }

    public PlayerBoard setupBoard(Point[][] battleshipPositions) throws InstantiationException {
        this.playerBoard = PlayerBoard.setup(battleshipPositions, null);
        return this.playerBoard;
    }

    public FieldState[][] getBoardState() {
        return playerBoard.getBoardState();
    }

    public ShotResult[][] getShotResults() {
        return playerBoard.getShotResults();
    }

    public ShotResult shoot(Point target) {
        //TODO
        return ShotResult.Miss;
    }

}
