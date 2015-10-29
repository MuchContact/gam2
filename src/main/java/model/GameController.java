package model;

import java.util.List;

import static model.Equipment.EquipmentType.BLOCK;

public class GameController {
    private final List<Player> players;
    private final List<RealEstimate> realEstimates;
    private final Dice dice;

    public GameController(List<Player> players, List<RealEstimate> realEstimates, Dice dice) {

        this.players = players;
        this.realEstimates = realEstimates;
        this.dice = dice;
    }

    public void roll() {
        int roll = dice.roll();
        int newPos = (getCurrentPlayer().getPosition() + roll) % realEstimates.size();
        for (int i = 1; i <= roll; i++) {
            int i1 = (getCurrentPlayer().getPosition() + i) % realEstimates.size();
            if(realEstimates.get(i1).getEquipment().getType().equals(BLOCK)){
                newPos = i1;
                break;
            }
        }
        getCurrentPlayer().moveTo(newPos);
    }

    public Player getCurrentPlayer() {
        return players.get(0);
    }
}
