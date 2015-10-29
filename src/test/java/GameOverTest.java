import model.Dice;
import model.GameController;
import model.Player;
import model.RealEstimate;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameOverTest {
    @Test
    public void should_game_over_when_alive_players_less_than_2() throws Exception {
        Dice dice = mock(Dice.class);
        when(dice.roll()).thenReturn(1);

        Player player1 = new Player(100, 1);
        RealEstimate second = new RealEstimate(200);
        Player player2 = new Player();
        GameController gameController = new GameController(
                asList(player1, player2),
                asList(new RealEstimate(), second),
                dice
        );
        gameController.buy();
        assertThat(gameController.isGameOver(), is(true));
        assertThat(gameController.getWinner(), is(player2));
    }
}
