import com.arpi.*;
import com.arpi.control.GameScreenController;
import com.arpi.display.ConsoleGameScreenDisplayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameScreenControllerTest {

    GameScreenController gameScreenController;

    @BeforeEach
    public void init() {

        gameScreenController = new GameScreenController();
        gameScreenController.initGrid(10, 20);
        gameScreenController.setGameScreenDisplayer(new ConsoleGameScreenDisplayer());

    }

    @Test
    public void getXY_getsCorrectValuesValues_ReturnCorrectValues() {

        // given
        gameScreenController.setXY(2, 2, Constants.ALIVE);

        // when
        String shouldAlive = gameScreenController.getXY(2, 2);

        // then
        Assertions.assertEquals(Constants.ALIVE, shouldAlive);

    }

    @Test
    public void getXY_getsOutOfBoundValues_shouldReturnEmptyString() {

        // given
        gameScreenController.setXY(2, 2, Constants.ALIVE);

        // when
        String shouldEmptyStr = gameScreenController.getXY(-10, 1000);

        // then
        Assertions.assertEquals("", shouldEmptyStr);

    }

    @Test
    public void setXY_getsCorrectValues_ReturnCorrectValues() {

        // given
        gameScreenController.setXY(2, 2, Constants.ALIVE);


        // when
        int shouldZero = gameScreenController.setXY(3, 3, Constants.ALIVE);
        String shouldAlive = gameScreenController.getXY(2, 2);

        // then
        Assertions.assertEquals(0, shouldZero);
        Assertions.assertEquals(shouldAlive, shouldAlive);

    }


    @Test
    public void setXY_getsOutOfBoundValues_shouldReturnNegativeOne() {

        // when
        int shouldNegativeOne = gameScreenController.setXY(-10, 1000, Constants.ALIVE);

        // then
        Assertions.assertEquals(-1, shouldNegativeOne);


    }

}
