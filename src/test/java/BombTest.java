import model.*;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BombTest {
    @Test
    public void should_bombed_and_skipped_for_3_times() throws Exception {
        Dice dice = mock(Dice.class);
        when(dice.roll()).thenReturn(3);

        Player player1 = new Player();
        Player player2 = new Player();
        RealEstimate third = new RealEstimate();
        third.putBomb();
        RealEstimate hospital = new RealEstimate(EstimateType.Hospital);
        GameController gameController = new GameController(
                asList(player1, player2),
                asList(new RealEstimate(), hospital, third, new RealEstimate()),
                dice
        );
        gameController.roll();
        assertThat(player1.getPosition(), is(1));
        gameController.roll();
        assertThat(gameController.getCurrentPlayer(), is(player2));
        gameController.roll();
        assertThat(gameController.getCurrentPlayer(), is(player2));
        gameController.roll();
        assertThat(gameController.getCurrentPlayer(), is(player2));
        gameController.roll();
        assertThat(gameController.getCurrentPlayer(), is(player1));
    }
}
