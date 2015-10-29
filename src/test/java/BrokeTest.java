import model.Dice;
import model.GameController;
import model.Player;
import model.RealEstimate;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BrokeTest {
    @Test
    public void should_broke_and_reset_all_belongings() throws Exception {
        Dice dice = mock(Dice.class);
        when(dice.roll()).thenReturn(1);

        Player player1 = new Player(100, 1);
        RealEstimate second = new RealEstimate(200);
        RealEstimate third = new RealEstimate(200, player1);
        GameController gameController = new GameController(
                asList(player1, new Player()),
                asList(new RealEstimate(), second, third),
                dice
        );
        gameController.buy();
        assertThat(player1.getMoney(), is(-100));
        assertThat(second.getOwner(), is(nullValue()));
        assertThat(third.getOwner(), is(nullValue()));
    }
}
