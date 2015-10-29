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
            if (realEstimates.get(i1).getEquipment() != null
                    && realEstimates.get(i1).getEquipment().getType().equals(BLOCK)) {
                newPos = i1;
                break;
            }
        }
        Player owner = realEstimates.get(newPos).getOwner();
        if (owner != null && !owner.equals(getCurrentPlayer())) {
            getCurrentPlayer().payForRent(owner, realEstimates.get(newPos));
        }
        getCurrentPlayer().moveTo(newPos);
    }

    public Player getCurrentPlayer() {
        return players.get(0);
    }

    public void buy() {
        RealEstimate realEstimate = realEstimates.get(getCurrentPlayer().getPosition());
        if (realEstimate.getType().equals(EstimateType.VaccantLand) && realEstimate.getOwner() == null) {
            getCurrentPlayer().buy(realEstimate);
        }
        if (getCurrentPlayer().isBroken()) {
            realEstimates.stream()
                    .filter(realEstimate1 -> realEstimate1.getOwner() != null
                            && realEstimate1.getOwner().equals(getCurrentPlayer()))
                    .forEach(RealEstimate::reset);
        }
    }

    public void upgrade() {
        RealEstimate realEstimate = realEstimates.get(getCurrentPlayer().getPosition());
        if (realEstimate.getOwner().equals(getCurrentPlayer())) {
            getCurrentPlayer().upgrade(realEstimate);
        }
    }
}
