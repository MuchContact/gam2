import model.*;
import org.junit.Test;

import static java.util.Arrays.asList;
import static model.EstimateType.EquipmentRoom;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChooseEquipmentTest {
    @Test
    public void should_choose_equipments_in_equipment_room() throws Exception {
        Dice dice = mock(Dice.class);
        when(dice.roll()).thenReturn(1);

        Player player1 = new Player(300, 1, 100);
        RealEstimate equipmentRoom = new RealEstimate(EquipmentRoom);
        GameController gameController = new GameController(
                asList(player1, new Player()),
                asList(new RealEstimate(), equipmentRoom, new RealEstimate()),
                dice
        );
        gameController.choose(1);
        assertThat(player1.getPoints(), is(50));
        assertThat(player1.getEquipments(Equipment.EquipmentType.BLOCK).size(), is(1));
        gameController.choose(1);
        assertThat(player1.getPoints(), is(0));
        assertThat(player1.getEquipments(Equipment.EquipmentType.BLOCK).size(), is(2));
    }
}
