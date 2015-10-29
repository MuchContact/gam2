import model.*;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChooseGiftTest {
    @Test
    public void should_choose_gifts_in_gift_room() throws Exception {
        Dice dice = mock(Dice.class);
        when(dice.roll()).thenReturn(1);

        Player player1 = new Player(300, 1);
        RealEstimate giftRoom = new RealEstimate(EstimateType.GiftRoom);
        GameController gameController = new GameController(
                asList(player1, new Player()),
                asList(new RealEstimate(), giftRoom, new RealEstimate()),
                dice
        );
        gameController.choose(1);
        assertThat(player1.getMoney(), is(2300));
        gameController.choose(1);
        assertThat(player1.getMoney(), is(2300));

    }
}
