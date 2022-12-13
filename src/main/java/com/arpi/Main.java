package com.arpi;

import com.arpi.control.GameScreenController;
import com.arpi.display.ConsoleGameScreenDisplayer;
import com.arpi.display.FXGameScreenDisplayer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GameScreenController gameScreenController = new GameScreenController();

        FXGameScreenDisplayer fxGameScreenDisplayer = new FXGameScreenDisplayer();

        gameScreenController.setGameScreenDisplayer(fxGameScreenDisplayer);
        gameScreenController.initGrid(216, 384);
        gameScreenController.fillGameScreenWithRandom();

        fxGameScreenDisplayer.setPrimaryStage(primaryStage);
        gameScreenController.showGameScreen();

    }

}