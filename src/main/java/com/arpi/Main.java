package com.arpi;

import com.arpi.control.GameScreenController;
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
        gameScreenController.initGrid(40, 40);
        gameScreenController.fillGameScreenWithRandom();

        FXGameScreenDisplayer fxGameScreenDisplayer = new FXGameScreenDisplayer();
        fxGameScreenDisplayer.setPrimaryStage(primaryStage);
        gameScreenController.setGameScreenDisplayer(fxGameScreenDisplayer);
        gameScreenController.showGameScreen();


        /*
        ConsoleGameScreenDisplayer consoleGameScreenDisplayer = new ConsoleGameScreenDisplayer();
        gameScreenController.setGameScreenDisplayer(consoleGameScreenDisplayer);
        gameScreenController.showGameScreen();
        */


    }

}