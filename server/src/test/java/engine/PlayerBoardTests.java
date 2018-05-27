package engine;

import com.thoughtworks.xstream.core.util.Fields;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.lang.reflect.Field;

public class PlayerBoardTests {

    @Test
    public void givenBoardsConstructorTest() {
        // Arrange
        FieldState[][] stateBoard = new FieldState[10][10];
        ShotResult[][] shotResults = new ShotResult[10][10];

        // Act
        PlayerBoard result = new PlayerBoard(stateBoard, shotResults);
        // Assert
        Assert.assertNotNull(result);
    }

    @Test
    public void getNumberOfBattleshipsPartsLeftTest() {
        // Arrange
        FieldState[][] stateBoard = new FieldState[10][10];
        stateBoard[1][1] = FieldState.BattleshipPart;
        stateBoard[1][2] = FieldState.BattleshipPart;
        stateBoard[1][3] = FieldState.BattleshipPart;
        stateBoard[1][4] = FieldState.SunkBattleshipPart;

        ShotResult[][] shotResults = new ShotResult[10][10];
        PlayerBoard board = new PlayerBoard(stateBoard, shotResults);

        // Act
        int result = board.getNumberOfBattleshipsLeft();

        // Assert
        Assert.assertEquals(3, result);
    }

    @Test
    public void givenPositionsConstructorTest() {
        // Arrange
        Point[][] battleshipsPositions = new Point[][]{
                new Point[]{new Point(1, 1)},
                new Point[]{new Point(1, 2)},
                new Point[]{new Point(1, 3)},
                new Point[]{new Point(8, 8)},
        };

        // Act
        PlayerBoard result = new PlayerBoard(battleshipsPositions);
        // Assert
        Assert.assertEquals(4, result.getNumberOfBattleshipsLeft());
    }

    @Test
    public void getBoardStateTest() {
        // Arrange
        FieldState[][] stateBoard = new FieldState[10][10];
        stateBoard[1][1] = FieldState.BattleshipPart;
        stateBoard[1][2] = FieldState.BattleshipPart;

        ShotResult[][] shotResults = new ShotResult[10][10];
        PlayerBoard board = new PlayerBoard(stateBoard, shotResults);

        // Act
        FieldState[][] result = board.getBoardState();

        // Assert
        Assert.assertArrayEquals(stateBoard, result);
    }

    @Test
    public void getShotResultsTest() {
        // Arrange
        ShotResult[][] shotResults = new ShotResult[10][10];
        shotResults[1][1] = ShotResult.Miss;
        shotResults[1][2] = ShotResult.HitAndSunk;

        FieldState[][] stateBoard = new FieldState[10][10];
        PlayerBoard board = new PlayerBoard(stateBoard, shotResults);

        // Act
        ShotResult[][] result = board.getShotResults();

        // Assert
        Assert.assertArrayEquals(shotResults, result);
    }

    @Test
    public void markFriendlyShotTest() {
        // Arrange
        PlayerBoard board = new PlayerBoard(new Point[0][]);

        // Act
        board.markFriendlyShot(new Point(2, 2), ShotResult.Hit);
        ShotResult[][] result = board.getShotResults();

        // Assert
        Assert.assertEquals(ShotResult.Hit, result[2][2]);
    }

    @Test
    public void shot_Miss_Test() {
        // Arrange
        PlayerBoard board = new PlayerBoard(new Point[0][]);

        // Act
        ShotResult result = board.shot(new Point(2, 2));
        FieldState[][] boardState = board.getBoardState();

        // Assert
        Assert.assertEquals(ShotResult.Miss, result);
        Assert.assertEquals(FieldState.Clear, boardState[2][2]);
    }

    @Test
    public void shot_Hit_Test() {
        // Arrange
        PlayerBoard board = new PlayerBoard(new Point[][]{new Point[]{new Point(2, 2), new Point(2, 3)}});

        // Act
        ShotResult result = board.shot(new Point(2, 2));
        FieldState[][] boardState = board.getBoardState();

        // Assert
        Assert.assertEquals(ShotResult.Hit, result);
        Assert.assertEquals(FieldState.SunkBattleshipPart, boardState[2][2]);
    }

    @Test
    public void shot_HitAndSunk_Test() {
        // Arrange
        PlayerBoard board = new PlayerBoard(new Point[][]{new Point[]{new Point(2, 2), new Point(2, 3)}});

        // Act
        ShotResult firstShotResult = board.shot(new Point(2, 2));
        ShotResult secondShotResult = board.shot(new Point(2, 3));
        FieldState[][] boardState = board.getBoardState();

        // Assert
        Assert.assertEquals(ShotResult.Hit, firstShotResult);
        Assert.assertEquals(ShotResult.HitAndSunk, secondShotResult);
        Assert.assertEquals(FieldState.SunkBattleshipPart, boardState[2][2]);
    }
}
