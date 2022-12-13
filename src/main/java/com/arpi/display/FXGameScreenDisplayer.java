package com.arpi.display;

import com.arpi.Constants;
import com.arpi.model.GameScreen;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class FXGameScreenDisplayer extends AbstractGamesScreenDisplayer {
    protected static final int width = Constants.SCREEN_INITIAL_WIDTH;
    protected static final int height = Constants.SCREEN_INITIAL_HEIGHT;
    protected GraphicsContext graphicsContext;
    protected Stage primaryStage;
    protected Canvas canvas;
    @Override
    public void displayGameScreen(GameScreen gameScreen) {

        VBox root = new VBox(10);
        Scene scene = new Scene(root, width, height + 100);
        Canvas canvas = new Canvas(width, height);
        graphicsContext = canvas.getGraphicsContext2D();
        Button reset = new Button("Reset");
        Button step = new Button("Step");
        Button run = new Button("Run");
        Button stop = new Button("Stop");

        root.getChildren().addAll(canvas, new HBox(10, reset, step, run, stop));
        AnimationTimer runAnimation = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                // only update once every second
                if ((now - lastUpdate) >= TimeUnit.MILLISECONDS.toNanos(Constants.ANIMATION_INTERVAL_MS)) {
                    tick();
                    lastUpdate = now;
                }
            }
        };

        reset.setOnAction(l -> tick() );
        run.setOnAction(  l -> runAnimation.start());
        step.setOnAction( l -> tick());
        stop.setOnAction( l -> runAnimation.stop());


        draw();

        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void tick() {

        int rows = gameScreen.getRows();
        int cols = gameScreen.getCols();

        String[][] next = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int neighbors = countAliveNeighbors(i, j);

                if (neighbors == 3) {
                    next[i][j] = Constants.ALIVE;
                }else if (neighbors < 2 || neighbors > 3) {
                    next[i][j] = Constants.DEAD;
                }else {
                    next[i][j] = gameScreen.getGrid()[i][j];
                }
            }
        }
        gameScreen.setGrid(next);
        draw();
    }
    private int countAliveNeighbors(int i, int j) {
        int sum = 0;
        int iStart = i == 0 ? 0 : -1;
        int iEndInclusive = i == gameScreen.getGrid().length - 1 ? 0 : 1;
        int jStart = j == 0 ? 0 : -1;
        int jEndInclusive = j == gameScreen.getGrid()[0].length - 1 ? 0 : 1;

    for (int k = iStart; k <= iEndInclusive; k++) {
            for (int l = jStart; l <= jEndInclusive; l++) {
                sum +=Integer.parseInt(gameScreen.getGrid()[i + k][l + j]);
            }
        }

        sum -= Integer.parseInt(gameScreen.getGrid()[i][j]);

        return sum;
    }

    private void draw() {
        // clear graphics
        graphicsContext.setFill(Color.GREY);
        graphicsContext.fillRect(0, 0, width, height);

        for (int i = 0; i < gameScreen.getRows(); i++) {
            for (int j = 0; j < gameScreen.getCols(); j++) {
                if (gameScreen.getGrid()[i][j].equals(Constants.ALIVE)) {
                    // first rect will end up becoming the border
                    drawCell(graphicsContext, i, j, Constants.ALIVE_COLOR);
                } else {
                    drawCell(graphicsContext, i, j, Constants.DEAD_COLOR);
                }
            }
        }
    }

    private void drawCell(GraphicsContext graphicsContext, int x, int y, Color cellColor) {
        graphicsContext.setFill(Constants.BORDER_COLOR);
        graphicsContext.fillRect(y * Constants.CELL_SIZE, x * Constants.CELL_SIZE, Constants.CELL_SIZE, Constants.CELL_SIZE);
        graphicsContext.setFill(cellColor);
        graphicsContext.fillRect(y * Constants.CELL_SIZE + Constants.BORDER_WIDTH, x * Constants.CELL_SIZE + Constants.BORDER_WIDTH, Constants.CELL_SIZE - Constants.BORDER_WIDTH, Constants.CELL_SIZE - Constants.BORDER_WIDTH);
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
