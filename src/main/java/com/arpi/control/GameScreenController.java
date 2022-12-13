package com.arpi.control;

import com.arpi.display.AbstractGamesScreenDisplayer;
import com.arpi.display.GameScreenDisplayer;
import com.arpi.model.GameScreen;

import java.util.Random;

public class GameScreenController {

    private AbstractGamesScreenDisplayer gameScreenDisplayer;

    public void initGrid(int rows, int cols) {

        GameScreen gameScreen = new GameScreen();
        gameScreen.setRows(rows);
        gameScreen.setCols(cols);
        gameScreen.setGrid(new String[rows][cols]);
        gameScreenDisplayer.setGameScreen(gameScreen);
    }

    public String getXY(int rowIdx, int colIdx) {

        String[][] grid = this.gameScreenDisplayer.getGameScreen().getGrid();

        if (ensureGridContainsRowColCoordinates(grid, rowIdx, colIdx)) {
            return grid[rowIdx][colIdx];
        } else {
            System.out.println("Could not GET screen value from x= " + colIdx + " y= " + colIdx + " - returning empty string");
            return "";
        }

    }

    public int setXY(int rowIdx, int colIdx, String value) {

        if (ensureGridContainsRowColCoordinates(this.gameScreenDisplayer.getGameScreen().getGrid(), rowIdx, colIdx)) {
            this.gameScreenDisplayer.getGameScreen().getGrid()[rowIdx][colIdx] = value;
            return 0;
        } else {
            System.out.println("Could not SET screen value from x= " + colIdx + " y= " + colIdx);
            return -1;
        }
    }

    private boolean ensureGridContainsRowColCoordinates(String[][] grid, int rowIdx, int colIdx) {
        return grid != null && (rowIdx >= 0 && rowIdx < grid.length) && (colIdx >= 0 && colIdx < grid[0].length);
    }

    public void showGameScreen() {
        this.gameScreenDisplayer.displayGameScreen(gameScreenDisplayer.getGameScreen());
    }

    public void fillGameScreenWithValue(String value) {
        for (int i = 0; i < gameScreenDisplayer.getGameScreen().getRows(); i++) {
            for (int j = 0; j < gameScreenDisplayer.getGameScreen().getCols(); j++) {
                setXY(i, j, value);
            }
        }
    }


    public void fillGameScreenWithRandom() {
        Random random = new Random();
        for (int i = 0; i < gameScreenDisplayer.getGameScreen().getRows(); i++) {
            for (int j = 0; j < gameScreenDisplayer.getGameScreen().getCols(); j++) {
                setXY(i, j, Integer.toString(random.nextInt(2)));
            }
        }

    }

    public GameScreenDisplayer getGameScreenDisplayer() {
        return gameScreenDisplayer;
    }

    public void setGameScreenDisplayer(AbstractGamesScreenDisplayer gameScreenDisplayer) {
        this.gameScreenDisplayer = gameScreenDisplayer;
    }
}
