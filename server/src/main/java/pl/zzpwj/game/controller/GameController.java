package pl.zzpwj.game.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.zzpwj.game.dto.PlayerBoardDto;
import pl.zzpwj.game.dto.UserDto;
import pl.zzpwj.game.engine.FieldState;
import pl.zzpwj.game.engine.Point;
import pl.zzpwj.game.engine.ShotResult;
import pl.zzpwj.game.mapper.PlayerBoardMapper;
import pl.zzpwj.game.mapper.UserMapper;
import pl.zzpwj.game.model.User;
import pl.zzpwj.game.service.GameService;
import pl.zzpwj.game.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/game")
@Controller
public class GameController {
    private static final Logger LOG = LoggerFactory.getLogger(GameController.class);
    private GameService gameService;
    private PlayerBoardMapper playerBoardMapper;

    @Autowired
    public GameController(GameService gameService, PlayerBoardMapper playerBoardMapper) {
        this.gameService = gameService;
        this.playerBoardMapper = playerBoardMapper;
    }

    @RequestMapping(value = "/setup", method = RequestMethod.POST)
    public ResponseEntity<PlayerBoardDto> setupBoard(@RequestBody Point[][] battleshipPositions) {
        try {
            PlayerBoardDto boardDto = playerBoardMapper.mapToDto(gameService.setupBoard(battleshipPositions));
            return new ResponseEntity<>(boardDto, HttpStatus.OK);
        } catch (Exception e) {
            LOG.info("Exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/state", method = RequestMethod.GET)
    public ResponseEntity<FieldState[][]> getBoardState() {
        try {
            FieldState[][] boardState = gameService.getBoardState();
            return new ResponseEntity<>(boardState, HttpStatus.OK);
        } catch (Exception e) {
            LOG.info("Exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/shoot/{x}/{y}", method = RequestMethod.GET)
    public ResponseEntity<ShotResult> shoot(@PathVariable int x, @PathVariable int y) {
        try {
            ShotResult shotResult = gameService.shoot(new Point(x,y));
            return new ResponseEntity<>(shotResult, HttpStatus.OK);
        } catch (Exception e) {
            LOG.info("Exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}