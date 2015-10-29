package model;

import java.util.List;

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
        getCurrentPlayer().moveTo(newPos);
    }

    public Player getCurrentPlayer() {
        return players.get(0);
    }
}
