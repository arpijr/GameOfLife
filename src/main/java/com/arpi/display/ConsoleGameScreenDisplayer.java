package com.arpi.display;

import com.arpi.model.GameScreen;

public class ConsoleGameScreenDisplayer implements GameScreenDisplayer {
    @Override
    public void displayGameScreen(GameScreen gameScreen) {

        System.out.println(" *** CURRENT GAME SCREEN ***");
        for(int i=0;i<gameScreen.getRows();i++){
            for(int j=0;j<gameScreen.getCols();j++){
                System.out.print(gameScreen.getGrid()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
