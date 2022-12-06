package com.arpi.control;

import com.arpi.display.GameScreenDisplayer;
import com.arpi.model.GameScreen;

import java.util.Random;

public class GameScreenController {


    private GameScreen gameScreen;

    private GameScreenDisplayer gameScreenDisplayer;

    public void initGrid(int rows, int cols) {

        GameScreen gameScreen = new GameScreen();
        gameScreen.setRows(rows);
        gameScreen.setCols(cols);
        gameScreen.setGrid(new String[rows][cols]);
        this.gameScreen = gameScreen;
    }

    public String getXY(int rowIdx, int colIdx) {

        String[][] grid = this.gameScreen.getGrid();

        if (ensureGridContainsRowColCoordinates(gameScreen.getGrid(), rowIdx, colIdx)) {
            return grid[rowIdx][colIdx];
        } else {
            System.out.println("Could not GET screen value from x= " + colIdx + " y= " + colIdx + " - returning empty string");
            return "";
        }

    }

    public int setXY(int rowIdx, int colIdx, String value) {

        if (ensureGridContainsRowColCoordinates(gameScreen.getGrid(), rowIdx, colIdx)) {
            this.gameScreen.getGrid()[rowIdx][colIdx] = value;
            return 0;
        } else {
            System.out.println("Could not SET screen value from x= " + colIdx + " y= " + colIdx);
            return -1;
        }
    }

    private boolean ensureGridContainsRowColCoordinates(String[][] grid, int rowIdx, int colIdx) {
        return grid != null && (rowIdx >= 0 && rowIdx < grid.length) && (colIdx >= 0 && rowIdx < grid[0].length);
    }

    public void showGameScreen() {
        this.gameScreenDisplayer.displayGameScreen(this.gameScreen);
    }

    public void fillGameScreenWithValue(String value) {
        for (int i = 0; i < gameScreen.getRows(); i++) {
            for (int j = 0; j < gameScreen.getCols(); j++) {
                setXY(i, j, value);
            }
        }
    }


    public void fillGameScreenWithRandom() {
        Random random = new Random();
        for (int i = 0; i < gameScreen.getRows(); i++) {
            for (int j = 0; j < gameScreen.getCols(); j++) {
                setXY(i, j, Integer.toString(random.nextInt(2)));
            }
        }

    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public GameScreenDisplayer getGameScreenDisplayer() {
        return gameScreenDisplayer;
    }

    public void setGameScreenDisplayer(GameScreenDisplayer gameScreenDisplayer) {
        this.gameScreenDisplayer = gameScreenDisplayer;
    }
}
