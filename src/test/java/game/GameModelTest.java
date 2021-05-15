package game;

import game.model.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameModelTest {

    private GameModel gameModel;

    @BeforeEach
    void setUp(){
        gameModel = new GameModel();
        gameModel.setP1name("asd");
        gameModel.setP2name("asder");
    } //minden teszt előtt inic a gamemodell kezdőállapotát


    @Test
    void testisEmptyField() {
        assertTrue(gameModel.isEmptyField(0));
        assertTrue(gameModel.isEmptyField(25));
        assertTrue(gameModel.isEmptyField(24));
        gameModel.setGrid(new int[]{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        assertFalse(gameModel.isEmptyField(2));
    }
    @Test
    void testMove(){
        gameModel.placeStone("asd",0);
        assertArrayEquals(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},gameModel.getGrid());
        gameModel.placeStone("asder",3);
        assertArrayEquals(new int[]{1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},gameModel.getGrid());
        gameModel.placeStone("asd",0);
        assertArrayEquals(new int[]{1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},gameModel.getGrid());
        gameModel.placeStone("asd",16);
        assertArrayEquals(new int[]{1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},gameModel.getGrid());
        gameModel.placeStone("asder",5);
        assertArrayEquals(new int[]{1, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},gameModel.getGrid());
    }

    @Test
    void testisGameOver(){
        gameModel.setGrid(new int[]{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0});
        assertTrue(gameModel.isGameOver());
        gameModel.setGrid(new int[]{1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0});
        assertFalse(gameModel.isGameOver());
        gameModel.setGrid(new int[]{1, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 1, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0});
        assertTrue(gameModel.isGameOver());
        gameModel.setGrid(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        assertFalse(gameModel.isGameOver());
        gameModel.setGrid(new int[]{1, 0, 2, 0, 1, 0, 2, 0, 1, 0, 2, 0, 1, 0, 2, 0, 1, 0, 2, 0, 1, 0, 2, 0, 1, 0, 2, 0, 1, 0, 2, 0});
        assertTrue(gameModel.isGameOver());
    }
}
