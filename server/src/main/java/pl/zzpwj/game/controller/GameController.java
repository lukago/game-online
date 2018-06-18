package pl.zzpwj.game.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.zzpwj.game.dto.PlayerBoardDto;
import pl.zzpwj.game.engine.FieldState;
import pl.zzpwj.game.engine.Point;
import pl.zzpwj.game.engine.ShotResult;
import pl.zzpwj.game.mapper.PlayerBoardMapper;
import pl.zzpwj.game.service.GameService;
import pl.zzpwj.game.service.SessionService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/game")
@Controller
public class GameController {
    private static final Logger LOG = LoggerFactory.getLogger(GameController.class);
    private GameService gameService;
    private PlayerBoardMapper playerBoardMapper;
    private SessionService sessionService;

    @Autowired
    public GameController(GameService gameService, PlayerBoardMapper playerBoardMapper, SessionService sessionService) {
        this.gameService = gameService;
        this.playerBoardMapper = playerBoardMapper;
        this.sessionService = sessionService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ResponseEntity<String> createGame() {
        try {
            gameService.createGame(sessionService.getLoggedUsername());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("Exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/join/{hostUsername}", method = RequestMethod.GET)
    public ResponseEntity<String> joinGame(@PathVariable String hostUsername) {
        try {
            gameService.joinGame(sessionService.getLoggedUsername(), hostUsername);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("Exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/setup", method = RequestMethod.POST)
    public ResponseEntity<PlayerBoardDto> setupBoard(@RequestBody Point[][] battleshipPositions) {
        try {
            PlayerBoardDto boardDto = playerBoardMapper.mapToDto(gameService.setupBoard(sessionService.getLoggedUsername(), battleshipPositions));
            return new ResponseEntity<>(boardDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("Exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/state", method = RequestMethod.GET)
    public ResponseEntity<FieldState[][]> getBoardState() {
        try {
            FieldState[][] boardState = gameService.getBoardState(sessionService.getLoggedUsername());
            return new ResponseEntity<>(boardState, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("Exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public ResponseEntity<ShotResult[][]> getShotResults() {
        try {
            ShotResult[][] shotResults = gameService.getShotResults(sessionService.getLoggedUsername());
            return new ResponseEntity<>(shotResults, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("Exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/shoot/{x}/{y}", method = RequestMethod.GET)
    public ResponseEntity<ShotResult> shoot(@PathVariable int x, @PathVariable int y) {
        try {
            ShotResult shotResult = gameService.shoot(sessionService.getLoggedUsername(), new Point(x, y));
            return new ResponseEntity<>(shotResult, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("Exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}