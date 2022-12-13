package com.arpi.display;

import com.arpi.model.GameScreen;

public abstract class AbstractGamesScreenDisplayer implements GameScreenDisplayer {

    protected GameScreen gameScreen;
    public GameScreen getGameScreen() {
        return gameScreen;
    }
    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

}
