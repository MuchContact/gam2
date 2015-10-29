package model;

import java.util.List;

import static model.EstimateType.*;

public class GameController {
    private final List<Player> players;
    private final List<RealEstimate> realEstimates;
    private final Dice dice;
    private int cursorForCurrentPlayer;

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
            Equipment equipment = realEstimates.get(i1).getEquipment();
            if (equipment != null) {
                newPos = equipment.act(getCurrentPlayer(), realEstimates, i1);
                break;
            }

        }
        Player owner = realEstimates.get(newPos).getOwner();
        if (owner != null && !owner.equals(getCurrentPlayer())) {
            getCurrentPlayer().payForRent(owner, realEstimates.get(newPos));
            if (getCurrentPlayer().isBroken()) {
                getCurrentPlayer().markToDie();
                realEstimates.stream()
                        .filter(realEstimate1 -> realEstimate1.getOwner() != null
                                && realEstimate1.getOwner().equals(getCurrentPlayer()))
                        .forEach(RealEstimate::reset);
            }
        }
        getCurrentPlayer().moveTo(newPos);
        EstimateType type = realEstimates.get(newPos).getType();
        if (type!=null&&type.equals(Prison)){
            getCurrentPlayer().skipForTimes(2);
        }
        cursorForCurrentPlayer = (cursorForCurrentPlayer + 1) % players.size();

        if (getCurrentPlayer().needToSkip()) {
            getCurrentPlayer().skip();
            cursorForCurrentPlayer = (cursorForCurrentPlayer + 1) % players.size();
        }
    }

    public Player getCurrentPlayer() {
        return players.get(cursorForCurrentPlayer);
    }

    public void buy() {
        RealEstimate realEstimate = realEstimates.get(getCurrentPlayer().getPosition());
        if (realEstimate.getType().equals(VaccantLand) && realEstimate.getOwner() == null) {
            getCurrentPlayer().buy(realEstimate);
        }
        if (getCurrentPlayer().isBroken()) {
            getCurrentPlayer().markToDie();
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

    public boolean isGameOver() {
        return players.stream().filter(Player::isAlive).count() < 2;
    }

    public Player getWinner() {
        if (isGameOver()) {
            return players.stream().filter(Player::isAlive).findFirst().get();
        }
        return null;
    }

    public void choose(int giftOrEquipmentIndex) {
        if (getCurrentPlayer().notChoosable())
            return ;
        int position = getCurrentPlayer().getPosition();
        EstimateType type = realEstimates.get(position).getType();
        if(type!=null&&type.equals(GiftRoom)){
            switch (giftOrEquipmentIndex){
                case 1:
                    getCurrentPlayer().own(2000);
                    break;
                case 2:
                    getCurrentPlayer().gainPoints(200);
                    break;
                case 3:
                    getCurrentPlayer().gainGodProtection();
                    break;
            }
        }
        getCurrentPlayer().notChoosable(true);
    }
}
